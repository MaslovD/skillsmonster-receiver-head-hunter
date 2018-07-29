create table search_request
(
	id serial not null
		constraint search_request_pkey
			primary key,
	date_time timestamp,
	found bigint,
	pages integer,
	per_page integer,
	period_from timestamp,
	period_to timestamp,
	raw_request varchar(500),
	area_id integer,
	industry_id varchar(255),
	specialization_id varchar(255)
)
;

create index search_request_id_index
	on search_request (id)
;

create table search_result
(
	id serial not null
		constraint search_result_pkey
			primary key,
	page integer,
	raw_response jsonb,
	search_request_id integer
		constraint fkt6g19cci1c2qmwqahpkhsmxqu
			references search_request
		constraint search_result_search_request_id_fk
			references search_request,
	status varchar(20),
	found bigint
)
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

create table skill
(
	id integer not null
		constraint skill_pkey
			primary key,
	code varchar(50),
	collection_id integer,
	key_skill boolean,
	name varchar(200)
)
;

create table vacancy
(
	id bigserial not null
		constraint vacancy_id_pk
			primary key,
	load_date_time timestamp,
	raw_data jsonb,
	search_request_id integer,
	vacancy_id varchar(200)
)
;



create unique index vacancy_id_uindex
	on remove_vacancy (id)
;

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

create table test_into
(
	created date,
	name text
)
;

create table skill_group
(
	id serial not null,
	name varchar(100),
	skill varchar(100)
)
;

create unique index skill_tag_id_uindex
	on skill_group (id)
;

create index skill_group_name_index
	on skill_group (name)
;

create table prog_lang_hh_tb
(
	created date,
	skill text,
	sgroup varchar(100)
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
	country_id varchar(255),
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

alter table search_request
	add constraint fkqk07xxsmsok1005xy1mto26s
		foreign key (area_id) references area
;

create table specialization
(
	main_id integer not null,
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

create unique index specialization_sub_id_uindex
	on specialization (sub_id)
;

create unique index specialization_id_uindex
	on specialization (id)
;

alter table search_request
	add constraint fkohpx97n4c4iaap210lxcql1k
		foreign key (specialization_id) references specialization (sub_id)
;

alter table search_request
	add constraint search_request_specialization_sub_id_fk
		foreign key (specialization_id) references specialization (sub_id)
;

create table industry
(
	spec_id integer,
	spec_name text,
	sub_spec_id text,
	sub_spec_name text,
	id serial not null,
	code varchar(255)
		constraint uk_l0r2gj6r0hfml537tccjh11hq
			unique
)
;

create unique index industry_id_uindex
	on industry (id)
;

alter table search_request
	add constraint fk8w02bq4kg0kdihce8sf9bbbdd
		foreign key (industry_id) references industry (code)
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

create table table_name
(
	column_1 jsonb
)
;

create table processing_queue
(
	id bigserial not null
		constraint processing_queue_id_pk
			primary key,
	status varchar(20),
	queued_at timestamp,
	processed_at timestamp,
	result_id integer,
	found bigint,
	vacancy_id text,
	name text,
	created_at timestamp,
	emp_name text,
	emp_city text,
	area_id text,
	area_name text,
	salary_to jsonb
)
;

CREATE VIEW vacancy_to_load AS SELECT i.id,
    i.name,
    i.created_at,
    (i.employer -> 'name'::text) AS emp_name,
    (i.address -> 'city'::text) AS emp_city,
    (i.salary -> 'to'::text) AS salary_to
   FROM (search_result sr
     CROSS JOIN LATERAL jsonb_to_recordset((sr.raw_response #> '{items}'::text[])) i(id text, name text, url text, created_at timestamp without time zone, employer jsonb, address jsonb, salary jsonb))
  WHERE (is_vacancy_loaded((i.id)::character varying) IS FALSE)
;

CREATE VIEW all_vacancies AS SELECT i.id,
    i.name,
    i.created_at,
    (i.employer -> 'name'::text) AS emp_name,
    (i.address -> 'city'::text) AS emp_city,
    (i.salary -> 'to'::text) AS salary_to
   FROM (search_result sr
     CROSS JOIN LATERAL jsonb_to_recordset((sr.raw_response #> '{items}'::text[])) i(id text, name text, url text, created_at timestamp without time zone, employer jsonb, address jsonb, salary jsonb))
;

CREATE VIEW all_key_skills AS SELECT i.name,
    count(i.name) AS count
   FROM (remove_vacancy vl
     CROSS JOIN LATERAL jsonb_to_recordset((vl.raw_data #> '{key_skills}'::text[])) i(name text))
  GROUP BY i.name
  ORDER BY (count(i.name)) DESC
;

CREATE VIEW all_key_skills_by_date AS SELECT ((vl.raw_data ->> 'published_at'::text))::timestamp with time zone AS published,
    i.name,
    count(*) AS count
   FROM (remove_vacancy vl
     CROSS JOIN LATERAL jsonb_to_recordset((vl.raw_data #> '{key_skills}'::text[])) i(name text))
  GROUP BY ((vl.raw_data ->> 'published_at'::text))::timestamp with time zone, i.name
;

CREATE VIEW vacancy_to_load_hh AS SELECT i.id,
    i.name,
    i.created_at,
    (i.employer -> 'name'::text) AS emp_name,
    (i.address -> 'city'::text) AS emp_city,
    (i.salary -> 'to'::text) AS salary_to
   FROM (search_result sr
     CROSS JOIN LATERAL jsonb_to_recordset((sr.raw_response #> '{items}'::text[])) i(id text, name text, url text, created_at timestamp without time zone, employer jsonb, address jsonb, salary jsonb))
  WHERE (NOT (i.id IN ( SELECT (remove_vacancy.vacancy_id)::text AS id
           FROM remove_vacancy)))
;

CREATE VIEW all_vacancies_hh AS SELECT (remove_vacancy.raw_data ->> 'id'::text) AS id,
    (remove_vacancy.raw_data ->> 'name'::text) AS name,
    ((remove_vacancy.raw_data -> 'area'::text) ->> 'name'::text) AS city,
    ((remove_vacancy.raw_data ->> 'created_at'::text))::date AS created,
    (remove_vacancy.raw_data ->> 'description'::text) AS description,
    ((remove_vacancy.raw_data -> 'employer'::text) ->> 'name'::text) AS employer,
    ((remove_vacancy.raw_data -> 'billing_type'::text) ->> 'name'::text) AS billing
   FROM remove_vacancy
;

CREATE VIEW all_key_skills_hh AS SELECT s.name AS skill,
    count(1) AS cnt
   FROM (remove_vacancy
     CROSS JOIN LATERAL jsonb_to_recordset((remove_vacancy.raw_data #> '{key_skills}'::text[])) s(name text))
  WHERE (s.name IS NOT NULL)
  GROUP BY s.name
  ORDER BY (count(1)) DESC
;

CREATE VIEW salary_rating_hh AS SELECT (remove_vacancy.raw_data ->> 'id'::text) AS id,
    (remove_vacancy.raw_data ->> 'name'::text) AS name,
    ((remove_vacancy.raw_data -> 'salary'::text) ->> 'currency'::text) AS cur,
    (((remove_vacancy.raw_data -> 'salary'::text) ->> 'to'::text))::double precision AS sto
   FROM remove_vacancy
  WHERE ((((remove_vacancy.raw_data -> 'salary'::text) ->> 'to'::text) IS NOT NULL) AND (((remove_vacancy.raw_data -> 'salary'::text) ->> 'currency'::text) = 'RUR'::text))
  ORDER BY (((vacancyq.raw_data -> 'salary'::text) ->> 'to'::text))::double precision DESC
;

CREATE VIEW top_20_key_skills AS SELECT i.name,
    count(1) AS cnt
   FROM (remove_vacancy vl
     CROSS JOIN LATERAL jsonb_to_recordset((vl.raw_data #> '{key_skills}'::text[])) i(name text))
  GROUP BY i.name
  ORDER BY (count(1)) DESC
 LIMIT 20
;

CREATE VIEW all_skills_by_date AS SELECT ((vl.raw_data ->> 'created_at'::text))::date AS created,
    i.name
   FROM (remove_vacancy vl
     CROSS JOIN LATERAL jsonb_to_recordset((vl.raw_data #> '{key_skills}'::text[])) i(name text))
  ORDER BY i.name DESC
;

CREATE VIEW all_by_employer_hh AS SELECT ((remove_vacancy.raw_data -> 'employer'::text) ->> 'name'::text) AS employer
   FROM remove_vacancy
;

CREATE VIEW top_25_employers_hh AS SELECT ((remove_vacancy.raw_data -> 'employer'::text) ->> 'name'::text) AS employer,
    count(1) AS cnt
   FROM remove_vacancy
  GROUP BY ((remove_vacancy.raw_data -> 'employer'::text) ->> 'name'::text)
  ORDER BY (count(1)) DESC
 LIMIT 25
;

CREATE VIEW prog_lang_hh AS SELECT ((remove_vacancy.raw_data ->> 'created_at'::text))::date AS created,
    s.name AS skill,
    sg.name AS sgroup
   FROM ((remove_vacancy
     CROSS JOIN LATERAL jsonb_to_recordset((remove_vacancy.raw_data #> '{key_skills}'::text[])) s(name text))
     LEFT JOIN skill_group sg ON ((s.name = (sg.skill)::text)))
  WHERE ((s.name IS NOT NULL) AND ((sg.name)::text = 'Programming Language'::text))
;

CREATE VIEW all_industries_hh AS SELECT (ar.value ->> 'id'::text) AS spec_id,
    (ar.value ->> 'name'::text) AS spec_name,
    i.id AS sub_spec_id,
    i.name AS sub_spec_name
   FROM ((dictionary_hh dic
     CROSS JOIN LATERAL jsonb_array_elements(dic.value) ar(value))
     CROSS JOIN LATERAL jsonb_to_recordset((ar.value #> '{industries}'::text[])) i(id text, name text, areas jsonb))
  WHERE ((dic.type)::text = 'INDUSTRY'::text)
;

CREATE VIEW all_areas_hh AS SELECT (ar.value ->> 'id'::text) AS country_id,
    (ar.value ->> 'name'::text) AS country_name,
    i.id AS area_id,
    i.name AS area_name
   FROM ((dictionary_hh dic
     CROSS JOIN LATERAL jsonb_array_elements(dic.value) ar(value))

     CROSS JOIN LATERAL jsonb_to_recordset((ar.value #> '{areas}'::text[])) i(id text, name text, areas jsonb))
  WHERE ((dic.type)::text = 'AREA'::text)
;

CREATE VIEW all_specializations_hh AS SELECT (ar.value ->> 'id'::text) AS id,
    (ar.value ->> 'name'::text) AS name,
    i.id AS sub_id,
    i.name AS sub_name
   FROM ((dictionary_hh dic
     CROSS JOIN LATERAL jsonb_array_elements(dic.value) ar(value))
     CROSS JOIN LATERAL jsonb_to_recordset((ar.value #> '{specializations}'::text[])) i(id text, name text, areas jsonb))
  WHERE ((dic.type)::text = 'SPECIALIZATION'::text)
;

CREATE VIEW key_skills AS SELECT vl.vacancy_id,
    i.name
   FROM (remove_vacancy vl
     CROSS JOIN LATERAL jsonb_to_recordset((vl.raw_data #> '{key_skills}'::text[])) i(name text))
;

CREATE MATERIALIZED VIEW mv_top_skills_by_month_ru_hh AS SELECT row_number() OVER () AS id,
																																date_trunc('MONTH'::text, ((remove_vacancy.raw_data ->> 'created_at'::text))::timestamp without time zone) AS month,
																																s.name AS skill,
																																count(1) AS score
																												 FROM ((remove_vacancy
																													 CROSS JOIN LATERAL jsonb_to_recordset((remove_vacancy.raw_data #> '{key_skills}'::text[])) s(name text))
																													 LEFT JOIN skill_group sg ON ((s.name = (sg.skill)::text)))
																												 WHERE ((s.name IS NOT NULL) AND ((sg.name)::text = 'Programming Language'::text))
																												 GROUP BY (date_trunc('MONTH'::text, ((remove_vacancy.raw_data ->> 'created_at'::text))::timestamp without time zone)), s.name
																												 ORDER BY (date_trunc('MONTH'::text, ((vacancyq.raw_data ->> 'created_at'::text))::timestamp without time zone)), (count(1)) DESC;
CREATE UNIQUE INDEX mv_top_skills_by_month_ru_hh_id_idx
	ON mv_top_skills_by_month_ru_hh (id);

create function is_vacancy_loaded(vac_id character varying) returns boolean
	language sql
as $$
SELECT exists(SELECT 1 FROM remove_vacancy WHERE id = vac_id)
$$
;

create function add_vacancy_to_queue() returns integer
	language plpgsql
as $$
DECLARE
  query_txt TEXT;
  result BIGINT;
BEGIN

  query_txt:='DROP TABLE IF EXISTS tmp_vac;
CREATE TEMP TABLE IF NOT EXISTS tmp_vac AS
  SELECT
    sreq.id                            req_id,
    sres.id                            res_id,
    sreq.found                         found,
    i.id                               v_id,
    i.name,
    i.created_at,
    (i.employer ->> ''name'' :: TEXT) AS emp_name,
    (i.address ->> ''city'' :: TEXT)  AS emp_city,
    (i.area ->> ''id'' :: TEXT)       AS area_id,
    (i.area ->> ''name'' :: TEXT)     AS area_name,
    (i.salary -> ''to'' :: TEXT)      AS salary_to

  FROM (search_result sres
    CROSS JOIN
      LATERAL jsonb_to_recordset((sres.raw_response #> ''{items}'' :: TEXT []))
      i(id TEXT,
      name TEXT,
      url TEXT,
      created_at TIMESTAMP WITHOUT TIME ZONE,
      employer JSONB,
      address JSONB,
      area JSONB,
      salary JSONB
      )
    INNER JOIN search_request sreq
      ON sres.search_request_id = sreq.id AND sres.status = ''NEW'');

INSERT INTO processing_queue (result_id,
                              found,
                              vacancy_id,
                              name,
                              created_at,
                              emp_name,
                              emp_city,
                              area_id,
                              area_name,
                              salary_to,
                              status,
                              queued_at
)
  SELECT
    vac.res_id,
    vac.found,
    vac.v_id,
    vac.name,
    vac.created_at,
    vac.emp_name,
    vac.emp_city,
    vac.area_id,
    vac.area_name,
    vac.salary_to,
    ''NEW'',
    current_timestamp
  FROM
    tmp_vac vac;

UPDATE search_result sreq SET status = ''QEUED'' FROM tmp_vac tv WHERE sreq.id = tv.res_id;

SELECT count(*) FROM tmp_vac;';

  EXECUTE query_txt INTO result;
  RETURN result;

END
$$
;

