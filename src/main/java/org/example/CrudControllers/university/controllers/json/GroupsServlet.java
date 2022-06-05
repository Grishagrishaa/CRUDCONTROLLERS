package org.example.CrudControllers.university.controllers.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.example.CrudControllers.university.dto.GroupForBase;
import org.example.CrudControllers.university.service.GroupsService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "GroupsServlet", value = "/groups")
public class GroupsServlet extends HttpServlet {
    private ObjectMapper mapper;
    private GroupsService service;

    public GroupsServlet() {
        mapper = new ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
                                // .setSerializationInclusion(JsonInclude.Include.NON_NULL);
        service = GroupsService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");

        PrintWriter writer = resp.getWriter();
        writer.println(mapper.writeValueAsString(service.readAll()));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");

        GroupForBase group = mapper.readValue(req.getInputStream(), GroupForBase.class);
        service.create(group);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");

        GroupForBase group = mapper.readValue(req.getInputStream(), GroupForBase.class);
        service.update(group);

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");

        GroupForBase group = mapper.readValue(req.getInputStream(), GroupForBase.class);
        service.delete(group);
    }
}
