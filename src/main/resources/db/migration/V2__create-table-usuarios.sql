create table usuarios(

    id bigint not null auto_increment,
    name varchar(200) not null,
    cursos varchar(500) not null unique,


    primary key(id)

);