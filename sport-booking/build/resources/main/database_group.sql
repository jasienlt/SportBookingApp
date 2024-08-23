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
    , created_date date
    , booking_id bigint
    , payment_type varchar(50) NOT NULL
    , payment_evidence varchar(5000)
    , payment_stts ENUM('SUCCESSFUL','PENDING', 'CANCELED') NOT NULL
    , PRIMARY KEY (id)
);

CREATE TABLE booking (
    id bigint NOT NULL AUTO_INCREMENT
    , created_date date
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
    , 'abc xyz'
    , '0123456789'
    , 1
    , 3
    )
        , (
            'Dung Cau Long - Chi nhanh 2'
          , 'def ghi'
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
        ('Field A', 'Soccer', 1),
        ('Field B', 'Basketball', 2),
        ('Field C', 'Tennis', 3),
        ('Field D', 'Volleyball', 4),
        ('Field E', 'Badminton', 5),
        ('Field F', 'Soccer', 1),
        ('Field G', 'Basketball', 2),
        ('Field H', 'Tennis', 3),
        ('Field I', 'Volleyball', 4),
        ('Field J', 'Badminton', 5);


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

-- 8.5) Reserved Field - Timeslot
INSERT INTO reserved_field_timeslot (fts_id, booking_id, booking_date) VALUES
     (1, 1, '2024-08-21'),
    (2, 2, '2024-08-22'),
    (3, 3, '2024-08-23'),
    (4, 4, '2024-08-24'),
    (5, 5, '2024-08-25'),
    (6, 6, '2024-08-26'),
    (7, 7, '2024-08-27'),
    (8, 8, '2024-08-28'),
    (9, 9, '2024-08-29'),
    (1, 10, '2024-08-30');


-- 9) Payment
INSERT INTO payment (created_date, booking_id, payment_type, payment_evidence, payment_stts) VALUES
    ('2024-08-14', 1, 'Stripe', 'evidence_1.jpg', 'SUCCESSFUL'),
    ('2024-08-14', 2, 'Bank Transfer', 'evidence_2.jpg', 'PENDING'),
    ('2024-08-15', 3, 'Stripe', 'evidence_3.jpg', 'CANCELED'),
    ('2024-08-15', 4, 'Bank Transfer', 'evidence_4.jpg', 'SUCCESSFUL'),
    ('2024-08-16', 5, 'Stripe', 'evidence_5.jpg', 'PENDING'),
    ('2024-08-16', 6, 'Bank Transfer', 'evidence_6.jpg', 'SUCCESSFUL'),
    ('2024-08-17', 7, 'Stripe', 'evidence_7.jpg', 'CANCELED'),
    ('2024-08-17', 8, 'Bank Transfer', 'evidence_8.jpg', 'SUCCESSFUL'),
    ('2024-08-18', 9, 'Stripe', 'evidence_9.jpg', 'PENDING'),
    ('2024-08-18', 10, 'Bank Transfer', 'evidence_10.jpg', 'SUCCESSFUL'),
    ('2024-08-19', 11, 'Stripe', 'evidence_11.jpg', 'CANCELED'),
    ('2024-08-19', 12, 'Bank Transfer', 'evidence_12.jpg', 'SUCCESSFUL'),
    ('2024-08-20', 13, 'Stripe', 'evidence_13.jpg', 'PENDING'),
    ('2024-08-20', 14, 'Bank Transfer', 'evidence_14.jpg', 'SUCCESSFUL'),
    ('2024-08-21', 15, 'Stripe', 'evidence_15.jpg', 'CANCELED'),
    ('2024-08-21', 16, 'Bank Transfer', 'evidence_16.jpg', 'SUCCESSFUL'),
    ('2024-08-14', 17, 'Stripe', 'evidence_17.jpg', 'PENDING'),
    ('2024-08-15', 18, 'Bank Transfer', 'evidence_18.jpg', 'SUCCESSFUL'),
    ('2024-08-16', 19, 'Stripe', 'evidence_19.jpg', 'CANCELED'),
    ('2024-08-17', 20, 'Bank Transfer', 'evidence_20.jpg', 'SUCCESSFUL');

-- 10) Booking
INSERT INTO booking (created_date, customer_id, p_id, price, status, stripe_session_id) VALUES
        ('2024-08-14', 1, 1, 150.50, 'COMPLETED', 'ec28a5bc-d015-4d10-a8ed-7c1a178c1c7e'),
        ('2024-08-14', 2, 2, 200.75, 'PENDING', '04eabe75-1a30-409e-8c4e-789a0c6aeb17'),
        ('2024-08-15', 1, 3, 320.00, 'CANCELED', 'bfdc12fe-9298-4f7d-9a6a-1c7f88a2a8a6'),
        ('2024-08-15', 4, 4, 450.25, 'COMPLETED', '2b8f924e-fd69-44da-b731-890d1df9d2a7'),
        ('2024-08-16', 2, 5, 275.50, 'PENDING', 'a3bcd854-81fb-4d95-a659-d5f8f394e248'),
        ('2024-08-16', 3, 6, 199.99, 'COMPLETED', 'fe7adf76-bd23-4e96-99fd-7049f7d839e7'),
        ('2024-08-17', 6, 7, 399.49, 'CANCELED', '8be328ac-74cc-45f5-b938-cd76a2556df6'),
        ('2024-08-17', 2, 8, 620.89, 'PENDING', '229a7bf8-9c7e-48d5-89d9-84f557b0e48b'),
        ('2024-08-18', 1, 9, 720.35, 'COMPLETED', 'df64e4bb-7591-4b55-bf64-c47c6792eaf5'),
        ('2024-08-18', 3, 10, 815.75, 'PENDING', 'f8c83a91-230e-4f6a-8ed7-438efce24b47'),
        ('2024-08-19', 10, 11, 450.00, 'CANCELED', '4f274c3e-7834-4be0-9096-0ffcdc924d89'),
        ('2024-08-19', 1, 12, 530.50, 'COMPLETED', 'a1dfb04a-292b-4c1e-a4fe-5f91b1839a1c'),
        ('2024-08-20', 7, 13, 305.99, 'PENDING', '839f5cd4-fdb7-4986-bb85-c6f5db60cfc8'),
        ('2024-08-20', 9, 14, 688.20, 'COMPLETED', 'ef1fa57f-ff89-4501-8eae-8e76114c6e27'),
        ('2024-08-21', 3, 15, 402.75, 'CANCELED', '06c1de1f-1a69-4de3-816d-918aeb39f3c4'),
        ('2024-08-21', 5, 16, 550.45, 'PENDING', 'd1cb41f1-84cf-4a8e-859b-2b7390d47f4f'),
        ('2024-08-21', 4, 17, 650.25, 'COMPLETED', 'f82bfe50-d172-4911-95cb-76d4b7e36d83'),
        ('2024-08-21', 9, 18, 245.99, 'PENDING', 'd4b27425-5c47-4650-840e-06dbed18306a'),
        ('2024-08-21', 5, 19, 350.60, 'COMPLETED', 'b9e0f46a-baae-44ed-85b6-5e2526a939ff'),
        ('2024-08-21', 6, 20, 299.99, 'CANCELED', 'a9f11d4f-0e2c-4144-bb2f-7b8c0d9b4817');