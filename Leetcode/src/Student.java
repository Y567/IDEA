public class Student{
    private String name;
    private int score;
    private String className;

    public Student(String className,int score,String name){
        this.name = name;
        this.score = score;
        this.className = className;
    }

    public String getName() {
        return name;
    }

    public Student() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
