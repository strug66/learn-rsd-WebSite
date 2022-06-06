package com.rsd.myEnmu;

public enum Sex {

    tt, man("男"), woman("女"), xx("003", "不限");

    private String val;
    private String code;

    Sex() {

    }

    Sex(String val) {
        this.val = val;
    }

    Sex(String code, String val) {
        this.code = code;
        this.val = val;
    }

    public String getVal() {
        return val;
    }

    public String getCode() {
        return code;
    }
}




