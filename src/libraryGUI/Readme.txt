Read me file........
!!!!!....................Important info.............................!!!!!!
.......Follow the exact way as defined as follows......
.......Dont forget to put semicolumn after every query.....
!!!!!....................Important info.............................!!!!!!

Recommended supporting softwares..

     1: Java JRE 1.7 version software

     2: MySQL software
........................................................................................................


Step 1: Install any version of MySQL. Follow the configuration file of the MySQL to complete the installation 
        Make sure that you set MySQL port number as '3306'
        without any error ---(MySQL version 5.1 is preffered)--

Step 2: Open MySQL command Line Clint

Step 3: Type in the command prompt as follows

Step 4: create database if not exists library_books;

Step 5: press enter
             (If the typed query is correct, it will give Query OK... If it says error, then check your typed query)

Step 6: use library_books; (it will give: Database changed)

Step 7: press enter 
            (If the typed query is correct, it will give Query OK... If it says error, then check your typed query)

Step 8: create table shelf (book_id int(5),book_name varchar(255), author varchar(255), 
             department varchar(255), pieces int(10));

Step 9: press enter

Step 10: create table student (pnr int(5), name varchar(255), book_inhand varchar(255), status varchar(20));

Step 11: press enter

Step 12: create database if not exists oneeachdb;

Step 13: press enter

Step 14: \q + enter to shutdown the my sql command prompt

!!!!!.....................Important info.....................!!!!!!
...........Don't forget to restart the application..........

Created: 10:42 PM 11/19/2015



