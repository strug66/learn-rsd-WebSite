package com.rsd.bean;

import com.rsd.anno.FirstAnno;
import com.rsd.anno.NoParameters;

import java.io.Serializable;


public class SysFunction implements Serializable {
//    private static final long serialVersionUID = -2174975906268228223L;

    private Integer id;
    private String name;
    private String ischecked;
    private String url;
    private String isUsed;
//    private Integer s;  //序列化后，不能再任何修改

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NoParameters(supportSQL = false)
    public String getIschecked() {
        return ischecked;
    }

    public void setIschecked(String ischecked) {
        this.ischecked = ischecked;
    }

    @NoParameters(supportSQL = false)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @NoParameters(supportSQL = false)
    public String getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(String isUsed) {
        this.isUsed = isUsed;
    }
}
