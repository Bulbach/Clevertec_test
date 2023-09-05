--CREATE DATABASE myClevertecDB;
 CREATE TABLE IF NOT EXISTS banks (
    id serial PRIMARY KEY,
    brand varchar(20) UNIQUE
);
--TRUNCATE TABLE IF EXISTS clients
CREATE TABLE IF NOT EXISTS clients(
    id serial PRIMARY KEY,
    personalIdentifier varchar(30) UNIQUE,
    first_name varchar(20),
    surname varchar(20)
);
create table IF NOT EXISTS bank_accounts(
    id serial,
    account_number varchar(35) primary key unique,
    client_id bigint not null,
    bank_id bigint not null,
    currency varchar(10),
    open_date date,
    account_balance bigint,
    foreign key (client_id) references clients(id) on delete cascade ,
    foreign key (bank_id) references banks(id) on delete cascade
);

CREATE TABLE IF NOT EXISTS bank_transactions(
    id serial PRIMARY KEY,
    time_operation timestamp,
    type_transaction varchar(20),
    sender_account varchar(30) NOT NULL,
    sender_balance_before bigint,
    sender_bank varchar(30),
    recipient_account varchar(30) NOT NULL,
    recipient_balance_before bigint,
    recipient_bank varchar(30),
    amount bigint
);