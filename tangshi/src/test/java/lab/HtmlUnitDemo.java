package lab;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomText;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class HtmlUnitDemo {
    @Test
    public void test1() throws IOException {
        //无界面的浏览器（HTTP 客户端）
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        //关闭了浏览器的js执行引擎和css执行引擎
        webClient.getOptions().setJavaScriptEnabled(false);
        webClient.getOptions().setCssEnabled(false);

        //请求页面
        HtmlPage page = webClient.getPage("https://so.gushiwen.org/gushi/tangshi.aspx");
        System.out.println(page);

        //保存到指定路径
        File file = new File("唐诗三百首\\列表页.html");
        page.save(file);
        //获取html的body标签的内容
        HtmlElement body = page.getBody();
        //获取body中的有用的标签
        List<HtmlElement> elements = body.getElementsByAttribute("div", "class", "typecont");
     /*   for (HtmlElement e:elements){
            System.out.println(e);
        }*/

        //取出第一个五言绝句
        HtmlElement divElement = elements.get(0);
        List<HtmlElement> aElements = divElement.getElementsByAttribute("a", "target", "_blank");
        for (HtmlElement e:aElements) {
            System.out.println(e);
        }

        System.out.println(aElements.get(0).getAttribute("href"));
    }


    @Test
    public void test2HtmlUnitDetailPages() throws IOException {
        //无界面的浏览器（HTTP 客户端）
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        //关闭了浏览器的js执行引擎和css执行引擎
        webClient.getOptions().setJavaScriptEnabled(false);
        webClient.getOptions().setCssEnabled(false);

        //请求页面
        HtmlPage page = webClient.getPage("https://so.gushiwen.org/shiwenv_45c396367f59.aspx");

        //获取html的body标签的内容
        HtmlElement body = page.getBody();

        //爬取标签路径
        String xPath;
        {
            //获得标题、朝代作者等信息，根据xPath
            xPath = "//div[@class='cont']/h1/text()";
            Object o = body.getByXPath(xPath).get(0);
            DomText domText = (DomText)o;
            //标题
            String title = domText.asText();
            System.out.println("标题："+title);
        }

        //接下来获取朝代和作何
        {
            //朝代
            xPath = "//div[@class='cont']/p[@class='source']/a[1]/text()";
            Object o = body.getByXPath(xPath).get(0);
            DomText domText = (DomText)o;
            //朝代
            String dynasty = domText.asText();
            System.out.println("朝代:"+dynasty);

        }

        {
            //作者
            xPath = "//div[@class='cont']/p[@class='source']/a[2]/text()";
            Object o = body.getByXPath(xPath).get(0);
            DomText domText = (DomText)o;
            //作者
            String user = domText.asText();
            System.out.println("作者："+user);

        }

        {
            //古诗词内容
            xPath = "//div[@class='cont']/div[@class='contson']";
            Object o = body.getByXPath(xPath).get(0);
            HtmlElement htmlElement = (HtmlElement)o;
            //正文
            String content = htmlElement.getTextContent();
            System.out.println("正文："+content);

        }



    }
}
