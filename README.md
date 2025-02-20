
# 331-DB 

Experimenting with AdventureWorks on your local system  + JDBC connectivity, JAVA GUI 

1. Download AdventureWorks database from

   [https://sourceforge.net/projects/awmysql/](git@github.com:tamu-edu/clen-csce-choe-331-db.git)

   and unzip it to produce AWBackup.sql. 

   AdventureWorks is an example database made public by Microsoft.

2. Create a docker directory with the docker-compose.yml file provided here.

   * Note: username root, password: password 
   * You MUST change the above in the docker-compose.yml file for security.
   * In the examples below, I will assume that the default password is used.
   * You will use these credentials to connect to your MySQL server
   * Move the AWBackup.sql file to this directory.

   *  See https://medium.com/@chrischuck35/how-to-create-a-mysql-instance-with-docker-compose-1598f3cc1bee 

3. Start the container

   * change MySQL version in docker-compose.yml as necessary.
     - default is mysql:5.7
     - change to mysql:8.4.3
   
   * run (run with sudo for Linux/MacOS)

     ```
     docker-compose up -d
     ```

4. Populate your MySQL server with the data. You only need to do this once.

   run (run with sudo for Linux/MacOS)

   ```
   mysql --host 127.0.0.1 --port 3306 -u root  -ppassword < AWBackup.sql 
   ```

   this will take a while, and once it is done, you'll be dropped back to your prompt

5. Login to your MySQL server

   Important: first, install the mysql client if you have not installed it.

   ```
   Ubuntu:     sudo apt install mysql-client
   CentOS/RHL: sudo dnf install mysql
   MacOS:      brew install mysql-client 
   ```

   run

   ```
   mysql --host 127.0.0.1 --port 3306 -u root  -ppassword
   ```
6. Try out some commands

   in the MySQL prompt, type

   ```
   use adventureworks; 
   show tables;
   show columns from customer;
   select * from customer where AccountNumber="AW00029475";
   ```

7. Stop the container when you're done

   run

   ```
   docker-compose stop 
   ```

8. When you start the container again later and connect to your MySQL server,
you will find that the adventureworks DB is alive and well. Don't forget to do

   ```
   use adventureworks;
   ```

