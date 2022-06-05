package org.example.CrudControllers.university.api;

import org.example.CrudControllers.university.dto.Student;

import java.sql.ResultSet;
import java.util.List;

public interface IStudentsDao extends AutoCloseable {
    /**
     * just read all entities from certain table
     * @return
     */
     List<Student> readAll();

    /**
     *
     *
     * @param student - object we deserialize from json to put in DATABASE table
     */
     void create(Student student);

    /**
     *
     *
     *
     * @param student - object we deserialize from json, but with new values in fields
     */
     void update(Student student);

    /**
     *
     *
     * @param id - student id, by which we delete student
     */
     void delete(Long id);

}
