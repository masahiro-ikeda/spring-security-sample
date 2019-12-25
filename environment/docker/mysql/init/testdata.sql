INSERT
INTO users(user_id, password, user_name, user_role)
VALUES ('test', 'pass', 'テストユーザー', 'USER');

INSERT
INTO facilities(facility_id, facility_name)
VALUES ('f001', 'テスト施設');

INSERT
INTO user_facility(user_id, facility_id)
VALUES ('test', 'f001');