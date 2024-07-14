create table topicos (
    id bigint not null auto_increment,
    titulo varchar(200) not null,
    mensaje varchar(500) not null unique,
    fecha_creacion datetime not null,
    estatus varchar(100) not null,
    curso_id bigint not null,
    usuario_id bigint not null,
    respuestas varchar(250),

    primary key (id),
    foreign key (curso_id) references cursos(id),
    foreign key (usuario_id) references usuarios(id)
);
