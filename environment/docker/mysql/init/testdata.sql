INSERT
INTO users(user_id, password, user_name, user_role)
VALUES ('test', 'pass', 'テストユーザー', 'USER');

INSERT
INTO facilities(facility_id, facility_name)
VALUES (10001, '一ノ宮施設');

INSERT
INTO facilities(facility_id, facility_name)
VALUES (10002, '二本松施設');

INSERT
INTO user_facility(user_id, facility_id)
VALUES ('test', 10001);

INSERT
INTO user_facility(user_id, facility_id)
VALUES ('test', 10002);