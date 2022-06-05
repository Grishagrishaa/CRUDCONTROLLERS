package org.example.CrudControllers.university.dao.sqlUtils;

public class GroupsRequests {
    public static String getAll = "SELECT * FROM bstu.groups";

    public static String create = "" +
            "INSERT INTO bstu.groups(group_name) " +
            "VALUES(?); ";

    public static String update = "" +
            "UPDATE bstu.groups\n" +
            "SET group_name = ?\n" +
            "WHERE group_id = ?";

    public static String delete = "" +
            "DELETE FROM bstu.groups\n" +
            "WHERE group_id = ?;";
}
