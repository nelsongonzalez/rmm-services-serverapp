insert into device_types (name)
values ('Windows Workstation');

insert into services (name, description)
values ('Antivirus', 'To have antivirus in their devices.');

insert into services_cost (service_id, device_type_id, monthly_cost)
values (currval('services_id_seq'), currval('device_types_id_seq'), 5.00);

insert into device_types (name)
values ('Windows Server');

insert into services_cost (service_id, device_type_id, monthly_cost)
values (currval('services_id_seq'), currval('device_types_id_seq'), 5.00);

insert into device_types (name)
values ('Mac');

insert into services_cost (service_id, device_type_id, monthly_cost)
values (currval('services_id_seq'), currval('device_types_id_seq'), 7.00);

insert into services (name, description)
values ('Cloudberry', 'To backup data in their devices.');

insert into services_cost (service_id, device_type_id, monthly_cost)
values (currval('services_id_seq'), null, 3.00);

insert into services (name, description)
values ('PSA', 'Ticketing system for alerts in their devices.');

insert into services_cost (service_id, device_type_id, monthly_cost)
values (currval('services_id_seq'), null, 2.00);

insert into services (name, description)
values ('TeamViewer', 'Remote connection to devices.');

insert into services_cost (service_id, device_type_id, monthly_cost)
values (currval('services_id_seq'), null, 1.00);

insert into customers (name)
values ('Nelson Gonzalez');
