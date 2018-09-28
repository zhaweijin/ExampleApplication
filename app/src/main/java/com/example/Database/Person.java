package com.example.Database;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by carter on 3/8/17.
 */
@Table(name= "carter" )
public class Person extends Model{

    @Column(name = "name1")
    private String name;
    @Column(name = "age2")
    private int age;
    @Column(name = "sex")
    private String sex;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
