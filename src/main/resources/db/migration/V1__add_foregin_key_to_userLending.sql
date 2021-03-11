alter table UserLending
    add column id_book int;
alter table UserLending
    add foreign key (id_book) references Book (id);
