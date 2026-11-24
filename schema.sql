CREATE DATABASE IF NOT EXISTS petcare CHARACTER SET utf8mb4;
USE petcare;

CREATE TABLE IF NOT EXISTS tutor (
                                     id        INT          NOT NULL AUTO_INCREMENT,
                                     nome      VARCHAR(100) NOT NULL,
    cpf       VARCHAR(11)  NOT NULL,
    telefone  VARCHAR(20)  DEFAULT NULL,
    cep       VARCHAR(8)   NOT NULL,
    endereco  VARCHAR(150) NOT NULL,
    cidade    VARCHAR(100) NOT NULL,
    estado    VARCHAR(2)   NOT NULL,
    PRIMARY KEY (id)
    ) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS pet (
                                   id        INT          NOT NULL AUTO_INCREMENT,
                                   nome      VARCHAR(100) NOT NULL,
    idade     INT          NOT NULL,
    especie   VARCHAR(50)  NOT NULL,
    raca      VARCHAR(100) DEFAULT NULL,
    tutor_id  INT          NOT NULL,
    sexo      VARCHAR(10)  NOT NULL DEFAULT 'N/I',
    PRIMARY KEY (id),
    KEY idx_pet_tutor (tutor_id),
    CONSTRAINT fk_pet_tutor FOREIGN KEY (tutor_id) REFERENCES tutor (id)
    ) ENGINE=InnoDB;