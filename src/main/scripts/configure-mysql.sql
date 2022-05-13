## Use the below to run mysql docker image instead of local mysql db.
#docker run --name recipe1 -p 3306:3306 -e MYSQL_ALLOW_EMPTY_PASSWORD=yes -d mysql:8.0

# connect to mysql and run as root users
#Create Database Schemas
CREATE DATABASE mbf_dev;
CREATE DATABASE mbf_prod;

#Create database service accounts (No Root access)Local
CREATE USER 'mbf_dev_user'@'localhost' IDENTIFIED BY 'gram';
CREATE USER 'mbf_prod_user'@'localhost' IDENTIFIED BY 'gram';

#Create database service accounts (No Root access) Docker
CREATE USER 'mbf_dev_user'@'127.0.0.1' IDENTIFIED BY 'gram';
CREATE USER 'mbf_prod_user'@'127.0.0.1' IDENTIFIED BY 'gram';

#Database grants for local mysql
GRANT SELECT ON mbf_dev.* to 'mbf_dev_user'@'localhost';
GRANT INSERT ON mbf_dev.* to 'mbf_dev_user'@'localhost';
GRANT DELETE ON mbf_dev.* to 'mbf_dev_user'@'localhost';
GRANT UPDATE ON mbf_dev.* to 'mbf_dev_user'@'localhost';
GRANT SELECT ON mbf_prod.* to 'mbf_prod_user'@'localhost';
GRANT INSERT ON mbf_prod.* to 'mbf_prod_user'@'localhost';
GRANT DELETE ON mbf_prod.* to 'mbf_prod_user'@'localhost';
GRANT UPDATE ON mbf_prod.* to 'mbf_prod_user'@'localhost';
#Database grants for docker mysql
GRANT SELECT ON mbf_dev.* to 'mbf_dev_user'@'127.0.0.1';
GRANT INSERT ON mbf_dev.* to 'mbf_dev_user'@'127.0.0.1';
GRANT DELETE ON mbf_dev.* to 'mbf_dev_user'@'127.0.0.1';
GRANT UPDATE ON mbf_dev.* to 'mbf_dev_user'@'127.0.0.1';
GRANT SELECT ON mbf_prod.* to 'mbf_prod_user'@'127.0.0.1';
GRANT INSERT ON mbf_prod.* to 'mbf_prod_user'@'127.0.0.1';
GRANT DELETE ON mbf_prod.* to 'mbf_prod_user'@'127.0.0.1';
GRANT UPDATE ON mbf_prod.* to 'mbf_prod_user'@'127.0.0.1';