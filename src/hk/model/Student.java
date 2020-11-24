package hk.model;
/***********************************************************
 * 版权所有 (C)2020, hekai
 *
 * 文件名称：Student.java
 * 文件标识：无
 * 内容摘要：该类为学生类,从数据库中得到学生的数据,或者插入学生数据时,采用此结构
 * 其它说明：无
 * 当前版本： V1.0
 * 作   者：贺凯
 * 完成日期： 20201115
 **********************************************************/
public class Student {
    //学生学号
    private String Sno;
    //学生姓名
    private String Name;
    //性别
    private String sex;
    //年龄
    private int age;
    //身份证号
    private String ID;
    //学生所在班级
    private String ClassName;
    //学生所在院系
    private String Faculty;

    public String getSno() {
        return Sno;
    }

    public void setSno(String sno) {
        Sno = sno;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String className) {
        ClassName = className;
    }

    public String getFaculty() {
        return Faculty;
    }

    public void setFaculty(String faculty) {
        Faculty = faculty;
    }
}
