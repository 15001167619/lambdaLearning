package cn.whs.lambda.lambda;

import lombok.Data;

import java.util.function.*;

/**
 * @author 武海升
 * @version 2.0
 * @description 常用函数式接口示例
 * @date 2018-03-30 14:40
 */
public class LambdaLearnOne {

    static String getStr(){
        return "您的小可爱已上线";
    }

    public static void main(String[] args) {

        //（一）指定一个输出
        System.out.println("##########   指定一个输出   ########");
        Supplier<String> supplier1 = () -> "success";
        Supplier<Integer> supplier2 = () -> 100;
        Supplier<User> supplier3 = () -> new User();
        Supplier<String> supplier4 = () -> getStr();

        System.out.println(supplier1.get());
        System.out.println(supplier2.get());
        System.out.println(supplier3.get());
        System.out.println(supplier4.get());

        //（二）指定一个输入
        System.out.println("##########   指定一个输入   ########");
        Consumer<String> consumer = str -> System.out.println(str.toUpperCase());
        Consumer<Integer> consumer2 = integer -> System.out.println(String.valueOf(integer));
        Consumer<User> consumer3 = user -> System.out.println(user.toString());

        consumer.accept("admin");
        consumer2.accept(200);
        consumer3.accept(new User("admin",24));

        //(三) 指定俩个输入
        System.out.println("##########   指定俩个输入   ########");
        BiConsumer<String,User> biConsumer = (a, b) -> {
            System.out.println("第一个a输入是："+a+";"+"第二个b输入是："+b);
        };

        biConsumer.accept("admin",new User());

        /*
         * 四) 指定一个输入 一个输出
         * Function 输入与输出一般是不同类型
         * UnaryOperator 输入与输出是同一类型
         */
        System.out.println("##########   指定一个输入 一个输出 -> Function 不同类型 ########");
        Function<Integer,User> function = (age) -> new User(age);
        System.out.println(function.apply(100));

        Function<Integer,Integer> function1 = (parameter) -> {
            int sum = 0;
            for (int i = 0; i <parameter ; i++) {
                sum+=i;
            }
            return sum;
        };

        System.out.println(function1.apply(5));

        System.out.println("##########   指定一个输入 一个输出 -> UnaryOperator同一类型  ########");

        UnaryOperator<User> unaryOperator = user -> new User(user);

        System.out.println(unaryOperator.apply(new User("UnaryOperator",200)));


         /*
         * (五) 指定俩个输入 一个输出
         * BiFunction  输入与输出一般是不同类型
         * BinaryOperator 输入与输出是同一类型
         */
        System.out.println("##########   指定俩个输入 一个输出 ->BiFunction 不同类型   ########");
        BiFunction<String,Integer,User> biFunction = (name, age) -> {
            System.out.println("姓名：" + name);
            System.out.println("年龄：" + age);
            return new User(name,age);
        };

        System.out.println(biFunction.apply("小可爱",7));
        System.out.println("##########   指定俩个输入 一个输出 ->BinaryOperator 同一类型   ########");
        BinaryOperator<Integer> binaryOperator = (a,b) -> a * b;
        System.out.println(binaryOperator.apply(3,5));

    }

}

@Data
class User{
    private String name = "慕小谦";
    private Integer age = 23;

    public User(){
    }

    public User(User user){
        this.name = user.getName();
        this.age = user.age;
    }
    public User(String name,Integer age){
        this.name = name;
        this.age = age;
    }
    public User(Integer age){
        this.age = age;
    }
}
