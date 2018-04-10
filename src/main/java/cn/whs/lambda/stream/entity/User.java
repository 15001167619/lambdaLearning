package cn.whs.lambda.stream.entity;

import lombok.Data;

import java.time.LocalDate;

/**
 * @author 武海升
 * @version 2.0
 * @description
 * @date 2018-04-09 17:00
 */
@Data
public class User {

    private Integer id;
    private Integer age;
    private String name;
    private String address;
    private LocalDate debut;

    public User(Integer id, Integer age, String name,String address, LocalDate debut) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.address = address;
        this.debut = debut;
    }

    public static int compare(User x, User y) {
        return (x.getAge() > y.getAge()) ? -1 : ((x.getAge() .equals(y.getAge()) ) ? 0 : 1);
    }
}
