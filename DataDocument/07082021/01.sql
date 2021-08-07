SELECT * FROM ecommerce.users;
alter table ecommerce.users add column failed_attempt int default 0;
alter table ecommerce.users add column account_non_locked int default 1;
alter table ecommerce.users add column lock_time datetime ;