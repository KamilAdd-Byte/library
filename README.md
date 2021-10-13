# Library App - home books collection at lending it friends

# Table of contents
* [Description](#description)
* [Database use](#H2)
* [Technologies](#technologies)
* [Functionalities](#functionalities)
* [Deploy](#deploy)


# Description

Library Home it's simple application. User add book, delete book and update book in library. Save book add to data base (H2 in file). 
The form save book in Library Home Management is validated. Incorrect data it's not entred to data base.

Actual list book is on front page. List book is located in responsive table (Thymleaf and Bootstrap).

The user can borrow his books to his friends (database relation @ManyToOne) 


# H2:

This application use H2 database in file.

# Technologies:

- backend: Spring-boot, Java 11, Lombok, Thymeleaf.
  
- frontend: Bootstrap 4, Html5, Css.

# Functionalities:

- adding a book: title, author;
- book modification - CRUD;
- lending book by user, creates users and show list them;
- sorting available by clicking on the TITLE or AUTHOR
- returning books when a friend returns them
- manage your friends list 
- search book by title with your collections;
- list of all operations;

# Deploy

Deploy in Heroku: **https://fast-reef-35901.herokuapp.com/**
