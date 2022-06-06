package com.rsd.bean;

import lombok.Data;

import java.util.Date;

@Data
public class SysFiles {
    private Integer id;
    private String name;
    private String path;
    private long fileSize;
    private Date createTime;
}
