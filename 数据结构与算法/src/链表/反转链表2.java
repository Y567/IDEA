package 链表;

public class 反转链表2 {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode result = head;//记录头节点
        //需要记住四个关键的节点：m节点所处位置，m节点的前驱节点，n节点所处位置，n节点的后继节点
        ListNode mPre = null;
        //找到关于m节点的节点
        int mCount = m - 1;//找到m需要移动的次数
        while (head != null && mCount > 0) {
            //mPre始终是head的前一个节点
            mPre = head;
            mCount--;
            head = head.next;
        }
        //不用找到n的位置了，找到需要反转多少个节点就行了
        int serverCount = n - m + 1;
        //反转,记住最后的那个节点和后继
        ListNode newHead = null;//用于局部反转
        ListNode next = null;
        ListNode tail = head;  //逆置后的尾部
        while (head != null && serverCount > 0) {
            next = head.next;
            head.next = newHead;
            newHead = head;
            head = next;
            serverCount--;
        }
        tail.next = head;
        //现在指向的位置就是最后的节点
        if (mPre != null) {
            mPre.next = newHead;
        } else {
            result = newHead;
        }
        return result;
    }
}
