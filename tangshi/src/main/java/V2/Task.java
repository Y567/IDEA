package V2;

import com.gargoylesoftware.htmlunit.html.DomText;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import domain.Poetry;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.NlpAnalysis;

import javax.sql.DataSource;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Task implements Runnable {
    private HtmlPage page;
    private DataSource dataSource;
    private CountDownLatch countDownLatch;  //用来通知线程结束的

    public Task(HtmlPage page,DataSource dataSource, CountDownLatch countDownLatch){
        //设置解析路径
        this.page = page;
        this.dataSource = dataSource;
        this.countDownLatch = countDownLatch;
    }
    @Override
    public void run() {
        HtmlElement body = page.getBody();

        //xPath表示具体诗词内容的路径，可以通过它直接获取诗词的朝代，作者等信息
        String xPath;

        //定义一个Poetry对象
        Poetry poetry = new Poetry();

        //1.0 获取诗词的名字
        {
            xPath = "//div[@class='cont']/h1/text()";
            //获得了属性class的值为cont的div里的第一个h1标签的内容，即朝代名
            Object o = body.getByXPath(xPath).get(0);

            //将其强转为DomText对象
            DomText domText = (DomText)o;

            //设置值给Poetry
            poetry.setTitle(domText.asText());
        }

        //2.0 获取诗词的朝代
        {
            xPath = "//div[@class='cont']/p[@class='source']/a[1]/text()";
            Object o = body.getByXPath(xPath).get(0);
            DomText domText = (DomText)o;
            poetry.setDynasty(domText.asText());
        }

        //3.0 获取诗词的作者
        {
            xPath = "//div[@class='cont']/p[@class='source']/a[2]/text()";
            Object o = body.getByXPath(xPath).get(0);
            DomText domText = (DomText)o;
            poetry.setAuthor(domText.asText());
        }

        //4.0 获取诗词的正文
        {
            xPath = "//div[@class='cont']/div[@class='contson']";
            Object o = body.getByXPath(xPath).get(0);
            HtmlElement element = (HtmlElement)o;
            //用HtmlElement的getTextContent方法可以获取所有内容，而不是一行，更方便，所以用该方法
            poetry.setContent(element.getTextContent());
        }

        //5.0 计算SHA-256
        {
            try{
                //获取要加密的字符串表示
                String data = poetry.getTitle() + poetry.getContent();
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
                //存入诗词对象中
                poetry.setSha256(sha256.toString());
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        //6.0 求出分词
        {
            String content = poetry.getContent();
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
            poetry.setWords(words.toString());
        }

        //7.0 持久化到数据库
        {

            Connection con = null;
            PreparedStatement stmt = null;
            try{
                con = dataSource.getConnection();
                String sql = "insert into tangshi values(null,?,?,?,?,?,?)";
                stmt = con.prepareStatement(sql);
                stmt.setString(1,poetry.getSha256());
                stmt.setString(2,poetry.getDynasty());
                stmt.setString(3,poetry.getTitle());
                stmt.setString(4,poetry.getAuthor());
                stmt.setString(5,poetry.getContent());
                stmt.setString(6,poetry.getWords());
                //执行操作
                stmt.executeUpdate();

            }catch (SQLException e){
                e.printStackTrace();
            }finally {
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

                //表示该任务结束
                countDownLatch.countDown();
            }
        }
    }
}
