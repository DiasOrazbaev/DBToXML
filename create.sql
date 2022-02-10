create table customer
(
    id        serial primary key,
    firstName varchar(255),
    lastName  varchar(255),
    balance   float default 0.0,
    phone     varchar(25)
);

create table items
(
    id          serial primary key,
    name        varchar(46),
    description varchar(356),
    price       float default 0.0
);

create table orders
(
    id         serial primary key,
    customerID int REFERENCES customer (id),
    itemID     int REFERENCES items (id),
    amount     int   not null,
    totalPrice float not null
);