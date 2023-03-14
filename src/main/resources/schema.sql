--drop table invoices;
create table if not exists invoices
(
    id numeric primary key,
    pdf_url varchar(255),
    user_id varchar(255),
    amount  int,
    month varchar(3)
);