
public class Day20 {

    public static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode partition(ListNode pHead, int x) {
        // write code here
        ListNode left = null;
        ListNode right = null;
        ListNode p1 = null;
        ListNode p2 = null;
        while(pHead!=null){
            if(pHead.val<x){
                if(left == null){
                    left = new ListNode(pHead.val);
                    p1 = left; //左链表的头节点
                }else{
                    left.next = new ListNode(pHead.val);
                    left = left.next;
                }
            }else{
                if(right == null){
                    right = new ListNode(pHead.val);
                    p2 = right;    //右链表的头节点
                }else{
                    right.next = new ListNode(pHead.val);
                    right = right.next;
                }
            }
            pHead = pHead.next;
        }

        if(p1==null){
            return p2;
        }
        if(p2==null){
            return p1;
        }
        //走到这里说明都不为空，需要连接
        //找到左链表的最后一个节点将其与右链表连接
        ListNode cur = p1;
        while(cur.next!=null){
            cur = cur.next;
        }
        cur.next = p2;
        return p1;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(5);
//        ListNode listN = listNode;
        listNode.next = new ListNode(3);
        listNode.next.next = new ListNode(1);
        listNode.next.next.next = new ListNode(9);
        listNode.next.next.next.next = new ListNode(2);
/*        while(listN!=null){
            System.out.println(listN.val);
            listN = listN.next;
        }*/
        ListNode list = partition(listNode,3);
        while(list!=null){
            System.out.println(list.val);
            list = list.next;
        }
    }
}
