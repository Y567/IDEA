public class Day22V2 {
    static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
    public static ListNode plusAB(ListNode a, ListNode b) {
        StringBuffer a1 = new StringBuffer("");
        StringBuffer b1 = new StringBuffer("");
        while(a!=null){
            a1.insert(0,a.val);
            a = a.next;
        }
        while(b!=null){
            b1.insert(0,b.val);
            b = b.next;
        }
        int r = Integer.parseInt(a1.toString())+Integer.parseInt(b1.toString());
        StringBuffer res = new StringBuffer();
        res.append(r);
        StringBuffer reverse = res.reverse();
/*        System.out.println(reverse);*/
        ListNode result = null;
        ListNode p = null;
        for (int i = 0; i < reverse.length(); i++) {
            if(result==null){
                result = new ListNode(Integer.parseInt(reverse.substring(i,i+1)));
                p = result;
            }else{
                p.next = new ListNode(Integer.parseInt(reverse.substring(i,i+1)));
                p = p.next;
            }
        }
        return result;
        }

    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        a.next = new ListNode(2);
        a.next.next = new ListNode(3);
        ListNode b = new ListNode(1);
        b.next = new ListNode(2);
        b.next.next = new ListNode(1);
        ListNode res = plusAB(a,b);
        while(res!=null){
            System.out.println(res.val);
            res = res.next;
        }

    }
}
