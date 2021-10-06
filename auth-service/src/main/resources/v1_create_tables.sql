CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

drop sequence if exists sq_roles;
drop sequence if exists sq_claims;

drop table if exists ROLES;
drop table if exists users;
drop table if exists users_roles;
drop table if exists claims;
drop table if exists roles_claims;


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
	username VARCHAR(30) not null,
	email VARCHAR(30) not null,
	password varchar(30) not null,
	phoneNumber varchar(30) null,
	tokenAccess varchar(50) null,
	deviceAccess varchar(50) null,
	lastAccess TIMESTAMP null,
	twoFactoryAuthentication boolean null,
	isActive boolean null,
	isLocked boolean null,
	createAt TIMESTAMP not null,
	updateAt TIMESTAMP not null
);

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
)

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
