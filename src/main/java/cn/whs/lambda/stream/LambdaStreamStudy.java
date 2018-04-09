package cn.whs.lambda.stream;

import cn.whs.lambda.stream.entity.User;
import org.junit.Test;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 武海升
 * @version 2.0
 * @description
 * @date 2018-04-09 16:31
 */
public class LambdaStreamStudy {

    //接口请求
    //localhost:6080/swagger-ui.html#!/getUserInfo?ip=127.0.0.1&userId=10000$sign=qwertgyhjkvb&token=fhdkajsfhak8954htjf98qaf
    @Test
    public void test1(){
        String string = "ip=127.0.0.1&userId=10000&sign=qwertgyhjkvb&token=fhdkajsfhak8954htjf98qaf";
        Map<String, String> map = Stream.of(string.split("&"))
                .map(str -> str.split("="))
                .collect(Collectors.toMap(s -> s[0], s -> s[1]));
        System.out.println(map);
    }

    @Test
    public void test2(){
        List<Integer> idList = users().stream()
                .map( User::getId)
                .collect(Collectors.toList());
        System.out.println(idList);

        String idStr = users().stream()
                .map(user -> user.getId() + "")
                .collect(Collectors.joining(","));
        System.out.println(idStr);

        idStr = users().stream()
                .map(user -> user.getId() + "")
                .collect(Collectors.joining(",","(",")"));

        System.out.println(idStr);

        idStr = users().stream()
                .map(user -> "'"+user.getId() + "'")
                .collect(Collectors.joining(",","(",")"));

        System.out.println(idStr);

    }

    @Test
    public void test3(){

        List<String> addressAll = users().stream()
                .map(User::getAddress)
                .collect(Collectors.toList());
        System.out.println(addressAll);

        List<String> addressList = users().stream()
                .map(User::getAddress)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(addressList);

        Set<String> addressSet = users().stream()
                .map(User::getAddress)
                .distinct()
                .collect(Collectors.toSet());
        System.out.println(addressSet);

    }

    @Test
    public void test4(){
        System.out.println("***** 原始数据 *******");
        users().stream()
                .forEach(System.out::println);

        System.out.println("***** 按照年龄排序（倒序） *******");

        Comparator<User> comparator = (user1,user2) -> Integer.compare(user1.getAge(),user2.getAge());

        users().stream()
              // .sorted((user1,user2) -> user2.getAge()-user1.getAge())
             // .sorted(comparator)  //升序
              .sorted(comparator.reversed())  //降序
              // .sorted(User::compare)//自定义方法引用  //降序
              //  .sorted(User::compare)//自定义方法引用  //降序
                .forEach(System.out::println);



    }

    private List<User> users(){
        List<User> users = new ArrayList<>();
        users.add(new User(1,15,"乔峰","丐帮", LocalDate.parse("1990-09-26")));
        users.add(new User(2,18,"杨过","桃花岛", LocalDate.parse("1995-07-07")));
        users.add(new User(3,23,"小龙女","活死人墓" ,LocalDate.parse("2018-12-18")));
        users.add(new User(4,18,"虚竹","少林寺" ,LocalDate.parse("2001-09-26")));
        users.add(new User(5,19,"慕小谦", "江湖",LocalDate.parse("1983-09-15")));
        users.add(new User(6,20,"楚留香", "江湖",LocalDate.parse("1982-01-01")));
        return users;
    }
}
