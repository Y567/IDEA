public class LoginServlet implements HttpServlet{

    @Override
    public void doGet(Request req, Response resp) {
        String username = req.parameters.get("username");
        if(username != null){
            resp.setStatus("200 OK");
            resp.setHeader("Set-Cookie","username="+username+";expires=Tue, 07-Apr-2020 08:46:16 GMT; Max-Age=8640000");
            resp.println("欢迎你"+username);
        }else{
            resp.setStatus("401 Unauthorized");
            resp.println("请先登录！");
        }
    }
}
