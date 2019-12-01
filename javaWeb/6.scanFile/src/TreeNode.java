import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    File file;
    long totalLength;   // 汇总了该文件夹下所有文件的总大小
    List<TreeNode> children = new ArrayList<>();
}