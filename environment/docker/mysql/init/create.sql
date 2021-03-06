# ユーザー
CREATE TABLE users(
    user_id   VARCHAR(25) NOT NULL,
    password  VARCHAR(60) NOT NULL,
    user_name VARCHAR(25) NOT NULL,
    user_role VARCHAR(10) NOT NULL,
    version   INT(5) DEFAULT 0,
    PRIMARY KEY(user_id)
);

CREATE TABLE facilities(
    facility_id   INT(5) NOT NULL AUTO_INCREMENT,
    facility_name VARCHAR(60) NOT NULL,
    version       INT(5) DEFAULT 0,
    PRIMARY KEY(facility_id)
);

CREATE TABLE user_facility(
    user_id      VARCHAR(25) NOT NULL,
    facility_id  INT(5) NOT NULL,
    version      INT(5) DEFAULT 0,
    PRIMARY KEY(user_id, facility_id)
);