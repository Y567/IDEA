import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class IOTest {
    public static InputStream getInput() throws IOException {
        InputStream in;
        //in = new FileInputStream("本地文件.txt");

/*        byte[] bytes = "内存中读取\r\n".getBytes("UTF-8");
        in = new ByteArrayInputStream(bytes);*/

        //System.in


        //从网络中读
        Socket socket = new Socket("www.baidu.com",80);
        OutputStream out = socket.getOutputStream();
        Writer writer = new OutputStreamWriter(out,"UTF-8");
        PrintWriter printWriter = new PrintWriter(writer,false);
        printWriter.printf("GET / HTTP/1.0\r\n\r\n");
        printWriter.flush();
        in = socket.getInputStream();

        return in;
    }


    public static String getMessage(InputStream in) throws IOException {
     /*   //当buffer小于数据长度时，或者数据需要换行输出时，比较麻烦。
        byte[] bytes = new byte[1024];
        int len = in.read(bytes);
        String message = new String(bytes,0,len,"UTF-8");
        return message;*/

/*     //reader读，字符流
        Reader reader = new InputStreamReader(in,"UTF-8");
        char[] buffer = new char[1024];
        int len = reader.read(buffer);
        String message = new String(buffer,0,len);
        return message;*/
/*
        //buffer少于数据时，可以用sb解决
        Reader reader = new InputStreamReader(in,"UTF-8");
        char[] buffer = new char[4];
        StringBuffer sb = new StringBuffer();
        int len = 0;
        while((len=reader.read(buffer))!=-1){
            sb.append(buffer,0,len);
        }
        String message = sb.toString();
        return message;*/

      /* //用BufferReader读(里面可以直接读一行，比较方便)，需要穿入一个Reader
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in,"UTF-8"));
        //它的readLine会自动去掉\r\n;
        String line;
        StringBuffer sb = new StringBuffer();
        while((line=bufferedReader.readLine())!=null){
            sb.append(line+"\r\n");
        }
        String message = sb.toString();
        return message;*/

      Scanner input = new Scanner(in,"UTF-8"); // 也是去掉了\r\n
        return input.nextLine();

    }

    public static void main(String[] args) throws IOException {
        InputStream in = getInput();
        String s = getMessage(in);
        System.out.println(s);
    }
}
