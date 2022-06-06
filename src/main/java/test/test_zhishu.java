package test;

public class test_zhishu {
    public static void main(String[] args) {
//
//        //1-100以内的质数
//        for (int i = 2; i <= 100; i++) {
//
//
//            int count = 0;
//            for (int j = 2; j < i; j++) {
//
//                if (i % j == 0) { //被其它数整除
//                    count++;
//                    break;
//                }
//            }
//
//            //内层循环之后
//            if (count == 0) {
//                System.out.println(i);
//            }
//
//        }


        for (int i = 1; i <= 10; i++) {
            if (i == 4) {
                break;
            }
            System.out.println(i);
        }

        for (int i = 1; i <= 10; i++) {
            if (i == 4) {
                continue;
            }
            System.out.println(i);
        }


    }
}
