package com.rsd.service.impl;

import com.rsd.DAO.SysFileDAO;
import com.rsd.bean.SysFiles;
import com.rsd.service.ISysFileService;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.List;

public class SysFileServiceImpl implements ISysFileService {
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private SysFileDAO sysFileDAO = new SysFileDAO();

    public SysFiles getById(Integer id) {
        return sysFileDAO.getById(id);
    }

    public List<SysFiles> queryAllList() {
        List<SysFiles> sysFiles = sysFileDAO.queryAllList();
        return sysFiles;
    }

    public void insert(SysFiles sysFile) {
        sysFileDAO.insert(sysFile);
    }

    public void update(SysFiles sysFile) {
        sysFileDAO.update(sysFile);
    }

    public void delete(Integer id) {
        sysFileDAO.delete(id);
    }

    public void downLoad(Integer id, String realPath, HttpServletResponse response) throws IOException {

        SysFiles sysFile = getById(Integer.valueOf(id));
        File file = new File(realPath + sysFile.getPath());  //文件路径
        if (file.exists()) {
            String fileName = URLEncoder.encode(sysFile.getName(), "UTF-8");

            response.reset();
            response.setContentType("application/x-msdownload");
            response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
            /*创建输入流*/
            InputStream inStream = new FileInputStream(file);
            byte[] buf = new byte[4096];
            /*创建输出流*/
            ServletOutputStream servletOS = response.getOutputStream();
            int readLength;
            while (((readLength = inStream.read(buf)) != -1)) {
                servletOS.write(buf, 0, readLength);
            }
            inStream.close();
            servletOS.close();
        }
    }

    public void deleteFile(Integer id, String path) {
        SysFiles sysFileOld = getById(id);
        File file = new File(path + sysFileOld.getPath());
        if (file.exists()) {
            file.delete();
        }
    }


}
