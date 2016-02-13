CREATE DATABASE 'pacman_db' /*!40100 DEFAULT CHARACTER SET utf8 */;

CREATE USER pacman@localhost
  IDENTIFIED BY 'pacman123';
GRANT ALL ON pacman_db.* TO pacman2@localhost;
CREATE USER pacman@127.0.0.1
  IDENTIFIED BY 'pacman123';
GRANT ALL ON pacman_db.* TO pacman@127.0.0.1;
CREATE USER pacman@'%'
  IDENTIFIED BY 'pacman123';
GRANT ALL ON pacman_db.* TO pacman@'%';
