public class TestV2 {
    private static int i = 9;
    public static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
    public static ListNode Merge(ListNode list1,ListNode list2) {
        if(list2==null){
            return list1;
        }
        if(list1==null){
            return list2;
        }
        ListNode p1 = list1;
        ListNode p2 = list2;
        ListNode result = null;
        ListNode p3 = null;
        while(p1!=null&&p2!=null){
            while(p1!=null&&p2!=null&&p1.val<p2.val){
                if(result==null){
                    result = new ListNode(p1.val);
                    p3=result;
                }else{
                    p3.next = new ListNode(p1.val);
                    p3 = p3.next;
                }
                p1 = p1.next;
            }

            while(p2!=null&&p1!=null&&p2.val<=p1.val){
                if(result==null){
                    result = new ListNode(p2.val);
                    p3=result;
                }else{
                    p3.next = new ListNode(p2.val);
                    p3 = p3.next;
                }
                p2 = p2.next;
            }
        }
        if(p1!=null){
            while(p1!=null){
                p3.next = new ListNode(p1.val);
                p3 = p3.next;
                p1 = p1.next;
            }
        }
        if(p2!=null){
            while(p2!=null){
                p3.next = new ListNode(p2.val);
                p3 = p3.next;
                p2 = p2.next;
            }
        }
        return result;
    }


        public static void main(String[] args) {
            ListNode a = new ListNode(1);
            a.next = new ListNode(7);
            a.next.next = new ListNode(9);
//            ListNode b =null;
            ListNode b = new ListNode(2);
            b.next = new ListNode(3);
            b.next.next = new ListNode(4);

            ListNode res = Merge(a,b);
            while(res!=null){
                System.out.print(res.val+" ");
                res =res.next;
            }
        }

}
