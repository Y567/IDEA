package NowCoder;

public class 满二叉树找最近公共祖先 {

    public static int getLCA(int a, int b) {
        // write code here
        int father = -1;
        //存储小的那个数
        int small =  a < b ? a : b;
        //存储大的那个数
        int big = a > b ? a : b;

        if(small == big){
            return small;//说明他们就是自己的祖先
        }

        //否则就去自底向上的查询，让大的那个节点先走
        while(small > 0 && big > 0){
            //没有追溯到根节点就不停,这里我们先求big的父节点,big为偶数直接除以2，为奇数-1后再除
            if((big % 2) != 0){
                //说明是奇数
                father = (big-1) / 2;
            }else{
                //说明是偶数
                father = big / 2;
            }
            big = father;
            //得到father后判断father是否和small相等，相等说明祖先是small
            if(father == small){
                return small;
            }
            //否则说明不是，继续比较(这时候需要改变small和big的值了，因为big经过变化后可能变的更小了)
            if(small > big){
                //需要交换了
                small = small ^ big;
                big = small ^ big;
                small = small ^ big;
            }
        }
        //跳出循环只能说明一件事就是没找到，只能是根结点了
        return 1;
    }


    public String replaceSpace(String iniString, int length) {
        // write code here
        if(length==0){
            //原路返回即可
            return iniString;
        }
        ////将字符串转换字符数组，遍历遇到空格就替换
        //先定义一个StringBuilder
        StringBuilder sb = new StringBuilder();  //用来拼接字符串
        char[] chars = iniString.toCharArray();
        for(char c: chars){
            if(c == ' '){
                sb.append("%20");
            }else{
                sb.append(String.valueOf(c));
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("最近公共祖先是"+getLCA(6,7));
    }
}
