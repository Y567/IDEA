public class HelloServlet implements HttpServlet{
    @Override
    public void doGet(Request req, Response resp) {
        resp.setHeader("Content-type","text/plain;charset=UTF-8");
        resp.setHeader("X-Teacher","陈沛鑫");
        resp.setHeader("X-Room","201");

        //加入响应体
        resp.println("你好 "+req.parameters.getOrDefault("target","火柴人"));

        //查看Cookie保存的用户信息
        String cookieValue = req.headers.get("Cookie");
        if(cookieValue.contains("username")){
            resp.println("当前用户是"+cookieValue.split("=")[2]);
        }else{
            resp.println("当前无用户登录");
        }
    }
}
