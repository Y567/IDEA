import java.io.*;
import java.util.Comparator;

public class Scanner {
    public static void scannerDirectory(TreeNode node) {
        File[] files = node.file.listFiles();
        if (files == null) {
            return;
        }

        for (File file : files) {
            TreeNode child = new TreeNode();
            child.file = file;
            if (file.isDirectory()) {
                scannerDirectory(child);
            } else {
                child.totalLength = file.length();
            }
            node.totalLength += child.totalLength;
            node.children.add(child);
        }
    }

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        TreeNode root = new TreeNode();
        root.file = new File("E:\\videos");
        scannerDirectory(root);
        PrintStream out = new PrintStream(
                new FileOutputStream("扫描报告.txt"),
                true, "UTF-8");
        Comparator<TreeNode> sortByLengthDesc = new Comparator<TreeNode>() {
            @Override
            public int compare(TreeNode o1, TreeNode o2) {
                if (o1.totalLength > o2.totalLength) {
                    return -1;
                } else if (o1.totalLength == o2.totalLength) {
                    return 0;
                } else {
                    return 1;
                }
            }
        };

        report(root, 0, sortByLengthDesc, out);

        out.close();
    }

    private static void report(TreeNode root, int level, Comparator<TreeNode> sortBy, PrintStream out) {
        for (int i = 0; i < level * 4; i++) {
            out.print(" ");
        }
        if (level == 0) {
            out.print(root.file.getAbsolutePath());
        } else {
            out.print(root.file.getName());
        }
        if (root.file.isDirectory()) {
            out.print("\\");
        }

        String unit = "字节";
        double length = root.totalLength;
        if (length > 1024 * 1024 * 1024) {
            unit = "G字节";
            length = length / 1024 / 1024 / 1024;
        } else if (length > 1024 * 1024) {
            unit = "M字节";
            length = length / 1024 / 1024;
        } else if (length > 1024) {
            unit = "K字节";
            length = length / 1024;
        }
        out.printf("    %.2f%s%n", length, unit);

        root.children.sort(sortBy);
        for (TreeNode node : root.children) {
            report(node, level + 1, sortBy, out);
        }
    }
}
