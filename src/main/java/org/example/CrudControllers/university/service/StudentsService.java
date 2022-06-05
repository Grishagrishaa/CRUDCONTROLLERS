package org.example.CrudControllers.university.service;

import org.example.CrudControllers.university.dao.StudentsDao;
import org.example.CrudControllers.university.dto.Student;

import java.util.List;

public class StudentsService {
    private static StudentsService instance;
    private StudentsDao dao;

    private StudentsService() {
        this.dao = StudentsDao.getInstance();
    }


    public List<Student> readAll(){
        List<Student> students = dao.readAll();
        return students;
    }

    public void create(Student student){
        dao.create(student);
    }

    public void update(Student student){
        dao.update(student);
    }

    public void delete(Student student){
        Long id = student.getId();
        dao.delete(id);
    }

    public static StudentsService getInstance() {
        if(instance == null){
            return new StudentsService();
        }
        return instance;
    }
}
