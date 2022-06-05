package org.example.CrudControllers.university.dao;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TestMain {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
//        String a = "[\n" +
//                "      {\n" +
//                "        \"id\": 25,\n" +
//                "        \"name\": \"Lesha\",\n" +
//                "        \"age\": 18,\n" +
//                "        \"score\": 8,\n" +
//                "        \"olympicGamer\": false\n" +
//                "      },\n" +
//                "      {\n" +
//                "        \"id\": 26,\n" +
//                "        \"name\": \"Grisha\",\n" +
//                "        \"age\": 18,\n" +
//                "        \"score\": 6.5,\n" +
//                "        \"olympicGamer\": false\n" +
//                "      }\n" +
//                "    ]";
       GroupsStudentsDao dao = GroupsStudentsDao.getInstance();
       String b = "{\n" +
               "    \"group_name\": \"Маркетинг\",\n" +
               "    \"students\": [\n" +
               "      {\n" +
               "        \"id\": 29,\n" +
               "        \"name\": \"Test\",\n" +
               "        \"age\": 18,\n" +
               "        \"score\": 10,\n" +
               "        \"olympic_gamer\": true\n" +
               "      },\n" +
               "      {\n" +
               "        \"id\": 30,\n" +
               "        \"name\": \"Test1\",\n" +
               "        \"age\": 18,\n" +
               "        \"score\": 10,\n" +
               "        \"olympic_gamer\": true\n" +
               "      }\n" +
               "    ]\n" +
               "  }";

        System.out.println(dao.getIdByName("ЭУП"));

    }
}
