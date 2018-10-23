create table device_types
(
  id   serial primary key,
  name varchar(25) not null
);

create table services
(
  id          serial primary key,
  name        varchar(25)  not null,
  description varchar(100) not null
);

create table services_cost
(
  id             serial primary key,
  service_id     integer references services(id) not null,
  monthly_cost   numeric(10, 2)                  not null,
  device_type_id integer references device_types (id)
);

create table customers
(
  id   serial primary key,
  name varchar(25) unique not null
);

create table customer_devices
(
  id             serial primary key,
  system_name    varchar(25) unique                  not null,
  device_type_id integer references device_types(id) not null,
  owner_id       integer references customers(id)    not null
);

create table customer_services
(
  id         serial primary key,
  service_id integer references services(id)  not null,
  owner_id   integer references customers(id) not null,
  constraint unique_same_service_per_customer unique(owner_id, service_id)
);
