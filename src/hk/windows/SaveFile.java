package hk.windows;

import hk.model.Student;
import hk.sql.StudentList;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
/***********************************************************
 * ��Ȩ���� (C)2020, hekai
 *
 * �ļ����ƣ�SaveFile.java
 * �ļ���ʶ����
 * ����ժҪ�����ཫ�ļ����д���
 * ����˵������
 * ��ǰ�汾�� V1.0
 * ��   �ߣ��ؿ�
 * ������ڣ� 20201115
 **********************************************************/
public class SaveFile {
    public SaveFile(File file){
        StudentList listStudent=new StudentList();
        List<Student> students=listStudent.StudentRes();
        BufferedWriter bufferedWriter;
        try{
            bufferedWriter=new BufferedWriter(new FileWriter(file));
            for(Student s:students)
            {
                bufferedWriter.write(s.getSno()+"\t");
                bufferedWriter.write(s.getName()+"\t");
                bufferedWriter.write(s.getSex()+"\t");
                bufferedWriter.write(s.getAge()+"\t");
                bufferedWriter.write(s.getID()+"\t");
                bufferedWriter.write(s.getClassName()+"\t");
                bufferedWriter.write(s.getFaculty()+"\t");
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }


}
