## Use the below to run mysql docker image instead of local mysql db.
# docker run --name mysqldb -p 3306:3306 -e MYSQL_ALLOW_EMPTY_PASSWORD=yes -d mysql

# connect to mysql and run as root users
#Create Databases
CREATE DATABASE mbf_dev;
CREATE DATABASE mbf_prod;

#Create database service accounts
CREATE USER 'mbf_dev_user'@'localhost' IDENTIFIED BY 'gram';
CREATE USER 'mbf_prod_user'@'localhost' IDENTIFIED BY 'gram';

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
GRANT SELECT ON mbf_dev.* to 'mbf_dev_user'@'%';
GRANT INSERT ON mbf_dev.* to 'mbf_dev_user'@'%';
GRANT DELETE ON mbf_dev.* to 'mbf_dev_user'@'%';
GRANT UPDATE ON mbf_dev.* to 'mbf_dev_user'@'%';
GRANT SELECT ON mbf_prod.* to 'mbf_prod_user'@'%';
GRANT INSERT ON mbf_prod.* to 'mbf_prod_user'@'%';
GRANT DELETE ON mbf_prod.* to 'mbf_prod_user'@'%';
GRANT UPDATE ON mbf_prod.* to 'mbf_prod_user'@'%';