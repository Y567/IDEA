package 链表;



class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
 }
public class 反转链表 {

    /**
     * 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
     *
     *  
     *
     * 示例:
     *
     * 输入: 1->2->3->4->5->NULL
     * 输出: 5->4->3->2->1->NULL
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/fan-zhuan-lian-biao-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public ListNode reverseList(ListNode head) {
        ListNode new_head = null;
        ListNode next;
        while(head != null){
            //保存head的下一个节点
            next = head.next;
            head.next = new_head;
            new_head = head;
            //向后移动
            head = next;
        }
        return new_head;
    }
}
