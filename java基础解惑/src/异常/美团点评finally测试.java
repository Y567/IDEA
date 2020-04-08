package 异常;

public class 美团点评finally测试 {

    public static int returnValue(){
        try{
            System.out.println("要返回值了");
            return 1;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return 2;
        }
    }
    public static void main(String[] args) {
        System.out.println(returnValue());

    }
}
