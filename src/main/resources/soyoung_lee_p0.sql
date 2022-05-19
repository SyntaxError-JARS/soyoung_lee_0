select * from customer ;

select * from account;

select * from trans;



 drop table trans;
 drop table account;
 drop table customer;




create schema test_slee_p0;

CREATE table customer (
	customer_id 	serial 			primary key,
	customer_name 	varchar(30) 	not null,
	email_1	 		varchar(20) 	not null,
	phone_1 		varchar(20) 	not null,
	address 		varchar(100)	not null,
	login_id 		varchar(20) 	not null unique,
	login_password 	varchar(20) 	not null,
	block 			boolean 		default false
);

insert into customer
values
(default, 'name 1', 'go1@gmail.com', '111-333-9999', '12345 Street', 'go1', 'password', default),
(default, 'name 2', 'go2@gmail.com', '111-333-9999', '12345 Street', 'go2', 'password', default),
(default, 'name 3', 'go3@gmail.com', '111-333-9999', '12345 Street', 'go3', 'password', default),
(default, 'name 4', 'go4@gmail.com', '111-333-9999', '12345 Street', 'go4', 'password', default),
(default, 'name 5', 'go5@gmail.com', '111-333-9999', '12345 Street', 'go5', 'password', default),
(default, 'name 6', 'go6@gmail.com', '111-333-9999', '12345 Street', 'go6', 'password', default),
(default, 'name 7', 'go7@gmail.com', '111-333-9999', '12345 Street', 'go7', 'password', default),
(default, 'name 8', 'go8@gmail.com', '111-333-9999', '12345 Street', 'go8', 'password', default),
(default, 'name 9', 'go9@gmail.com', '111-333-9999', '12345 Street', 'go9', 'password', default);


CREATE table account (
	account_id serial primary key,
	account_number    varchar(20) unique not null,
	account_type      varchar(10) not null default 'saving', -- saving, check, closed
	first_date        varchar(15) not null,
	last_date         varchar(15) default null,
	-- interest          numeric not null default 0.03,
	interest		  numeric(5,2) not null default 0.03,
	total             numeric(12,2) default 0.00 check (total>=0.00),
	customer_id_1     int not null,
	customer_id_2     int
);


alter table account
add constraint fk_customer
foreign key(customer_id_1) references customer(customer_id)
--ON DELETE CASCADE;
;

insert into account
(account_id, account_number, account_type, first_date, last_date, interest, total, customer_id_1, customer_id_2 )
values
(default, '111122224444','saving', '01/20/2020', null, default, 3000.00, 6, default  ),
(default, '222222224444','saving', '01/20/2020', null, default, 3000.00, 1, 2  ),
(default, '333322224444','saving', '01/20/2020', null, default, 3000.00, 3, default  ),
(default, '444422224444','saving', '01/20/2020', null, default, 3000.00, 4, default  ),
(default, '555522224444','saving', '01/20/2020', null, default, 3000.00, 5, default );

insert into account
(account_id, account_number, account_type, first_date, last_date, interest, total, customer_id_1, customer_id_2 )
values
(default, '666622224444','checking', '01/20/2020', null, default, 2000.00, 7, default  ),
(default, '777722224444','checking', '01/20/2020', null, default, 3000.00, 8, default  ),
(default, '888822224444','checking', '01/20/2020', null, default, 2000.00, 2, default  ),
(default, '999922224444','checking', '01/20/2020', null, default, 2000.00, 9, default  );


create table trans (
	trans_id serial primary key,
	account_number 	varchar(20) not null,      -- foreign key
	trans_type 		varchar(10) not null, -- withdraw, deposit, transfer
	trans_date 		varchar(10) not null,
	from_account 	varchar(20) default '', -- trnasfer money from/to account number
	to_account 		varchar(20) default '', -- trnasfer money from/to account number
	amount 			numeric(12,2) not null default 0.00
);

alter table trans
add constraint fk_account
foreign key(account_number) references account(account_number)
--ON DELETE CASCADE;
;


insert into trans
(trans_id, account_number, trans_type, trans_date, from_account, to_account, amount)
values
(default, '111122224444', 'deposit', '4/21/2022', '111122224444', '', 1000.00),
(default, '111122224444', 'withdraw','4/22/2022', '111122224444', '', -1000.00),
(default, '111122224444', 'deposit', '4/23/2022', '111122224444', '', 1000.00),
(default, '222222224444', 'deposit', '4/21/2022', '222222224444', '', 1000.00),
(default, '222222224444', 'withdraw','4/22/2022', '222222224444', '', -1000.00),
(default, '333322224444', 'deposit', '4/21/2022', '333322224444', '', 1000.00),
(default, '333322224444', 'deposit', '4/22/2022', '333322224444', '', 1000.00),
(default, '333322224444', 'deposit', '4/23/2022', '333322224444', '', 1000.00),
(default, '444422224444', 'withdraw','4/21/2022', '444422224444', '', -1000.00),
(default, '444422224444', 'deposit', '4/22/2022', '444422224444', '', 1000.00),
(default, '555522224444', 'deposit', '4/21/2022', '555522224444', '', 1000.00),
(default, '555522224444', 'deposit', '4/22/2022', '555522224444', '', 1000.00),
(default, '666622224444', 'deposit', '4/21/2022', '666622224444', '', 1000.00),
(default, '666622224444', 'deposit', '4/22/2022', '666622224444', '', 1000.00),
(default, '777722224444', 'deposit', '4/21/2022', '777722224444', '', 1000.00),
(default, '777722224444', 'withdraw','4/22/2022', '777722224444', '', -1000.00),
(default, '777722224444', 'deposit', '4/23/2022', '777722224444', '', 1000.00),
(default, '888822224444', 'deposit', '4/21/2022', '888822224444', '', 1000.00),
(default, '888822224444', 'withdraw','4/22/2022', '888822224444', '', -1000.00),
(default, '999922224444', 'deposit', '4/21/2022', '999922224444', '', 1000.00),
(default, '999922224444', 'deposit', '4/22/2022', '999922224444', '', 1000.00),
(default, '999922224444', 'deposit', '4/23/2022', '999922224444', '', 1000.00);

UPDATE account a
    SET total = t.tot_balance
FROM (SELECT account_number, SUM(amount) as tot_balance
      FROM trans
      GROUP BY account_number
     ) t

WHERE a.account_number = t.account_number;

