CREATE TABLE employee (
    id           bigint                      NOT NULL,
    document     character varying(255)      NOT NULL,
    name         character varying(255)      NOT NULL,
    born_at      date                        NOT NULL,
    active       boolean                     NOT NULL,
    version      bigint                      NOT NULL,
    created_at   timestamp without time zone NOT NULL,
    updated_at   timestamp without time zone NOT NULL
);

ALTER TABLE ONLY employee
    ADD CONSTRAINT employee_pkey     PRIMARY KEY (id);

ALTER TABLE employee ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME employee_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
