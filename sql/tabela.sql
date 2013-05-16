CREATE TABLE medico(
crm VARCHAR(20) UNIQUE NOT NULL,
nome VARCHAR(150) NOT NULL,
email VARCHAR(150) NOT NULL,
senha VARCHAR(50) NOT NULL,
CONSTRAINT pk_medico PRIMARY KEY(crm)
);

CREATE TABLE paciente(
id SERIAL NOT NULL,
nome VARCHAR(150) NOT NULL,
chaveMedico VARCHAR(150),
chaveFamilia VARCHAR(150),
login varchar(50) UNIQUE NOT NULL,
senha varchar(50) NOT NULL,
email varchar(150) UNIQUE NOT NULL,
CONSTRAINT pk_paciente PRIMARY KEY(id)
);

CREATE TABLE status(
id SERIAL NOT NULL,
paciente int NOT NULL,
medico VARCHAR(20) NOT NULL,
descricao VARCHAR(300) NOT NULL,
data date DEFAULT(current_date),
hora time DEFAULT(current_time),
CONSTRAINT pk_status PRIMARY KEY(id),
CONSTRAINT fk_status_medico FOREIGN KEY(medico) REFERENCES medico(crm),
CONSTRAINT fk_status_paciente FOREIGN KEY(paciente) REFERENCES paciente(id)
);

CREATE TABLE autorizacoes(
crm VARCHAR(20) UNIQUE NOT NULL,
chaveAutorizacao VARCHAR(150)
);

CREATE TABLE administrador(
usuario VARCHAR(100) UNIQUE NOT NULL,
senha VARCHAR(100),
CONSTRAINT pk_administrador PRIMARY KEY(usuario)
);