# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table exchange_offices (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  address                   varchar(255),
  city                      varchar(255),
  country                   varchar(255),
  phone                     varchar(255),
  longitude                 float,
  latitude                  float,
  likes                     integer,
  dislikes                  integer,
  created_on                datetime not null,
  last_updated_on           datetime not null,
  constraint pk_exchange_offices primary key (id))
;

create table exchange_rates (
  id                        bigint auto_increment not null,
  curr_from                 varchar(255),
  curr_to                   varchar(255),
  amount                    decimal(38,2),
  eo_id                     bigint,
  created_on                datetime not null,
  last_updated_on           datetime not null,
  constraint pk_exchange_rates primary key (id))
;

alter table exchange_rates add constraint fk_exchange_rates_eo_1 foreign key (eo_id) references exchange_offices (id) on delete restrict on update restrict;
create index ix_exchange_rates_eo_1 on exchange_rates (eo_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table exchange_offices;

drop table exchange_rates;

SET FOREIGN_KEY_CHECKS=1;

