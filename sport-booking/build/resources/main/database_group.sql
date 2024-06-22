-- use this if error logged that database exists
DROP DATABASE IF EXISTS sport_app;

CREATE DATABASE sport_app;
use sport_app;

CREATE TABLE sportgroup (
                            id bigint NOT NULL AUTO_INCREMENT
    , name varchar(100) NOT NULL
    , PRIMARY KEY (id)
);

CREATE TABLE court(
                      id bigint NOT NULL AUTO_INCREMENT
    , name varchar(100) NOT NULL
    , address varchar(1000) NULL
    , phone varchar(10) NULL
    , sportgroup_id bigint
    , PRIMARY KEY (id)
    , INDEX sportgroup_idx (sportgroup_id)
    , FOREIGN KEY (sportgroup_id) REFERENCES sportgroup(id)
                          ON
                              UPDATE
                              CASCADE
                          ON DELETE CASCADE
);

CREATE TABLE product (
                         id bigint NOT NULL AUTO_INCREMENT
    , name varchar(100) NOT NULL
    , price float NOT NULL
    , amount int NOT NULL
    , PRIMARY KEY (id)
    , court_id bigint
    , INDEX prod_court_idx (court_id)
    , FOREIGN KEY (court_id) REFERENCES court(id)
                             ON
                                 UPDATE
                                 CASCADE
                             ON DELETE CASCADE
);

CREATE TABLE customer (
                          id bigint NOT NULL AUTO_INCREMENT
    , first_name varchar(100) NOT NULL
    , last_name varchar(100) NOT NULL
    , phone varchar(15) NULL
    , email varchar(1000) NULL
    , password varchar(1000) NULL
    , PRIMARY KEY (id)
);

CREATE TABLE court_customer (
                               court_id bigint NOT NULL
    , customer_id bigint NOT NULL
    , loyalty_point float NULL
    , PRIMARY KEY (court_id, customer_id)
    , INDEX cc_court_idx (court_id)
    , FOREIGN KEY (court_id) REFERENCES court(id)
                                   ON
                                       UPDATE
                                       CASCADE
                                   ON DELETE CASCADE
    , INDEX cc_customer_idx (customer_id)
    , FOREIGN KEY (customer_id) REFERENCES customer(id)
                                   ON
                                       UPDATE
                                       CASCADE
                                   ON DELETE CASCADE
);


CREATE TABLE field (
                       id bigint NOT NULL AUTO_INCREMENT
    , name varchar(100) NOT NULL
    , sport_type varchar(100) NOT NULL
    , court_id bigint
    , PRIMARY KEY (id)
    , INDEX f_court_idx (court_id)
    , FOREIGN KEY (court_id) REFERENCES court(id)
                           ON
                               UPDATE
                               CASCADE
                           ON DELETE CASCADE
);

CREATE TABLE timeslot(
                         id bigint NOT NULL AUTO_INCREMENT
    , start_time time
    , end_time time
    , court_id bigint
    , PRIMARY KEY (id)
    , INDEX ts_court_idx (court_id)
    , FOREIGN KEY (court_id) REFERENCES court (id)
                             ON
                                 UPDATE
                                 CASCADE
                             ON DELETE CASCADE
);

CREATE TABLE field_timeslot (
                               field_id bigint NOT NULL
    , ts_id bigint NOT NULL
    , price float NOT NULL
    , day_in_week int NOT NULL
    , booking_id bigint
    , PRIMARY KEY (field_id, ts_id, day_in_week)
    , INDEX fts_field_id (field_id)
    , FOREIGN KEY (field_id) REFERENCES field(id)
                                   ON
                                       UPDATE
                                       CASCADE
                                   ON DELETE CASCADE
    , INDEX fts_timeslot_id (ts_id)
    , FOREIGN KEY (ts_id) REFERENCES timeslot(id)
                                   ON
                                       UPDATE
                                       CASCADE
                                   ON DELETE CASCADE
);

CREATE TABLE payment(
                        id bigint NOT NULL AUTO_INCREMENT
    , payment_type int NOT NULL
    , PRIMARY KEY (id)
);

CREATE TABLE booking (
                         id bigint NOT NULL AUTO_INCREMENT
    , date date
    , customer_id bigint
    , field_id bigint
    , ts_id bigint
    , p_id bigint
    , price float
    , PRIMARY KEY (id)
    , INDEX b_customer_id (customer_id)
    , FOREIGN KEY (customer_id) REFERENCES customer(id)
                             ON
                                 UPDATE
                                 CASCADE
                             ON DELETE CASCADE
    , INDEX b_field_id (field_id)
    , FOREIGN KEY (field_id) REFERENCES field(id)
                             ON
                                 UPDATE
                                 CASCADE
                             ON DELETE CASCADE
    , INDEX b_timeslot_id (ts_id)
    , FOREIGN KEY (ts_id) REFERENCES timeslot(id)
                             ON
                                 UPDATE
                                 CASCADE
                             ON DELETE CASCADE
    , INDEX b_payment_id (p_id)
    , FOREIGN KEY (p_id) REFERENCES payment(id)
                             ON
                                 UPDATE
                                 CASCADE
                             ON DELETE CASCADE
);


-- insert for each table

-- 1) Group
INSERT INTO
    sportgroup(name)
VALUES
('Dung Cau Long')
     , ('Vuong Bong Ro');

-- 2) Court
INSERT INTO
    court(name, address, phone, sportgroup_id)
VALUES
(
    'Dung Cau Long - Chi nhanh 1'
, 'abc xyz'
, '0123456789'
, 1
)
     , (
         'Dung Cau Long - Chi nhanh 2'
       , 'def ghi'
       , '0987654321'
       , 1
);

-- 3) Product
INSERT INTO
    product(name, price, amount, court_id)
VALUES
('Sting Dau', 8000, 1, 1)
     , ('Pepsi', 10000, 1, 1)
     , ('7Up', 10500, 1, 1);

-- 4) Customer
INSERT INTO
    customer(first_name, last_name, phone, email, password)
VALUES
(
    'Dung'
, 'Nguyen'
, 0112349395
, 'dung.nguyen@yahoo.com'
, 'dung_nguyen'
)
     , (
         'Vuong'
       , 'Tran'
       , 0482039493
       , 'vuong.tran@hotmail.com'
       , 'vuong_tran'
);

-- 5) Court_Customer
INSERT INTO
    court_customer(court_id, customer_id, loyalty_point)
VALUES
(1, 1, 500)
     , (1, 2, 300);

-- 6) Field
INSERT INTO
    field(name, sport_type, court_id)
VALUES
('San 1', 1, 1)
     , ('San 2', 1, 1)
     , ('San 3', 1, 1);

-- 7) Time Slot
INSERT INTO
    timeslot(start_time, end_time, court_id)
VALUES
(
    str_to_date('0500', '%h%i')
, str_to_date('0530', '%h%i')
, 1
)
     , (
         str_to_date('0530', '%h%i')
       , str_to_date('0600', '%h%i')
       , 1
)
     , (
         str_to_date('0600', '%h%i')
       , str_to_date('0630', '%h%i')
       , 1
);

-- 😎 Field - Timeslot
INSERT INTO field_timeslot (field_id, ts_id, price, day_in_week) VALUES
     (1, 1, 50000, 1), (1, 1, 50001, 2), (1, 1, 50002, 3), (1, 1, 50003, 4), (1, 1, 50004, 5), (1, 1, 50005, 6), (1, 1, 50006, 7),
     (1, 2, 50007, 1), (1, 2, 50008, 2), (1, 2, 50009, 3), (1, 2, 50010, 4), (1, 2, 50011, 5), (1, 2, 50012, 6), (1, 2, 50013, 7),
     (1, 3, 50014, 1), (1, 3, 50015, 2), (1, 3, 50016, 3), (1, 3, 50017, 4), (1, 3, 50018, 5), (1, 3, 50019, 6), (1, 3, 50020, 7);

INSERT INTO field_timeslot (field_id, ts_id, price, day_in_week) VALUES
     (2, 1, 50021, 1), (2, 1, 50022, 2), (2, 1, 50023, 3), (2, 1, 50024, 4), (2, 1, 50025, 5), (2, 1, 50026, 6), (2, 1, 50027, 7),
     (2, 2, 50028, 1), (2, 2, 50029, 2), (2, 2, 50030, 3), (2, 2, 50031, 4), (2, 2, 50032, 5), (2, 2, 50033, 6), (2, 2, 50034, 7),
     (2, 3, 50035, 1), (2, 3, 50036, 2), (2, 3, 50037, 3), (2, 3, 50038, 4), (2, 3, 50039, 5), (2, 3, 50040, 6), (2, 3, 50041, 7);

INSERT INTO field_timeslot (field_id, ts_id, price, day_in_week) VALUES
    (3, 1, 50042, 1), (3, 1, 50043, 2), (3, 1, 50044, 3), (3, 1, 50045, 4), (3, 1, 50046, 5), (3, 1, 50047, 6), (3, 1, 50048, 7),
    (3, 2, 50049, 1), (3, 2, 50050, 2), (3, 2, 50051, 3), (3, 2, 50052, 4), (3, 2, 50053, 5), (3, 2, 50054, 6), (3, 2, 50055, 7),
    (3, 3, 50056, 1), (3, 3, 50057, 2), (3, 3, 50058, 3), (3, 3, 50059, 4), (3, 3, 50060, 5), (3, 3, 50061, 6), (3, 3, 50062, 7);

-- 9) Payment
INSERT INTO
    payment(payment_type)
VALUES
(1)
     , (2)
     , (1);

-- 10) Booking
-- INSERT INTO
--     booking(date, customer_id, field_id, ts_id, p_id)
-- VALUES
-- (str_to_date('2024-05-01', '%Y-%m-%d'), 1, 1, 1, 1)
--      , (str_to_date('2024-05-01', '%Y-%m-%d'), 1, 1, 2, 1);