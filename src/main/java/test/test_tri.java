package test;

public class test_tri {

    public static void main(String[] args) {
        System.out.println(jinzita(4));
        System.out.println("-----------------------");
        System.out.println(sanjiaxing(4));

    }

    /**
     * 金字塔
     * 行数      1 2 3 4 5    【n  5行】
     * 星号的个数 1 3 5 7 9（2*i-1） 【i  i=1 i>=9  i+=2】
     * 空格的个数 4 3 2 1 0（n-i）  【m  m=4;m--】
     *
     * @param n 行数
     */
    public static String jinzita(int n) {
        String str = "";
        for (int i = 1, m = n - 1; i <= 2 * n - 1; i += 2, m--) {
            for (int j = 1; j <= m; j++) {
                str += " ";
            }
            for (int k = 1; k <= i; k++) {
                str += "*";
            }
            str += "\n";
        }
        return str;
    }

    public static String sanjiaxing(int n) {
        String str = "";

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                str += "*";
                System.out.print("*"); //打印星星，不换行
            }
            str += "\n";
            System.out.println();
        }

        return str;
    }
}
