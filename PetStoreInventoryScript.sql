select * from pg_catalog.pg_stat_user_tables 

-- drop table products if EXISTS;

-- drop table pets if EXISTS;

-- drop table category if EXISTS;

-- drop table tags if EXISTS;

create table tags(id int primary key, name varchar(50))

insert into tags values(1, 'small');
insert into tags values(2, 'large');
insert into tags values(3, 'Doggie');
insert into tags values(4, 'kittie');
insert into tags values(5, 'fish');

select * from tags;

create table category(id int primary key, name varchar(50), categorytype varchar(50));

insert into category values(1, 'Dog Toy', 'product');
insert into category values(2, 'Dog Food', 'product');
insert into category values(3, 'Cat Toy', 'product');
insert into category values(4, 'Cat Food', 'product');
insert into category values(5, 'Fish Toy', 'product');
insert into category values(6, 'Fish Food', 'product');
insert into category values(7, 'Dog', 'pet');
insert into category values(8, 'Cat', 'pet');
insert into category values(9, 'Fish', 'pet');

select * from category;

create table products(id int primary key, category int references category(id), name varchar(100), photoUrl varchar(250), status varchar(30), tags integer[])

insert into products values(1, 1, 'Ball', 'https://raw.githubusercontent.com/chtrembl/staticcontent/master/dog-toys/ball.jpg?raw=true', 'available', '{1,2}');
insert into products values(2, 1, 'Ball Launcher', 'https://raw.githubusercontent.com/chtrembl/staticcontent/master/dog-toys/ball-launcher.jpg?raw=true', 'available', '{2}');
insert into products values(3, 1, 'Plush Lamb', 'https://raw.githubusercontent.com/chtrembl/staticcontent/master/dog-toys/plush-lamb.jpg?raw=true', 'available', '{1,2}');
insert into products values(4, 1, 'Plush Moose', 'https://raw.githubusercontent.com/chtrembl/staticcontent/master/dog-toys/plush-moose.jpg?raw=true', 'available', '{1,2}');
insert into products values(5, 2, 'Large Breed Dry Food', 'https://raw.githubusercontent.com/chtrembl/staticcontent/master/dog-food/large-dog.jpg?raw=true', 'available', '{2}');
insert into products values(6, 2, 'Small Breed Dry Food', 'https://raw.githubusercontent.com/chtrembl/staticcontent/master/dog-food/small-dog.jpg?raw=true', 'available', '{1}');
insert into products values(7, 3, 'Mouse', 'https://raw.githubusercontent.com/chtrembl/staticcontent/master/cat-toys/mouse.jpg?raw=true', 'available', '{1,2}');
insert into products values(8, 3, 'Scratcher', 'https://raw.githubusercontent.com/chtrembl/staticcontent/master/cat-toys/scratcher.jpg?raw=true', 'available', '{1,2}');
insert into products values(9, 4, 'All Sizes Cat Dry Food', 'https://raw.githubusercontent.com/chtrembl/staticcontent/master/cat-food/cat.jpg?raw=true', 'available', '{1,2}');
insert into products values(10, 5, 'Mangrove Ornament', 'https://raw.githubusercontent.com/chtrembl/staticcontent/master/fish-toys/mangrove.jpg?raw=true', 'available', '{1,2}');
insert into products values(11, 6, 'All Sizes Fish Food', 'https://raw.githubusercontent.com/chtrembl/staticcontent/master/fish-food/fish.jpg?raw=true', 'available', '{1,2}');

select * from products;

select p.id, p.name, c.name, p.photoUrl, p.status, t.id, t.name from tags t, category c join products p on p.category = c.id where t.id = any(p.tags) order by p.id

create table pets(id int primary key, category int references category(id), name varchar(100), photoUrl varchar(250), status varchar(30), tags integer[])

insert into pets values(1, 7, 'Afador', 'https://raw.githubusercontent.com/chtrembl/staticcontent/master/dog-breeds/afador.jpg?raw=true', 'available', '{3,2}');
insert into pets values(2, 7, 'American Bulldog', 'https://raw.githubusercontent.com/chtrembl/staticcontent/master/dog-breeds/american-bulldog.jpg?raw=true', 'available', '{3,2}');
insert into pets values(3, 7, 'Australian Retriever', 'https://raw.githubusercontent.com/chtrembl/staticcontent/master/dog-breeds/australian-retriever.jpg?raw=true', 'available', '{3,2}');
insert into pets values(4, 7, 'Australian Shepherd', 'https://raw.githubusercontent.com/chtrembl/staticcontent/master/dog-breeds/australian-shepherd.jpg?raw=true', 'available', '{3,2}');
insert into pets values(5, 7, 'Basset Hound', 'https://raw.githubusercontent.com/chtrembl/staticcontent/master/dog-breeds/basset-hound.jpg?raw=true', 'available', '{3,1}');
insert into pets values(6, 7, 'Beagle', 'https://raw.githubusercontent.com/chtrembl/staticcontent/master/dog-breeds/beagle.jpg?raw=true', 'available', '{3,1}');
insert into pets values(7, 7, 'Border Terrier', 'https://raw.githubusercontent.com/chtrembl/staticcontent/master/dog-breeds/border-terrier.jpg?raw=true', 'available', '{3,1}');
insert into pets values(8, 8, 'Abyssinian', 'https://raw.githubusercontent.com/chtrembl/staticcontent/master/cat-breeds/abyssinian.jpg?raw=true', 'available', '{4,1}');
insert into pets values(9, 8, 'American Bobtail', 'https://raw.githubusercontent.com/chtrembl/staticcontent/master/cat-breeds/american-bobtail.jpg?raw=true', 'available', '{4,1}');
insert into pets values(10, 8, 'American Shorthair', 'https://raw.githubusercontent.com/chtrembl/staticcontent/master/cat-breeds/american-shorthair.jpg?raw=true', 'available', '{4,1}');
insert into pets values(11, 8, 'Balinese', 'https://raw.githubusercontent.com/chtrembl/staticcontent/master/cat-breeds/balinese.jpg?raw=true', 'available', '{4,1}');
insert into pets values(12, 8, 'Birman', 'https://raw.githubusercontent.com/chtrembl/staticcontent/master/cat-breeds/birman.jpg?raw=true', 'available', '{4,1}');
insert into pets values(13, 8, 'Bombay', 'https://raw.githubusercontent.com/chtrembl/staticcontent/master/cat-breeds/bombay.jpg?raw=true', 'available', '{4,1}');
insert into pets values(14, 8, 'British Shorthair', 'https://raw.githubusercontent.com/chtrembl/staticcontent/master/cat-breeds/british-shorthair.jpg?raw=true', 'available', '{4,1}');
insert into pets values(15, 9, 'Goldfish', 'https://raw.githubusercontent.com/chtrembl/staticcontent/master/fish-breeds/goldfish.jpg?raw=true', 'available', '{5,1}');

select * from pets;

select p.id, p.name, c.name, p.photoUrl, p.status, t.id, t.name from tags t, category c join pets p on p.category = c.id where t.id = any(p.tags) order by p.id
