package com.smalldemo.jaxb.example1;

import lombok.Data;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Jim
 */
@Data
@XmlRootElement(name = "customer")
public class Customer {

    String name;
    int age;
    int id;

    public String getName() {
        return name;
    }

    /**
     * name可不写，不写的话，会使用默认的属性，也即name
     *
     * @param name name
     */
    @XmlElement(name = "userName", required = false)
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    @XmlElement
    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    @XmlAttribute
    public void setId(int id) {
        this.id = id;
    }

}
