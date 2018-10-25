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

insert into services_cost (id, service_id, monthly_cost, device_type_id)
values (1, 1, 5.00, 1);
insert into services_cost (id, service_id, monthly_cost, device_type_id)
values (2, 1, 5.00, 2);
insert into services_cost (id, service_id, monthly_cost, device_type_id)
values (3, 1, 7.00, 3);
insert into services_cost (id, service_id, monthly_cost, device_type_id)
values (4, 2, 3.00, 1);
insert into services_cost (id, service_id, monthly_cost, device_type_id)
values (5, 2, 3.00, 2);
insert into services_cost (id, service_id, monthly_cost, device_type_id)
values (6, 2, 3.00, 3);
insert into services_cost (id, service_id, monthly_cost, device_type_id)
values (7, 3, 2.00, 1);
insert into services_cost (id, service_id, monthly_cost, device_type_id)
values (8, 3, 2.00, 2);
insert into services_cost (id, service_id, monthly_cost, device_type_id)
values (9, 3, 2.00, 3);
insert into services_cost (id, service_id, monthly_cost, device_type_id)
values (10, 4, 1.00, 1);
insert into services_cost (id, service_id, monthly_cost, device_type_id)
values (11, 4, 1.00, 2);
insert into services_cost (id, service_id, monthly_cost, device_type_id)
values (12, 4, 1.00, 3);

insert into customers (id, name)
values (1, 'Nelson Gonzalez');

alter sequence devices_id_seq restart with 100;
alter sequence account_services_id_seq restart with 100;
alter sequence customers_id_seq restart with 100;
alter sequence device_types_id_seq restart with 100;
alter sequence services_cost_id_seq restart with 100;
alter sequence services_id_seq restart with 100;
