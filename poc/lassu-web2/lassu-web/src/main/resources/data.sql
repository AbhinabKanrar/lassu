CREATE SCHEMA IF NOT EXISTS lassu;

CREATE TABLE IF NOT EXISTS lassu.user_detail2(
	user_id BIGINT PRIMARY KEY,
	username VARCHAR(20) NOT NULL,
	password VARCHAR(60) NOT NULL,
	role VARCHAR(20) NOT NULL,
	mail VARCHAR(255),
	phone_number VARCHAR(13),
	user_status VARCHAR(20) NOT NULL,
	UNIQUE (username)
);

CREATE TABLE IF NOT EXISTS lassu.user_detail1_audit2(
	audit_id BIGINT PRIMARY KEY,
	user_id BIGINT NOT NULL,
	reporting_site VARCHAR(255) NOT NULL,
	crtn_ts timestamp without time zone NOT NULL,
	CONSTRAINT user_detail1_audit_fk_ FOREIGN KEY (user_id) REFERENCES lassu.user_detail2 (user_id)
);

--INSERT INTO lassu.user_detail2 VALUES (2860524688118663536,'admin','$2a$10$trT3.R/Nfey62eczbKEnueTcIbJXW.u1ffAo/XfyLpofwNDbEB86O','ADMIN',null,null,'ACTIVE');
