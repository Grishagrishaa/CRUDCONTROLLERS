package org.example.CrudControllers.university.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.io.Serializable;
import java.util.Objects;

/**
 * 	1.1 Порядковый номер (int)
 * 	1.2 Имя (Строка размером от 3 до 10 русских символов)
 * 	1.3 возраст (7-17)
 * 	1.4 оценка(0.0-10.0)
 * 	1.5 признак участия в олимпиадах (bool).
 */
@JsonDeserialize(builder = Student.Builder.class)
public class Student implements Serializable {
    private static final long serialVersionUID = 2L;

    private final Long id;
    private final String name;
    private final Integer age;
    private final Double score;
    private final Boolean olympicGamer;

    private Student(Long id, String name, Integer age, Double score, Boolean olympicGamer){
        this.id = id;
        this.name = name;
        this.age = age;
        this.score = score;
        this.olympicGamer = olympicGamer;
    }


    @JsonPOJOBuilder(withPrefix = "set")
    public static class Builder{
        private Long id;
        private String name;
        private Integer age;
        private Double score;
        private Boolean olympicGamer;

        private Builder(){
        }

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;

        }

        public Builder setAge(Integer age) {
            this.age = age;
            return this;

        }

        public Builder setScore(Double score) {
            this.score = score;
            return this;
        }

        public Boolean isOlympicGamer() {
            return olympicGamer;
        }

        public Builder setOlympicGamer(Boolean olympicGamer) {
            this.olympicGamer = olympicGamer;
            return this;
        }

        public static Builder create(){
            return new Builder();
        }

        public Student build(){
            return new Student(id, name, age, score, olympicGamer);
        }
    }




    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Double getScore() {
        return score;
    }

    public Boolean isOlympicGamer() {
        return olympicGamer;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;
        return age == student.age &&
                olympicGamer == student.olympicGamer &&
                Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, olympicGamer);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", score=" + score +
                ", olympicGamer=" + olympicGamer +
                '}';
    }
}
