## Use to run mysql db docker image, optional if you're not using a local mysqldb
# docker run --name mysqldb -p 3306:3306 -e MYSQL_ALLOW_EMPTY_PASSWORD=yes -d mysql

# connect to mysql and run as root user
#Create Databases
CREATE DATABASE dev_db;
CREATE DATABASE prod_db;

#Create database service accounts
CREATE USER 'dev_user'@'localhost' IDENTIFIED BY 'password';
CREATE USER 'prod_user'@'localhost' IDENTIFIED BY 'password';
CREATE USER 'dev_user'@'%' IDENTIFIED BY 'password';
CREATE USER 'prod_user'@'%' IDENTIFIED BY 'password';

#Database grants
GRANT SELECT ON dev_db.* to 'dev_user'@'localhost';
GRANT INSERT ON dev_db.* to 'dev_user'@'localhost';
GRANT DELETE ON dev_db.* to 'dev_user'@'localhost';
GRANT UPDATE ON dev_db.* to 'dev_user'@'localhost';
GRANT SELECT ON prod_db.* to 'prod_user'@'localhost';
GRANT INSERT ON prod_db.* to 'prod_user'@'localhost';
GRANT DELETE ON prod_db.* to 'prod_user'@'localhost';
GRANT UPDATE ON prod_db.* to 'prod_user'@'localhost';
GRANT SELECT ON dev_db.* to 'dev_user'@'%';
GRANT INSERT ON dev_db.* to 'dev_user'@'%';
GRANT DELETE ON dev_db.* to 'dev_user'@'%';
GRANT UPDATE ON dev_db.* to 'dev_user'@'%';
GRANT SELECT ON prod_db.* to 'prod_user'@'%';
GRANT INSERT ON prod_db.* to 'prod_user'@'%';
GRANT DELETE ON prod_db.* to 'prod_user'@'%';
GRANT UPDATE ON prod_db.* to 'prod_user'@'%';