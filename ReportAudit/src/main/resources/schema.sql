DROP TABLE audit_detail IF EXISTS;

CREATE TABLE audit_detail (
  id bigint NOT NULL AUTO_INCREMENT,
  created_time datetime(6) NOT NULL,
  query varchar(500) NOT NULL,
  soe_id varchar(8) NOT NULL,
  PRIMARY KEY (id)
);