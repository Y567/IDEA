
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class Response {
    //状态码
    String status = "200 OK";
    //响应头
    Map<String, String> headers = new HashMap<>();
    //响应体
    StringBuilder bodyBuilder = new StringBuilder();

    //构造同时添加一个响应头
    Response() {
        headers.put("Content-Type", "text/html; charset=utf-8");
    }

    //设置状态码
    public void setStatus(String status) {
        this.status = status;
    }

    //设置响应头
    public void setHeader(String key, String value) {
        headers.put(key, value);
    }

    //添加响应体内容
    public void print(String s) {
        bodyBuilder.append(s);
    }
    public void println(String s) {
        bodyBuilder.append(s);
        bodyBuilder.append("\r\n");
    }

    //响应并刷新
    void writeAndFlush(OutputStream os) throws IOException {
        // 1. 组装响应
        String response = buildResponse();
        os.write(response.getBytes("UTF-8"));
        os.flush();
    }

    //组装响应
    private String buildResponse() throws IOException {
        StringBuilder responseBuilder = new StringBuilder();
        // 响应行
        responseBuilder.append("HTTP/1.0 ");
        responseBuilder.append(status);
        responseBuilder.append("\r\n");
        // 响应头
        int contentLength = bodyBuilder.toString().getBytes("UTF-8").length;
        //这个响应头是每次都加的
        setHeader("Content-Length", String.valueOf(contentLength));
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            responseBuilder.append(entry.getKey());
            responseBuilder.append(": ");
            responseBuilder.append(entry.getValue());
            responseBuilder.append("\r\n");
        }
        // 空行
        responseBuilder.append("\r\n");
        // body
        responseBuilder.append(bodyBuilder);

        return responseBuilder.toString();
    }


    @Override
    public String toString() {
        return "Response{" +
                "status='" + status + '\'' +
                ", headers=" + headers +
                ", bodyBuilder=" + bodyBuilder +
                '}';
    }
}
