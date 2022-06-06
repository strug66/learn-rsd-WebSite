package com.rsd.service;

import com.rsd.bean.SysFiles;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

public interface ISysFileService {
    final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 通过id查询对象
     * @param id 主键
     * @return 对象
     */
    SysFiles getById(Integer id);

    /**
     * 查询所有对象
     * @return 对象集合
     */
    List<SysFiles> queryAllList();

    /**
     * 新增方法
     * @param sysFile
     */
    void insert(SysFiles sysFile);

    /**
     * 修改方法
     * @param sysFile
     */
    void update(SysFiles sysFile);

    /**
     * 删除方法
     * @param id
     */
    void delete(Integer id);

    /**
     * 文件下载的方法
     * @param id 主键
     * @param realPath 真实路径
     * @param response
     * @throws IOException
     */
    void downLoad(Integer id, String realPath, HttpServletResponse response) throws IOException;

    /**
     * 删除真实文件的方法
     * @param id 主键
     * @param path 路径
     */
    void deleteFile(Integer id, String path);

}
