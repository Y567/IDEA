package NowCoder;

import java.util.*;
public class 记票统计{
    private static Map<String,Integer> map = new LinkedHashMap<String,Integer>();
    private static int count = 0;   //记录投票的候选人不存在的数量
    //设置候选人姓名
    public static int AddCandidate(String name){
        if(name == null || "".equals(name)){
            return 0;
        }
        if(map.containsKey(name)){
            //包含直接返回
            return 0;
        }
        map.put(name,0);
        return 1;
    }

    public static void Vote(String name){
        //map的键值对的对加1
        if(!map.containsKey(name)){
            //包含直接返回
            count++;
            return;  //不进行投票
        }
        map.put(name,map.getOrDefault(name,0)+1);
    }

    public static int GetVoteResult(String name){
        if(name == null || name.length()==0){
            return -1;  //表示无效的票数
        }
        //获得候选人票数
        int votes = map.getOrDefault(name,-1);
//        //移除掉该候选人
//        map.remove(name);
        return votes;
    }
    public static void Clear(){
        map = new LinkedHashMap<String,Integer>();  //移除所有,新赋一个引用
        count = 0;
    }
    public static void println(){
        Set<String> keys = map.keySet();
        for(String key:keys){
            System.out.println(key+" : "+GetVoteResult(key));
        }
        System.out.println("Invalid"+" : "+count);
    }

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        while(in.hasNextInt()){
            int first = in.nextInt();
            in.nextLine();
            String candidate = in.nextLine();
            String[] candidates = candidate.split(" ");
            for(int i = 0;i < candidates.length;i++){
                AddCandidate(candidates[i]);
            }
            //吃个空
            int second = in.nextInt();
            in.nextLine();
            String vote = in.nextLine();
            String[] votes = vote.split(" ");
            for(int i = 0;i < votes.length;i++){
                Vote(votes[i]);
            }
            println();
            Clear();
        }

    }
}