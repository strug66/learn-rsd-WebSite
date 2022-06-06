package com.rsd.bean;

import com.rsd.anno.FirstAnno;
import com.rsd.anno.NoParameters;

import java.util.Date;

@FirstAnno("aaa")
public class SysRole {

    private Integer id;
    private String name;
    private Date createTime;
    private String[] sysFunctionIds;


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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @NoParameters(supportSQL = false)
    public String[] getSysFunctionIds() {
        return sysFunctionIds;
    }

    public void setSysFunctionIds(String[] sysFunctionIds) {
        this.sysFunctionIds = sysFunctionIds;
    }
}
