package cn.whs.lambda.lambda;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.*;

/**
 * @author 武海升
 * @version 2.0
 * @description  函数式接口方法的引用：
 *                              函数式接口的实现可由实例方法调用，那么则可以使用方法的引用
 * @date 2018-03-30 16:18
 */
public class LambdaLearnTwo {

    //静态方法
    static Integer factorial(Integer parameter){
        if(parameter==null)return null;
        if(parameter<0) return 0;
        if (parameter <= 1){
            return parameter;
        }else {
            return parameter * factorial(parameter-1);
        }
    }

    public void setValue(Integer parameter){
        System.out.println("parameter:" + parameter);
    }


    public static void main(String[] args) {
        /*
         *  静态方法引用
         *  语法：类名 :: staticMethodName
         *        lambda 表达式 (args) -> 类名.staticMethodName(args)
         */
        System.out.println("############# 静态方法引用 ################");
        Function<Integer,Integer> function = (a) -> LambdaLearnTwo.factorial(a);
        Function<Integer,Integer> function2 = LambdaLearnTwo:: factorial;
        System.out.println(function.apply(3));
        System.out.println(function2.apply(null));

       // BiFunction<String,String,Integer> biFunction = (a,b) -> a.length()+b.length();

         /*
          *  实例方法引用
          *  语法：inst :: instMethodName
          *        lambda 表达式 (args) -> new 类名.instMethodName(args)
          */

        System.out.println("############# 实例方法引用 ################");

        Consumer<Integer> consumer = (parameter) -> new LambdaLearnTwo().setValue(parameter);
        Consumer<Integer> consumer1 = new LambdaLearnTwo()::setValue;
        LambdaLearnTwo lambdaLearn = new LambdaLearnTwo();
        Consumer<Integer> consumer2 = lambdaLearn::setValue;

        consumer.accept(100);
        consumer1.accept(200);
        consumer2.accept(300);

        System.out.println("############# 对象方法引用 ################");
         /*
          *  对象方法引用
          *  条件：抽象方法必须有参数类型，且第一个参数类型必须是实例方法的类型，
          *        最后抽象方法剩余的参数恰好可以当作实例方法的参数
          *  语法：类名 :: instMethodName
          *        lambda 表达式 (inst,args) -> 类名.instMethodName(args)
          *
          *  没有输入参数，则不能使用对象方法引用
          *       Runnable run = () -> {};
          *       Supplier<Integer> supplier = ()-> 100;
          *       Closeable closeable = () -> {};
          *
          *  只有一个参数类型的
          *  Consumer<Person> consumer3 = (person) -> new Person().userList();
          *  输入参数 Person 实例对象是Person 满足条件
          *  但  Consumer<Person> consumer6 = Person1::userList;
          *  输入参数与实例对象属于不同类型，不满足对象引用的条件，故而不能使用对象引用的条件 但可以用实例方法
          *  Supplier<Integer> supplier = ()-> 100;
          *  Closeable closeable = () -> {};
          */

            Consumer<Person> consumer3 = (person) -> new Person().userList();
            Consumer<Person> consumer4 = Person::userList;
            consumer3.accept(new Person());
            consumer4.accept(new Person());

            Consumer<Person> consumer5 = (person) -> new Person1().userList(person);
            //Consumer<Person> consumer6 = Person1::userList; 错误的
            Consumer<Person> consumer6 = new Person1()::userList;

            BiConsumer<Person,String> biConsumer = (person, name) -> new Person().setPersonName(name);
            BiConsumer<Person,String> biConsumer1 = Person:: setPersonName;
            biConsumer.accept(new Person(),"慕小谦");
            biConsumer1.accept(new Person(),"小可爱");

            BiFunction<Person,String,Person> biFunction = (person, name) -> new Person().setName(person,name);
            System.out.println(biFunction.apply(new Person(),"Root").toString());
            BiFunction<Person,String,Person> biFunction1 = (person, name) -> new Person().setName(person,name);
            System.out.println(biFunction1.apply(new Person(),"Admin").toString());

            System.out.println("############# 构造方法引用 ################");

          /*
           *  构造方法(构造函数)引用
           *  语法：类名 :: new
           *        lambda 表达式 (args) -> new 类名()
           */
            //无参构造函数
            Supplier<Person> consumer7 = () -> new Person();
            Supplier<Person> consumer8 = Person::new;
            consumer7.get();
            consumer8.get();

            Supplier<List> consumer9 = ArrayList::new;
            Supplier<Set> consumer10 = HashSet::new;

            //有参构造函数
            Consumer<String> consumer11 = (name) -> new Person(name);
            consumer11.accept("慕小谦");
            Consumer<String> consumer12 = Person:: new;
            consumer12.accept("武海升");

            Function<String,Person> function1 = (name) -> new Person(name);
            System.out.println(function1.apply("小可爱"));
            Function<String,Person> function3 = Person:: new;
            System.out.println(function3.apply("小老虎"));

    }
}

class Person{

    private String name;

    public Person(){
        System.out.println("方法引用");
    }

    public Person(String name){
        this.name =name;
        System.out.println(name);
    }


    public Person setName(Person person, String name) {
        person.name = name;
        return person;
    }

    public void userList(){
        System.out.println("userList");
    }
    public void setPersonName(String name){
        System.out.println("PersonName:"+name);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}

class Person1{

    public void userList(Person person){
        System.out.println("Person person");
    }

}

