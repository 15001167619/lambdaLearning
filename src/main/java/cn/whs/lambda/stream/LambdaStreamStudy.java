package cn.whs.lambda.stream;

import cn.whs.lambda.stream.entity.User;
import org.junit.Test;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
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
    //获取各种id集合
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
        //获取全部地址
        List<String> addressAll = users().stream()
                .map(User::getAddress)
                .collect(Collectors.toList());
        System.out.println(addressAll);
        //获取全部地址（去重）
        List<String> addressList = users().stream()
                .map(User::getAddress)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(addressList);
        //获取全部地址（去重）
        Set<String> addressSet = users().stream()
                .map(User::getAddress)
                .distinct()
                .collect(Collectors.toSet());
        System.out.println(addressSet);

    }

    @Test
    //排序测试
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

    @Test
    //多重排序测试
    public void test5(){
        System.out.println("***** 按照年龄排序（默认升序） *******");
        users().stream()
                .sorted(Comparator.comparing(User::getAge))
                .forEach(System.out::println);
        System.out.println("***** 按照年龄排序（降序） *******");
        users().stream()
                .sorted(Comparator.comparing(User::getAge)
                        .reversed()//降序
                )
                .forEach(System.out::println);
        System.out.println("***** 按照年龄排序（降序） 其中年龄相同，按照出场时间降序*******");
        users().stream()
                .sorted(Comparator.comparing(User::getAge)
                        .thenComparing(User::getDebut)
                        .reversed()//降序
                )
                .forEach(System.out::println);
    }
    @Test
    //转换map
    public void test6(){
        System.out.println("***** 转换map（key :id,value :user） *******");
        Map<Integer, User> map = users().stream()
                .collect(Collectors.toMap(User::getId, user -> user));
        System.out.println(map);
    }
    @Test
    //转换map
    public void test60(){
        System.out.println("***** 转换map（key :id,value :user） *******");
        Function<User, User> mapper = (user)->{
            String address = user.getAddress();
            user.setAddress("address"+address);


            return user;
        };
        List<User> collect = users().stream().map(mapper).collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    //转换map  方法的引用
    public void test61(){
        System.out.println("***** 转换map（key :id,value :user） *******");
        Function<User, User> mapper = (user)->{
            String address = user.getAddress();
            user.setAddress("address"+address);
            return user;
        };

        Function<User, User> mapper1 = User::getUser;

        List<User> collect = users().stream().map(mapper1).collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    //查询
    public void test7(){
        System.out.println("***** 最大年龄 *******");
        Integer max = users().stream()
                .map(User::getAge)
                .max(Integer::compare).get();

        System.out.println("最大年龄:"+max);

        System.out.println("***** 平均年龄 *******");
        Double averagingDoubleAge = users().stream()
                .collect(Collectors.averagingDouble(User::getAge));
        System.out.println("平均年龄:"+averagingDoubleAge);


        System.out.println("***** Collectors.maxBy(Comparator.comparing(   )) *******");
        Integer maxAge = users().stream()
                .collect(Collectors.maxBy(Comparator.comparing(User::getAge)))
                .get()
                .getAge();
        System.out.println("maxAge"+maxAge);
        LocalDate maxDebut = users().stream()
                .collect(Collectors.maxBy(Comparator.comparing(User::getDebut)))
                .get()
                .getDebut();
        System.out.println("最新出场时间："+maxDebut);
        User user = users().stream()
                .collect(Collectors.maxBy(Comparator.comparing(User::getAge)
                        .thenComparing(Comparator.comparing(User::getDebut))))
                .get();
        System.out.println("年龄最大 出场时间最晚："+user);
    }

    @Test
    //分组
    public void test8(){
        System.out.println("***** 按地址分组 *******");
        Map<String, List<User>> mapUser = users().stream()
                .collect(Collectors.groupingBy(User::getAddress));

        System.out.println(mapUser);

        Map<String, Long> mapCount = users().stream()
                .collect(Collectors.groupingBy(User::getAddress, Collectors.counting()));
        System.out.println("不同地址下 共有多少人："+mapCount);

        Map<String, Integer> mapAgeSum = users().stream()
                .collect(Collectors.groupingBy(User::getAddress, Collectors.summingInt(User::getAge)));
        System.out.println("不同地址下 人数年龄总和："+mapAgeSum);

        Map<String, Double> mapAgeAveraging = users().stream()
                .collect(Collectors.groupingBy(User::getAddress, Collectors.averagingDouble(User::getAge)));
        System.out.println("不同地址下 人数平均年龄："+mapAgeAveraging);

        Map<String, Optional<User>> mapAgeMax = users().stream()
                .collect(Collectors.groupingBy(User::getAddress, Collectors.maxBy(Comparator.comparing(User::getAge))));
        System.out.println("不同地址下 年龄最大的："+mapAgeMax);

    }

    @Test
    //过滤
    public void test9(){
        List<User> userList = users().stream()
                .filter(user -> user.getAge() >= 25)
                .sorted(Comparator.comparing(User::getAge)
                .thenComparing(Comparator.comparing(User::getDebut)).reversed())
                .collect(Collectors.toList());

        System.out.println(userList);
    }

    private List<User> users(){
        List<User> users = new ArrayList<>();
        users.add(new User(1,18,"乔峰","丐帮", LocalDate.parse("1997-09-26")));
        users.add(new User(2,18,"杨过","桃花岛", LocalDate.parse("1995-07-07")));
        users.add(new User(3,23,"小龙女","活死人墓" ,LocalDate.parse("2014-12-18")));
        users.add(new User(4,18,"虚竹","少林寺" ,LocalDate.parse("2001-09-26")));
        users.add(new User(5,29,"慕小谦", "江湖",LocalDate.parse("1983-09-15")));
        users.add(new User(6,35,"小李飞刀", "江湖",LocalDate.parse("2005-01-01")));
        return users;
    }
}
