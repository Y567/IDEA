import java.util.*;

public class Java {
    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("c1",93,"n1"));
        students.add(new Student("c2",85,"n1"));
        students.add(new Student("c3",67,"n1"));
        students.add(new Student("c1",73,"n2"));
        students.add(new Student("c2",65,"n2"));
        students.add(new Student("c3",97,"n2"));
        students.add(new Student("c1",83,"n3"));
        students.add(new Student("c2",87,"n3"));
        students.add(new Student("c3",89,"n3"));
        HashMap<String, Student> treeMap = new HashMap<>();
        for (Student student : students) {
            if (treeMap.getOrDefault(student.getClassName(), new Student()).getScore() < student.getScore()) {
                //大的放进去
                treeMap.put(student.getClassName(), student);
            }
        }
        Set<String> keys = treeMap.keySet();
        for (String s:keys){
            Student student = treeMap.get(s);
            System.out.println("class:"+student.getClassName()+"的最高成绩的学生叫"+student.getName()+","+student.getScore());
        }

    }

}
