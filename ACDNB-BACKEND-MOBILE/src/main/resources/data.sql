-- DROP DATABASE IF EXISTS smashDB;
-- CREATE DATABASE acdnbDB;

-- USE acdnbDB;

-- CREATE TABLE usuario(
--     id INT AUTO_INCREMENT PRIMARY KEY,
--     nome VARCHAR(100) NOT NULL,
--     email VARCHAR(100) NOT NULL,
--     senha VARCHAR(100) NOT NULL,
--     celular VARCHAR(14),
--     data_nascimento DATE NOT NULL,
--     nome_social VARCHAR(100),
--     genero VARCHAR(20),
--     telefone VARCHAR(14),
--     cargo VARCHAR(50),
--     data_inclusao DATE NOT NULL,
--     usuario_inclusao_id INT,
--     FOREIGN KEY (usuario_inclusao_id) REFERENCES usuario(id),
--     token_recuperacao_senha VARCHAR(255),
--     token_expiracao DATETIME
-- );
--
-- CREATE TABLE endereco(
--     id INT AUTO_INCREMENT PRIMARY KEY,
--     logradouro VARCHAR(100) NOT NULL,
--     num_log VARCHAR(4) NOT NULL,
--     bairro VARCHAR(50) NOT NULL,
--     cidade VARCHAR(50) NOT NULL,
--     estado VARCHAR(2) NOT NULL,
--     cep VARCHAR(9) NOT NULL
-- );
--
-- CREATE TABLE responsavel(
--     id INT AUTO_INCREMENT PRIMARY KEY,
--     nome VARCHAR(100) NOT NULL,
--     cpf CHAR(11) NOT NULL,
--     celular VARCHAR(14),
--     email VARCHAR(100) NOT NULL,
--     rg VARCHAR(20) NOT NULL,
--     telefone VARCHAR(14),
--     nome_social VARCHAR(100),
--     genero VARCHAR(20),
--     profissao VARCHAR(50)
-- );
--
-- CREATE TABLE aluno(
--     id INT AUTO_INCREMENT PRIMARY KEY,
--     nome VARCHAR(100) NOT NULL,
--     email VARCHAR(100),
--     data_nascimento DATE NOT NULL,
--     cpf CHAR(11),
--     rg VARCHAR(20),
--     nome_social VARCHAR(100),
--     genero VARCHAR(20),
--     celular VARCHAR(14),
--     telefone VARCHAR(14),
--     nacionalidade VARCHAR(50),
--     naturalidade VARCHAR(50),
--     profissao VARCHAR(50),
--     deficiencia VARCHAR(100),
--     ativo BOOLEAN NOT NULL DEFAULT TRUE,
--     atestado BOOLEAN NOT NULL DEFAULT FALSE,
--     autorizado BOOLEAN NOT NULL DEFAULT FALSE,
--     data_inclusao DATE NOT NULL,
--     endereco_id INT,
--     usuario_inclusao_id INT,
--     nivel_id INT,
--     FOREIGN KEY (endereco_id) REFERENCES endereco(id),
--     FOREIGN KEY (usuario_inclusao_id) REFERENCES usuario(id)
--     FOREIGN KEY (nivel_id) REFERENCES nivel(id)
-- );
--
-- CREATE TABLE responsavel_aluno(
--     id INT AUTO_INCREMENT PRIMARY KEY,
--     id_responsavel INT,
--     id_aluno INT,
--     FOREIGN KEY (id_responsavel) REFERENCES responsavel(id),
--     FOREIGN KEY (id_aluno) REFERENCES aluno(id)
-- );
--
-- CREATE TABLE horario_preferencia(
--     id INT AUTO_INCREMENT PRIMARY KEY,
--     horario_aula_inicio TIME NOT NULL,
--     horario_aula_fim TIME NOT NULL,
--     data_inclusao DATE NOT NULL
-- );
--
-- CREATE TABLE lista_espera(
--     id INT AUTO_INCREMENT PRIMARY KEY,
--     nome VARCHAR(100) NOT NULL,
--     email VARCHAR(100) NOT NULL,
--     celular VARCHAR(14) NOT NULL,
--     data_nascimento DATE NOT NULL,
--     nome_social VARCHAR(100),
--     genero VARCHAR(20),
--     telefone VARCHAR(14),
--     data_inclusao DATE NOT NULL,
--     data_interesse DATE NOT NULL,
--     horario_preferencia_id INT,
--     usuario_inclusao_id INT,
--     endereco_id INT
--     FOREIGN KEY (horario_preferencia_id) REFERENCES horario_preferencia(id),
--     FOREIGN KEY (usuario_inclusao_id) REFERENCES usuario(id)
--     FOREIGN KEY (endereco_id) REFERENCES endereco(id)
-- );
--
-- CREATE TABLE valor_mensalidade(
--     id INT AUTO_INCREMENT PRIMARY KEY,
--     valor DECIMAL(10, 2) NOT NULL,
--     manual_flag BOOLEAN NOT NULL DEFAULT FALSE,
--     desconto BOOLEAN NOT NULL DEFAULT FALSE,
--     data_inclusao DATE NOT NULL
-- );
--
-- CREATE TABLE comprovante(
--     id INT AUTO_INCREMENT PRIMARY KEY,
--     nome_remetente VARCHAR(100) NOT NULL,
--     banco_origem VARCHAR(50) NOT NULL,
--     data_envio DATE NOT NULL,
--     conta_destino VARCHAR(20) NOT NULL,
--     banco_destino VARCHAR(50) NOT NULL
-- );
--
-- CREATE TABLE mensalidade(
--     id INT AUTO_INCREMENT PRIMARY KEY,
--     data_vencimento DATE NOT NULL,
--     data_pagamento DATE,
--     status_pagamento ENUM('PENDENTE', 'PAGO', 'ATRASADO') NOT NULL DEFAULT 'PENDENTE',
--     alteracao_automatica BOOLEAN NOT NULL DEFAULT FALSE,
--     forma_pagamento ENUM('DINHEIRO', 'CARTAO', 'PIX'),
--     aluno_id INT,
--     valor_mensalidade_id INT,
--     comprovante_id INT,
--     FOREIGN KEY (aluno_id) REFERENCES aluno(id),
--     FOREIGN KEY (valor_mensalidade_id) REFERENCES valor_mensalidade(id),
--     FOREIGN KEY (comprovante_id) REFERENCES comprovante(id)
-- );
--
-- CREATE TABLE nivel(
--     id INT AUTO_INCREMENT PRIMARY KEY,
--     descricao VARCHAR(50) NOT NULL
-- );
--
-- CREATE TABLE observacao(
--     id INT AUTO_INCREMENT PRIMARY KEY,
--     descricao VARCHAR(300) NOT NULL,
--     aluno_id INT,
--     FOREIGN KEY(aluno_id) REFERENCES aluno(id)
-- );

insert into usuario
    (nome, email, senha, celular, data_nascimento, data_inclusao)
values
    ('User', 'user@adm.com', '$2a$10$UM8lVJYL2yz5nhvlcD6Oh.vQkGEl/klH..96PzoVwd3HYXzvD33k.', '(11)99999-9999', '1990-01-01', now());

insert into valor_mensalidade
    (data_inclusao, valor, desconto, manual_flag)
values
    (now(), 120.00, false, false),
    (now(), 110.00, true, false);

insert into horario_preferencia
    (horario_aula_inicio, horario_aula_fim, data_inclusao)
values
    ('14:00', '17:00', now()),
    ('18:00', '20:00', now()),
    ('20:00', '22:00', now());

INSERT INTO endereco
    (logradouro, num_log, bairro, cidade, estado, cep)
VALUES
    ('Avenida Maria Dias', 776, 'Jardim São Carlos', 'Bebedouro', 'SP', '14702-248'),
    ('Rua Capitão João Carlos', 864, 'Nossa Senhora do Ó', 'São Paulo', 'SP', '02926-060'),
    ('Rua Vitório', 288, 'Jardim Julieta', 'Itapevi', 'SP', '06653-400'),
    ('Rua Felício Atala', 457, 'Jardim Flórida', 'Bauru', 'SP', '17024-650'),
    ('Rua Odilon Chaves', 742, 'Jardim Nazareth', 'São Paulo', 'SP', '08150-560'),
    ('Avenida Maria Lopes Castilho', 952, 'Vila Espírito Santo', 'Sorocaba', 'SP', '18051-410'),
    ('Rua Rio dos Cedros', 849, 'Jardim Peri', 'São Paulo', 'SP', '02679-060'),
    ('Rua Joaquim Matos', 113, 'Vila Mangalot', 'São Paulo', 'SP', '05131-010'),
    ('Rua Antônio Fernando Arruda Moraes', 670, 'Vila Arruda', 'Itapetininga', 'SP', '18212-110'),
    ('Rua João Batista Carri', 949, 'Parque Residencial Maria Stella Faga', 'São Carlos', 'SP', '13568-410'),
    ('Rua Maria de Lourdes Santos', 210, 'Conjunto Habitacional Ana Jacinta', 'Presidente Prudente', 'SP', '19064-445'),
    ('Rua do Níquel', 591, 'Prosperidade', 'São Caetano do Sul', 'SP', '09550-490'),
    ('Rua Alberto Simões', 294, 'Vila Lourdes', 'Carapicuíba', 'SP', '06397-230'),
    ('Travessa da Abolição', 128, 'Centro', 'Bragança Paulista', 'SP', '12900-240'),
    ('Rua Itália', 110, 'Vila Elisa', 'Ribeirão Preto', 'SP', '14075-460'),
    ('Avenida Professora Maria do Carmo Guimarães Pellegrini', 467, 'Retiro', 'Jundiaí', 'SP', '13209-500'),
    ('Rua Aiapaina', 262, 'Vila Gomes Cardim', 'São Paulo', 'SP', '03321-040'),
    ('Rua Cinco', 851,  'Vila José Salem', 'Itapetininga', 'SP', '18210-771'),
    ('Rua Jandira de Marins Peixoto', 102, 'Metalúrgicos', 'Osasco', 'SP', '06150-240'),
    ('Rodovia Dom Pedro I / Quilômetro 114', 708, 'Lopes', 'Valinhos', 'SP', '13273-901'),
    ('Rua Presidente Castelo Branco', 130, 'Jardim Santo Antônio', 'Salto', 'SP', '13321-473'),
    ('Avenida dos Remédios', 509, 'Remédios', 'Osasco', 'SP', '06298-007'),
    ('Rua Pilla de More Lopes', 930, 'Parque das Esmeraldas', 'Marília', 'SP', '17516-705'),
    ('Rua Coelho Neto', 302, 'Conceição', 'Diadema', 'SP', '09990-170'),
    ('Rua Dario Mecatti', 175, 'Parque Arariba', 'São Paulo', 'SP', '05778-140'),
    ('Rua Americana', 549, 'Vila Áurea (Vicente de Carvalho)', 'Guarujá', 'SP', '11454-310'),
    ('Rua Uaimaré', 237, 'Planalto Paulista', 'São Paulo', 'SP', '04068-080'),
    ('Rua José Batistella', 630, 'Jardim São José', 'Mauá', 'SP', '09340-710'),
    ('Rua Nelson Japaulo', 570, 'Residencial Jardim Vera Cruz', 'Franca', 'SP', '14407-495'),
    ('Rua do Cobre', 757, 'Vila Biasi', 'Americana', 'SP', '13466-706'),
    ('Rua Imperador Adriano', 637, 'Jardim Imperador', 'Americana', 'SP', '13479-780'),
    ('Praça Padre Léo Morin', 144, 'Cidade Patriarca', 'São Paulo', 'SP', '03556-050'),
    ('Rua Antônio Ignacio Ribeiro', 592, 'Santa Cecília', 'Barretos', 'SP', '14786-041'),
    ('Rua Luiz Paiva', 298, 'Vila Assis', 'Jaú', 'SP', '17210-180'),
    ('Rua Fernando Giacomini', 528, 'Conjunto Habitacional Maestro Júlio Ferrari', 'Lençóis Paulista', 'SP', '18684-280'),
    ('Rua Doutor João de Paula Cabral', 668, 'Recanto dos Pinheiros', 'São José dos Campos', 'SP', '12237-670'),
    ('Rua Uruguai', 385, 'Jardim Bela Vista', 'Ferraz de Vasconcelos', 'SP', '08532-410'),
    ('Rua Sapoti', 190, 'Campo Belo', 'São Paulo', 'SP', '04615-040'),
    ('Rua Centenário do Sul', 766, 'Vila Carmelina Gonçalves', 'Taboão da Serra', 'SP', '06774-085'),
    ('Rua Abílio Antônio da Cunha', 921, 'Jardim de Cresci', 'São Carlos', 'SP', '13571-617');

INSERT INTO nivel(descricao)
VALUES
    ('Iniciante'),
    ('Intermediário'),
    ('Avançado'),
    ('Profissional');
    
INSERT INTO aluno
    (nome, email, data_nascimento, cpf, rg, genero, celular, nacionalidade, naturalidade, telefone, profissao, ativo, atestado, deficiencia, autorizado, endereco_id, data_inclusao, nivel_id)
VALUES
    ('Giovanna Julia Assis', 'giovanna-assis81@gmail.com', '1979-06-09', '14389008803', '10.036.757-4', 'Feminino', '(17)98171-3456', 'Brasileira', 'Bebedouro', null, 'Engenheira de Software', true, true, 'Daltonismo', true, 1, '2025-06-01 09:00:00', 1),
    ('Yuri Enrico Thales Duarte', 'yuri_duarte@gmail.com', '1989-03-17', '08582254849', '11.874.451-3', 'Masculino', '(11)98755-1988', 'Brasileira', 'São Paulo', '(11) 2635-2938', 'Técnico de Enfermagem', true, true, null, true, 2, '2025-06-01 09:00:00', 1),
    ('Lucca Raimundo dos Santos', 'lucca.raimundo.dossantos@outlook.com', '1999-06-02', '08499656838', '26.150.856-8', 'Masculino', '(11)99574-1639', 'Brasileira', 'Itapevi', '(11) 2783-6298', 'Marceneiro', true, true, null, true, 3, '2025-06-01 09:00:00', 1),
    ('Sérgio Manuel Márcio da Mata', 'sergio_damata@hotmail.com', '1997-08-25', '18053949835', '48.206.398-1', 'Masculino', '(14)99518-6976', 'Brasileira', 'Bauru', '(14) 3630-6113', 'Estagiário', true, true, null, true, 4, '2025-06-01 09:00:00', 2),
    ('Carlos Eduardo Iago Ramos', 'carloseduardoramos@outlook.com', '1960-07-02', '07195674835', '15.526.220-8', 'Masculino', '(11)99788-5434', 'Brasileira', 'São Paulo', null, 'Designer Gráfico', true, true,'Perda auditiva parcial', true, 5, '2025-06-01 09:00:00',2),
    ('Bruna Stefany Almeida', 'bruna.stefany.almeida@gmail.com', '1990-02-06', '63192900806', '48.688.760-1', 'Feminino', '(15)98726-7162', 'Brasileira', 'Sorocaba', '(15) 2625-4607', 'Pintora', true, true, null, true, 6, '2025-06-01 09:00:00', 2),
    ('Osvaldo Joaquim Julio Lopes', 'osvaldojoaquimlopes@hotmail.com', '2002-06-06', '58373390863', '22.492.238-5', 'Masculino', '(11)98748-1114', 'Brasileira', 'São Paulo', '(11) 2534-6517', 'Professor', true, true, 'Baixa visão', true, 7, '2025-06-01 09:00:00',3),
    ('Lorena Rebeca Eliane Monteiro', 'lorena.rebeca.monteiro@hotmail.com', '1967-04-07', '39173602841', '33.813.771-3', 'Feminino', '(11)98619-7210', 'Brasileira', 'São Paulo', '(11) 2653-5312', 'Arquiteta', true, true, null, true, 8, '2025-06-01 09:00:00', 3),
    ('Julio Thomas Peixoto', 'julio_thomas_peixoto@gmail.com', '1974-06-12', '38544057829', '27.755.336-2', 'Masculino', '(15)99812-7129', 'Brasileira', 'Itapetininga', '(15) 3610-5532', 'Chef de Cozinha', true, true, null, true, 9, '2025-06-01 09:00:00', 3),
    ('Samuel Martin Fogaça', 'samuel.martin.fogaca@outlook.com', '1965-03-26', '39023853830', '42.596.317-2', 'Masculino', '(16)99122-7178', 'Brasileira', 'São Carlos', null, 'Analista de Dados', true, true, null, true, 10, '2025-06-01 09:00:00', 4);

INSERT INTO observacao(descricao, aluno_id)
VALUES
    ('Aluno 1 está com dificuldades no saque', 1),
    ('Aluno 2 tem problemas para defender', 2),
    ('Aluno 3 tem pouca resistência', 3),
    ('Aluno 4 só falta', 4);

-- Aluno 1
INSERT INTO mensalidade VALUES (false, 1, null, '2026-01-10', 1, 1, 1, '2026-01-09', 'PAGO');
INSERT INTO mensalidade VALUES (false, 1, null, '2026-02-10', 1, 2, 2, '2026-02-09', 'PAGO');
INSERT INTO mensalidade VALUES (false, 1, null, '2026-03-10', 1, 3, 2, '2026-03-09', 'PAGO');
INSERT INTO mensalidade VALUES (false, 1, null, '2026-04-10', 1, 4, 1, null, 'ATRASADO');
INSERT INTO mensalidade VALUES (false, 1, null, '2026-05-10', 1, 5, 1, null, 'PENDENTE');

-- Aluno 2
INSERT INTO mensalidade VALUES (false, 2, null, '2026-01-10', 1, 6, 1, '2026-01-09', 'PAGO');
INSERT INTO mensalidade VALUES (false, 2, null, '2026-02-10', 1, 7, 2, '2026-02-09', 'PAGO');
INSERT INTO mensalidade VALUES (false, 2, null, '2026-03-10', 1, 8, 1, null, 'ATRASADO');
INSERT INTO mensalidade VALUES (false, 2, null, '2026-04-10', 1, 9, 2, '2026-04-08', 'PAGO');
INSERT INTO mensalidade VALUES (false, 2, null, '2026-05-10', 1, 10, 1, null, 'PENDENTE');

-- Aluno 3
INSERT INTO mensalidade VALUES (false, 3, null, '2026-01-10', 1, 11, 2, '2026-01-08', 'PAGO');
INSERT INTO mensalidade VALUES (false, 3, null, '2026-02-10', 1, 12, 1, null, 'ATRASADO');
INSERT INTO mensalidade VALUES (false, 3, null, '2026-03-10', 1, 13, 2, '2026-03-07', 'PAGO');
INSERT INTO mensalidade VALUES (false, 3, null, '2026-04-10', 1, 14, 1, null, 'ATRASADO');
INSERT INTO mensalidade VALUES (false, 3, null, '2026-05-10', 1, 15, 2, '2026-05-09', 'PAGO');

-- Aluno 4
INSERT INTO mensalidade VALUES (false, 4, null, '2026-01-10', 1, 16, 1, null, 'ATRASADO');
INSERT INTO mensalidade VALUES (false, 4, null, '2026-02-10', 1, 17, 2, '2026-02-08', 'PAGO');
INSERT INTO mensalidade VALUES (false, 4, null, '2026-03-10', 1, 18, 1, null, 'ATRASADO');
INSERT INTO mensalidade VALUES (false, 4, null, '2026-04-10', 1, 19, 2, '2026-04-09', 'PAGO');
INSERT INTO mensalidade VALUES (false, 4, null, '2026-05-10', 1, 20, 1, null, 'PENDENTE');

-- Aluno 5
INSERT INTO mensalidade VALUES (false, 5, null, '2026-01-10', 1, 21, 2, '2026-01-09', 'PAGO');
INSERT INTO mensalidade VALUES (false, 5, null, '2026-02-10', 1, 22, 1, null, 'ATRASADO');
INSERT INTO mensalidade VALUES (false, 5, null, '2026-03-10', 1, 23, 2, '2026-03-08', 'PAGO');
INSERT INTO mensalidade VALUES (false, 5, null, '2026-04-10', 1, 24, 1, null, 'ATRASADO');
INSERT INTO mensalidade VALUES (false, 5, null, '2026-05-10', 1, 25, 2, '2026-05-09', 'PAGO');

-- Aluno 6
INSERT INTO mensalidade VALUES (false, 6, null, '2026-01-10', 1, 26, 1, null, 'ATRASADO');
INSERT INTO mensalidade VALUES (false, 6, null, '2026-02-10', 1, 27, 2, '2026-02-07', 'PAGO');
INSERT INTO mensalidade VALUES (false, 6, null, '2026-03-10', 1, 28, 1, null, 'ATRASADO');
INSERT INTO mensalidade VALUES (false, 6, null, '2026-04-10', 1, 29, 2, '2026-04-09', 'PAGO');
INSERT INTO mensalidade VALUES (false, 6, null, '2026-05-10', 1, 30, 1, null, 'PENDENTE');

-- Aluno 7
INSERT INTO mensalidade VALUES (false, 7, null, '2026-01-10', 1, 31, 2, '2026-01-08', 'PAGO');
INSERT INTO mensalidade VALUES (false, 7, null, '2026-02-10', 1, 32, 1, null, 'ATRASADO');
INSERT INTO mensalidade VALUES (false, 7, null, '2026-03-10', 1, 33, 2, '2026-03-09', 'PAGO');
INSERT INTO mensalidade VALUES (false, 7, null, '2026-04-10', 1, 34, 1, null, 'ATRASADO');
INSERT INTO mensalidade VALUES (false, 7, null, '2026-05-10', 1, 35, 2, '2026-05-08', 'PAGO');

-- Aluno 8
INSERT INTO mensalidade VALUES (false, 8, null, '2026-01-10', 1, 36, 1, null, 'ATRASADO');
INSERT INTO mensalidade VALUES (false, 8, null, '2026-02-10', 1, 37, 2, '2026-02-09', 'PAGO');
INSERT INTO mensalidade VALUES (false, 8, null, '2026-03-10', 1, 38, 1, null, 'ATRASADO');
INSERT INTO mensalidade VALUES (false, 8, null, '2026-04-10', 1, 39, 2, '2026-04-08', 'PAGO');
INSERT INTO mensalidade VALUES (false, 8, null, '2026-05-10', 1, 40, 1, null, 'PENDENTE');

-- Aluno 9
INSERT INTO mensalidade VALUES (false, 9, null, '2026-01-10', 1, 41, 2, '2026-01-09', 'PAGO');
INSERT INTO mensalidade VALUES (false, 9, null, '2026-02-10', 1, 42, 1, null, 'ATRASADO');
INSERT INTO mensalidade VALUES (false, 9, null, '2026-03-10', 1, 43, 2, '2026-03-08', 'PAGO');
INSERT INTO mensalidade VALUES (false, 9, null, '2026-04-10', 1, 44, 1, null, 'ATRASADO');
INSERT INTO mensalidade VALUES (false, 9, null, '2026-05-10', 1, 45, 2, '2026-05-09', 'PAGO');

-- Aluno 10
INSERT INTO mensalidade VALUES (false, 10, null, '2026-01-10', 1, 46, 1, null, 'ATRASADO');
INSERT INTO mensalidade VALUES (false, 10, null, '2026-02-10', 1, 47, 2, '2026-02-08', 'PAGO');
INSERT INTO mensalidade VALUES (false, 10, null, '2026-03-10', 1, 48, 1, null, 'ATRASADO');
INSERT INTO mensalidade VALUES (false, 10, null, '2026-04-10', 1, 49, 2, '2026-04-09', 'PAGO');
INSERT INTO mensalidade VALUES (false, 10, null, '2026-05-10', 1, 50, 1, null, 'PENDENTE');

-- LISTA ESPERA
INSERT INTO lista_espera
(nome, email, celular, data_nascimento, nome_social, genero, telefone, data_inclusao, data_interesse, horario_preferencia_id, usuario_inclusao_id, endereco_id)
VALUES
    ('João Silva Santos', 'joao.silva@email.com', '11987654321', '1995-03-15', null, 'Masculino', '11934567890', '2024-01-01 08:15:00', '2024-01-01 08:15:00', 1, 1, 11),
    ('Maria Oliveira Costa', 'maria.oliveira@email.com', '11998765432', '1998-07-22', null, 'Feminino', null, '2024-01-02 09:30:00', '2024-01-02 09:30:00', 2, 1, 12),
    ('Carlos Mendes Rocha', 'carlos.mendes@email.com', '11976543210', '2000-11-08', null, 'Masculino', '2137894561', '2024-01-03 10:45:00', '2024-01-03 10:45:00', 3, 1, 13),
    ('Ana Paula Ferreira', 'ana.paula@email.com', '11987650123', '1992-01-30', null, 'Feminino', null, '2024-01-04 11:00:00', '2024-01-04 11:00:00', 1, 1, 14),
    ('Roberto Gomes Silva', 'roberto.gomes@email.com', '11992345678', '1985-05-12', null, 'Masculino', '1125678901', '2024-01-05 13:20:00', '2024-01-05 13:20:00', 2, 1, 15),
    ('Fernanda Santos Alves', 'fernanda.santos@email.com', '11989012345', '1997-09-18', null, 'Feminino', null, '2024-01-06 14:10:00', '2024-01-06 14:10:00', 1, 1, 16),
    ('Lucas David Martins', 'lucas.martins@email.com', '11995678901', '1996-02-28', null, 'Masculino', '3132345678', '2024-01-07 15:35:00', '2024-01-07 15:35:00', 3, 1, 17),
    ('Juliana Carmo Duarte', 'juliana.carmo@email.com', '11978901234', '1994-11-05', null, 'Feminino', null, '2024-01-08 16:50:00', '2024-01-08 16:50:00', 2, 1, 18),
    ('Pedro Oliveira Lima', 'pedro.oliveira@email.com', '11982345678', '1999-04-10', null, 'Masculino', '8534567890', '2024-01-09 17:25:00', '2024-01-09 17:25:00', 1, 1, 19),
    ('Camila Ribeiro Costa', 'camila.ribeiro@email.com', '11990123456', '1993-08-25', null, 'Feminino', null, '2024-01-10 18:40:00', '2024-01-10 18:40:00', 3, 1, 20),
    ('Lucas Pereira Costa', 'lucas.costa@email.com', '11981234567', '1992-07-21', null, 'Masculino', '1134567890', '2024-01-11 19:55:00', '2024-01-11 19:55:00', 1, 1, 21),
    ('Theo Daniel Ferreira', 'theo.ferreira@gmail.com', '11982342127', '1998-05-15', null, 'Masculino', '1134269890', '2025-05-18 15:22:31', '2025-05-18 15:22:31', 1, 1, 22),
    ('Tiago Henrique Figueiredo', 'tiagohenriquefigueiredo@stetnet.com.br', '11995866242', '1947-03-06', null, 'Masculino', '1125573509', now(), now(), 3, 1, 23),
    ('Breno Martin Alves', 'breno.martin.alves@mpv.org.br', '11991936628', '1955-03-15', null, 'Masculino', '1125880042', now(), now(), 2, 1, 24),
    ('Marlene Cecília Drumond', 'marlene_drumond@bemdito.com.br', '14991279264', '1968-03-01', null, 'Feminino', '1427142048', now(), now(), 2, 1, 25),
    ('Carolina Regina Raimunda Silveira', 'carolina_regina_silveira@bighost.com.br', '11988165623', '1977-03-03', null, 'Feminino', '1136294975', now(), now(), 3, 1, 26),
    ('Felipe Vitor Gonçalves', 'felipevitorgoncalves@obrativaengenharia.com.br', '11981923204', '1947-04-19', null, 'Masculino', '1135287330', now(), now(), 2, 1, 27),
    ('Benedita Fátima Jesus', 'benedita_jesus@badauy.com', '13993101636', '1956-02-07', null, 'Feminino', '1327710696', now(), now(), 1, 1, 28),
    ('Sophie Clarice Alícia Baptista', 'sophie.clarice.baptista@projetochama.com.br', '11983464683', '1958-02-23', null, 'Feminino', '1136790153', now(), now(), 2, 1, 29),
    ('Giovana Fátima Ferreira', 'giovana_fatima_ferreira@owl-ti.com.br', '11989558752', '1983-03-07', null, 'Feminino', '1125185762', now(), now(), 1, 1, 30),
    ('Leonardo Carlos Eduardo Castro', 'leonardo.carlos.castro@gmx.ca', '16993703822', '1965-03-06', null, 'Masculino', '1627766971', now(), now(), 3, 1, 31),
    ('Vanessa Maria Gabriela Nogueira', 'vanessa_maria_nogueira@hotmaill.com', '19981970663', '1992-02-23', null, 'Feminino', '1936378906', now(), now(), 2, 1, 32),
    ('Ruan Kauê Assis', 'ruan-assis88@orteca.com.br', '19993922194', '1967-04-02', null, 'Masculino', '1925433912', now(), now(), 2, 1, 33),
    ('Edson Vitor da Rosa', 'edson_vitor_darosa@phocus.com.br', '11996653691', '1999-02-03', null, 'Masculino', '1138089049', now(), now(), 3, 1, 34),
    ('Beatriz Julia Yasmin Porto', 'beatriz_porto@sp.senac.com.br', '17997943449', '1969-02-20', null, 'Feminino', '1739679366', now(), now(), 1, 1, 35),
    ('Diogo Rafael Lima', 'diogo_rafael_lima@pig.com.br', '14984107600', '1967-02-15', null, 'Masculino', '1425050098', now(), now(), 2, 1, 36),
    ('Jennifer Isis Nair Monteiro', 'jennifer-monteiro91@sp.gov.br', '14989146809', '1981-02-15', null, 'Feminino', '1435044497', now(), now(), 1, 1, 37),
    ('Danilo Yuri Luiz Nunes', 'danilo_nunes@ibest.com.br', '12989683519', '1999-03-21', null, 'Masculino', '1227843658', now(), now(), 2, 1, 38),
    ('Raimunda Sônia Jennifer Melo', 'raimunda_sonia_melo@dominiozeladoria.com.br', '11993298171', '1984-02-24', null, 'Feminino', '1128988903', now(), now(), 3, 1, 39),
    ('Luzia Andrea Pires', 'luzia-pires70@clinicasilhouette.com.br', '11988757582', '2000-03-11', null, 'Feminino', '1137768048', now(), now(), 2, 1, 40);