public class ParseRequest {
    public static void main(String[] args) {
        StringBuffer request = new StringBuffer();
        request.append("GET / HTTP/1.1\r\n");
        request.append("Host:127.0.0.1\r\n");
        request.append("Accept:text/html\r\n");
        request.append("X-Teacher:peixinchen\r\n");
        String s = request.toString();

    }
}
