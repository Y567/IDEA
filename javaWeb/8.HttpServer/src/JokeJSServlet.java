public class JokeJSServlet implements HttpServlet {
    @Override
    public void doGet(Request req, Response resp) {
        resp.setStatus("200 OK");
        resp.setHeader("Content-Type", "text/html; charset=utf-8");
        resp.println("<h1 style='background-color:red;'>小博哥牛逼</h1><marquee>好勇啊</marquee>");
    }
}
