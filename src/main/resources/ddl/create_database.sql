create table dictionary_hh
(
  id serial not null
    constraint hh_dictionary_id_pk
    primary key,
  type varchar(100),
  value jsonb,
  updated_on timestamp
)
;

create unique index hh_dictionary_id_uindex
  on dictionary_hh (id)
;

create table vacancy_arch
(
  id bigserial not null,
  load_date_time timestamp,
  raw_data jsonb,
  vacancy_id varchar(200),
  processing_queue_id bigint,
  name varchar,
  area_url varchar,
  area_name varchar,
  salary_from double precision,
  salary_to double precision,
  salary_gross boolean,
  salary_currency varchar,
  snippet_requirement varchar,
  snippet_responsibility varchar,
  archived boolean,
  premium boolean,
  source varchar,
  created_at timestamp,
  url varchar,
  alternate_url varchar,
  apply_alternate_url varchar,
  address varchar,
  department_id varchar,
  department_name varchar,
  sort_point_distance varchar,
  emp_id varchar,
  emp_name varchar,
  emp_url varchar,
  status varchar,
  type_id varchar,
  type_name varchar,
  area_id varchar,
  published_at timestamp
)
;

create unique index vacancy_id_uindex
  on vacancy_arch (id)
;

create unique index vacancy_vacancy_id_published_at_created_at_uindex
  on vacancy_arch (vacancy_id, published_at, created_at)
;

create table search_result
(
  id serial not null
    constraint search_result_pkey
    primary key,
  page integer,
  raw_response jsonb,
  status varchar(20),
  found double precision,
  raw_request varchar(255) not null,
  total_pages integer not null
)
;

create table app_setting
(
  id serial not null,
  val json
)
;

create table area
(
  country_id integer,
  country_name varchar(255),
  id serial not null
    constraint area_id_pk
    primary key,
  area_name varchar(255),
  code varchar(255),
  ext_code varchar(255),
  name varchar(255),
  type varchar(255),
  zip varchar(255)
)
;

create unique index area_id_uindex
  on area (id)
;

create table country
(
  alpha_3 varchar(3) not null
    constraint country_pkey
    primary key,
  alpha_2 varchar(2),
  full_name varchar(500),
  num_code integer,
  short_name varchar(200)
)
;

create table industry
(
  ind_id integer,
  ind_name text,
  sub_ind_id text,
  sub_ind_name text,
  id serial not null
)
;

create unique index industry_id_uindex
  on industry (id)
;

create table skill_group
(
  id serial not null,
  name varchar(100),
  skill varchar(100)
)
;

create index skill_group_name_index
  on skill_group (name)
;

create unique index skill_tag_id_uindex
  on skill_group (id)
;

create table processing_queue
(
  id bigserial not null
    constraint processing_queue_id_pk
    primary key,
  status varchar(20),
  queued_at timestamp,
  processed_at timestamp,
  search_result_id integer
    constraint processing_queue_search_result_id_fk
    references search_result,
  found bigint,
  vacancy_id text not null,
  name text,
  created_at timestamp not null,
  emp_name text,
  emp_city text,
  area_id text,
  area_name text,
  salary_to jsonb,
  source varchar(20),
  published_at timestamp not null,
  result_id integer
)
;

create unique index processing_queue_vacancy_id_created_at_published_at_uindex
  on processing_queue (vacancy_id, created_at, published_at)
;

create table prog_lang_hh_tb
(
  created date,
  skill text,
  sgroup varchar(100)
)
;

create table skill
(
  id bigint not null
    constraint skill_pkey
    primary key,
  code varchar(50),
  key_skill boolean,
  name varchar(200),
  vacancy bigint not null
)
;

alter table skill owner to postgres
;

create table source_site
(
  id serial not null
    constraint source_site_pkey
    primary key,
  api_url varchar(255),
  name varchar(255),
  status varchar(255) not null,
  url varchar(255)
)
;

create table specialization
(
  main_id varchar(255) not null,
  main_name varchar(255),
  sub_id varchar(255)
    constraint uk_oxgmaj17eguxcmnr8pqnc9rwn
    unique,
  sub_name varchar(255),
  id serial not null
    constraint specialization_id_pk
    primary key
)
;

create unique index specialization_id_uindex
  on specialization (id)
;

create unique index specialization_sub_id_uindex
  on specialization (sub_id)
;

create table ui_menu
(
  id serial not null
    constraint ui_menu_pkey
    primary key,
  expanded boolean,
  icon varchar(255),
  item_order varchar(255),
  router_link varchar(500),
  selected boolean,
  target varchar(255),
  title varchar(500),
  url varchar(500),
  parent integer
    constraint fkeekt2ddd1wxlu52v3ninc6eul
    references ui_menu
)
;

create table vacancy
(
  id bigserial not null
    constraint vacancy_id_pk
    primary key,
  vacancy_id varchar(200),
  created_at timestamp,
  published_at timestamp,
  name varchar,
  emp_name varchar,
  area_name varchar,
  salary_from double precision,
  salary_to double precision,
  salary_gross boolean,
  salary_currency varchar,
  snippet_requirement varchar,
  snippet_responsibility varchar,
  archived boolean,
  premium boolean,
  source varchar,
  url varchar,
  alternate_url varchar,
  apply_alternate_url varchar,
  address varchar,
  department_id varchar,
  department_name varchar,
  sort_point_distance varchar,
  emp_id varchar,
  area_url varchar,
  emp_url varchar,
  status varchar,
  type_id varchar,
  type_name varchar,
  area_id varchar,
  industryid varchar,
  industrysubid varchar,
  specializationid varchar,
  specializationsubid varchar,
  raw_data jsonb,
  load_date_time timestamp,
  industry_id varchar,
  industry_sub_id varchar,
  specialization_id varchar,
  specialization_sub_id varchar
)
;

alter table vacancy owner to postgres
;

create unique index vacancy_vacancy_id_created_at_published_at_uindex
  on vacancy (vacancy_id, created_at, published_at)
;

create index vacancy_created_at_index
  on vacancy (created_at)
;

