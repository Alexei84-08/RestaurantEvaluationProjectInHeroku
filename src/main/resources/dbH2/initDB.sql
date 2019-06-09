create schema PUBLIC;

create table RESTAURANTS
(
	ID INTEGER not null
		primary key,
	NAME VARCHAR(255) not null
);

create table DISHES
(
	ID INTEGER not null
		primary key,
	NAME VARCHAR(255) not null,
	DATE DATE not null,
	PRICE INTEGER,
	RESTAURANT_ID INTEGER not null,
	constraint FKPSLSA9MCI7GSFHWUKB3MX7S6N
		foreign key (RESTAURANT_ID) references RESTAURANTS
);

create table USERS
(
	ID INTEGER not null
		primary key,
	NAME VARCHAR(255) not null,
	EMAIL VARCHAR(255) not null
		unique,
	LOGIN VARCHAR(255) not null
		unique,
	PASSWORD VARCHAR(255) not null
);

create table USER_ROLES
(
	USER_ID INTEGER not null,
	ROLE VARCHAR(255),
	constraint FKHFH9DX7W3UBF1CO1VDEV94G3F
		foreign key (USER_ID) references USERS
);

create table VOTES
(
	ID INTEGER not null
		primary key,
	DATE DATE not null,
	TIME TIME not null,
	RESTAURANT_ID INTEGER not null,
	USER_ID INTEGER not null,
	constraint FK93NQD6KKY7CYVBE4Q1EUP9GCX
		foreign key (RESTAURANT_ID) references RESTAURANTS
			on delete cascade,
	constraint FKLI4UJ3IC2VYPF5PIALCHJ925E
		foreign key (USER_ID) references USERS
);

