import java.io.*;
import java.net.Socket;

public class OutStream {
    public static OutputStream getOut() throws IOException {
        //本地输出
        OutputStream out;
        out = new FileOutputStream("本地输出文件.txt");
//        out = new Socket("www.baidu.com",80).getOutputStream();
//        out = new ByteArrayOutputStream();

        return out;

    }


    public static void printf(OutputStream out,String message) throws IOException {
        byte[] buffer = message.getBytes();
        out.write(buffer);

        Writer writer = new OutputStreamWriter(out,"UTF-8");
        writer.append(message);
        writer.flush();
    }
}
