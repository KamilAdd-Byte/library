drop table if exists user_lending;
create table user_lending
(
    id           int primary key,
    firstName    varchar(30)  not null,
    lastName     varchar(25)  not null,
    email        varchar(20) not null
)
