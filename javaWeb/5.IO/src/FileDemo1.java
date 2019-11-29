import java.io.File;
import java.io.IOException;

public class FileDemo1 {
    public static void main(String[] args) throws IOException {
        //目录是否存在
        File file = new File("D:\\IDEA_project");
        File f = new File("D:\\IDEA_project","test.txt");

        //创建一个隐藏文件
        f.createNewFile();
        System.out.println(f.isHidden());
        System.out.println(f.length());  //返回文件的大小
/*        try {
            //不写这句话，就只是创建对象
            f.createNewFile();
//            //java退出时删除文件
//            f.deleteOnExit();


        } catch (IOException e) {
            e.printStackTrace();
        }*/
        f.mkdir();  //创建一个文件夹
        System.out.println(file.exists());          //文件或目录是否存在
        System.out.println(file.getUsableSpace());  //可用空间
        System.out.println(file.getParent());       //上一级路径
        System.out.println(file.getAbsolutePath()); //绝对路径
        System.out.println(file.getTotalSpace());  //统计总空间
        System.out.println(file.isDirectory());    //判断是否为目录
        System.out.println(file.canRead());        //可读？
        System.out.println(file.canWrite());       //可写？
        System.out.println(file.canExecute());     //可执行
    }
}
