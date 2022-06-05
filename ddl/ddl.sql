CREATE DATABASE university
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'ru_RU.UTF-8'
    LC_CTYPE = 'ru_RU.UTF-8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = 15;

COMMENT ON DATABASE university
    IS 'belarussian state technological university';



CREATE SCHEMA IF NOT EXISTS bstu
    AUTHORIZATION postgres;



CREATE TABLE IF NOT EXISTS bstu.students
(
    student_id bigint NOT NULL DEFAULT nextval('bstu.students_id_seq'::regclass),
    name character varying(30) COLLATE pg_catalog."default",
    age integer,
    score real,
    olympic_gamer boolean,
    CONSTRAINT students_pkey PRIMARY KEY (student_id)
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS bstu.students
    OWNER to postgres;



CREATE TABLE IF NOT EXISTS bstu.groups
(
    group_id bigint NOT NULL DEFAULT nextval('bstu.groups_id_seq'::regclass),
    group_name character varying(30) COLLATE pg_catalog."default",
    CONSTRAINT groups_pkey PRIMARY KEY (group_id)
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS bstu.groups
    OWNER to postgres;


CREATE TABLE IF NOT EXISTS bstu."cross"
(
    group_id bigint,
    student_id bigint,
    CONSTRAINT "duplicateError" UNIQUE (group_id, student_id),
    CONSTRAINT "duplicateInGroupError" UNIQUE (student_id),
    CONSTRAINT cross_group_id_fkey FOREIGN KEY (group_id)
    REFERENCES bstu.groups (group_id) MATCH FULL
    ON UPDATE CASCADE
    ON DELETE CASCADE,
    CONSTRAINT cross_student_id_fkey FOREIGN KEY (student_id)
    REFERENCES bstu.students (student_id) MATCH FULL
    ON UPDATE CASCADE
    ON DELETE CASCADE
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS bstu."cross"
    OWNER to postgres;