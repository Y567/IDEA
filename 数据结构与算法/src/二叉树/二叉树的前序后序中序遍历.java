package 二叉树;

class Node{
    private int id;
    private String name;
    private Node left;   //表示左孩子
    private Node right;  //表示右孩子

    public Node() {
    }

    public Node(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    //前序遍历
    public void preOrder(){
        System.out.println(this);  //输出当前节点
        if(this.left != null){
            this.left.preOrder();
        }
        if(this.right != null){
            this.right.preOrder();
        }
    }

    //中序遍历
    public void infixOrder(){
        if(this.left != null){
            this.left.infixOrder();
        }
        System.out.println(this);  //输出当前节点
        if(this.right != null){
            this.right.infixOrder();
        }
    }


    //后序遍历
    public void postOrder(){
        if(this.left != null){
            this.left.postOrder();
        }
        if(this.right != null){
            this.right.postOrder();
        }
        System.out.println(this);  //输出当前节点
    }

    //前序遍历查找
    public Node preOrderSearch(int id){
        System.out.println("前序查找");
        //前序的话直接判断当前是不是相等
        if(this.id == id){
            return this;
        }
        //没有找到，那么继续看左孩子节点
        //我们需要定义一个节点来存储找到的节点
        Node node = null;
        if(this.left != null){
            node  = this.left.preOrderSearch(id);
        }
        if(node != null){
            //不为空说明找到了
            return node;
        }
        //如果还没有找到，我们就需要去查询右孩子了
        if(this.right != null){
            node = this.right.preOrderSearch(id);
        }
        //这里即使没有找到返回的也是null，因为初始值是null
        return node;
    }

    //中序查找
    public Node infixOrderSearch(int id){
        //没有找到，那么继续看左孩子节点
        //我们需要定义一个节点来存储找到的节点
        Node node = null;
        if(this.left != null){
            node  = this.left.infixOrderSearch(id);
        }
        if(node != null){
            //不为空说明找到了
            return node;
        }
        //中序的话直接判断当前是不是相等
        if(this.id == id){
            return this;
        }
        //如果还没有找到，我们就需要去查询右孩子了
        if(this.right != null){
            node = this.right.infixOrderSearch(id);
        }
        //这里即使没有找到返回的也是null，因为初始值是null
        return node;
    }


    //后序查找
    public Node postOrderSearch(int id){
        //没有找到，那么继续看左孩子节点
        //我们需要定义一个节点来存储找到的节点
        Node node = null;
        if(this.left != null){
            node  = this.left.postOrderSearch(id);
        }
        if(node != null){
            //不为空说明找到了
            return node;
        }
        //如果还没有找到，我们就需要去查询右孩子了
        if(this.right != null){
            node = this.right.postOrderSearch(id);
        }
        if(node != null){
            return node;
        }

        //后序的话最后判断当前是不是相等
        if(this.id == id){
            return this;
        }
        return null;  //所有的情况都查找过了，都没有查找到所以
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

class BinaryTree{
    private Node root;  //表示根节点

    public BinaryTree(Node root) {
        this.root = root;
    }

    //前序遍历
    public void preOrder(){
        if(this.root != null){
            //可以遍历
            this.root.preOrder();
        }
    }

    //中序遍历
    public void infixOrder(){
        if(this.root != null){
            //可以遍历
            this.root.infixOrder();
        }
    }


    //后序遍历
    public void postOrder(){
        if(this.root != null){
            //可以遍历
            this.root.postOrder();
        }
    }


    //前序遍历查找
    public Node preOrderSearch(int id){
        if(this.root != null){
            return root.preOrderSearch(id);
        }else{
            return null;
        }
    }

    //中序遍历查找
    public Node infixOrderSearch(int id){
        if(this.root != null){
            return root.infixOrderSearch(id);
        }else{
            return null;
        }
    }

    //后序遍历查找
    public Node postOrderSearch(int id){
        if(this.root != null){
            return root.postOrderSearch(id);
        }else{
            return null;
        }
    }
}
public class 二叉树的前序后序中序遍历 {

    public static void main(String[] args) {
        Node root = new Node(1, "雨涵");
        root.setLeft(new Node(2,"高痒痒"));
        root.setRight(new Node(3,"阿狗"));
        root.getLeft().setRight(new Node(4,"川哥"));
        root.getLeft().setLeft(new Node(5,"阿崇"));
        root.getLeft().getLeft().setLeft(new Node(6,"阿坤"));
        //手动创建一棵树
        BinaryTree binaryTree = new BinaryTree(root);
        /*binaryTree.preOrder();
        System.out.println("---------------------------");
        binaryTree.infixOrder();
        System.out.println("---------------------------");
        binaryTree.postOrder();*/

        //测试查找算法正确不
        System.out.println(binaryTree.preOrderSearch(5));
    }
}
