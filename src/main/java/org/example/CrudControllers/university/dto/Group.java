package org.example.CrudControllers.university.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import org.example.CrudControllers.university.api.IGroup;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonDeserialize(builder = Group.Builder.class)
public class Group implements Serializable, IGroup {
    private static final long serialVersionUID = 3L;
    private final String groupName;
    private final List<Student> students;

    public Group(String groupName, List<Student> students) {
        this.groupName = groupName;
        this.students = students;
    }


    @JsonPOJOBuilder(withPrefix = "set")
    public static class Builder{
        private String groupName;
        private List<Student> students;

        private Builder(){
            students = new ArrayList<>();
        }

        public Builder setGroupName(String groupName) {
            this.groupName = groupName;
            return this;
        }

        public Builder setStudents(List<Student> students) {
            this.students = students;
            return this;
        }

        public Builder addStudents(Student student) {
            students.add(student);
            return this;
        }

        public static Builder create(){
            return new Builder();
        }

        public Group build(){
            return new Group(groupName, students);
        }

    }

    public String getGroupName() {
        return groupName;
    }

    public List<Student> getStudents() {
        return students;
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupName='" + groupName + '\'' +
                ", students=" + students +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Objects.equals(groupName, group.groupName) && Objects.equals(students, group.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupName, students);
    }
}
