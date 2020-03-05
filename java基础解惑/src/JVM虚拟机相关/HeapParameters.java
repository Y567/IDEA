package JVM虚拟机相关;

/**
 * 手动设置堆的大小
 */
public class HeapParameters {
    public static void main(String[] args) {
        long maxMemory = Runtime.getRuntime().maxMemory();
        long totalMemory = Runtime.getRuntime().totalMemory();
        //输出JVM被分配的内存大小
        System.out.println("分配的内存"+maxMemory+"\n"+(double)maxMemory/1024*1024+"MB");
        //输出JVM初始化的内存
        System.out.println("初始化的内存"+totalMemory+"\n"+(double)totalMemory/1024*1024+"MB");

        System.out.println("-----------------------------------------");

        //进行修改参数
        //-Xms:1024m -Xmx:1000m -XX:PrintGCDetails
        System.out.println("分配的内存"+maxMemory+"\n"+(double)maxMemory/1024*1024+"MB");
        //输出JVM初始化的内存
        System.out.println("初始化的内存"+totalMemory+"\n"+(double)totalMemory/1024*1024+"MB");

    }
}
