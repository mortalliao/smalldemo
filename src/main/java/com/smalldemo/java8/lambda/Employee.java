package com.smalldemo.java8.lambda;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Jim
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Employee {

    private String name;
    private Integer age;
    private double salary;

    public Employee(Integer age){
        this.age = age;
    }
}
