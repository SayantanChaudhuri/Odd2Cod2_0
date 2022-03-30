DROP TABLE table_details IF EXISTS;

CREATE TABLE table_details (
  id bigint NOT NULL AUTO_INCREMENT,
  db_name varchar(255) NOT NULL,
  db_type varchar(255) NOT NULL,
  masked_fields varchar(500) NULL,
  table_name varchar(255) NOT NULL,
  PRIMARY KEY (id)
);