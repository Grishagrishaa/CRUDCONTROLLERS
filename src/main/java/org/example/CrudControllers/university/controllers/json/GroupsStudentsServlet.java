package org.example.CrudControllers.university.controllers.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.example.CrudControllers.university.dto.Group;
import org.example.CrudControllers.university.service.GroupsStudentsService;
import org.example.CrudControllers.university.service.StudentsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "GroupsStudentsServlet", urlPatterns = "/groupsStudents")
public class GroupsStudentsServlet extends HttpServlet {
    private ObjectMapper mapper;
    private GroupsStudentsService service;

    public GroupsStudentsServlet() {
        this.mapper = new ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        // .setSerializationInclusion(JsonInclude.Include.NON_NULL);
        this.service = GroupsStudentsService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");

        PrintWriter writer = resp.getWriter();
        writer.println(mapper.writeValueAsString(service.readAll()));
    }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");

        service.update(mapper.readValue(req.getInputStream(), Group.class));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");

        service.delete(mapper.readValue(req.getInputStream(), Group.class));}
}
