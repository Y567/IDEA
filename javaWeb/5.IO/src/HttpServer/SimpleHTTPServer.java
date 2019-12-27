package HttpServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleHTTPServer {
    private static class Task implements Runnable {
        private final Socket socket;

        Task(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                InputStream is = socket.getInputStream();
                OutputStream os = socket.getOutputStream();
                // 解析请求
                Request request = Request.parse(is);
                System.out.println(request);
                // 处理业务
            /*
            String body = "<h1>一切正常</h1>";
            // 拼接响应
            Response response = Response.build(os);
            // 发送响应
            response.println(body);
            response.flush();
             */
                if(request.path.equalsIgnoreCase("/")){
                    String body = "alert('sorry呀')";
                    byte[] bodyBuffer = body.getBytes("UTF-8");
                    StringBuffer sb = new StringBuffer();
                    sb.append("HTTP/1.0 200 OK\r\n");
                    sb.append("Content-type:application/javascript;charset=utf8\r\n");
                    sb.append("Content-Length:"+bodyBuffer.length);
                    sb.append("\r\n");
                    sb.append("\r\n");
                    os.write(sb.toString().getBytes("UTF-8"));
                    os.write(bodyBuffer);
                    os.flush();
                }else if(request.path.equalsIgnoreCase("/run")){
                    String body = "<script src='/'></script>";
                    byte[] bodyBuffer = body.getBytes("UTF-8");
                    StringBuilder sb = new StringBuilder();
                    sb.append("HTTP/1.0 200 OK\r\n");
                    sb.append("Content-Type: text/html; charset=UTF-8\r\n");
                    sb.append("Content-Length: ");
                    sb.append(bodyBuffer.length);
                    sb.append("\r\n");
                    sb.append("\r\n");

                    os.write(sb.toString().getBytes("UTF-8"));
                    os.write(bodyBuffer);
                    os.flush();
                } else{
                    StringBuffer sb = new StringBuffer();
                    sb.append("HTTP/1.0 404 Not Found\r\n");
                    sb.append("\r\n");
                    os.write(sb.toString().getBytes("UTF-8"));
                    os.flush();
                }
                socket.close();
            } catch (Exception e) {}
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(80);
        ExecutorService pool = Executors.newFixedThreadPool(10);
        while (true) {
            Socket socket = serverSocket.accept();
            pool.execute(new Task(socket));
        }
    }
}
