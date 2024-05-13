CREATE DATABASE sportApp;
USE sportApp;

-- create table schema

CREATE TABLE Group(
    GroupId int NOT NULL AUTO_INCREMENT,
    GroupName varchar(100) NOT NULL,
    PRIMARY KEY (GroupId)
);

CREATE TABLE Court(
    CourtId int NOT NULL AUTO_INCREMENT,
    CourtName varchar(100) NOT NULL,
    Address varchar(1000) NULL,
    Phone varchar(10) NULL,
    GroupId int,

    PRIMARY KEY (CourtId),
    
    INDEX group_id (GroupId),
    FOREIGN KEY (GroupId)
        REFERENCES Group(GroupId)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE Product(
    ProductId int NOT NULL AUTO_INCREMENT,
    ProductName varchar(100) NOT NULL,
    ProductPrice float NOT NULL,
    ProductAmount int NOT NULL,
    CourtId int,

    PRIMARY KEY (CourtId),

    INDEX prod_court_id (CourtId),
    FOREIGN KEY (CourtId)
        REFERENCES Court(CourtId)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE Court_Customer(
    CourtId int NOT NULL,
    CustId int NOT NULL,
    LoyaltyPoint float NULL,

    PRIMARY KEY (CourtId),
    PRIMARY KEY (CustId),

    INDEX cc_court_id (CourtId),
    FOREIGN KEY (CourtId)
        REFERENCES Court(CourtId)
        ON UPDATE CASCADE
        ON DELETE CASCADE,

    INDEX cc_cust_id (CustId),
    FOREIGN KEY (CustId)
        REFERENCES Customer(CustId)
        ON UPDATE CASCADE
        ON DELETE CASCADE

);

CREATE TABLE Customer(
    CustId int NOT NULL AUTO_INCREMENT,
    FirstName varchar(100) NOT NULL,
    LastName varchar(100) NOT NULL,
    Phone varchar(10) NULL,
    Email varchar(1000) NULL,

    PRIMARY KEY (CustId)
);

CREATE TABLE Field (
    FieldId int NOT NULL AUTO_INCREMENT,
    FieldName varchar(100) NOT NULL,
    SportType varchar(100) NOT NULL,
    CourtId int,

    PRIMARY KEY (FieldId),   
    INDEX field_court_id (CourtId),
    FOREIGN KEY (CourtId)
        REFERENCES Court(CourtId)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE TimeSlot(
    TimeSlotId int NOT NULL AUTO_INCREMENT,
    StartTime time,
    EndTime time,
    CourtId int,

    PRIMARY KEY (TimeSlotId),
    
    INDEX ts_court_id (CourtId),
    FOREIGN KEY (CourtId)
        REFERENCES Group(CourtId)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE Field_TimeSlot(
    FieldId int NOT NULL,
    TimeSlotId int NOT NULL,
    Price float NOT NULL,
    DayInWeek int NOT NULL,

    PRIMARY KEY (FieldId),
    PRIMARY KEY (TimeSlotId),

    INDEX fts_field_id (FieldId),
    FOREIGN KEY (FieldId)
        REFERENCES Field(FieldId)
        ON UPDATE CASCADE
        ON DELETE CASCADE.
    
    INDEX fts_timeslot_id (TimeSlotId),
    FOREIGN KEY (TimeSlotId)
        REFERENCES TimeSlot(TimeSlotId)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE Booking (
    BookingId int NOT NULL AUTO_INCREMENT,
    BookingDate
    CustId int,
    FieldId int,
    TimeSlotId int,
    PaymentId int,

    PRIMARY KEY (BookingId),

    INDEX b_cust_id (CustId),
    FOREIGN KEY (CustId)
        REFERENCES Customer(CustId)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    INDEX b_field_id (FieldId),
    FOREIGN KEY (FieldId)
        REFERENCES Field(FieldId)
        ON UPDATE CASCADE
        ON DELETE CASCADE.
    INDEX b_timeslot_id (TimeSlotId),
    FOREIGN KEY (TimeSlotId)
        REFERENCES TimeSlot(TimeSlotId)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    INDEX b_payment_id (PaymentId),
    FOREIGN KEY (PaymentId)
        REFERENCES Payment(PaymentId)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE Payment(
    PaymentId int NOT NULL AUTO_INCREMENT,
    PaymentType int NOT NULL,

    PRIMARY KEY (PaymentId)
);

-- insert for each table

-- 1) Group
INSERT INTO Group(GroupId,GroupName)
VALUES (1,"Dung Cau Long"), (2,"Vuong Bong Ro");

-- 2) Court
INSERT INTO Court(CourtId,CourtName,Address,Phone,GroupId)
VALUES (1,"Dung Cau Long - Chi nhanh 1", "abc xyz", "0123456789",1),
(2,"Dung Cau Long - Chi nhanh 2","def ghi", "0987654321",1);

-- 3) Product
INSERT INTO Product(ProductName,ProductPrice,ProductAmount,CourtId)
VALUES ("Sting Dau", 8000, 1, 1),("Pepsi", 10000,1,1),("7Up",10500,1,1);

-- 4) Customer
INSERT INTO Customer(CustId, FirstName, LastName, Phone, Email)
VALUES (1,"Dung","Nguyen",0112349395,"dung.nguyen@yahoo.com"),(2,"Vuong","Tran",0482039493,"vuong.tran@hotmail.com");

-- 5) Court_Customer
INSERT INTO Court_Customer(CourtId,CustId,LoyaltyPoint)
VALUES (1,1,500), (1,2,300);

-- 6) Field
INSERT INTO Field(FieldId, FieldName, SportType, CourtId)
VALUES (1,"San 1", 1, 1), (2,"San 2",1,1), (3,"San 3",1,1);

-- 7) Time Slot
INSERT INTO TimeSlot(TimeSlotId, StartTime, EndTime, Price, DayInWeek)
VALUES (1,str_to_date('0500', '%h%i'),str_to_date('0530', '%h%i'),50000,1),
       (2,str_to_date('0530', '%h%i'),str_to_date('0600', '%h%i'),50000,1),
       (3,str_to_date('0600', '%h%i'),str_to_date('0630', '%h%i'),55000,1);

-- 8) Payment
INSERT INTO Payment(PaymentId, PaymentType)
VALUES (1,1), (2,2),(3,1);

-- 9) Booking
INSERT INTO Booking(BookingDate, CustId, FieldId, TimeSlotId,PaymentId)
VALUES (str_to_date('2024-05-01', '%Y%m%d'),1,1,1,1),
       (str_to_date('2024-05-01', '%Y%m%d'),1,1,2,1);

