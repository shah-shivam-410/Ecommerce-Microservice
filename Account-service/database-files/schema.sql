create table account (account_id bigint not null, created_at timestamp(6) with time zone, last_modified_at timestamp(6) with time zone, account_name varchar(255), account_type smallint check ((account_type between 0 and 1)), address_id bigint, primary key (account_id))
create table address (id bigint not null, created_at timestamp(6) with time zone, last_modified_at timestamp(6) with time zone, city varchar(255), country varchar(255), postal_code varchar(255), state varchar(255), street varchar(255), primary key (id))
create table contact (id bigint not null, created_at timestamp(6) with time zone, last_modified_at timestamp(6) with time zone, email varchar(255), name varchar(255), phone varchar(255), account_id bigint not null, primary key (id))
create sequence seq_account_id start with 1 increment by 50
create sequence seq_address_id start with 1 increment by 50
create sequence seq_contact_id start with 1 increment by 50
alter table if exists account add constraint FK9lna4d7ow9qbs27m5psafys58 foreign key (address_id) references address
alter table if exists contact add constraint FK3ctagodg5h629t8ltnam39l5w foreign key (account_id) references account

