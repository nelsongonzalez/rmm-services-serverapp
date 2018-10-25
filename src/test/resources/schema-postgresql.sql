drop table if exists device_types_by_groups;
drop table if exists services_cost;
drop table if exists device_type_groups;
drop table if exists account_services;
drop table if exists devices;
drop table if exists device_types;
drop table if exists services;
drop table if exists customers;
drop sequence if exists devices_id_seq;
drop sequence if exists account_services_id_seq;
drop sequence if exists customers_id_seq;
drop sequence if exists device_type_groups_id_seq;
drop sequence if exists device_types_id_seq;
drop sequence if exists services_cost_id_seq;
drop sequence if exists services_id_seq;

create table device_types
(
  id   serial primary key,
  name varchar(25) not null
);

create table device_type_groups
(
  id   serial primary key,
  name varchar(25) not null
);

create table device_types_by_groups
(
  device_type_group_id integer references device_type_groups (id) not null,
  device_type_id       integer references device_types (id)       not null
);

create table services
(
  id          serial primary key,
  name        varchar(25)  not null,
  description varchar(100) not null
);

create table services_cost
(
  id                   serial primary key,
  service_id           integer references services (id)           not null,
  monthly_cost         numeric(10, 2)                             not null,
  device_type_group_id integer references device_type_groups (id) not null
);

create table customers
(
  id   serial primary key,
  name varchar(25) unique not null
);

create table devices
(
  id             serial primary key,
  system_name    varchar(25) unique                   not null,
  device_type_id integer references device_types (id) not null,
  customer_id    integer references customers (id)    not null
);

create table account_services
(
  id          serial primary key,
  service_id  integer references services (id)  not null,
  customer_id integer references customers (id) not null,
  constraint unique_same_service_per_customer unique (customer_id, service_id)
);
