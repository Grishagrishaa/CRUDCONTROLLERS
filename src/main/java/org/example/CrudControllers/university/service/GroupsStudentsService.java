package org.example.CrudControllers.university.service;

import org.example.CrudControllers.university.api.IGroup;
import org.example.CrudControllers.university.dao.GroupsStudentsDao;
import org.example.CrudControllers.university.dto.Group;

import java.util.List;

public class GroupsStudentsService {
    private static GroupsStudentsService instance;
    private GroupsStudentsDao dao;

    private GroupsStudentsService() {
        this.dao = GroupsStudentsDao.getInstance();
    }

    public List<IGroup> readAll(){
        return dao.readAll();
    }

    public void update(Group group) {
        dao.update(group);
    }

    public void delete(Group group) {
        dao.delete(group);
    }

    public static GroupsStudentsService getInstance(){
        if(instance == null){
            instance = new GroupsStudentsService();
        }
        return instance;
    }
}
