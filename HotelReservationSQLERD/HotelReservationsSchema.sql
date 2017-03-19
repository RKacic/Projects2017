drop database if exists HotelReservations;

Create database HotelReservations;

Use HotelReservations;

create table Reservations (
	ReservationID int(6) auto_increment not null,
    CustomerID int not null,
    CheckIn datetime not null,
    checkOut datetime not null,
    primary key (ReservationID) 
);

create table Bills (
	BillID int(6) auto_increment not null,
    ReservationID int not null,
    TaxID int not null,
    Total int not null,
    Paid boolean not null default 0,
    PromoID int  null,
    PromoAmount int null,
    primary key (BillID)
);

create table Promos (
	PromoID int(6) auto_increment not null,
    PromoType varchar(10) not null,
    primary key (PromoID)
);

create table Customers (	
	CustomerID int(6) auto_increment not null,
    FirstName varchar(30) not null,
    LastName varchar(30) not null,
    CompanyName varchar(50) null,
    Address varchar(80) not null,
    Phone varchar(10) not null,
    Email varchar(40) null,
    primary key (CustomerID)
);

create table ResToRooms (
    ReservationID int not null,
    OccupiedRoomID int not null,
    primary key (ReservationID, OccupiedRoomID)
);

create table OccupiedRooms (
	OccupiedRoomID int(6) auto_increment not null,
    RoomDetailsID int not null,
    primary key (OccupiedRoomID)
);

create table RoomDetails (
	RoomDetailsID int(6) auto_increment not null,
    Style varchar(40) not null,
    NumberOfBeds int(1) not null,
    MaxOccupancy int(1) not null,
    Price int(3) not null,
    Floor int(1) not null,
    RoomNumber int (3) not null,
    StartDate date not null,
    EndDate date null,
    primary key (RoomDetailsID)
);

create table Guests (
	GuestID int(6) auto_increment not null,
    Name varchar(50) not null,
    Age int(3) not null,
    primary key (GuestID)
);

create table GuestsToRoom (
	OccupiedRoomID int not null,
    GuestID int not null,
    primary key (GuestID, OccupiedRoomID)
);

create table AmenitiesPerStay (
	OccupiedRoomID int not null,
    AmenityID int not null,
    primary key (OccupiedRoomID, AmenityID)
);

create table Amenities (
	AmenityID int(6) auto_increment not null,
    Name varchar(30) not null,
    Description varchar(50) null,
    Price int(5) null,
    StartDate date not null,
    EndDate date null,
    primary key (AmenityID)
);

create table Taxes (
	TaxID int(6) auto_increment not null,
    TaxRate double(4,2) not null,
    State varchar(2) not null, -- state initials
    primary key (TaxID)
);

alter table Reservations
add constraint fk_Reservations_CustomerID
foreign key (CustomerID)
references Customers(CustomerID);

alter table Bills
add constraint fk_Bills_TaxID
foreign key (TaxID)
references Taxes(TaxID);

alter table Bills
add constraint fk_Bills_PromoID
foreign key (PromoID)
references Promos(PromoID);

alter table Bills
add constraint fk_Bills_ReservationID
foreign key (ReservationID)
references Reservations(ReservationID);

alter table ResToRooms
add constraint fk_ResToRoom_ReservationID
foreign key (ReservationID)
references Reservations(ReservationID);

alter table ResToRooms
add constraint fk_ResToRooms_OccupiedRoomID
foreign key (OccupiedRoomID)
references OccupiedRooms(OccupiedRoomID);

alter table OccupiedRooms
add constraint fk_OccupiedRooms_RoomDetailsID
foreign key (RoomDetailsID)
references RoomDetails(RoomDetailsID);

alter table AmenitiesPerStay
add constraint fk_AmenitiesPerStay_OccupiedRoomID
foreign key (OccupiedRoomID)
references OccupiedRooms(OccupiedRoomID);

alter table AmenitiesPerStay
add constraint fk_AmenitiesPerStay_AmenitiesID
foreign key (AmenityID)
references Amenities(AmenityID);

alter table GuestsToRoom
add constraint fk_Guests_GuestID
foreign key (GuestID)
references Guests(GuestID);

alter table GuestsToRoom
add constraint fk_OccupiedRooms_OccupiedRoomID
foreign key (OccupiedRoomID)
references OccupiedRooms(OccupiedRoomID);

