package abc.small.demo.jaxb.example2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author Jim
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class Student {

    /**
     * 作为节点的一个属性<student id="1">
     */
    @XmlAttribute
    private int id;

    @XmlElement(name = "stAge")
    private int studentAge;

    @XmlElement(name = "stName")
    private String studentName;

    public Student() {

    }

    /**
     * 创建一个新的实例Student.
     *
     * @param id
     * @param studentAge
     * @param studentName
     */
    public Student(int id, int studentAge, String studentName) {
        super();
        this.id = id;
        this.studentAge = studentAge;
        this.studentName = studentName;
    }

    /**
     * 获取studentAge
     *
     * @return studentAge studentAge
     */
    public int getStudentAge() {
        return studentAge;
    }

    /**
     * 设置studentAge
     *
     * @param studentAge studentAge
     */
    public void setStudentAge(int studentAge) {
        this.studentAge = studentAge;
    }

    /**
     * 获取studentName
     *
     * @return studentName studentName
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     * 设置studentName
     *
     * @param studentName studentName
     */
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    /**
     * 获取id
     *
     * @return id id
     */
    public int getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(int id) {
        this.id = id;
    }

}
