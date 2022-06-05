package org.example.CrudControllers.university.dao;

import org.example.CrudControllers.university.api.IGroup;
import org.example.CrudControllers.university.api.IGroupsDao;
import org.example.CrudControllers.university.dao.sqlUtils.GroupsRequests;
import org.example.CrudControllers.university.dto.GroupForBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupsDao implements IGroupsDao {
    private static GroupsDao instance;
    private static  List<IGroup> groups;


    public List<IGroup> readAll() {
        groups = new ArrayList<>();
        try(
                Connection connection = ConnectionFactory.getConnection();
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(GroupsRequests.getAll)
           ){
            while(rs.next()){
                groups.add(map(rs));
            }

            statement.close();
            connection.close();
            rs.close();
            return groups;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void create(GroupForBase group) {
        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(GroupsRequests.create);
        ){
            statement.setString(1,group.getGroupName());
            statement.executeUpdate();

            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void update(GroupForBase group) {
        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(GroupsRequests.update);
        ){
            statement.setString(1, group.getGroupName());
            statement.setLong(2, group.getGroupId());
            statement.executeUpdate();

            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void delete(Long id) {
        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(GroupsRequests.delete);
        ){
            statement.setLong(1, id);
            statement.executeUpdate();

            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private GroupForBase map(ResultSet rs) throws SQLException {
        return GroupForBase.Builder.create()
                .setGroupId(rs.getLong("group_id"))
                .setGroupName(rs.getString("group_name"))
                .build();
    }




    public static GroupsDao getInstance(){
        if(instance == null){
            instance = new GroupsDao();
        }
        return  instance;
    }


    @Override
    public void close() throws Exception {
        ConnectionFactory.close();
    }
}
