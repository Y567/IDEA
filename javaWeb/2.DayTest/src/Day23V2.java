public class Day23V2 {
    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }
    public boolean isBalance(TreeNode root) {
        // write code here
        if(root==null){
            return true;
        }
        int left = getTreeHeight(root.left);
        int right = getTreeHeight(root.right);
        return Math.abs(left - right) <= 1;
    }

    public static int getTreeHeight(TreeNode root){
        if(root==null){
            return 1;
        }
        //这里为什么加1?，因为root为null时返回的是0，我们假设只有三个节点的完全二叉树
        //那是不是最后的结果就是根节点的左孩子0+0+1，根节点的右孩子0+0+1,最后1和1取大的再加1，不就是2嘛
        //如果返回值改为1，就可以不加1了
        return Math.max(getTreeHeight(root.left),getTreeHeight(root.right))+1;
    }
}
