import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomText;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import domain.Poetry;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.NlpAnalysis;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SingleThreadCatch {
    private static MysqlConnectionPoolDataSource dataSource = new MysqlConnectionPoolDataSource();
    private static Connection con;
    private static PreparedStatement stmt;

    static {
        try{
            //1.注册数据库驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2.设置配置信息，连接的数据库，用户密码等信息
            dataSource.setServerName("127.0.0.1");
            dataSource.setPort(3306);
            dataSource.setUser("root");
            dataSource.setPassword("yy5201314");
            dataSource.setDatabaseName("tangshi");
            dataSource.setUseSSL(false);
            dataSource.setCharacterEncoding("UTF-8");
            //3.通过MysqlConnectionPoolDataSource获取连接对象
            con = dataSource.getConnection();
            //4.获取执行对象
            //4.1定义sql语句
            String sql = "insert into tangshi values(null,?,?,?,?,?,?)";
            stmt = con.prepareStatement(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //存入数据库
    public static void saveToDatabaseV1(Poetry poetry) throws ClassNotFoundException {
        //执行对象
        try {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //释放资源
    public static void releaseSource(){
        if(stmt != null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(con != null){
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, ClassNotFoundException, SQLException {
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        //关闭了浏览器的js执行引擎和css执行引擎
        webClient.getOptions().setJavaScriptEnabled(false);
        webClient.getOptions().setCssEnabled(false);

        //baseUrl就是不变的路径
        String baseUrl = "https://so.gushiwen.org";
        //pathUrl路径就是会变的路径，随着它的变化进入不同的页面，比如列表页或详情页
        //默认值为列表页
        String pathUrl = "/gushi/tangshi.aspx";

        //定义一个存放详情页的所有链接的路径链表
        List<String> hrefs = new ArrayList<String>();

        //定义一个存在诗词的链表
        List<Poetry> poetries = new ArrayList<Poetry>();

        //列表页的解析,目的就是获取所有详情页的链接路径
        {
            String url = baseUrl + pathUrl;

            //获取页面的body标签部分
            HtmlPage page = webClient.getPage(url);

            //获取body标签的内容，以便后面爬取诗词的详情页面
            HtmlElement body = page.getBody();

            //根据body获取所有class属性为typecont的div（这里面存放着详情页的路径）
            List<HtmlElement> elements = body.getElementsByAttribute(
                    "div",
                    "class",
                    "typecont");

            for(HtmlElement element:elements){
                //获取所有的href属性的值并存放到hrefs路径中
                for(HtmlElement e:element.getElementsByTagName("a")){
                    String href = e.getAttribute("href");
                    hrefs.add(href);
                }
            }
        }


        //详情页的解析
        {
            //接下来就是根据hrefs里的路径进入一个个详情页进行解析诗词了,遍历所有路径去访问
            for(String href:hrefs){
                String path = baseUrl + href;
                HtmlPage page = webClient.getPage(path);
                HtmlElement body = page.getBody();

                //xPath表示具体诗词内容的路径，可以通过它直接获取诗词的朝代，作者等信息
                String xPath;

                //定义一个Poetry对象
                Poetry poetry = new Poetry();

                //获取诗词的名字
                {
                    xPath = "//div[@class='cont']/h1/text()";
                    //获得了属性class的值为cont的div里的第一个h1标签的内容，即朝代名
                    Object o = body.getByXPath(xPath).get(0);

                    //将其强转为DomText对象
                    DomText domText = (DomText)o;

                    //设置值给Poetry
                    poetry.setTitle(domText.asText());
                }

                //获取诗词的朝代
                {
                    xPath = "//div[@class='cont']/p[@class='source']/a[1]/text()";
                    Object o = body.getByXPath(xPath).get(0);
                    DomText domText = (DomText)o;
                    poetry.setDynasty(domText.asText());
                }

                //获取诗词的作者
                {
                    xPath = "//div[@class='cont']/p[@class='source']/a[2]/text()";
                    Object o = body.getByXPath(xPath).get(0);
                    DomText domText = (DomText)o;
                    poetry.setAuthor(domText.asText());
                }

                //获取诗词的正文
                {
                    xPath = "//div[@class='cont']/div[@class='contson']";
                    Object o = body.getByXPath(xPath).get(0);
                    HtmlElement element = (HtmlElement)o;
                    //用HtmlElement的getTextContent方法可以获取所有内容，而不是一行，更方便，所以用该方法
                    poetry.setContent(element.getTextContent());
                }

                poetries.add(poetry);
            }
        }

        //已经存入诗词的链表中去
        /*System.out.println(poetries);*/
        //计算sha256（将加密和分词分析放在一起，少遍历一遍）
        {
            //计算sha256（通过诗词的标题加正文）遍历诗的列表
            for(Poetry p:poetries){
                //获取要加密的字符串表示
                String data = p.getTitle() + p.getContent();
                byte[] bytes = data.getBytes("UTF-8");

                //获取加密对象
                MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

                messageDigest.update(bytes);

                //获取加密后的消息
                byte[] result = messageDigest.digest();

                StringBuffer sha256 = new StringBuffer();
                for(byte b:result){
                    sha256.append(String.format("%02x",b));
                }

//                System.out.println(sha256.toString());
                //存入诗词对象中
                p.setSha256(sha256.toString());
            }
        }

        //计算分词
        {
            //这里只计算了正文的词语
            for(Poetry p:poetries){
                String content = p.getContent();
                //获取分好的词语对象
                List<Term> terms = NlpAnalysis.parse(content).getTerms();
                StringBuffer words = new StringBuffer();
                for(Term t:terms){
                    String realName = t.getRealName();
                    if(realName==null || realName.length()==0){
                        continue;
                    }else if(realName.length() < 2){
                        continue;
                    }else{
//                        System.out.println(realName);
                        //选择出了长度大于2的词语
                        words.append(realName).append(" ");
                    }
                }
//                System.out.println(words);
                p.setWords(words.toString());
            }
        }


        //至此已经将唐诗对象封装完毕
//        System.out.println(poetries);
        for(Poetry p:poetries){
            stmt.setString(1,p.getSha256());
            stmt.setString(2,p.getDynasty());
            stmt.setString(3,p.getTitle());
            stmt.setString(4,p.getAuthor());
            stmt.setString(5,p.getContent());
            stmt.setString(6,p.getWords());
            saveToDatabaseV1(p);
        }

        //最后在释放资源
        releaseSource();
    }
}
