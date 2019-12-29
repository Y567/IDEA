
import java.net.Socket;

public class Task implements Runnable {
    private Socket socket;

    public Task(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // 读取并解析请求 -> 处理业务 -> 组装并发送响应

            // 1. 读取并解析请求
            Request request = Request.parse(socket.getInputStream());
            Response response = new Response();

            // 2. 处理业务
            if (request.path.equals("/joke.js")) {
                HttpServlet servlet = new JokeJSServlet();
                //servlet的doGet方法是用来设置响应对象的内容的
                servlet.doGet(request, response);
            }else if(request.path.equals("/login")){
                HttpServlet servlet = new LoginServlet();
                servlet.doGet(request,response);
            }else if(request.path.equals("/hello")){
                HelloServlet servlet = new HelloServlet();
                servlet.doGet(request,response);
            }

            // 3. 组装并发送响应
            System.out.println("要发送的响应为\r\n"+response);
            response.writeAndFlush(socket.getOutputStream());
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
