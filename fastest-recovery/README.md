# Fastest Recovery Project - CMPE 172 - Spring 2018
## Setting up the database
- Open terminal and run mysql 
    
      $ mysql -u root 
- Create a new database called 'sakila'
  
      mysql> CREATE DATABASE sakila;
- Import schema from the .sql file

      mysql> source sakila-mv-schema.sql;
- Import data from the .sql file

      mysql> source sakila-mv-data.sql;
- Create a new database called 'employees'

      mysql> CREATE DATABASE employees;
- Import data from the .sql file

      mysql> source employees.sql;
- Verify that the databases were succesfully created

      mysql> show databases;
- Expected results: 

        +--------------------+
        | Database           |
        +--------------------+
        | employees          |
        | sakila             |
        +--------------------+
- Navigate on the 'sakila' database and see its data:

      mysql> USE sakila;
      mysql> SHOW tables;
### Expected results:

        +----------------------------+
        | Tables_in_sakila           |
        +----------------------------+
        | actor                      |
        | actor_info                 |
        | address                    |
        | category                   |
        | city                       |
        | country                    |
        | customer                   |
        | customer_list              |
        | film                       |
        | film_actor                 |
        | film_category              |
        | film_list                  |
        | film_text                  |
        | inventory                  |
        | language                   |
        | nicer_but_slower_film_list |
        | payment                    |
        | rental                     |
        | sales_by_film_category     |
        | sales_by_store             |
        | staff                      |
        | staff_list                 |
        | store                      |
        +----------------------------+
        23 rows in set (0.00 sec)
- Navigate on the 'employees' database and see its data:

      mysql> USE employees;
      mysql> SHOW tables;
### Expected results:

        +----------------------+
        | Tables_in_employees  |
        +----------------------+
        | current_dept_emp     |
        | departments          |
        | dept_emp             |
        | dept_emp_latest_date |
        | dept_manager         |
        | employees            |
        | salaries             |
        | titles               |
        +----------------------+
        8 rows in set (0.00 sec)
