import java.util.Stack;

public class Day17_2 {
     public class TreeNode {
     int val = 0;
     TreeNode left = null;
     TreeNode right = null;

     public TreeNode(int val) {
     this.val = val;
     }
     }

    public class Solution {
         //递归
        public void Mirror(TreeNode root) {
            if(root == null){
                return;
            }
            //这里和非递归的条件很类似，都是说了一件事，只要有一个不为空，我就要交换（空和非空也有左右对称嘛）
            if(root.left==null&&root.right==null){
                return;
            }

            //交换
            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;

            //递归左子树
            if(root.left!=null){
                Mirror(root.left);
            }
            //递归右子树
            if(root.right!=null){
                Mirror(root.right);
            }
        }

        //非递归
        public void Mirror2(TreeNode root) {
            if(root == null){
                return;
            }
            Stack<TreeNode> stack = new Stack<>();
            stack.push(root);

            while(!stack.isEmpty()){
                TreeNode node = stack.pop();
                //这个条件映衬了上面所说的话
                if(node.left!=null||node.right!=null){
                    TreeNode temp = node.left;
                    node.left = node.right;
                    node.right = temp;
                }

                //其实递归就是栈的入栈出栈，所以代码很相似
                if(node.left!=null){
                    stack.push(node.left);
                }
                if(node.right!=null){
                    stack.push(node.right);
                }
            }
        }
    }
}
