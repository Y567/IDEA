
import java.util.Scanner;

public class Day19V2 {
    static class Person{
        private String name;
        private int score;

        public Person(String name, int score) {
            this.name = name;
            this.score = score;
        }

        @Override
        public String toString() {
            return this.name+" "+this.score;
        }
    }


    //按低到高(利用冒泡保证稳定性)
    public static void sort1(Person[] people){
        //次数
        for (int i = 0; i < people.length-1; i++){
            //需要到达的底部
            for (int j = 0; j < people.length-i-1; j++) {
                if(people[j].score>people[j+1].score){
                    Person temp = people[j];
                    people[j] = people[j+1];
                    people[j+1] = temp;
                }
            }
        }
    }


    //按高到低(利用冒泡保证稳定性)
    public static void sort2(Person[] people){
        //次数
        for (int i = 0; i < people.length-1; i++){
            //需要到达的底部
            for (int j = 0; j < people.length-i-1; j++) {
                if(people[j].score<people[j+1].score){
                    Person temp = people[j];
                    people[j] = people[j+1];
                    people[j+1] = temp;
                }
            }
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            int N = in.nextInt();  //人数
            int ways = in.nextInt(); //排序方式
            Person[] person = new Person[N];
            in.nextLine();  //吃空格
            for (int i = 0; i < N; i++) {
                String s = in.nextLine();
                String[] strings = s.split(" ");
                person[i] = new Person(strings[0],Integer.parseInt(strings[1]));
            }

            if(ways == 0){
                sort2(person);
                for(Person p:person){
                    System.out.println(p);
                }
            }else{
                sort1(person);
                for(Person p:person){
                    System.out.println(p);
                }
            }
        }

    }
}
