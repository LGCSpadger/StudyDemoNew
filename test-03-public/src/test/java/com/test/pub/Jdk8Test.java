package com.test.pub;

import com.test.pub.entity.UserPo;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * java8 新特性使用测试
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)//不加这个注解 userService 就无法注入
@SpringBootTest
public class Jdk8Test {

  //jdk8 Stream流 常见用法测试
  @Test
  public void test01() {
    List<UserPo> list = new ArrayList<>();
    list.add(new UserPo("小一", "一班", 10.d));
    list.add(new UserPo("小五", "一班", 50.d));
    list.add(new UserPo("小六", "二班", 60.d));
    list.add(new UserPo("小6", "一班", 60.d));
    list.add(new UserPo("小空", "二班", 56.d));
    list.add(new UserPo("小九", "三班", 90.d));
    long count = 0;
    List<UserPo> filterList = null;

    // filter 过滤器的使用
    // 筛选出成绩不为空的学生人数
    count = list.stream().filter(p -> null != p.getScore()).count();
    System.out.println("参加考试的学生人数：" + count);

    // collect
    // 筛选出成绩不为空的学生集合
    filterList = list.stream().filter(p -> null != p.getScore()).collect(Collectors.toList());
    System.out.println("参加考试的学生信息：");
    filterList.stream().forEach(System.out::println);

    // map 将集合映射为另外一个集合
    // 取出所有学生的成绩
    List<Double> scoreList = list.stream().map(p -> p.getScore()).collect(Collectors.toList());
    System.out.println("所有学生的成绩集合：" + scoreList);

    // 将学生姓名集合串成字符串，用逗号分隔
    String nameString = list.stream().map(p -> p.getName()).collect(Collectors.joining(","));
    System.out.println("所有学生的姓名字符串：" + nameString);

    // sorted排序
    // 按学生成绩逆序排序 正序则不需要加.reversed()
    filterList = list.stream().filter(p -> null != p.getScore()).sorted(Comparator.comparing(UserPo::getScore).reversed()).collect(Collectors.toList());
    System.out.println("所有学生的成绩集合，逆序排序：");
    filterList.stream().forEach(System.out::println);

    System.out.println("按学生成绩归集：");
    Map<Double, List<UserPo>> groupByScoreMap = list.stream().filter(p -> null != p.getScore())
        .collect(Collectors.groupingBy(UserPo::getScore));
    for (Map.Entry<Double, List<UserPo>> entry : groupByScoreMap.entrySet()) {
      System.out.println("成绩：" + entry.getKey() + " 人数：" + entry.getValue().size());
    }

    //按照班级分组
    Map<String, List<UserPo>> groupByClassMap = list.stream().filter(p -> null != p.getCla()).collect(Collectors.groupingBy(UserPo::getCla));
    log.info("按照班级分组：{}",groupByClassMap);

    // forEach
    filterList.stream().forEach(p -> p.setScore(p.getScore() + 10));
    System.out.println("及格人数太少，给每个人加10分");
    filterList.stream().forEach(System.out::println);

    // count
    count = filterList.stream().filter(p -> p.getScore() >= 60).count();
    System.out.println("最后及格人数" + count);

    DoubleSummaryStatistics statistics = filterList.stream().mapToDouble(p -> p.getScore()).summaryStatistics();
    System.out.println("列表中最大的数 : " + statistics.getMax());
    System.out.println("列表中最小的数 : " + statistics.getMin());
    System.out.println("所有数之和 : " + statistics.getSum());
    System.out.println("平均数 : " + statistics.getAverage());

    // 并行流 使用
    count = list.parallelStream().filter(p -> null != p.getScore()).count();
    System.out.println("并行流处理参加考试的学生人数：" + count);

    //根据指定字段去重
    log.info("根据指定字段去重之前：{}",list);
    List<UserPo> users= list.stream().filter(distinctByKey(UserPo::getScore)).collect(Collectors.toList());
    log.info("根据指定字段去重之后：{}",users);
  }

  private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
    Map<Object, Boolean> seen = new ConcurrentHashMap<>();
    return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
  }

}
