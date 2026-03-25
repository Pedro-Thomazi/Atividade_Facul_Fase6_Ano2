-- Sequence e trigger para users
CREATE SEQUENCE seq_users START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;

CREATE TABLE users (
  id NUMBER PRIMARY KEY,
  name VARCHAR2(100) NOT NULL,
  email VARCHAR2(100) NOT NULL,
  password VARCHAR2(100) NOT NULL,
  ativo NUMBER(1) DEFAULT 1 NOT NULL,
  CONSTRAINT users_email_unq UNIQUE (email)
);

CREATE OR REPLACE TRIGGER trg_users_bi
BEFORE INSERT ON users
FOR EACH ROW
WHEN (NEW.id IS NULL)
BEGIN
  SELECT seq_users.NEXTVAL INTO :NEW.id FROM dual;
END;
/

-- Sequence e trigger para treinamento
CREATE SEQUENCE seq_treinamento START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;

CREATE TABLE treinamento (
  id NUMBER PRIMARY KEY,
  titulo VARCHAR2(255) NOT NULL,
  descricao VARCHAR2(255)
);

CREATE OR REPLACE TRIGGER trg_treinamento_bi
BEFORE INSERT ON treinamento
FOR EACH ROW
WHEN (NEW.id IS NULL)
BEGIN
  SELECT seq_treinamento.NEXTVAL INTO :NEW.id FROM dual;
END;
/

-- Sequence e trigger para registro_conclusao
CREATE SEQUENCE seq_registro_conclusao START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;

CREATE TABLE registro_conclusao (
  id NUMBER PRIMARY KEY,
  users_id NUMBER NOT NULL,
  treinamento_id NUMBER NOT NULL,
  data_conclusao TIMESTAMP DEFAULT SYSTIMESTAMP,
  nota_avaliacao NUMBER(10,2),
  CONSTRAINT fk_reg_users FOREIGN KEY (users_id) REFERENCES users(id),
  CONSTRAINT fk_reg_treinamento FOREIGN KEY (treinamento_id) REFERENCES treinamento(id)
);

CREATE OR REPLACE TRIGGER trg_registro_conclusao_bi
BEFORE INSERT ON registro_conclusao
FOR EACH ROW
WHEN (NEW.id IS NULL)
BEGIN
  SELECT seq_registro_conclusao.NEXTVAL INTO :NEW.id FROM dual;
END;
/

SELECT * FROM users;
