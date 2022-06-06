package test;

import com.rsd.bean.SysFunction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class test4_listRemove {
    public static void main(String[] args) {
        List<SysFunction> sysFunctionList = new ArrayList<>();

        SysFunction sysFunction1 = new SysFunction();
        sysFunction1.setName("功能1");
        sysFunction1.setIsUsed("Y");

        SysFunction sysFunction2 = new SysFunction();
        sysFunction2.setName("功能2");
        sysFunction2.setIsUsed("N");

        SysFunction sysFunction3 = new SysFunction();
        sysFunction3.setName("功能3");
        sysFunction3.setIsUsed("N");

        sysFunctionList.add(sysFunction1);
        sysFunctionList.add(sysFunction2);
        sysFunctionList.add(sysFunction3);

        List<SysFunction> removeList = new ArrayList<>();
        for (int i = 0; i < sysFunctionList.size(); i++) {
            SysFunction sysFunction = sysFunctionList.get(i);
            System.out.println(i + "\t" + sysFunction.getName() + "\t" + sysFunction.getIsUsed());

            if (sysFunction.getIsUsed().equals("N")) {
//                sysFunctionList.remove(i);
                removeList.add(sysFunctionList.get(i));

            }
        }

        //功能集合中删除部分元素 removeAll
        sysFunctionList.removeAll(removeList);


        System.out.println(sysFunctionList);

//        //功能是否禁用
//        List<SysFunction> removeList = new ArrayList<>();
//        for (int i = 0; i < sysFunctionList.size(); i++) {
//            SysFunction sysFunction = sysFunctionList.get(i);
//
//            if (sysFunction.getIsUsed().equals("N")) {
//                removeList.add(sysFunction);
//                System.out.println("删除的集合列表添加----------------" + sysFunction.getName());
//            }
//        }
//        sysFunctionList.removeAll(removeList);

    }
}
