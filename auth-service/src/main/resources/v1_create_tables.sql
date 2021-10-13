CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

drop sequence if exists sq_roles;
drop sequence if exists sq_claims;

drop table if exists roles_claims;
drop table if exists claims;
drop table if exists users_roles;
drop table if exists ROLES;
drop table if exists users;




create table if not exists ROLES(
	roleid integer not null,
	name VARCHAR(30) not null
);

CREATE SEQUENCE sq_roles
INCREMENT 1
START 1;


alter table roles add primary key (roleid);
CREATE UNIQUE index idx_role_name on ROLES(name)

create table users(
	id UUID DEFAULT uuid_generate_v4() primary key,
	username VARCHAR(30) not NULL,
	email VARCHAR(30) not NULL,
	password varchar(100) not null,
	phoneNumber varchar(30) null,
	tokenAccess varchar(50) null,
	deviceAccess varchar(50) null,
	lastAccess TIMESTAMP null,
	twoFactoryAuthentication boolean null,
	isActive boolean null default true,
	isLocked boolean null default false,
	createdAt TIMESTAMP not null,
	updatedAt TIMESTAMP not null 
);

CREATE UNIQUE index idx_user_username on USERS(username);
CREATE UNIQUE index idx_user_email on USERS(email);

create table users_roles(
	userId UUID not null,
	roleId integer not null,
	primary KEY(userId,roleId),
	constraint fk_users_roles foreign key(userId) references users(id),
	constraint fk_roles_users foreign key(roleId) references roles(roleId)
);


create table claims(
	claimId integer not null,
	name varchar(30) not null,
	read boolean not null,
	write boolean not null,
	execute boolean not null,
	primary key (claimId)
);

CREATE SEQUENCE sq_claims
INCREMENT 1
START 1;

create table roles_claims(
	claimId integer not null,
	roleId integer not null,
	primary KEY(roleId,claimId),
	constraint fk_role_claim foreign key(roleId) references roles(roleId),
	constraint fk_claim_role foreign key(claimId) references claims(claimId)
);


select * from users u where u.username = 'admin';
delete from users_roles;
delete from users;
delete from ROLES;

select * from ROLES;
select * from users_roles ur inner join roles r 
on ur.roleid = r.roleid where ur.userid= 'a70d52bf-9a68-4608-89ef-ff960ed441c9';


insert into ROLES (roleid ,"name") VALUES(nextval('sq_roles'), 'MANAGER')
insert into ROLES (roleid ,"name") VALUES(nextval('sq_roles'), 'ADMIN')
insert into ROLES (roleid ,"name") VALUES(nextval('sq_roles'), 'ROOT')

INSERT into users_roles (userId,roleId) VALUES('a70d52bf-9a68-4608-89ef-ff960ed441c9',1);
INSERT into users_roles (userId,roleId) VALUES('a70d52bf-9a68-4608-89ef-ff960ed441c9',2);
INSERT into users_roles (userId,roleId) VALUES('a70d52bf-9a68-4608-89ef-ff960ed441c9',3);

