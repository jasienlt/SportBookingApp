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

CREATE TABLE courtCustomer (
                               court_id bigint NOT NULL
    , customer_id bigint NOT NULL
    , loyalty_point float NULL
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

CREATE TABLE fieldTimeslot (
                               field_id bigint NOT NULL
    , ts_id bigint NOT NULL
    , price float NOT NULL
    , day_in_week int NOT NULL
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
    courtCustomer(court_id, customer_id, loyalty_point)
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
INSERT INTO
    fieldTimeslot(field_id, ts_id, price, day_in_week)
VALUES
(1, 1, 50000, 1)
     , (1, 2, 50000, 1)
     , (1, 3, 55000, 1);

-- 9) Payment
INSERT INTO
    payment(payment_type)
VALUES
(1)
     , (2)
     , (1);

-- 10) Booking
INSERT INTO
    booking(date, customer_id, field_id, ts_id, p_id)
VALUES
(str_to_date('2024-05-01', '%Y-%m-%d'), 1, 1, 1, 1)
     , (str_to_date('2024-05-01', '%Y-%m-%d'), 1, 1, 2, 1);