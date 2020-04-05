package 编译原理;

public class 无符号数 {

    private final static int DIGIT = 1;
    private final static int POINT = 2;
    private final static int OTHER = 3;
    private final static int POWER = 4;
    private final static int PLUS = 5;
    private final static int MINUS = 6;
    private final static int EndState = -1;

    private static int w = 0;	//整数部分
    private static int p = 0;	//小数部分
    private static int e = 1;	//幂次是+ 还是 -
    private static int d = 0;	//暂存数据
    private static int n = 0;	//表示小数点后边有几个位
    private static int currentState = 0;	//当前状态，默认是0，后面执行的时候还要初始化
    private static double result = 0.0;		//result用来存最后结果

    //解析字符，返回相应的状态，前面静态变量定义好了
    private static int analysisCharacter(char c) {
        switch (c) {
            case '.':
                return POINT;
            case 'E':
            case 'e':
                return POWER;
            case '+':
                return PLUS;
            case '-':
                return MINUS;
            default:
                if (Character.isDigit(c)) {
                    d = c - '0';
                    return DIGIT;
                }
                break;
        }
        return OTHER;
    }

    //判断是不是无符号证书，返回相应的状态
    private static boolean isUnsignedNumber(String s) {
        //在这里全部都要重新初始化
        currentState = 0;	//当前状态初始化0
        n = 0;			//小数点后位数初始化0
        w = 0;			//整数部分初始化0
        p = 0;			//小数部分初始化0
        e = 1;			//默认是＋
        for(int i=0;i<s.length();i++) {
            int ch = analysisCharacter(s.charAt(i));
            currentToNext(ch);
            if(currentState == EndState) {
                return false;
            }
        }
        switch(currentState) {
            case 1:
            case 2:
            case 6:
                result = w * Math.pow(10,e*p-n);
                return true;
        }
        return false;
    }

    //从当前状态到下一个状态的执行，参数是一个字符串，currentState是静态成员变量不需要传参
    private static void currentToNext(int ch) {
        switch (currentState) {
            case 0:
                switch (ch) {
                    case DIGIT:
                        w = w * 10 + d;
                        currentState = 1;
                        break;
                    case POINT:
                        currentState = 3;
                        break;
                    default:
                        currentState = EndState;
                        break;
                }
                break;
            case 1:
                switch (ch) {
                    case DIGIT:
                        w = w * 10 + d;
                        currentState = 1;
                        break;
                    case POINT:
                        currentState = 2;
                        break;
                    case POWER:
                        currentState = 4;
                        break;
                    default:
                        currentState = EndState;
                        break;
                }
                break;
            case 2:
                switch (ch) {
                    case DIGIT:
                        w = w * 10 + d;
                        ++n;
                        currentState = 2;
                        break;
                    case POWER:
                        currentState = 4;
                        break;
                    default:
                        currentState = EndState;
                        break;
                }
                break;
            case 3:
                if (ch == DIGIT) {
                    w = w * 10 + d;
                    ++n;
                    currentState = 2;
                } else {
                    currentState = EndState;
                }
                break;
            case 4:
                switch (ch) {
                    case DIGIT:
                        p = p * 10 + d;
                        currentState = 6;
                        break;
                    case PLUS:
                        currentState = 5;
                        break;
                    case MINUS:
                        e = -1;
                        currentState = 5;
                        break;
                    default:
                        currentState = EndState;
                        break;
                }
                break;
            case 5:
                if (ch == DIGIT) {
                    p = p * 10 + d;
                    currentState = 6;
                } else {
                    currentState = EndState;
                }
                break;
            case 6:
                if (ch == DIGIT) {
                    p = p * 10 + d;
                    currentState = 6;
                } else {
                    currentState = EndState;
                }
                break;
        }
    }
    public static void main(String[] args) {
        //定义一个字符串数组，进行测试程序
        String[] strings = {
                "34342","65.321","22.3",".444","14E64","2e34","12E+34",
                "2e+34","12e-34","12.3E4","12.3e4","2.3E+4","12.3e+4",
                ".21E-4",".3e-5","3.E45","21.e4","3.E+45","218.e+4",
                "3.E-4","21.e-4"
        };
        for(String s:strings) {
            if(isUnsignedNumber(s)) {
                System.out.println(s+"\t\t是无符号数，转换格式后为"+" "+result);
            }else {
                System.out.println(s+"\t\t不是无符号数");
            }
        }
    }

}