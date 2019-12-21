import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class SimpleHttp {
    public static void main(String[] args) throws IOException {
        String req = "GET / HTTP/1.0\r\nHost: www.baidu.com\r\n\r\n";
        Socket socket = new Socket("www.baidu.com",80);
        socket.getOutputStream().write(req.getBytes("UTF-8"));
        byte[] bytes = new byte[4096];
        int len = socket.getInputStream().read(bytes);   //这里从流中读的，没有指定下标表示将bytes读满，指定了则表示读取指定内容
/*        String resp = new String(bytes,0,len,"UTF-8");
        System.out.println(resp);*/

        int index = -1;
        //找到响应头终止的下标
        for (int i = 0; i < len - 3; i++) {
            if(bytes[i]=='\r'&& bytes[i+1]=='\n'&&bytes[i+2]=='\r'&&bytes[i+3]=='\n'){
                index = i;
                break;
            }
        }

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes, 0, len + 4);
        Scanner scanner = new Scanner(byteArrayInputStream);
        String statusLine = scanner.nextLine();
        System.out.println("状态行："+statusLine);
        String[] s = statusLine.split(" ");
        System.out.println("协议版本："+s[0]);
        System.out.println("状态码："+s[1]);
        System.out.println("状态信息："+s[2]);
        String line;
        int contentLength = -1;
        while(!(line = scanner.nextLine()).isEmpty()){
            String[] split = line.split(":");
            String key = split[0].trim();
            String value = split[1].trim();
            if(key.equals("Content-Length")){
                contentLength = Integer.valueOf(value);
            }
            System.out.println(key+"="+value);
        }

        int readed = len - index - 4;
        int end = contentLength - readed;
        byte[] bytes1 = new byte[contentLength];
        System.arraycopy(bytes,index+4,bytes1,0,readed);  //将已经读了的先拷贝
        //再从输入流中读剩下的
        socket.getInputStream().read(bytes1,readed,end); //end表示还应该读的
        FileOutputStream fileOutputStream = new FileOutputStream("百度.html");
        fileOutputStream.write(bytes1);
    }
}
