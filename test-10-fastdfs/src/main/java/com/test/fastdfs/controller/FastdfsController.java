package com.test.fastdfs.controller;

import com.test.fastdfs.utils.FastdfsUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("fastdfs")
public class FastdfsController {

    @Autowired
    private FastdfsUtil fastdfsUtil;

    /**
     * 文件上传
     */
    @PostMapping(value = "/uploadFile",headers="content-type=multipart/form-data")
    public String uploadFile (@RequestParam("file") MultipartFile file){
        String result ;
        try{
            String path = fastdfsUtil.upload(file);
            if (!StringUtils.isEmpty(path)){
                result = path ;
            } else {
                result = "上传失败" ;
            }
        } catch (Exception e){
            e.printStackTrace() ;
            result = "服务异常" ;
        }
        return result;
    }

}
