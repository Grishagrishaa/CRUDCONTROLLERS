package org.example.CrudControllers.university.service;

import org.example.CrudControllers.university.api.IGroup;
import org.example.CrudControllers.university.dao.GroupsDao;
import org.example.CrudControllers.university.dto.GroupForBase;

import java.util.List;

public class GroupsService {
    private static GroupsService instance;
    private GroupsDao dao;

    private GroupsService() {
        this.dao = GroupsDao.getInstance();
    }


    public List<IGroup> readAll(){
        List<IGroup> iGroups = dao.readAll();

        return iGroups;
    }

    public void create(GroupForBase group){
        dao.create(group);
    }


    public void update(GroupForBase group){
        dao.update(group);
    }

    public void delete(GroupForBase group){
        Long id = group.getGroupId();
        dao.delete(id);
    }


    public static GroupsService getInstance(){
        if(instance == null){
            instance  = new GroupsService();
        }
        return instance;
    }
}
