package org.example.CrudControllers.university.api;

import org.example.CrudControllers.university.dto.GroupForBase;
import org.example.CrudControllers.university.dto.Student;

import java.sql.ResultSet;
import java.util.List;

public interface IGroupsDao extends AutoCloseable {
    /**
     * just read all entities from certain table
     * @return
     */
    List<IGroup> readAll();

    /**
     *
     *
     * @param group - object we deserialize from json to put in DATABASE table
     */
    void create(GroupForBase group);

    /**
     *
     *
     *
     * @param group - object we deserialize from json,\
     *             but with new values in field - name, id generating by db
     */
    void update(GroupForBase group);

    /**
     *
     *
     * @param id - group id, by which we delete group
     */
    void delete(Long id);

}
