CREATE TABLE pessoa(
		codigo SERIAL PRIMARY KEY,
		nome TEXT NOT NULL,
		ativo BOOLEAN,
		logradouro TEXT NOT NULL,
		complemento TEXT,
		bairro TEXT,
		cep TEXT NOT NULL,
		cidade TEXT,
		estado TEXT ,
		numero TEXT NOT NULL
);

ALTER TABLE PESSOA ALTER COLUMN ativo
SET DEFAULT TRUE;

INSERT INTO pessoa(nome,logradouro,complemento,bairro,cep,cidade,estado,numero) 
values('davi holanda','rua santiago','SN','serrinha','60741510','fortaleza','ceara','279');
