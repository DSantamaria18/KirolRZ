insert into centro_deportivo (id, nombre, created_at, updated_at) values (1, 'FADURA', now(), now());
insert into centro_deportivo (id, nombre, created_at, updated_at) values (2, 'GOBELA', now(), now());
insert into centro_deportivo (id, nombre, created_at, updated_at) values (3, 'ANDRA-MARI', now(), now());
insert into centro_deportivo (id, nombre, created_at, updated_at) values (4, 'ROMO', now(), now());


insert into centro_salud (id, nombre, created_at, updated_at) values (1, 'CRUCES', now(), now());
insert into centro_salud (id, nombre, created_at, updated_at) values (2, 'AMBULATORIO-ALGORTA', now(), now());
insert into centro_salud (id, nombre, created_at, updated_at) values (3, 'AMBULATORIO-GETXO', now(), now());
insert into centro_salud (id, nombre, created_at, updated_at) values (4, 'BASURTO', now(), now());


insert into medico (id, nss, nombre, apellidos, centro_salud_id, created_at, updated_at) values (1, 123451, 'nombre1', 'apellido11 apellido12',1, now(), now());
insert into medico (id, nss, nombre, apellidos, centro_salud_id, created_at, updated_at) values (2, 123452, 'nombre1', 'apellido11 apellido12',2, now(), now());
insert into medico (id, nss, nombre, apellidos, centro_salud_id, created_at, updated_at) values (3, 123453, 'nombre1', 'apellido11 apellido12',1, now(), now());
insert into medico (id, nss, nombre, apellidos, centro_salud_id, created_at, updated_at) values (4, 123454, 'nombre1', 'apellido11 apellido12',4, now(), now());


insert into tecnicogk (id, dni, nombre, apellidos, centro_deportivo_id, created_at, updated_at) values (1, '45671001A', 'tnombre1', 'tapellido11 tapellido12',1, now(), now());
insert into tecnicogk (id, dni, nombre, apellidos, centro_deportivo_id, created_at, updated_at) values (2, '45671002B', 'tnombre2', 'tapellido21 tapellido22',2, now(), now());
insert into tecnicogk (id, dni, nombre, apellidos, centro_deportivo_id, created_at, updated_at) values (3, '45671003C', 'tnombre3', 'tapellido31 tapellido32',3, now(), now());
insert into tecnicogk (id, dni, nombre, apellidos, centro_deportivo_id, created_at, updated_at) values (4, '45671004D', 'tnombre4', 'tapellido41 tapellido42',1, now(), now());