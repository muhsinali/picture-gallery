# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table place (
  id                        bigint not null,
  name                      varchar(255),
  country                   varchar(255),
  picture                   blob,
  content_type              varchar(255),
  description               varchar(255),
  constraint pk_place primary key (id))
;

create sequence place_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists place;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists place_seq;

