package com.test.pub;

import com.test.common.utils.*;
import com.test.pub.entity.Article;
import com.test.pub.mapper.TestProcedureMapper;
import com.test.pub.service.ArticleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)//不加这个注解 userService 就无法注入
@SpringBootTest
public class TestPublic {

    private final Logger log = LoggerFactory.getLogger(TestPublic.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private TestProcedureMapper testProcedureMapper;

    @Test
    public void test01() {
        String a = "abcd";//字符串常量，在常量池中
        String b = "abcd";
        String c = new String("abcd");//字符串变量，在堆内存中
        String d = c;

        System.out.println(a.equals(b));
        System.out.println(a.equals(c));
        System.out.println(c.equals(d));
        System.out.println(a.equals(d));
        System.out.println("-------------------------");
        System.out.println(a == b);
        System.out.println(a == c);
        System.out.println(a == d);
        System.out.println(c == d);
    }

    /**
     * 文件加密、解密及http请求调用测试
     */
    @Test
    public void test02() {
//        String key = "skf%0jfl@ssff#_7";
//        String iv = "0102030405060708";
//        //本地文件路径
//        String path = "E:\\test\\test.xml";
//        File file = new File(path);
//        //将本地文件转成String
//        String a = AesEncryptUtil.fileToString(file,"UTF-8");
//        log.info("文件转成String的结果------" + a);
//        //字符串进行加密处理
//        String encrypt = AesEncryptUtil.encrypt(a, key, iv);
//        log.info("String加密后的结果------" + encrypt);
//        //解密
//        String desEncrypt = AesEncryptUtil.desEncrypt(encrypt);
//        log.info("String解密后的结果------" + desEncrypt);
//        Map<String,String> map = new HashMap<>();
//        map.put("fileStr",encrypt);
//        System.out.println(HttpClientUtil.doGet("http://localhost:8001/test03",map));
    }

    /**
     * 使用redis测试
     */
    @Test
    public void test03() {
        redisTemplate.opsForValue().set("mykey02","天王盖地虎");
        System.out.println("mykey02: " + redisTemplate.opsForValue().get("mykey02"));
    }

    /**
     * 导出数据到word文档测试
     */
    @Test
    public void test04() throws IOException, IllegalAccessException {
        List<Map<String,Object>> detailList=new ArrayList<Map<String,Object>>();
        for (int i = 0; i < 3; i++) {
            Map<String,Object> detailMap = new HashMap<String, Object>();
            detailMap.put("id", i+1);//序号
            detailMap.put("title", "商品"+i);//商品名称
            detailMap.put("content", "套");//商品规格
            detailMap.put("occurTime", 3+i);//销售数量
            detailMap.put("rdl", 100+i);//销售价格
            detailList.add(detailMap);
        }

        /** 用于组装word页面需要的数据 */
        Map<String, Object> dataMap = new HashMap<String, Object>();
        List<Article> articleList = articleService.queryAllByLimit(1, 10);
        dataMap.put("listInfo",articleList);
        dataMap.put("detailList",detailList);
        dataMap.put("tableName","article");
        dataMap.put("tableSize",2000000);
        dataMap.put("total",articleList.size());
        String filePath = "F:/test/";
        //文件唯一名称
        String fileOnlyName = "测试生成Word文档.doc";
        /** 生成word  数据包装，模板名，文件生成路径，生成的文件名*/
//        WordUtil.createWord(dataMap, "model05.ftl", filePath, fileOnlyName);
    }

    /**
     * 数据导出到excel中，并市县合并单元格（只要下一个单元与上一个单元格内容相同就合并）
     */
    @Test
    public void test05() {
        List<String> tableHead = new ArrayList<>();
        tableHead.add("书籍编号");
        tableHead.add("书籍名称");
        tableHead.add("作者");
        tableHead.add("目录");
        tableHead.add("简介");
        List<String> fields = Arrays.asList("id","name","author","title","content");
        String filePath = "F:\\test";//临时文件存储路径
        String fileName = "四大名著666.xlsx";
        String sheetName = "四大名著章回信息";
        List<Article> articles = new ArrayList<>();
        Article article01 = new Article();
        article01.setId(1L);
        article01.setName("水浒传");
        article01.setAuthor("施耐庵");
        article01.setTitle("第一回");
        article01.setContent("王教头私走延安府 九纹龙大闹史家村");
        Article article02 = new Article();
        article02.setId(1L);
        article02.setName("水浒传");
        article02.setAuthor("施耐庵");
        article02.setTitle("第二回");
        article02.setContent("史大郎夜走华阴县 鲁提辖拳打镇关西");
        Article article03 = new Article();
        article03.setId(1L);
        article03.setName("水浒传");
        article03.setAuthor("施耐庵");
        article03.setTitle("第三回");
        article03.setContent("赵员外重修文殊院 鲁智深大闹五台山");
        Article article04 = new Article();
        article04.setId(2L);
        article04.setName("红楼梦");
        article04.setAuthor("曹雪芹");
        article04.setTitle("第1回");
        article04.setContent("甄士隐梦幻识通灵 贾雨村风尘怀闺秀");
        Article article05 = new Article();
        article05.setId(2L);
        article05.setName("红楼梦");
        article05.setAuthor("曹雪芹");
        article05.setTitle("第2回");
        article05.setContent("贾夫人仙逝扬州城 冷子兴演说荣国府");
        Article article06 = new Article();
        article06.setId(3L);
        article06.setName("三国演义");
        article06.setAuthor("罗贯中");
        article06.setTitle("第009回");
        article06.setContent("除暴凶吕布助司徒 犯长安李傕听贾诩");
        Article article07 = new Article();
        article07.setId(3L);
        article07.setName("三国演义");
        article07.setAuthor("罗贯中");
        article07.setTitle("第012回");
        article07.setContent("除暴凶吕布助司徒 犯长安李傕听贾诩");
        Article article08 = new Article();
        article08.setId(3L);
        article08.setName("三国演义");
        article08.setAuthor("罗贯中");
        article08.setTitle("第011回");
        article08.setContent("刘皇叔北海救孔融 吕温侯濮阳破曹操");

        Article article09 = new Article();
        article09.setId(4L);
        article09.setName("西游记");
        article09.setAuthor("吴承恩");
        article09.setTitle("第六回");
        article09.setContent("观音赴会问原因　小圣施威降大圣");
        Article article10 = new Article();
        article10.setId(4L);
        article10.setName("西游记");
        article10.setAuthor("吴承恩");
        article10.setTitle("第十四回");
        article10.setContent("心猿归正　六贼无踪");
        Article article11 = new Article();
        article11.setId(4L);
        article11.setName("西游记");
        article11.setAuthor("吴承恩");
        article11.setTitle("第三十八回");
        article11.setContent("婴儿问母知邪正　金木参玄见假真");

        Article article12 = new Article();
        article12.setId(5L);
        article12.setName("斗罗大陆");
        article12.setAuthor("天蚕土豆");
        article12.setTitle("第1集");
        article12.setContent("天使圣剑");
        Article article13 = new Article();
        article13.setId(5L);
        article13.setName("斗罗大陆");
        article13.setAuthor("天蚕土豆");
        article13.setTitle("第2集");
        article13.setContent("昊天锤");
        Article article14 = new Article();
        article14.setId(5L);
        article14.setName("斗罗大陆");
        article14.setAuthor("天蚕土豆");
        article14.setTitle("第3集");
        article14.setContent("昊天锤");
        Article article15 = new Article();
        article15.setId(5L);
        article15.setName("斗罗大陆");
        article15.setAuthor("天蚕土豆");
        article15.setTitle("第4集");
        article15.setContent("海神岛历练");
        articles.add(article01);
        articles.add(article02);
        articles.add(article03);
        articles.add(article04);
        articles.add(article05);
        articles.add(article06);
        articles.add(article07);
        articles.add(article08);
        articles.add(article09);
        articles.add(article10);
        articles.add(article11);
        articles.add(article12);
        articles.add(article13);
        articles.add(article14);
        articles.add(article15);
        new DataExportToExcelUtil<Article>().test(sheetName,filePath,fileName,tableHead,fields,articles);
    }

    //使用redisTemplate存取字节数组
    @Test
    public void test06() {
        Article article = articleService.queryById(1L);
        byte[] actByte = SerializeUtil.serialize(article);
        String key = "test002";
//        Object setResult = redisTemplate.execute((RedisConnection redisConnection)
//                -> redisConnection.set(key.getBytes(StandardCharsets.UTF_8),actByte));
//        System.out.println("01: " + setResult);
        byte[] getReusult = (byte[]) redisTemplate.execute((RedisConnection redisConnection)
                -> redisConnection.get(key.getBytes(StandardCharsets.UTF_8)));
        System.out.println("02: " + getReusult);
        Article unserializeArticle = (Article) SerializeUtil.unserialize(getReusult);
        System.out.println("03: " + unserializeArticle.getContent());
    }

    @Test
    public void test07() {
        List<Article> test = articleService.test();
        System.out.println(test.size());
    }

    @Test
    public void test08() {
        Integer a = 5;
        int b = 6;
        System.out.println(a.equals(b));
        if (a.equals(b)) {
            System.out.println("1111");
        } else {
            System.out.println("2222");
        }
    }

    //测试调用存储过程
    @Test
    public void test09() {
        List<Map<String,String>> inList = testProcedureMapper.testProcedureIn("无线专网",15);
        inList.forEach(System.out::println);
        List<Map<String,String>> outList = testProcedureMapper.testProcedureOut("无线专网",15,188);
        outList.forEach(System.out::println);
    }

}
