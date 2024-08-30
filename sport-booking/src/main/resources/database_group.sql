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
    , managed_by bigint
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
    , role ENUM('OWNER', 'ADMIN', 'CUSTOMER') NOT NULL
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
    , created_date DATETIME
    , booking_id bigint
    , payment_type varchar(50) NOT NULL
    , payment_evidence varchar(5000)
    , payment_stts ENUM('SUCCESSFUL','PENDING', 'CANCELED') NOT NULL
    , PRIMARY KEY (id)
);

CREATE TABLE booking (
    id bigint NOT NULL AUTO_INCREMENT
    , created_date DATETIME
    , customer_id bigint
    , p_id bigint
    , price float
    , status ENUM('COMPLETED', 'PENDING', 'CANCELED') NOT NULL
    , stripe_session_id text NOT NULL
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

CREATE TABLE feedback (
                         id bigint NOT NULL AUTO_INCREMENT
    , date date
    , customer_id bigint
    , court_id bigint
    , feedback varchar(5000)
    , rating tinyint

    , PRIMARY KEY (id)
    , INDEX fe_customer_id (customer_id)
    , FOREIGN KEY (customer_id) REFERENCES customer(id)
                             ON
                                 UPDATE
                                 CASCADE
                             ON DELETE CASCADE
    , INDEX fe_court_id (court_id)
    , FOREIGN KEY (court_id) REFERENCES court(id)
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

-- 4) Customer
INSERT INTO customer (first_name, last_name, phone, email, password, role) VALUES
    ('John', 'Doe', '1234567890', 'johndoe@example.com', 'password123', 'CUSTOMER'),
    ('Jane', 'Smith', '0987654321', 'janesmith@example.com', 'password456', 'CUSTOMER'),
    ('Alice', 'Johnson', '5551234567', 'alice.johnson@example.com', 'password789', 'ADMIN'),
    ('Bob', 'Brown', '5559876543', 'bob.brown@example.com', 'password101112', 'OWNER'),
    ('Charlie', 'Davis', '5551112222', 'charlie.davis@example.com', 'password131415', 'CUSTOMER'),
    ('David', 'Miller', '5553334444', 'david.miller@example.com', 'password161718', 'ADMIN'),
    ('Ella', 'Wilson', '5555556666', 'ella.wilson@example.com', 'password192021', 'OWNER'),
    ('Fiona', 'Taylor', '5557778888', 'fiona.taylor@example.com', 'password222324', 'CUSTOMER'),
    ('George', 'Anderson', '5559990000', 'george.anderson@example.com', 'password252627', 'ADMIN'),
    ('Hannah', 'Thomas', '5551213141', 'hannah.thomas@example.com', 'password282930', 'OWNER'),
    ('Isaac', 'Jackson', '5554151617', 'isaac.jackson@example.com', 'password313233', 'CUSTOMER'),
    ('Jack', 'White', '5557181920', 'jack.white@example.com', 'password343536', 'ADMIN'),
    ('Karen', 'Harris', '5551415161', 'karen.harris@example.com', 'password373839', 'OWNER'),
    ('Liam', 'Clark', '5558192021', 'liam.clark@example.com', 'password404142', 'CUSTOMER');

-- 2) Court
INSERT INTO
    court(name, address, phone, sportgroup_id, managed_by)
VALUES
    (
      'Dung Cau Long - Chi nhanh 1'
    , '89 Culloden Road, Marsfield NSW 2122'
    , '0123456789'
    , 1
    , 3
    )
        , (
            'Dung Cau Long - Chi nhanh 2'
          , '2/2 Inglewood Pl, Norwest NSW 2153'
          , '0987654321'
          , 1
          , 3
    ),
    ('Central Park Court', '123 Central Park, New York, NY', '1234567890', 1, 3),
    ('Lakeside Arena', '456 Lakeside Dr, Chicago, IL', '0987654321', 1, 3),
    ('Sunset Sports Complex', '789 Sunset Blvd, Los Angeles, CA', '5551234567', 2, 6),
    ('Riverside Court', '321 Riverside Ave, Austin, TX', '5559876543', 2, 6),
    ('Mountain View Field', '654 Mountain Rd, Denver, CO', '5551112222', 2, 6);


-- 3) Product
INSERT INTO
    product(name, price, amount, court_id)
VALUES
('Sting Dau', 8000, 1, 1)
     , ('Pepsi', 10000, 1, 1)
     , ('7Up', 10500, 1, 1);

-- 5) Court_Customer
INSERT INTO
    court_customer(court_id, customer_id, loyalty_point)
VALUES
(1, 1, 500)
     , (1, 2, 300);

-- 6) Field
INSERT INTO field (name, sport_type, court_id) VALUES
    ('Field A1', 'Soccer', 1),
    ('Field A2', 'Soccer', 1),
    ('Field B1', 'Basketball', 1),
    ('Field B2', 'Basketball', 1),
    ('Field C1', 'Tennis', 1),
    ('Field C2', 'Tennis', 1),
    ('Field D1', 'Badminton', 1),
    ('Field D2', 'Badminton', 1),
    ('Field E1', 'Volleyball', 1),
    ('Field E2', 'Volleyball', 1),
    ('Field F1', 'Squash', 1),
    ('Field F2', 'Squash', 1),
    ('Field G1', 'Hockey', 1),
    ('Field G2', 'Hockey', 1),
    ('Field H1', 'Cricket', 1),
    ('Field H2', 'Cricket', 1),
    ('Field I1', 'Rugby', 1),
    ('Field I2', 'Rugby', 1),
    ('Field J1', 'Baseball', 1),
    ('Field J2', 'Baseball', 1);



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
INSERT INTO timeslot (start_time, end_time, court_id) VALUES ('08:00:00', '09:00:00', 1);
INSERT INTO timeslot (start_time, end_time, court_id) VALUES ('09:00:00', '10:00:00', 1);
INSERT INTO timeslot (start_time, end_time, court_id) VALUES ('10:00:00', '11:00:00', 1);
INSERT INTO timeslot (start_time, end_time, court_id) VALUES ('11:00:00', '12:00:00', 1);
INSERT INTO timeslot (start_time, end_time, court_id) VALUES ('12:00:00', '13:00:00', 1);
INSERT INTO timeslot (start_time, end_time, court_id) VALUES ('13:00:00', '14:00:00', 1);
INSERT INTO timeslot (start_time, end_time, court_id) VALUES ('14:00:00', '15:00:00', 1);
INSERT INTO timeslot (start_time, end_time, court_id) VALUES ('15:00:00', '16:00:00', 1);
INSERT INTO timeslot (start_time, end_time, court_id) VALUES ('16:00:00', '17:00:00', 1);
INSERT INTO timeslot (start_time, end_time, court_id) VALUES ('17:00:00', '18:00:00', 1);
INSERT INTO timeslot (start_time, end_time, court_id) VALUES ('18:00:00', '19:00:00', 1);
INSERT INTO timeslot (start_time, end_time, court_id) VALUES ('19:00:00', '20:00:00', 1);
INSERT INTO timeslot (start_time, end_time, court_id) VALUES ('20:00:00', '21:00:00', 1);
INSERT INTO timeslot (start_time, end_time, court_id) VALUES ('21:00:00', '22:00:00', 1);
INSERT INTO timeslot (start_time, end_time, court_id) VALUES ('22:00:00', '23:00:00', 1);
INSERT INTO timeslot (start_time, end_time, court_id) VALUES ('23:00:00', '00:00:00', 1);
INSERT INTO timeslot (start_time, end_time, court_id) VALUES ('00:00:00', '01:00:00', 1);
INSERT INTO timeslot (start_time, end_time, court_id) VALUES ('01:00:00', '02:00:00', 1);
INSERT INTO timeslot (start_time, end_time, court_id) VALUES ('02:00:00', '03:00:00', 1);
INSERT INTO timeslot (start_time, end_time, court_id) VALUES ('03:00:00', '04:00:00', 1);

-- 😎 Field - Timeslot
DELIMITER $$

CREATE PROCEDURE insert_field_timeslots()
BEGIN
    DECLARE field_id INT DEFAULT 1;
    DECLARE ts_id INT DEFAULT 1;
    DECLARE day_counter INT DEFAULT 1;
    DECLARE price INT DEFAULT 50000;
    DECLARE day_of_week VARCHAR(10);

    -- Loop for 20 fields
    WHILE field_id <= 20 DO
        -- Loop for 23 timeslots
        SET ts_id = 1;
        WHILE ts_id <= 23 DO
            -- Loop for 7 days of the week
            SET day_counter = 1;
            WHILE day_counter <= 7 DO
                SET day_of_week = CASE day_counter
                    WHEN 1 THEN 'MONDAY'
                    WHEN 2 THEN 'TUESDAY'
                    WHEN 3 THEN 'WEDNESDAY'
                    WHEN 4 THEN 'THURSDAY'
                    WHEN 5 THEN 'FRIDAY'
                    WHEN 6 THEN 'SATURDAY'
                    WHEN 7 THEN 'SUNDAY'
END;

                -- Insert statement
INSERT INTO field_timeslot (field_id, ts_id, price, day_in_week)
VALUES (field_id, ts_id, price, day_of_week);

-- Increment the price
SET price = price + 1;
                SET day_counter = day_counter + 1;
END WHILE;

            SET ts_id = ts_id + 1;
END WHILE;

        SET field_id = field_id + 1;
END WHILE;
END$$

DELIMITER ;

-- Call the procedure to insert the data
CALL insert_field_timeslots();

--
DROP EVENT IF EXISTS update_booking_status;

CREATE EVENT update_booking_status
ON SCHEDULE EVERY 30 SECOND
DO
UPDATE `sport_app`.`booking` SET `status` = 'CANCELED' WHERE
-- 1 = 1 AND
        `created_date` > NOW() - INTERVAL 30 MINUTE AND
    `status` = 'PENDING';

--
DROP EVENT IF EXISTS refresh_booking;

CREATE EVENT refresh_booking
ON SCHEDULE EVERY 1 DAY
DO
DELETE FROM `sport_app`.`booking` WHERE
-- 1 = 1 AND
    `status` = 'CANCELED';

-- INSERT INTO field_timeslot (field_id, ts_id, price, day_in_week) VALUES (1, 1, 25.00, 'MONDAY');
-- INSERT INTO field_timeslot (field_id, ts_id, price, day_in_week) VALUES (2, 2, 30.00, 'TUESDAY');
-- INSERT INTO field_timeslot (field_id, ts_id, price, day_in_week) VALUES (3, 3, 20.00, 'WEDNESDAY');
-- INSERT INTO field_timeslot (field_id, ts_id, price, day_in_week) VALUES (4, 4, 27.50, 'THURSDAY');
-- INSERT INTO field_timeslot (field_id, ts_id, price, day_in_week) VALUES (5, 5, 22.00, 'FRIDAY');
-- INSERT INTO field_timeslot (field_id, ts_id, price, day_in_week) VALUES (6, 6, 35.00, 'SATURDAY');
-- INSERT INTO field_timeslot (field_id, ts_id, price, day_in_week) VALUES (7, 7, 40.00, 'SUNDAY');
-- INSERT INTO field_timeslot (field_id, ts_id, price, day_in_week) VALUES (8, 8, 18.00, 'MONDAY');
-- INSERT INTO field_timeslot (field_id, ts_id, price, day_in_week) VALUES (9, 9, 26.00, 'TUESDAY');
-- INSERT INTO field_timeslot (field_id, ts_id, price, day_in_week) VALUES (10, 10, 33.00, 'WEDNESDAY');
-- INSERT INTO field_timeslot (field_id, ts_id, price, day_in_week) VALUES (11, 1, 28.00, 'THURSDAY');
-- INSERT INTO field_timeslot (field_id, ts_id, price, day_in_week) VALUES (12, 2, 32.50, 'FRIDAY');
-- INSERT INTO field_timeslot (field_id, ts_id, price, day_in_week) VALUES (13, 3, 29.00, 'SATURDAY');
-- INSERT INTO field_timeslot (field_id, ts_id, price, day_in_week) VALUES (14, 4, 31.00, 'SUNDAY');
-- INSERT INTO field_timeslot (field_id, ts_id, price, day_in_week) VALUES (15, 5, 23.50, 'MONDAY');
-- INSERT INTO field_timeslot (field_id, ts_id, price, day_in_week) VALUES (1, 6, 36.00, 'TUESDAY');
-- INSERT INTO field_timeslot (field_id, ts_id, price, day_in_week) VALUES (2, 7, 45.00, 'WEDNESDAY');
-- INSERT INTO field_timeslot (field_id, ts_id, price, day_in_week) VALUES (3, 8, 19.50, 'THURSDAY');
-- INSERT INTO field_timeslot (field_id, ts_id, price, day_in_week) VALUES (4, 9, 27.00, 'FRIDAY');
-- INSERT INTO field_timeslot (field_id, ts_id, price, day_in_week) VALUES (5, 10, 34.00, 'SATURDAY');

-- -- 8.5) Reserved Field - Timeslot
-- INSERT INTO reserved_field_timeslot (fts_id, booking_id, booking_date) VALUES
--      (1, 1, '2024-08-21'),
--     (2, 2, '2024-08-22'),
--     (3, 3, '2024-08-23'),
--     (4, 4, '2024-08-24'),
--     (5, 5, '2024-08-25'),
--     (6, 6, '2024-08-26'),
--     (7, 7, '2024-08-27'),
--     (8, 8, '2024-08-28'),
--     (9, 9, '2024-08-29'),
--     (1, 10, '2024-08-30');
-- INSERT INTO reserved_field_timeslot (fts_id, booking_id, booking_date) VALUES (1, 1, '2024-08-01');
-- INSERT INTO reserved_field_timeslot (fts_id, booking_id, booking_date) VALUES (2, 2, '2024-08-02');
-- INSERT INTO reserved_field_timeslot (fts_id, booking_id, booking_date) VALUES (3, 3, '2024-08-03');
-- INSERT INTO reserved_field_timeslot (fts_id, booking_id, booking_date) VALUES (4, 4, '2024-08-04');
-- INSERT INTO reserved_field_timeslot (fts_id, booking_id, booking_date) VALUES (5, 5, '2024-08-05');
-- INSERT INTO reserved_field_timeslot (fts_id, booking_id, booking_date) VALUES (6, 6, '2024-08-06');
-- INSERT INTO reserved_field_timeslot (fts_id, booking_id, booking_date) VALUES (7, 7, '2024-08-07');
-- INSERT INTO reserved_field_timeslot (fts_id, booking_id, booking_date) VALUES (8, 8, '2024-08-08');
-- INSERT INTO reserved_field_timeslot (fts_id, booking_id, booking_date) VALUES (9, 9, '2024-08-09');
-- INSERT INTO reserved_field_timeslot (fts_id, booking_id, booking_date) VALUES (10, 10, '2024-08-10');
-- INSERT INTO reserved_field_timeslot (fts_id, booking_id, booking_date) VALUES (11, 11, '2024-08-11');
-- INSERT INTO reserved_field_timeslot (fts_id, booking_id, booking_date) VALUES (12, 12, '2024-08-12');
-- INSERT INTO reserved_field_timeslot (fts_id, booking_id, booking_date) VALUES (13, 13, '2024-08-13');
-- INSERT INTO reserved_field_timeslot (fts_id, booking_id, booking_date) VALUES (14, 14, '2024-08-14');
-- INSERT INTO reserved_field_timeslot (fts_id, booking_id, booking_date) VALUES (15, 15, '2024-08-15');
-- INSERT INTO reserved_field_timeslot (fts_id, booking_id, booking_date) VALUES (1, 16, '2024-08-16');
-- INSERT INTO reserved_field_timeslot (fts_id, booking_id, booking_date) VALUES (2, 17, '2024-08-17');
-- INSERT INTO reserved_field_timeslot (fts_id, booking_id, booking_date) VALUES (3, 18, '2024-08-18');
-- INSERT INTO reserved_field_timeslot (fts_id, booking_id, booking_date) VALUES (4, 19, '2024-08-19');
-- INSERT INTO reserved_field_timeslot (fts_id, booking_id, booking_date) VALUES (5, 20, '2024-08-20');
--
--
-- -- 9) Payment
-- INSERT INTO payment (created_date, booking_id, payment_type, payment_evidence, payment_stts) VALUES
--     ('2024-08-14', 1, 'Stripe', 'evidence_1.jpg', 'SUCCESSFUL'),
--     ('2024-08-14', 2, 'Bank Transfer', 'evidence_2.jpg', 'PENDING'),
--     ('2024-08-15', 3, 'Stripe', 'evidence_3.jpg', 'CANCELED'),
--     ('2024-08-15', 4, 'Bank Transfer', 'evidence_4.jpg', 'SUCCESSFUL'),
--     ('2024-08-16', 5, 'Stripe', 'evidence_5.jpg', 'PENDING'),
--     ('2024-08-16', 6, 'Bank Transfer', 'evidence_6.jpg', 'SUCCESSFUL'),
--     ('2024-08-17', 7, 'Stripe', 'evidence_7.jpg', 'CANCELED'),
--     ('2024-08-17', 8, 'Bank Transfer', 'evidence_8.jpg', 'SUCCESSFUL'),
--     ('2024-08-18', 9, 'Stripe', 'evidence_9.jpg', 'PENDING'),
--     ('2024-08-18', 10, 'Bank Transfer', 'evidence_10.jpg', 'SUCCESSFUL'),
--     ('2024-08-19', 11, 'Stripe', 'evidence_11.jpg', 'CANCELED'),
--     ('2024-08-19', 12, 'Bank Transfer', 'evidence_12.jpg', 'SUCCESSFUL'),
--     ('2024-08-20', 13, 'Stripe', 'evidence_13.jpg', 'PENDING'),
--     ('2024-08-20', 14, 'Bank Transfer', 'evidence_14.jpg', 'SUCCESSFUL'),
--     ('2024-08-21', 15, 'Stripe', 'evidence_15.jpg', 'CANCELED'),
--     ('2024-08-21', 16, 'Bank Transfer', 'evidence_16.jpg', 'SUCCESSFUL'),
--     ('2024-08-14', 17, 'Stripe', 'evidence_17.jpg', 'PENDING'),
--     ('2024-08-15', 18, 'Bank Transfer', 'evidence_18.jpg', 'SUCCESSFUL'),
--     ('2024-08-16', 19, 'Stripe', 'evidence_19.jpg', 'CANCELED'),
--     ('2024-08-17', 20, 'Bank Transfer', 'evidence_20.jpg', 'SUCCESSFUL');
--
-- -- 10) Booking
-- INSERT INTO booking (created_date, customer_id, p_id, price, status, stripe_session_id) VALUES
--         ('2024-08-14', 1, 1, 150.50, 'COMPLETED', 'ec28a5bc-d015-4d10-a8ed-7c1a178c1c7e'),
--         ('2024-08-14', 2, 2, 200.75, 'PENDING', '04eabe75-1a30-409e-8c4e-789a0c6aeb17'),
--         ('2024-08-15', 1, 3, 320.00, 'CANCELED', 'bfdc12fe-9298-4f7d-9a6a-1c7f88a2a8a6'),
--         ('2024-08-15', 4, 4, 450.25, 'COMPLETED', '2b8f924e-fd69-44da-b731-890d1df9d2a7'),
--         ('2024-08-16', 2, 5, 275.50, 'PENDING', 'a3bcd854-81fb-4d95-a659-d5f8f394e248'),
--         ('2024-08-16', 3, 6, 199.99, 'COMPLETED', 'fe7adf76-bd23-4e96-99fd-7049f7d839e7'),
--         ('2024-08-17', 6, 7, 399.49, 'CANCELED', '8be328ac-74cc-45f5-b938-cd76a2556df6'),
--         ('2024-08-17', 2, 8, 620.89, 'PENDING', '229a7bf8-9c7e-48d5-89d9-84f557b0e48b'),
--         ('2024-08-18', 1, 9, 720.35, 'COMPLETED', 'df64e4bb-7591-4b55-bf64-c47c6792eaf5'),
--         ('2024-08-18', 3, 10, 815.75, 'PENDING', 'f8c83a91-230e-4f6a-8ed7-438efce24b47'),
--         ('2024-08-19', 10, 11, 450.00, 'CANCELED', '4f274c3e-7834-4be0-9096-0ffcdc924d89'),
--         ('2024-08-19', 1, 12, 530.50, 'COMPLETED', 'a1dfb04a-292b-4c1e-a4fe-5f91b1839a1c'),
--         ('2024-08-20', 7, 13, 305.99, 'PENDING', '839f5cd4-fdb7-4986-bb85-c6f5db60cfc8'),
--         ('2024-08-20', 9, 14, 688.20, 'COMPLETED', 'ef1fa57f-ff89-4501-8eae-8e76114c6e27'),
--         ('2024-08-21', 3, 15, 402.75, 'CANCELED', '06c1de1f-1a69-4de3-816d-918aeb39f3c4'),
--         ('2024-08-21', 5, 16, 550.45, 'PENDING', 'd1cb41f1-84cf-4a8e-859b-2b7390d47f4f'),
--         ('2024-08-21', 4, 17, 650.25, 'COMPLETED', 'f82bfe50-d172-4911-95cb-76d4b7e36d83'),
--         ('2024-08-21', 9, 18, 245.99, 'PENDING', 'd4b27425-5c47-4650-840e-06dbed18306a'),
--         ('2024-08-21', 5, 19, 350.60, 'COMPLETED', 'b9e0f46a-baae-44ed-85b6-5e2526a939ff'),
--         ('2024-08-21', 6, 20, 299.99, 'CANCELED', 'a9f11d4f-0e2c-4144-bb2f-7b8c0d9b4817');
--
-- INSERT INTO booking (created_date, customer_id, p_id, price, status, stripe_session_id) VALUES ('2024-08-01', 1, 1, 100.00, 'COMPLETED', 'session_1');
-- INSERT INTO booking (created_date, customer_id, p_id, price, status, stripe_session_id) VALUES ('2024-08-02', 2, 2, 150.00, 'PENDING', 'session_2');
-- INSERT INTO booking (created_date, customer_id, p_id, price, status, stripe_session_id) VALUES ('2024-08-03', 3, 3, 200.00, 'CANCELED', 'session_3');
-- INSERT INTO booking (created_date, customer_id, p_id, price, status, stripe_session_id) VALUES ('2024-08-04', 4, 4, 120.00, 'COMPLETED', 'session_4');
-- INSERT INTO booking (created_date, customer_id, p_id, price, status, stripe_session_id) VALUES ('2024-08-05', 5, 5, 130.00, 'PENDING', 'session_5');
-- INSERT INTO booking (created_date, customer_id, p_id, price, status, stripe_session_id) VALUES ('2024-08-06', 6, 6, 110.00, 'CANCELED', 'session_6');
-- INSERT INTO booking (created_date, customer_id, p_id, price, status, stripe_session_id) VALUES ('2024-08-07', 7, 7, 140.00, 'COMPLETED', 'session_7');
-- INSERT INTO booking (created_date, customer_id, p_id, price, status, stripe_session_id) VALUES ('2024-08-08', 8, 8, 160.00, 'PENDING', 'session_8');
-- INSERT INTO booking (created_date, customer_id, p_id, price, status, stripe_session_id) VALUES ('2024-08-09', 9, 9, 170.00, 'CANCELED', 'session_9');
-- INSERT INTO booking (created_date, customer_id, p_id, price, status, stripe_session_id) VALUES ('2024-08-10', 10, 10, 125.00, 'COMPLETED', 'session_10');
-- INSERT INTO booking (created_date, customer_id, p_id, price, status, stripe_session_id) VALUES ('2024-08-11', 11, 11, 135.00, 'PENDING', 'session_11');
-- INSERT INTO booking (created_date, customer_id, p_id, price, status, stripe_session_id) VALUES ('2024-08-12', 12, 12, 145.00, 'CANCELED', 'session_12');
-- INSERT INTO booking (created_date, customer_id, p_id, price, status, stripe_session_id) VALUES ('2024-08-13', 13, 13, 155.00, 'COMPLETED', 'session_13');
-- INSERT INTO booking (created_date, customer_id, p_id, price, status, stripe_session_id) VALUES ('2024-08-14', 14, 14, 165.00, 'PENDING', 'session_14');
-- INSERT INTO booking (created_date, customer_id, p_id, price, status, stripe_session_id) VALUES ('2024-08-15', 15, 15, 175.00, 'CANCELED', 'session_15');
-- INSERT INTO booking (created_date, customer_id, p_id, price, status, stripe_session_id) VALUES ('2024-08-16', 16, 16, 185.00, 'COMPLETED', 'session_16');
-- INSERT INTO booking (created_date, customer_id, p_id, price, status, stripe_session_id) VALUES ('2024-08-17', 17, 17, 195.00, 'PENDING', 'session_17');
-- INSERT INTO booking (created_date, customer_id, p_id, price, status, stripe_session_id) VALUES ('2024-08-18', 18, 18, 205.00, 'CANCELED', 'session_18');
-- INSERT INTO booking (created_date, customer_id, p_id, price, status, stripe_session_id) VALUES ('2024-08-19', 19, 19, 215.00, 'COMPLETED', 'session_19');
-- INSERT INTO booking (created_date, customer_id, p_id, price, status, stripe_session_id) VALUES ('2024-08-20', 20, 20, 225.00, 'PENDING', 'session_20');
