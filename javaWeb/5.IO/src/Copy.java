import java.io.*;

public class Copy {
    public static void main(String[] args) throws IOException {
        String src = "D:\\IDEA_project\\javaWeb\\5.IO\\测试文件.txt";
        String dest = "D:\\IDEA_project\\javaWeb\\5.IO\\拷贝文件.txt";


        FileInputStream fis = new FileInputStream(src);
        FileOutputStream fos = new FileOutputStream(dest);

        int b = 0;
        while((b=fis.read())!=-1){
            fos.write(b);
        }
    }
}
