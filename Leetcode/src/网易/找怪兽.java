package 网易;

public class 找怪兽 {
    /**
     * 功能： 找出指定英雄周围最近的怪兽的距离
     * @param i   英雄所在的行
     * @param j   英雄所在列
     * @param arr 棋盘
     * @return    返回的最小的距离
     */
    public static int getMin(int i,int j,int[][] arr){
        //思路：从里层向外层去遍历，八个方向,n表示层数：第一层八个方向去遍历，找到最近的怪兽，第二层，第三层，下标全部越界，找不到怪兽退出
        int n = 1;  //层数
        int min = 1;
        while(true){
            if(i - n >= 0){  //防止越界
                if(arr[i-n][j] == 0){
                    min = min < n ? min: n;
                }
            }
            if(j - n >= 0){  //防止越界
                if(arr[i][j-n] == 0){
                    return n;  //最近的距离怪兽为n
                }
            }
            if(i + n <= n){  //防止越界
                if(arr[i-n][j] == 0){
                    min = min < n ? min: n;
                }
            }
            if(i - n >= 0){  //防止越界
                if(arr[i-n][j] == 0){
                    min = min < n ? min: n;
                }
            }
            if(i - n >= 0){  //防止越界
                if(arr[i-n][j] == 0){
                    min = min < n ? min: n;
                }
            }
            if(i - n >= 0){  //防止越界
                if(arr[i-n][j] == 0){
                    min = min < n ? min: n;
                }
            }
            if(i - n >= 0){  //防止越界
                if(arr[i-n][j] == 0){
                    min = min < n ? min: n;
                }
            }
            if(i - n >= 0){  //防止越界
                if(arr[i-n][j] == 0){
                    min = min < n ? min: n;
                }
            }

            n++;
        }
    }
    public static void main(String[] args) {

    }
}
