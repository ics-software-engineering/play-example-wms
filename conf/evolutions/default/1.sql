# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table product (
  primary_key               bigint auto_increment not null,
  product_id                varchar(255),
  name                      varchar(255),
  description               varchar(255),
  constraint pk_product primary key (primary_key))
;

create table stock_item (
  primary_key               bigint auto_increment not null,
  stock_item_id             varchar(255),
  warehouse_primary_key     bigint,
  product_primary_key       bigint,
  quantity                  bigint,
  constraint pk_stock_item primary key (primary_key))
;

create table tag (
  primary_key               bigint auto_increment not null,
  tag_id                    varchar(255),
  constraint pk_tag primary key (primary_key))
;

create table warehouse (
  primary_key               bigint auto_increment not null,
  warehouse_id              varchar(255),
  name                      varchar(255),
  city                      varchar(255),
  state                     varchar(255),
  zip                       varchar(255),
  constraint pk_warehouse primary key (primary_key))
;


create table product_tag (
  product_primary_key            bigint not null,
  tag_primary_key                bigint not null,
  constraint pk_product_tag primary key (product_primary_key, tag_primary_key))
;
alter table stock_item add constraint fk_stock_item_warehouse_1 foreign key (warehouse_primary_key) references warehouse (primary_key) on delete restrict on update restrict;
create index ix_stock_item_warehouse_1 on stock_item (warehouse_primary_key);
alter table stock_item add constraint fk_stock_item_product_2 foreign key (product_primary_key) references product (primary_key) on delete restrict on update restrict;
create index ix_stock_item_product_2 on stock_item (product_primary_key);



alter table product_tag add constraint fk_product_tag_product_01 foreign key (product_primary_key) references product (primary_key) on delete restrict on update restrict;

alter table product_tag add constraint fk_product_tag_tag_02 foreign key (tag_primary_key) references tag (primary_key) on delete restrict on update restrict;

# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table product;

drop table product_tag;

drop table stock_item;

drop table tag;

drop table warehouse;

SET FOREIGN_KEY_CHECKS=1;

