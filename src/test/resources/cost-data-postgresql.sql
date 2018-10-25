insert into device_types (id, name)
values (1, 'Windows Workstation');
insert into device_types (id, name)
values (2, 'Windows Server');
insert into device_types (id, name)
values (3, 'Mac');

insert into device_type_groups (id, name)
values (1, 'Windows');
insert into device_type_groups (id, name)
values (2, 'Mac');
insert into device_type_groups (id, name)
values (3, 'Any');

insert into device_types_by_groups (device_type_group_id, device_type_id)
values (1, 1);
insert into device_types_by_groups (device_type_group_id, device_type_id)
values (1, 2);
insert into device_types_by_groups (device_type_group_id, device_type_id)
values (2, 3);
insert into device_types_by_groups (device_type_group_id, device_type_id)
values (3, 1);
insert into device_types_by_groups (device_type_group_id, device_type_id)
values (3, 2);
insert into device_types_by_groups (device_type_group_id, device_type_id)
values (3, 3);

insert into services (id, name, description)
values (1, 'Antivirus', 'To have antivirus in their devices.');
insert into services (id, name, description)
values (2, 'Cloudberry', 'To backup data in their devices.');
insert into services (id, name, description)
values (3, 'PSA', 'Ticketing system for alerts in their devices.');
insert into services (id, name, description)
values (4, 'TeamViewer', 'Remote connection to devices.');

insert into services_cost (id, service_id, monthly_cost, device_type_group_id)
values (1, 1, 5.00, 1);
insert into services_cost (id, service_id, monthly_cost, device_type_group_id)
values (2, 1, 7.00, 2);
insert into services_cost (id, service_id, monthly_cost, device_type_group_id)
values (3, 2, 3.00, 3);
insert into services_cost (id, service_id, monthly_cost, device_type_group_id)
values (4, 3, 2.00, 3);
insert into services_cost (id, service_id, monthly_cost, device_type_group_id)
values (5, 4, 1.00, 3);

insert into customers (id, name)
values (1, 'Nelson Gonzalez');

insert into devices(id, system_name, device_type_id, customer_id)
values (1, 'DEVICE01', 1, 1);
insert into devices(id, system_name, device_type_id, customer_id)
values (2, 'DEVICE02', 1, 1);
insert into devices(id, system_name, device_type_id, customer_id)
values (3, 'DEVICE03', 3, 1);
insert into devices(id, system_name, device_type_id, customer_id)
values (4, 'DEVICE04', 3, 1);
insert into devices(id, system_name, device_type_id, customer_id)
values (5, 'DEVICE05', 3, 1);

insert into account_services(id, service_id, customer_id)
values (1, 1, 1);
insert into account_services(id, service_id, customer_id)
values (2, 2, 1);
insert into account_services(id, service_id, customer_id)
values (3, 4, 1);

alter sequence devices_id_seq restart with 100;
alter sequence account_services_id_seq restart with 100;
alter sequence customers_id_seq restart with 100;
alter sequence device_type_groups_id_seq restart with 100;
alter sequence device_types_id_seq restart with 100;
alter sequence services_cost_id_seq restart with 100;
alter sequence services_id_seq restart with 100;
