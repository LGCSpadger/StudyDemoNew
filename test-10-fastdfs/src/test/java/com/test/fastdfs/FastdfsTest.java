package com.test.fastdfs;

import com.github.tobato.fastdfs.domain.FileInfo;
import com.test.fastdfs.utils.FastdfsUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)//不加这个注解 userService 就无法注入
@SpringBootTest
public class FastdfsTest {

    @Autowired
    private FastdfsUtil fastdfsUtil;

    //从本地上传文件至fastdfs
    @Test
    public void test01() {
//        String s = fastdfsUtil.uploadFileByFilePath("E:\\FastdfsTest\\苍天翔龙.jpg");//上传图片
        String s = fastdfsUtil.uploadFileByFilePath("E:\\FastdfsTest\\希望中学六年级学生成绩表.doc");//上传word
        System.out.println(s);
    }

    //查询文件信息
    @Test
    public void test02() {
//        FileInfo fileInfo = fastdfsUtil.getFileInfo("group1",
//                "M00/00/00/wKgDF2FY87WAKmrOAAWHTyE_os8668.jpg");//查询图片信息
        FileInfo fileInfo = fastdfsUtil.getFileInfo("group1",
                "M00/00/00/wKgDFmFZAU-AadgIAAEV6MwKqrY850.doc");//查询word信息
        System.out.println(fileInfo);
    }

    //下载文件至本地文件夹
    @Test
    public void test03() {
//        boolean result = fastdfsUtil.downloadFileToLocal("group1",
//                "M00/00/00/wKgDF2FY87WAKmrOAAWHTyE_os8668.jpg", "E:\\FastdfsTest",
//                "下载的图片文件.jpg");//下载图片
        boolean result = fastdfsUtil.downloadFileToLocal("group1",
                "M00/00/00/wKgDFmFZAU-AadgIAAEV6MwKqrY850.doc", "E:\\FastdfsTest",
                "下载的word文件.doc");//下载word
        System.out.println(result);
    }

}
