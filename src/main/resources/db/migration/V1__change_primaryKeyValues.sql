drop table if exists book;
create table book
(
    id           int primary key,
    title        varchar(25)  not null,
    author       varchar(25)  not null,
    description  varchar(100) not null,
    isbn         varchar(13)  not null,
    localization int
)
alter table book
    add column created_on datetime null;
alter table book
    add column updated_on datetime null;
