DROP TABLE IF EXISTS `query_details`,
                      `user`;


CREATE TABLE query_details (
  query_name varchar(255) NOT NULL,
  full_query varchar(500) NOT NULL,
  soe_id varchar(8) NOT NULL,
  PRIMARY KEY (query_name)
);

CREATE TABLE `user` (
  `soe_id` varchar(255) NOT NULL,
  `blocked_tables` varchar(500) DEFAULT NULL,
  `db_name` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  PRIMARY KEY (`soe_id`)
);

ALTER TABLE query_details
ADD FOREIGN KEY (soe_id)
REFERENCES user(soe_id);
