import java.util.*;

public class Day16{
    //方法
    public static int fun(int N,String scores,int theScore){
        int count = 0;
        String[] s = scores.split(" ");
        for(int i = 0;i<N;i++){
            int score = Integer.parseInt(s[i]);
            if(theScore==score){
                count++;
            }
        }
        return count;
    }
    public static class Test{
        int N;
        String scores;
        int theScore;
        int count=0;

        public Test(int N,String scores,int theScore){
            this.N=N;
            this.scores = scores;
            this.theScore = theScore;
        }

        public void setCount(int count){
            this.count = count;
        }
    }
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        List<Test> list = new ArrayList<Test>();
        int N=in.nextInt();
        in.nextLine();  //吃空格
        while(N!=0){
            String scores=in.nextLine();/*
            System.out.println(scores);*/
            int theScore = in.nextInt();
            list.add(new Test(N,scores,theScore));
            N = in.nextInt();
            in.nextLine();
        }
        for(int i = 0;i < list.size();i++){
            Test test = list.get(i);
            int count = fun(test.N,test.scores,test.theScore);
            test.setCount(count);
        }

        for(int i = 0;i<list.size();i++){
            System.out.println(list.get(i).count);
        }
    }
}