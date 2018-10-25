insert into device_types (id, name)
values (1, 'Windows Workstation');
insert into device_types (id, name)
values (2, 'Windows Server');
insert into device_types (id, name)
values (3, 'Mac');

insert into services (id, name, description)
values (1, 'Antivirus', 'To have antivirus in their devices.');
insert into services (id, name, description)
values (2, 'Cloudberry', 'To backup data in their devices.');
insert into services (id, name, description)
values (3, 'PSA', 'Ticketing system for alerts in their devices.');
insert into services (id, name, description)
values (4, 'TeamViewer', 'Remote connection to devices.');

insert into customers (id, name)
values (1, 'Nelson Gonzalez');

insert into devices(id, system_name, device_type_id, customer_id)
values (1, 'WSERVER01', 2, 1);
insert into devices(id, system_name, device_type_id, customer_id)
values (2, 'WSERVER02', 3, 1);

insert into account_services(id, service_id, customer_id)
values (1, 1, 1);
insert into account_services(id, service_id, customer_id)
values (2, 3, 1);

alter sequence devices_id_seq restart with 100;
alter sequence account_services_id_seq restart with 100;
alter sequence customers_id_seq restart with 100;
alter sequence device_types_id_seq restart with 100;
alter sequence services_cost_id_seq restart with 100;
alter sequence services_id_seq restart with 100;
