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
    id bigint NOT NULL AUTO_INCREMENT,
    field_id bigint NOT NULL
    , ts_id bigint NOT NULL
    , price float NOT NULL
    , day_in_week ENUM('MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY', 'SATURDAY', 'SUNDAY') NOT NULL
    , PRIMARY KEY (id)
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
    , customer_id bigint
    , p_id bigint
    , price float
    , PRIMARY KEY (id)
    , INDEX b_customer_id (customer_id)
    , FOREIGN KEY (customer_id) REFERENCES customer(id)
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

CREATE TABLE reserved_field_timeslot (
    id bigint NOT NULL AUTO_INCREMENT,
    fts_id bigint,
    booking_id bigint,
    booking_date date,
    PRIMARY KEY (id),
    INDEX r_fts_id (fts_id),
    FOREIGN KEY (fts_id) REFERENCES field_timeslot(id)
        ON
            UPDATE
            CASCADE
        ON DELETE CASCADE,
    INDEX r_booking_id (booking_id),
    FOREIGN KEY (booking_id) REFERENCES booking(id)
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
    (1, 1, 50000, 'MONDAY'), (1, 1, 50001, 'TUESDAY'), (1, 1, 50002, 'WEDNESDAY'), (1, 1, 50003, 'THURSDAY'), (1, 1, 50004, 'FRIDAY'), (1, 1, 50005, 'SATURDAY'), (1, 1, 50006, 'SUNDAY'),
    (1, 2, 50007, 'MONDAY'), (1, 2, 50008, 'TUESDAY'), (1, 2, 50009, 'WEDNESDAY'), (1, 2, 50010, 'THURSDAY'), (1, 2, 50011, 'FRIDAY'), (1, 2, 50012, 'SATURDAY'), (1, 2, 50013, 'SUNDAY'),
    (1, 3, 50014, 'MONDAY'), (1, 3, 50015, 'TUESDAY'), (1, 3, 50016, 'WEDNESDAY'), (1, 3, 50017, 'THURSDAY'), (1, 3, 50018, 'FRIDAY'), (1, 3, 50019, 'SATURDAY'), (1, 3, 50020, 'SUNDAY');

INSERT INTO field_timeslot (field_id, ts_id, price, day_in_week) VALUES
    (2, 1, 50021, 'MONDAY'), (2, 1, 50022, 'TUESDAY'), (2, 1, 50023, 'WEDNESDAY'), (2, 1, 50024, 'THURSDAY'), (2, 1, 50025, 'FRIDAY'), (2, 1, 50026, 'SATURDAY'), (2, 1, 50027, 'SUNDAY'),
    (2, 2, 50028, 'MONDAY'), (2, 2, 50029, 'TUESDAY'), (2, 2, 50030, 'WEDNESDAY'), (2, 2, 50031, 'THURSDAY'), (2, 2, 50032, 'FRIDAY'), (2, 2, 50033, 'SATURDAY'), (2, 2, 50034, 'SUNDAY'),
    (2, 3, 50035, 'MONDAY'), (2, 3, 50036, 'TUESDAY'), (2, 3, 50037, 'WEDNESDAY'), (2, 3, 50038, 'THURSDAY'), (2, 3, 50039, 'FRIDAY'), (2, 3, 50040, 'SATURDAY'), (2, 3, 50041, 'SUNDAY');

INSERT INTO field_timeslot (field_id, ts_id, price, day_in_week) VALUES
    (3, 1, 50042, 'MONDAY'), (3, 1, 50043, 'TUESDAY'), (3, 1, 50044, 'WEDNESDAY'), (3, 1, 50045, 'THURSDAY'), (3, 1, 50046, 'FRIDAY'), (3, 1, 50047, 'SATURDAY'), (3, 1, 50048, 'SUNDAY'),
    (3, 2, 50049, 'MONDAY'), (3, 2, 50050, 'TUESDAY'), (3, 2, 50051, 'WEDNESDAY'), (3, 2, 50052, 'THURSDAY'), (3, 2, 50053, 'FRIDAY'), (3, 2, 50054, 'SATURDAY'), (3, 2, 50055, 'SUNDAY'),
    (3, 3, 50056, 'MONDAY'), (3, 3, 50057, 'TUESDAY'), (3, 3, 50058, 'WEDNESDAY'), (3, 3, 50059, 'THURSDAY'), (3, 3, 50060, 'FRIDAY'), (3, 3, 50061, 'SATURDAY'), (3, 3, 50062, 'SUNDAY');


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