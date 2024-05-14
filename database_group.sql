CREATE TABLE sportgroup (
    id int NOT NULL,
    name varchar(100) NOT NULL,
    
    PRIMARY KEY (id)
);

CREATE TABLE court(
    id int NOT NULL,
    name varchar(100) NOT NULL,
    address varchar(1000) NULL,
    phone varchar(10) NULL,
    sportgroup_id int,
    
    PRIMARY KEY (id),
    INDEX sportgroup_idx (sportgroup_id),
    FOREIGN KEY (sportgroup_id)
        REFERENCES sportgroup(id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE product (
    id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name varchar(100) NOT NULL,
    price float NOT NULL,
    amount int NOT NULL,
    court_id int,


    INDEX prod_court_idx (court_id),
    FOREIGN KEY (court_id)
        REFERENCES court(id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE customer (
    id int NOT NULL,
    first_name varchar(100) NOT NULL,
    last_name varchar(100) NOT NULL,
    phone varchar(10) NULL,
    email varchar(1000) NULL,

    PRIMARY KEY (id)
);

CREATE TABLE court_customer (
    court_id int NOT NULL,
    customer_id int NOT NULL,
    loyalty_point float NULL,

    INDEX cc_court_idx (court_id),
    FOREIGN KEY (court_id)
        REFERENCES court(id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,

    INDEX cc_customer_idx (customer_id),
    FOREIGN KEY (customer_id)
        REFERENCES customer(id)
        ON UPDATE CASCADE
        ON DELETE CASCADE

);


CREATE TABLE field (
    id int NOT NULL,
    name varchar(100) NOT NULL,
    sport_type varchar(100) NOT NULL,
    court_id int,

    PRIMARY KEY (id),   
    INDEX f_court_idx (court_id),
    FOREIGN KEY (court_id)
        REFERENCES court(id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE timeslot(
    id int NOT NULL,
    start_time time,
    end_time time,
    court_id int,

    PRIMARY KEY (id),
    
    INDEX ts_court_idx (court_id),
    FOREIGN KEY (court_id)
        REFERENCES court (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE field_timeslot (
    field_id int NOT NULL,
    ts_id int NOT NULL,
    price float NOT NULL,
    day_in_week int NOT NULL,


    INDEX fts_field_id (field_id),
    FOREIGN KEY (field_id)
        REFERENCES field(id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    
    INDEX fts_timeslot_id (ts_id),
    FOREIGN KEY (ts_id)
        REFERENCES timeslot(id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE payment(
    id int NOT NULL,
    payment_type int NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE booking (
    id int NOT NULL,
    date date,
    customer_id int,
    field_id int,
    ts_id int,
    p_id int,

    PRIMARY KEY (id),

    INDEX b_customer_id (customer_id),
    FOREIGN KEY (customer_id)
        REFERENCES customer(id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    INDEX b_field_id (field_id),
    FOREIGN KEY (field_id)
        REFERENCES field(id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    INDEX b_timeslot_id (ts_id),
    FOREIGN KEY (ts_id)
        REFERENCES timeslot(id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    INDEX b_payment_id (p_id),
    FOREIGN KEY (p_id)
        REFERENCES payment(id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);


-- insert for each table

-- 1) Group
INSERT INTO sportgroup(id,name)
VALUES (1,'Dung Cau Long'), (2,'Vuong Bong Ro');

-- 2) Court
INSERT INTO court(id,name,address,phone,sportgroup_id)
VALUES (1,'Dung Cau Long - Chi nhanh 1', 'abc xyz', '0123456789',1),
(2,'Dung Cau Long - Chi nhanh 2','def ghi', '0987654321',1);

-- 3) Product
INSERT INTO product(id,name,price,amount,court_id)
VALUES (1,'Sting Dau', 8000, 1, 1),(2,'Pepsi', 10000,1,1),(3,'7Up',10500,1,1);

-- 4) Customer
INSERT INTO customer(id, first_name, last_name, phone, email)
VALUES (1,'Dung','Nguyen',0112349395,'dung.nguyen@yahoo.com'),(2,'Vuong','Tran',0482039493,'vuong.tran@hotmail.com');

-- 5) Court_Customer
INSERT INTO court_customer(court_id,customer_id,loyalty_point)
VALUES (1,1,500), (1,2,300);

-- 6) Field
INSERT INTO field(id, name, sport_type, court_id)
VALUES (1,'San 1', 1, 1), (2,'San 2',1,1), (3,'San 3',1,1);

-- 7) Time Slot
INSERT INTO timeslot(id, start_time, end_time, court_id)
VALUES (1,str_to_date('0500', '%h%i'),str_to_date('0530', '%h%i'),1),
       (2,str_to_date('0530', '%h%i'),str_to_date('0600', '%h%i'),1),
       (3,str_to_date('0600', '%h%i'),str_to_date('0630', '%h%i'),1);

-- 8) Field - Timeslot 
INSERT INTO field_timeslot(field_id, ts_id, price, day_in_week)
VALUES (1,1,50000,1),
       (1,2,50000,1),
       (1,3,55000,1);

-- 9) Payment
INSERT INTO payment(id, payment_type)
VALUES (1,1), (2,2),(3,1);

-- 10) Booking
INSERT INTO booking(id,date, customer_id, field_id, ts_id,p_id)
VALUES (1,str_to_date('2024-05-01', '%Y-%m-%d'),1,1,1,1),
       (2,str_to_date('2024-05-01', '%Y-%m-%d'),1,1,2,1);

