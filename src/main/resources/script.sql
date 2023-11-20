create database KnowledgeBaseEditor;
use KnowledgeBaseEditor;

create table rules(
id int auto_increment primary key,
fact varchar(20) not null,
result varchar(20) not null

);

insert into rules(fact,result) values("if x and y and c","then pasta or mike");


