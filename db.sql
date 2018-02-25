CREATE TABLE station(
   stationid integer  primary key   NOT NULL,
   name           varchar(50)    NOT NULL
  
);

-- NOTICE:  CREATE TABLE / PRIMARY KEY will create implicit index "station_pkey" for table "station"

CREATE TABLE track(
   trackid integer  primary key   NOT NULL,
   stationid1       integer    NOT NULL,
   stationid2      integer    NOT NULL,
   distance integer NOT NULL,
   foreign key (stationid1) references station(stationid),
   foreign key (stationid2) references station(stationid)
);

-- NOTICE:  CREATE TABLE / PRIMARY KEY will create implicit index "track_pkey" for table "track"

CREATE TABLE train(
   trainid integer  primary key   NOT NULL,
   trainname varchar(50) NOT NULL,
   stationid1       integer    NOT NULL,
   stationid2      integer    NOT NULL,
   slcap      integer    NOT NULL,
   accap      integer    NOT NULL,
   foreign key (stationid1) references station(stationid),
   foreign key (stationid2) references station(stationid)
);

--NOTICE:  CREATE TABLE / PRIMARY KEY will create implicit index "train_pkey" for table "train"

CREATE TABLE calendar(
   trainid integer   NOT NULL,
   arrivaltime      timestamp,
   departuretime    timestamp, 
   late time default '00:00:00',
   primary key(trainid,arrivaltime),
  foreign key(trainid) references train
);

CREATE TABLE route(
   trainid integer NOT NULL,  
   stationid integer NOT NULL,
   arrivaltime time,
   departuretime time,
   fareac integer NOT NULL,
   faresl integer NOT NULL,
   faregn integer NOT NULL,
   check (fareac>0),
   check (faresl>0),
   check (faregn>0),
   check (fareac>faresl),
   check (fareac>faregn),
   check (faresl>faregn),
   primary key(trainid,stationid),
   foreign key(trainid) references train,
   foreign key(stationid) references station
);


insert into station values(1,'sa');
insert into station values(2,'sb');
insert into station values(3,'sc');
insert into station values(4,'sd');
insert into station values(5,'se');
insert into station values(6,'sf');

insert into track values(1,1,2,123);
insert into track values(2,2,3,200);
insert into track values(3,3,4,12);
insert into track values(4,4,5,233);
insert into track values(5,5,6,345);

insert into train values(1,'ta',1,6,50,100);
insert into train values(2,'tb',2,6,50,100);
insert into train values(3,'tc',1,4,50,100);
insert into train values(4,'td',5,2,50,100);
insert into train values(5,'te',6,2,50,100);


insert into calendar values(1,'2015-09-28 04:00:00', '2015-09-29 02:45:00');
insert into calendar values(1,'2015-09-30 04:00:00', '2015-09-29 02:45:00');
insert into calendar values(1,'2015-08-27 04:00:00', '2015-08-27 09:00:00');
insert into calendar values(2,'2015-09-28 02:00:00', '2015-09-28 14:00:00');
insert into calendar values(2,'2015-08-28 02:00:00', '2015-08-28 14:00:00');
insert into calendar values(3,'2015-08-04 04:00:00', '2015-08-04 14:15:00');
insert into calendar values(5,'2015-09-28 04:00:00', '2015-09-28 07:55:00');

insert into route values(1,2,'04:00:00', '04:15:00',400,250,200);
insert into route values(1,3,'04:20:00', '04:30:00',400,250,200);
insert into route values(1,4,'04:35:00', '04:40:00',450,250,200);
insert into route values(1,5,'02:45:00', '03:30:00',500,250,200);
insert into route values(1,6,'02:00:00', '02:05:00',600,400,300);
insert into route values(5,6,'04:00:00', '04:05:00',400,300,200);
insert into route values(5,2,'06:50:00', '07:00:00',400,250,200);
insert into route values(5,3,'04:10:00', '04:15:00',400,300,200);
insert into route values(5,5,'07:50:00', '07:55:00',400,250,200);
insert into route values(2,1,'02:00:00', '02:05:00',400,250,200);
insert into route values(2,2,'09:05:00', '09:10:00',500,250,200);
insert into route values(2,6,'14:00:00', '14:10:00',600,250,200);



select x.trainid,x.departuretime,y.arrivaltime from route x, route y, calendar z where x.trainid=y.trainid and x.stationid='2' and y.stationid='6' and z.trainid=y.trainid and  ((x.arrivaltime<y.arrivaltime and z.departuretime::date='2015-09-28') or (x.arrivaltime>y.arrivaltime and z.departuretime::date>'2015-09-28')) and  ((z.arrivaltime::date='2015-09-28' and x.arrivaltime>=z.arrivaltime::time) or (z.departuretime::date='2015-09-28' and x.arrivaltime<=z.arrivaltime::time));

create view a as select x.* from route x,calendar y where x.trainid='1' and y.trainid=x.trainid and x.arrivaltime<='23:59:59' and x.arrivaltime>=y.arrivaltime::time order by x.arrivaltime;
select * from a;
select * from route z where z.trainid='1' and not exists (select * from a where arrivaltime=z.arrivaltime) order by z.arrivaltime;

select * from route where trainid='1' and (stationid='2' or stationid='6') order by arrivaltime;

select sum(distance) where stationid>='1' and stationid<'6';

create or replace function f(integer,integer) returns integer as
$$ 
  declare  i integer;
  begin 
	select sum(distance) into i from track where stationid1>=$1 and stationid1<$2;
	return i;
  end;
$$  LANGUAGE plpgsql;
  

  
with a as (select x.* from route x,calendar y where x.trainid='2' and y.trainid=x.trainid and x.arrivaltime<='23:59:59' and x.arrivaltime>=y.arrivaltime::time order by x.arrivaltime) select distinct * from a;

with a as (select x.* from route x,calendar y where x.trainid='1' and y.trainid=x.trainid and x.arrivaltime<='23:59:59' and x.arrivaltime>=y.arrivaltime::time order by x.arrivaltime) select distinct * from route z where z.trainid='1' and not exists (select * from a where arrivaltime=z.arrivaltime) order by z.arrivaltime;



--Note:1.Show seat availability of trains. 

CREATE TABLE customer(
   cid varchar(50)  primary key   NOT NULL,
   cname varchar(50)    NOT NULL,
   cpass           varchar(50)    NOT NULL
);

insert into customer values('1','ca',md5('123'));
insert into customer values('2','cb',md5('12'));

select * from calendar where trainid='1' and ((arrivaltime::date='2015-09-29' and arrivaltime::time<='02:30:00'and arrivaltime::date!=departuretime::date) or (departuretime::date='2015-09-29' and departuretime::time>='02:30:00'and arrivaltime::date!=departuretime::date) or
(departuretime::date='2015-09-29' and arrivaltime::date='2015-09-29' and departuretime::time>'02:30:00' and arrivaltime::time<='02:30:00' and departuretime::date>='2015-09-29')); 

select * from customer where cid='1' and cpass=md5('123');

create table seatcap(
	trainid integer,
	tdate date,
	stationid integer,
	accap integer,
	slcap integer,
	primary key(trainid,stationid,tdate),
	foreign key(trainid) references train,
	foreign key(stationid) references station
);
insert into seatcap values(1,'2015-09-28',2,100,50);
insert into seatcap values(1,'2015-09-28',3,100,50);
insert into seatcap values(1,'2015-09-28',4,100,50);
insert into seatcap values(1,'2015-09-28',6,100,50);
insert into seatcap values(1,'2015-09-28',5,100,50);
insert into seatcap values(1,'2015-09-30',2,100,50);
insert into seatcap values(1,'2015-09-30',3,100,50);
insert into seatcap values(1,'2015-09-30',4,100,50);
insert into seatcap values(1,'2015-09-30',6,100,50);
insert into seatcap values(1,'2015-09-30',5,100,50);

insert into seatcap values(2,'2015-09-28',1,50,5);
insert into seatcap values(2,'2015-09-28',2,70,5);
insert into seatcap values(2,'2015-09-28',6,40,5);


create table trans(
  trind serial primary key,
  loginid varchar(50),
  trainid int,
  tdate date,
  stationid1 int,
  stationid2 int,
  class varchar(2),
  foreign key(loginid) references customer
);

Room:115

