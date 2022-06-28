CREATE TABLE employee (
      id           BIGSERIAL                PRIMARY KEY,
      document     character varying(255)      NOT NULL,
      name         character varying(255)      NOT NULL,
      born_at      date                        NOT NULL,
      active       boolean                     NOT NULL,
      version      bigint                      NOT NULL DEFAULT 1,
      created_at   timestamp without time zone NOT NULL,
      updated_at   timestamp without time zone NOT NULL
);

CREATE TABLE employee_role(
    id          BIGSERIAL  PRIMARY KEY,
    employee_id BIGINT     NOT NULL,
    role character varying(50) NOT NULL
);

ALTER TABLE employee_role
    ADD CONSTRAINT fk_employee_role_to_employee FOREIGN KEY (employee_id) REFERENCES employee (id);