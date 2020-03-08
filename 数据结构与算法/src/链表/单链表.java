package 链表;

class Node{
    public int id;
    public String name;
    public Node next;    //指向下一个节点

    public Node() {
    }

    public Node(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

class SingleLinkedList{
    //单链表对象
    private Node head;   //头节点

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public SingleLinkedList() {
    }

    public SingleLinkedList(Node head) {
        this.head = head;
    }

    //单链表添加节点到最后的方法的方法
    public void add(Node node){
        //需要一个中间变量来遍历
        Node cur = head;
        //走到最后
        while(true){
            if(cur.next == null){
                //找到最后一个节点了
                break;
            }else{
                cur =cur.next;
            }
        }
        //插入即可
        cur.next = node;
    }

    //插入数据的时候同时排好序
    public void addByOrder(Node node){
        Node cur = head;
        while(true){
            if(cur.next != null){
                if(cur.next.id > node.id){
                    //说明找到要插入的地方了
                    break;
                }else if(cur.next.id == node.id){
                    //存在了该节点，那么就提示用户不能插入
                    System.out.println("已存在该节点不能进行删除");
                    return;
                }else{
                    cur = cur.next;
                }
            }else{
                break;
            }
        }
        //找到了要插入的位置，直接插入
        node.next = cur.next;
        cur.next = node;  //无缝衔接
    }

    //修改单链表
    public void update(Node node){
        Node cur = head;
        while(true){
            //找到要被修改的节点
            if(cur.next != null){
                if(cur.next.id == node.id){
                    cur.next.name = node.name;
                    break;
                }
                cur = cur.next;
            }else{
                //找到了末尾都没找到
                System.out.println("没有该节点");
                return;
            }
        }
    }

    //删除节点
    public void delete(int id){
        Node cur = head;
        //需要找到要删除的节点的前置节点
        while(true){
            if(cur.next != null){
                if(cur.next.id == id){
                    break;//找到了直接跳出
                }
                cur = cur.next;
            }else{
                //找到末尾也没有找到
                System.out.println("没有找到要删除的节点");
                return;
            }
        }
        //走到这里说明找到了可以更新,跳过要删除的节点直接指向后一个，让GC回收
        cur.next = cur.next.next;
    }

    //反转单链表
    public void reverse(Node head){
        Node newHead = new Node();
        Node cur = head.next;   //循环遍历用到
        Node next = null;       //记录cur的下一个节点，保证改变链表引用后链表不会断掉
        while(cur != null){
            next = cur.next;
            cur.next = newHead.next;  //让当前节点指向newHead的下一个
            newHead.next = cur;
            cur = next;               //遍历下一个节点
        }
        head.next = newHead.next;
    }

    //输出链表的函数
    public void print(){
        //头节点不需要输出所以输出下一个
        Node cur = head.next;
        while(true){
            if(cur != null){
                System.out.println(cur);
                cur = cur.next;
            }else{
                break;
            }
        }
    }
}
public class 单链表 {
    public static void main(String[] args) {
        //测试一哈单链表
        Node head = new Node();
        SingleLinkedList list = new SingleLinkedList(head);
        Node node1 = new Node(1,"狗子");
        Node node2 = new Node(2,"二哈");
        Node node3 = new Node(3,"豆豆");
        Node node4 = new Node(4,"吾皇");
        list.addByOrder(node1);
        list.addByOrder(node4);
        list.addByOrder(node3);
        list.addByOrder(node2);
        list.addByOrder(node2);
        System.out.println("链表");
        list.print();
//        list.delete(4);
//        System.out.println("删除指定节点后");
//        Node node = new Node(2, "哈哈");
//        list.update(node);
//        System.out.println("更改后的链表");
//        list.print();

        System.out.println("反转链表后");
        list.reverse(list.getHead());
        list.print();


    }
}
