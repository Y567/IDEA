import java.io.File;

public class ListDir {
    //递归遍历文件
    public static void listDir(File dir){
        File[] files = dir.listFiles();
        for (File f:files) {
            if(f.isDirectory()){
                //如果是目录的话,递归遍历文件
                listDir(f);
            }else{
                System.out.println(f.getAbsolutePath());
            }
        }
    }


    /**
     * 下面这个方法是求指定根目录下最大的文件
     */
    //定义一个文件引用，存放最大文件
    static File biggest = new File("D:\\Program Files","init.txt");
    //选定一个根路径，找到该路径下最大的文件
    public static void findBiggestFile(File file){
        File[] files = file.listFiles();
        for (File f:files) {
            if(f.isDirectory()){
                //如果是目录的话,递归遍历文件
                findBiggestFile(f);
            }else{
                //说明找到文件了
                if(f.length()>biggest.length()){
                    biggest = f;
                }
            }
        }
    }

    public static void main(String[] args) {
        File file = new File("D:\\Program Files");
        findBiggestFile(file);
        System.out.println(biggest.getAbsolutePath());
    }
}
