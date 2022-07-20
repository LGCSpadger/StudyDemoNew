package com.test.pub;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.ResourceUtils;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)//不加这个注解 userService 就无法注入
@SpringBootTest
public class IoTest {

  //获取resources目录下路径的几种方法
  @Test
  public void test01() throws FileNotFoundException {
    //1.获取项目的根路径
    String xmglj = this.getClass().getResource("/").getPath();
    log.info("项目根路径：" + xmglj);
    //2.获取项目中 resources 目录下的 templates 目录的绝对路径（方式一）
    String temPath01 = this.getClass().getResource("/templates").getPath();
    log.info("项目中 resources 目录下的 templates 目录的绝对路径（方式一）：" + temPath01);
    String temPath02 = this.getClass().getClassLoader().getResource("templates").getPath();
    log.info("项目中 resources 目录下的 templates 目录的绝对路径（方式二）：" + temPath02);
    String url = ResourceUtils.getURL("src" + File.separator + "main" + File.separator +  "static" + File.separator + "upload").getPath();
    log.info("使用 ResourceUtils 工具类获取url："+url);
  }

  //FileOutputStream：文件输出流用于将数据写入文件File
  @Test
  public void test02() throws IOException {
    String url = ResourceUtils.getURL("src" + File.separator + "main" + File.separator + "resources" + File.separator +  "static" + File.separator + "txtfile").getPath();
    log.info("使用 ResourceUtils 工具类获取url："+url);
    if (StringUtils.isNotEmpty(url)) {
      File dicFile = new File(url);
      //exists()判断文件或者文件夹是否存在
      if (!dicFile.exists()) {
        dicFile.mkdir();//mkdir()创建单个文件夹，要确保它的上级文件夹存在
//        dicFile.mkdirs();//mkdirs()创建多个文件夹，并且不需要保证它的上级文件夹存在
      }
      String fileName = "testFile01.txt";
      File file = new File(url + File.separator + fileName);
      if (!file.exists()) {
        file.createNewFile();//createNewFile()创建一个新的文件
      }
      FileOutputStream fos = new FileOutputStream(url + File.separator + fileName);
      fos.write(98);//向文件中写入内容（一次写一个字节数据 ASCII码 98对应为b）
      fos.write("\n".getBytes(StandardCharsets.UTF_8));//  \n换行
      byte[] bys = {97,98,99,100,101};
      fos.write(bys);//向文件中写入内容（一次写一个字节数组数据）
      fos.write("\r".getBytes(StandardCharsets.UTF_8));//  \r回车
      fos.write("\n".getBytes(StandardCharsets.UTF_8));//  \n换行
      fos.write("\r\n".getBytes(StandardCharsets.UTF_8));//  \r\n回车换行
      byte[] bs1 = "abcdefghijk".getBytes(StandardCharsets.UTF_8);
      fos.write(bs1,3,4);//从指定的字节数组bs1的索引为3的地方开始写入，一次写入4个字节，也就是 defg
      byte[] bs2 = "我们是好朋友".getBytes(StandardCharsets.UTF_8);
      fos.write(bs2,0,bs2.length);//从指定的字节数组bs1的索引为0的地方开始写入，一次写入bs2.length个字节，也就是 我们是好朋友
      fos.flush();//flush 是清空的意思，一般主要用在IO中，即清空缓冲区数据，就是说你用读写流的时候，其实数据是先被读到了内存中，然后把数据写到文件中，当你数据读完的时候不代表你的数据已经写完了，因为还有一部分有可能会留在内存这个缓冲区中。这时候如果你调用了 close()方法关闭了读写流，那么这部分数据就会丢失，所以应该在关闭读写流之前先flush()，先清空数据
      fos.close();//关闭流
    }
  }

}
