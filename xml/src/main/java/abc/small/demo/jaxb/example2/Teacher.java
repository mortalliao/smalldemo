package abc.small.demo.jaxb.example2;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * @author Jim
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Teacher {

    @XmlElement(name = "teAge")
    private int teacherAge;

    @XmlElement(name = "teName")
    private String teacherName;

    /**
     * XmlElementWrapper这个会给xml包装一层
     * <students>
     *      <student>
     *           ......
     *      </student>
     *      ......
     * </students>
     */
    @XmlElementWrapper(name = "students")
    @XmlElement(name = "student")
    private List<Student> students;

    public Teacher() {
    }

    /**
     * 创建一个新的实例Teacher.
     *
     * @param teacherAge
     * @param teacherName
     */
    public Teacher(int teacherAge, String teacherName) {
        super();
        this.teacherAge = teacherAge;
        this.teacherName = teacherName;
    }

    /**
     * 获取teacherAge
     *
     * @return teacherAge teacherAge
     */
    public int getTeacherAge() {
        return teacherAge;
    }

    /**
     * 设置teacherAge
     *
     * @param teacherAge teacherAge
     */
    public void setTeacherAge(int teacherAge) {
        this.teacherAge = teacherAge;
    }

    /**
     * 获取teacherName
     *
     * @return teacherName teacherName
     */
    public String getTeacherName() {
        return teacherName;
    }

    /**
     * 设置teacherName
     *
     * @param teacherName teacherName
     */
    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    /**
     * 获取students
     *
     * @return students students
     */
    public List<Student> getStudents() {
        return students;
    }

    /**
     * 设置students
     *
     * @param students students
     */
    public void setStudents(List<Student> students) {
        this.students = students;
    }

}
