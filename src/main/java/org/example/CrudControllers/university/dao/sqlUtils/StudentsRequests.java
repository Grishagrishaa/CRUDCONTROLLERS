package org.example.CrudControllers.university.dao.sqlUtils;

import org.example.CrudControllers.university.dto.Student;

public class StudentsRequests {
    public static final String getAll = "SELECT * FROM bstu.students;";

    public static final String create =
            "INSERT INTO bstu.students (name, age, score, olympic_gamer)\n" +
            "    VALUES (?, ?, ?, ?);";


    public static String update(Student student){
        StringBuilder SetBld = new StringBuilder();

        if(student == null) return null;

        String update = "" +
                "UPDATE bstu.students \n" +
                "SET ";

        if(student.getId() == null){
            throw new IllegalArgumentException(" UPDATE ERROR.NO ID PROVIDED WITH STUDENT.");
        }

        if(student.getName() != null){
            SetBld.append("name = ?");
        }

        if(student.getAge() != null){
            if(SetBld.length() > 0) SetBld.append(",");
            SetBld.append("age = ?");
        }

        if(student.getScore() != null){
            if(SetBld.length() > 0) SetBld.append(",");
            SetBld.append("score = ?");
        }

        if(student.isOlympicGamer() != null){
            if(SetBld.length() > 0) SetBld.append(",");
            SetBld.append("olympic_gamer = ?");
        }
        if(SetBld.length() == 0) throw new IllegalArgumentException(" UPDATE ERROR.NO UPDATE PARAMETERS PROVIDED.");

        update += SetBld.toString();
        SetBld.setLength(0);
        return update += "\nWHERE student_id = ?;";
    }

    public static final String delete = " " +
            "DELETE FROM bstu.students\n" +
            "WHERE student_id = ?;";
}
