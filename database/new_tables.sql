create table user(
	user_id int auto_increment,
    fname varchar(10) NOT NULL,
	lname varchar(10) NOT NULL,
	email varchar(25) NOT NUll,
	password varchar(15) NOT NULL,
    primary key (user_id));


create table post 
(post_id int NOT NULL auto_increment,
data text,
likes int,
user_id int NOT NULL ,
created_timestamp timestamp NOT NULL,
primary key(post_id),
foreign key (user_id) references User(user_id)
);



