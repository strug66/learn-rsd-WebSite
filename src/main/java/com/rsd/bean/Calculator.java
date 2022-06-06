package com.rsd.bean;

public class Calculator {
    private String number1;
    private String number2;
    private String operator;
    private String result;

    /*
    都设置为string类型。
    展示历史记录
    数1 数2：和表单输入一致
    结果：float如果为整数，去掉小数点
     */

    public String getNumber1() {
        return number1;
    }

    public void setNumber1(String number1) {
        this.number1 = number1;
    }

    public String getNumber2() {
        return number2;
    }

    public void setNumber2(String number2) {
        this.number2 = number2;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
