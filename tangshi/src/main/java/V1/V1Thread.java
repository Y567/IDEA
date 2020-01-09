package V1;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class V1Thread {
    private static MysqlConnectionPoolDataSource dataSource = new MysqlConnectionPoolDataSource();
    private static CountDownLatch countDownLatch = new CountDownLatch(30);
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

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException{
        //无界面的浏览器（HTTP 客户端）
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

        //每个详情页启动一个线程来处理
        {
            //接下来就是根据hrefs里的路径进入一个个详情页进行解析诗词了,遍历所有路径去访问
            for(String href:hrefs){
                //每一个详情页启动一个任务，这里将path传入进去Task中
                String path = baseUrl + href;
                HtmlPage page = webClient.getPage(path);
                Thread thread = new Thread(new Task(page, dataSource));
                thread.start();
            }
        }

        //利用CountDownLatch等待诗词处理完毕
    }
}
