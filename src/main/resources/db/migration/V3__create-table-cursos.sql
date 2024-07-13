create table cursos(

    id bigint not null auto_increment,
    name varchar(200) not null,
    category varchar(500) not null unique,


    primary key(id)

);