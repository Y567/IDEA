
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day14 {

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

    /**
     * 返回从顶部到底部的能看见的顺序
     */

    /*List<Integer> lists = new ArrayList<Integer>();
    public List<Integer> rightSideView(TreeNode root){
        if(root == null){
            return null;
        }
        if(root.right != null){
            //先遍历右孩子，右孩子先被看到
            lists.add(root.right.val);
            rightSideView(root.right);
        }else if(root.left != null){
            //如果右孩子不存在那么能看到的就是左孩子
            lists.add(root.left.val);
            rightSideView(root.left);
        }else{
            //一条路走到了尽头

        }

    }*/

    //用双指针法来判断相交路线的函数
    public boolean intersect(int[] A,int[] B){
        int i = 0,j = 0;
        while(i < A.length && j < B.length){
            if(A[i] == B[j]){
                return true;
            }
            if(A[i] < B[j]){
                i++;
            }
            else{
                j++;
            }
        }
        return false;
    }
    //公交路线
    public int numBusesToDestination(int[][] routes, int S, int T) {
        if(S == T){
            return 0;
        }
        int N = routes.length;
        //graph用来存各自相交的公交车
        List<List<Integer>> graph = new ArrayList<List<Integer>>();
        //先对每个公交车路线排序后面好判断是否相交，顺便将graph的框架搭起，它的长度和routes的长度一样，只是每个元素里存的都是
        //与其下标（指公交车）路线相交的所以公交车（此题用了下标表示）
        for(int i = 0;i < N;i++){
            Arrays.sort(routes[i]);
            graph.add(new ArrayList<Integer>());
        }

        //存储期起点站的公交车
        HashSet<Integer> sources = new HashSet<>();
        //存储终点站的公交车
        HashSet<Integer> target = new HashSet<>();
        //存储路线(Point.x指那条路线（公交车）,Point.y指的深度已经坐了几辆车了)
        ArrayDeque<Point> queue = new ArrayDeque<>();

        //遍历路线，添加相交的路线
        for(int i = 0; i < N;i++){
            for(int j = i + 1;j < N;j++){
                //如果两个公交车的路线有公共站，则互相记录在graph中
                if(intersect(routes[i],routes[j])){
                    //互相记录相交路线
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }

        //接下来查找含有起点站和终点站的路线
        for(int i = 0; i < N;i++){
            if (Arrays.binarySearch(routes[i], S)>=0) {
                sources.add(i);
                //添加这条路线的深度（指坐了几次车，默认值为0，指终点站在这条路线上）
                queue.add(new Point(i,0));
            }
            //大于等于0说明找到了，小于零说明没有找到，这是该函数的返回值
            if(Arrays.binarySearch(routes[i],T)>=0){
                target.add(i);
            }
        }

        //开始查询路线
        while(!queue.isEmpty()){
            Point point = queue.poll();
            int x = point.x,y = point.y;
            if(target.contains(x)){
                //说明目标站就在x路线上
                return y+1;
            }
            //找出与该路线相交的所有路线然后判断该起点站里是否有目标值，没有的路线说明需要多转一次车（深度加一）
            for(Integer i:graph.get(x)) {
                //如果这条相交的路线不再起点站中需要多转一次
                if (!sources.contains(i)) {
                    sources.add(i);
                    queue.add(new Point(i, y + 1));
                }
            }
        }
        return -1;
    }

    //通配符匹配  动态规划解决
    public static boolean isMatch(String s, String p) {
        int m = s.length(),n = p.length();
        boolean[][] f = new boolean[m+1][n+1];
        f[0][0] = true;//前0个是一定匹配的
        for (int i = 1; i <= n; i++) {
            //p开头为*的
            f[0][i] = f[0][i-1] && p.charAt(i-1) == '*';
        }
        for(int i = 1;i <= m;i++){
            for(int j = 1;j <= n;j++){
                if(p.charAt(j-1)==s.charAt(i-1) || p.charAt(j-1) == '?'){
                    //前i和前j匹配需要前i-1,和j-1匹配
                    f[i][j] = f[i-1][j-1];
                }
                if(p.charAt(j-1) == '*'){
                    //*匹配任意字符串或者空字符
                    f[i][j] = f[i-1][j] || f[i][j-1];
                }
            }
        }
        return f[m][n];  //前m和前n匹配吗
    }
    public static void main(String[] args) {

//        float[][] f = new float[6][];
//
//        int[] array = {1,2,3,4,5,6,7,8,10};
//        System.out.println(Arrays.binarySearch(array,8));

        System.out.println(isMatch("aaaabaaaabbbbaabbbaabbaababbabbaaaababaaabbbbbbaabbbabababbaaabaabaaaaaabbaabbbbaababbababaabbbaababbbba","*****b*aba***babaa*bbaba***a*aaba*b*aa**a*b**ba***a*a*"));


    }
}
