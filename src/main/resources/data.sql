INSERT INTO user (first_name, last_name, email, password) VALUES ('Rita', 'Brown', 'rita@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO user (first_name, last_name, email, password) VALUES ('Fabiana', 'Green', 'fabiana@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');

INSERT INTO role (authority) VALUES ('ROLE_OPERATOR');
INSERT INTO role (authority) VALUES ('ROLE_ADMIN');

INSERT INTO user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO user_role (user_id, role_id) VALUES (2, 1);
INSERT INTO user_role (user_id, role_id) VALUES (2, 2);

INSERT INTO medium (first_name, last_name) VALUES ('Ananda', 'Silva');
INSERT INTO medium (first_name, last_name) VALUES ('Patricia', 'Souza');
INSERT INTO medium (first_name, last_name) VALUES ('Helena', 'Carter');

INSERT INTO psychography (full_name, date, text, created_At, medium_id, user_id) VALUES ('Silvia - uma irmã de todos vocês', '2022-08-18', 'Olá, queridos irmão de caminhada! Hoje é mais um dia de trabalho na vinha do Mestre Jesus. Aqui não é necessário enviar o currículo e ser aprovado. Basta ter coragem de arregaçar as mangas, pois o trabalho nunca falta. Hoje eu sinto gratidão e bastante esperança de dias melhores. Eu me chamo Silvia e fui muito solitária quando vivia entre vocês. Eu mesma me isolei do mundo, tinha família pequena e ficava muito sozinha. Às vezes saía e ficava observando o comportamento de outras pessoas. Voltava para casa ao final do dia e ficava nos meus estudos solitários. Demorei alguns anos para compreender que o sentido da vida está na nossa interação com os outros. Um dia uma conhecida pediu minha ajuda com algumas doações para um Lar onde viviam pessoas abandonadas pela família por terem alguma condição física e viverem de forma precária. Teve um evento de arrecadação de fundos e acabei auxiliando com algumas atividades. Passei a observar o comportamento humano daquele loca que era muito diferente. Aquelas pessoas eram esquecidas no mundo e careciam de uma atenção especial. Eu não era muito diferente delas - pensei - pois também ficava sozinha - convivia com meus pensamentos. Passei boa parte de minha vida naquele local, onde podia conversar e interagir com tantas pessoas e suas histórias. Montamos um projeto voltado para saúde mental, onde mesmo os desfavorecidos podem entender que também possuem o seu lugar no mundo. Hoje, do lado de cá, nós podemos visualizar as cores e ondas dos pensamentos das pessoas, e a emanação de sua energia. Os traumas do passado, as doenças modernas como depressão, a falta da fraternidade têm agravado esses isolamentos mentais. Dias melhores virão quando todos olharmos ao nosso redor para interagir e auxiliar um irmão necessitado, no espírito da verdadeira fraternidade. Não percam a esperança e não deixem de trabalhar na vinha do Mestre. Estaremos sempre ao lado dos irmãos necessitados, influenciando para o Bem. Graças a Deus.', NOW(),1, 1);

INSERT INTO message (text, date, full_name, created_At, user_id) VALUES ('Apóie teu irmão - Apóie teu irmão em crise, atingido pela doença ou pelo desemprego. Uma cesta básica doada, todo o mês, renovará o milagre da multiplicação de pães feito por Jesus.', '2022-08-18', 'Bezerra de Menezes', NOW(), 1);
INSERT INTO message (text, date, full_name, created_At, user_id) VALUES ('Entender - Quando a dor aparece, ei-la que vem por sombra. Qualquer valor externo é como se fugisse. Nas entranhas da alma, tudo se desarvora. O coração anseia em convulsões de angústia. Nesses instantes, ora e terás luz em ti. É que na luz da dor, Deus nos faz entender.', '2022-08-18', 'Bezerra de Menezes', NOW(), 1);

INSERT INTO module (name) VALUES ('Módulo 2');

INSERT INTO lecture (title, uri, date, created_At, medium_id, user_id) VALUES ('15_08_22 Rita Navarro', 'https://www.youtube.com/watch?v=nNeS4gUeSCw&t=64s', '2022-08-15', NOW(), 1, 1);
INSERT INTO lecture (title, uri, date, created_At, medium_id, user_id) VALUES ('01_08_22 Fabio', 'https://www.youtube.com/watch?v=1vtwV1PDAJk', '2022-08-01', NOW(), 1, 1);

INSERT INTO class (title, uri, date, created_At, module_id, medium_id, user_id) VALUES ('24ª Aula – Jesus; Anunciação, Nascimento, Família, Vida Dos 12 Aos 30', 'https://www.youtube.com/watch?v=pmXuNx0UptQ', '2022-08-16', NOW(), 1, 1, 1);
INSERT INTO class (title, uri, date, created_At, module_id, medium_id, user_id) VALUES ('23ª Aula – Dependência química – Atire a primeira pedra', 'https://www.youtube.com/watch?v=cjzSi1KFfOM', '2022-08-09', NOW(), 1, 1, 1);
