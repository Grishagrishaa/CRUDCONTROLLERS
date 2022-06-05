package org.example.CrudControllers.university.dao;

import org.example.CrudControllers.university.api.IGroup;
import org.example.CrudControllers.university.api.IGroupsDao;
import org.example.CrudControllers.university.dao.sqlUtils.GroupsStudentsRequests;
import org.example.CrudControllers.university.dto.Group;
import org.example.CrudControllers.university.dto.GroupForBase;
import org.example.CrudControllers.university.dto.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupsStudentsDao{
    private static GroupsStudentsDao instance;

    private GroupsStudentsDao() {
    }

    public List<IGroup> readAll() {
        List<Long> idList = getGroupsId();
        List<IGroup> groups = new ArrayList<>();

        try(
                Connection connection = ConnectionFactory.getConnection();
            ){
            for (Long id : idList) {
                PreparedStatement statement = connection.prepareStatement(GroupsStudentsRequests.getGroupWithStudents);
                statement.setLong(1,id);
                ResultSet resultSet = statement.executeQuery();
                groups.add(mapGroup(resultSet));
            }

            return groups;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public void update(Group group) {
        Long groupId = getIdByName(group.getGroupName());

        try(
                Connection connection = ConnectionFactory.getConnection();
        ){
            for (Student student : group.getStudents()) {
                PreparedStatement statement = connection.prepareStatement(GroupsStudentsRequests.addStudentsToGroup);
                statement.setLong(1,groupId);
                statement.setLong(2, student.getId());
                statement.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void delete(Group group) {
        Long groupId = getIdByName(group.getGroupName());

        try(
                Connection connection = ConnectionFactory.getConnection();
        ){
            for (Student student : group.getStudents()) {
                PreparedStatement statement = connection.prepareStatement(GroupsStudentsRequests.deleteStudentsFromGroup);
                statement.setLong(1,groupId);
                statement.setLong(2, student.getId());
                statement.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    private Group mapGroup(ResultSet set) throws SQLException {
        Group.Builder Groupbuilder = Group.Builder.create();

        while (set.next()){
            Groupbuilder
                    .setGroupName(set.getString("group_name"))
                    .addStudents(Student.Builder.create()
                            .setId(set.getLong("student_id"))
                            .setName(set.getString("name"))
                            .setAge(set.getInt("age"))
                            .setScore(set.getDouble("score"))
                            .setOlympicGamer(set.getBoolean("olympic_gamer"))
                            .build()
                                );
        }

        return Groupbuilder.build();
    }

    private List<Long> getGroupsId(){
        List<Long> idList = new ArrayList<>();
        try(
                Connection connection = ConnectionFactory.getConnection();
                Statement groupsIdstm = connection.createStatement();
                ResultSet groupsIdRs = groupsIdstm.executeQuery(GroupsStudentsRequests.getGroupsId);
        ){
            while(groupsIdRs.next()){//Мы заполнили лист с айдишниками существующих групп
                idList.add(groupsIdRs.getLong("group_id"));
            }
            groupsIdRs.close();
            groupsIdstm.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return idList;
    }

    public Long getIdByName(String name){
        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stm = connection.prepareStatement(GroupsStudentsRequests.getIdByName);
        ){
            stm.setString(1,name);
            ResultSet rs = stm.executeQuery();

            long id = 0;
            while(rs.next()){
                id = rs.getLong("group_id");
            }

            rs.close();

            return id;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static GroupsStudentsDao getInstance(){
        if(instance == null){
            instance = new GroupsStudentsDao();
        }
        return instance;
    }

    public void close() throws Exception {
        ConnectionFactory.close();
    }
}
