package org.example.CrudControllers.university.dao.sqlUtils;

public class GroupsStudentsRequests {
    public static String getGroupsId = "" +
            "SELECT DISTINCT bstu.cross.group_id\n" +
            "FROM bstu.cross;";

    public static String getIdByName = "" +
            "SELECT bstu.groups.group_id\n" +
            "FROM bstu.groups\n" +
            "WHERE bstu.groups.group_name = ?";

    public static String getGroupWithStudents = "" +
            "SELECT bstu.cross.group_id, group_name, bstu.students.student_id, name, age,score,olympic_gamer\n" +
            "FROM bstu.cross INNER JOIN bstu.students ON bstu.cross.student_id = bstu.students.student_id \n" +
            "INNER JOIN bstu.groups ON bstu.cross.group_id = bstu.groups.group_id\n" +
            "WHERE bstu.cross.group_id = ?";

    public static String addStudentsToGroup = "" +
            "INSERT INTO bstu.cross(group_id, student_id)\n" +
            "VALUES(?,? )";

    public static String deleteStudentsFromGroup = "" +
            "DELETE FROM bstu.cross\n" +
            "WHERE group_id = ? AND student_id = ?";
}
