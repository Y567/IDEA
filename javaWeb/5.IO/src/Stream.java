import java.io.*;

public class Stream {
    public static void main(String[] args) throws IOException {
        File file = new File("D:\\IDEA_project\\javaWeb\\5.IO","测试文件.txt");
        file.createNewFile();
        //输出流对象
        OutputStream fos = new FileOutputStream(file);
        fos.write(134523);
        //getBytes（）可以跟参数表示编码解码的编码集
        byte[] buf = "我是阿狗".getBytes();
        fos.write(buf);

        FileInputStream fis = new FileInputStream(file);
        int i;
        while((i=fis.read())!=-1){
            System.out.println(i);
        }



    }
}
