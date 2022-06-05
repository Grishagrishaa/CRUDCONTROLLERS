package org.example.CrudControllers.university.dao;

import org.example.CrudControllers.university.api.IStudentsDao;
import org.example.CrudControllers.university.dao.sqlUtils.StudentsRequests;
import org.example.CrudControllers.university.dto.Student;

import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentsDao implements IStudentsDao {
    private static StudentsDao instance;
    private List<Student> students;

    private StudentsDao() {
    }

    public List<Student> readAll(){
        try( Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(StudentsRequests.getAll)
        ){
            students = new ArrayList<>();
            while (rs.next()) {
                students.add(map(rs));
            }
            return students;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void create(Student student){
        try( Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(StudentsRequests.create) )
        {
        statement.setString(1, student.getName());
        statement.setInt(2, student.getAge());
        statement.setDouble(3, student.getScore());
        statement.setBoolean(4, student.isOlympicGamer());
        statement.executeUpdate();

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Student student){
        try( Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(StudentsRequests.update(student)) )
        {
            int index = 1;
            if(student.getName() != null){
                statement.setString(index,student.getName());
                index++;
            }

            if(student.getAge() != null){
                statement.setInt(index, student.getAge());
                index++;
            }

            if(student.getScore() != null){
                statement.setDouble(index, student.getScore());
                index++;
            }

            if(student.isOlympicGamer() != null){
                statement.setBoolean(index, student.isOlympicGamer());
                index++;
            }

            statement.setLong(index, student.getId());

            statement.executeUpdate();

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Long id){
        try( Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(StudentsRequests.delete) )
        {
            statement.setLong(1,id);
            statement.executeUpdate();

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Student map(ResultSet rs) throws SQLException {
        return Student.Builder.create()
                .setId(rs.getLong("student_id"))
                .setName(rs.getString("name"))
                .setAge(rs.getInt("age"))
                .setScore(rs.getDouble("score"))
                .setOlympicGamer(rs.getBoolean("olympic_gamer"))
                .build();
    }


    public static StudentsDao getInstance(){
        if(instance == null){
            instance = new StudentsDao();
        }
        return instance;
    }

    @Override
    public void close() throws Exception {
        ConnectionFactory.close();
    }
}
