create or replace view all_areas_hh as
  SELECT (ar.value ->> 'id'::text) AS country_id,
         (ar.value ->> 'name'::text) AS country_name,
         i.id AS area_id,
         i.name AS area_name
  FROM ((dictionary_hh dic
      CROSS JOIN LATERAL jsonb_array_elements(dic.value) ar(value))
      CROSS JOIN LATERAL jsonb_to_recordset((ar.value #> '{areas}'::text[])) i(id text, name text, areas jsonb))
  WHERE ((dic.type)::text = 'AREA'::text)
;

create or replace view all_by_employer_hh as
  SELECT ((vacancy.raw_data -> 'employer'::text) ->> 'name'::text) AS employer
  FROM vacancy
;

create or replace view all_industries_hh as
  SELECT (ar.value ->> 'id'::text) AS spec_id,
         (ar.value ->> 'name'::text) AS spec_name,
         i.id AS sub_spec_id,
         i.name AS sub_spec_name
  FROM ((dictionary_hh dic
      CROSS JOIN LATERAL jsonb_array_elements(dic.value) ar(value))
      CROSS JOIN LATERAL jsonb_to_recordset((ar.value #> '{industries}'::text[])) i(id text, name text, areas jsonb))
  WHERE ((dic.type)::text = 'INDUSTRY'::text)
;

create or replace view all_key_skills as
  SELECT i.name,
         count(i.name) AS count
  FROM (vacancy vl
      CROSS JOIN LATERAL jsonb_to_recordset((vl.raw_data #> '{key_skills}'::text[])) i(name text))
  GROUP BY i.name
  ORDER BY (count(i.name)) DESC
;

create or replace view all_key_skills_by_date as
  SELECT ((vl.raw_data ->> 'published_at'::text))::timestamp with time zone AS published,
         i.name,
         count(*) AS count
  FROM (vacancy vl
      CROSS JOIN LATERAL jsonb_to_recordset((vl.raw_data #> '{key_skills}'::text[])) i(name text))
  GROUP BY ((vl.raw_data ->> 'published_at'::text))::timestamp with time zone, i.name
;

create or replace view all_key_skills_hh as
  SELECT s.name AS skill,
         count(1) AS cnt
  FROM (vacancy
      CROSS JOIN LATERAL jsonb_to_recordset((vacancy.raw_data #> '{key_skills}'::text[])) s(name text))
  WHERE (s.name IS NOT NULL)
  GROUP BY s.name
  ORDER BY (count(1)) DESC
;

create or replace view all_skills_by_date as
  SELECT ((vl.raw_data ->> 'created_at'::text))::date AS created,
         i.name
  FROM (vacancy vl
      CROSS JOIN LATERAL jsonb_to_recordset((vl.raw_data #> '{key_skills}'::text[])) i(name text))
  ORDER BY i.name DESC
;

create or replace view all_specializations_hh as
  SELECT (ar.value ->> 'id'::text) AS id,
         (ar.value ->> 'name'::text) AS name,
         i.id AS sub_id,
         i.name AS sub_name
  FROM ((dictionary_hh dic
      CROSS JOIN LATERAL jsonb_array_elements(dic.value) ar(value))
      CROSS JOIN LATERAL jsonb_to_recordset((ar.value #> '{specializations}'::text[])) i(id text, name text, areas jsonb))
  WHERE ((dic.type)::text = 'SPECIALIZATION'::text)
;

create or replace view all_vacancies as
  SELECT i.id,
         i.name,
         i.created_at,
         (i.employer -> 'name'::text) AS emp_name,
         (i.address -> 'city'::text) AS emp_city,
         (i.salary -> 'to'::text) AS salary_to
  FROM (search_result sr
      CROSS JOIN LATERAL jsonb_to_recordset((sr.raw_response #> '{items}'::text[])) i(id text, name text, url text, created_at timestamp without time zone, employer jsonb, address jsonb, salary jsonb))
;

create or replace view all_vacancies_by_date as
  SELECT date(((vacancy.raw_data ->> 'created_at'::text))::timestamp without time zone) AS created,
         count(1) AS cnt
  FROM vacancy
  GROUP BY (date(((vacancy.raw_data ->> 'created_at'::text))::timestamp without time zone))
  ORDER BY (date(((vacancy.raw_data ->> 'created_at'::text))::timestamp without time zone)) DESC
;

create or replace view all_vacancies_count as
  SELECT count(*) AS count
  FROM vacancy
;

create or replace view all_vacancies_hh as
  SELECT (vacancy.raw_data ->> 'id'::text) AS id,
         (vacancy.raw_data ->> 'name'::text) AS name,
         ((vacancy.raw_data -> 'area'::text) ->> 'name'::text) AS city,
         ((vacancy.raw_data ->> 'created_at'::text))::date AS created,
         (vacancy.raw_data ->> 'description'::text) AS description,
         ((vacancy.raw_data -> 'employer'::text) ->> 'name'::text) AS employer,
         ((vacancy.raw_data -> 'billing_type'::text) ->> 'name'::text) AS billing
  FROM vacancy
;

create or replace view key_skills as
  SELECT vl.vacancy_id,
         i.name
  FROM (vacancy vl
      CROSS JOIN LATERAL jsonb_to_recordset((vl.raw_data #> '{key_skills}'::text[])) i(name text))
;

create or replace view prog_lang_hh as
  SELECT ((vacancy.raw_data ->> 'created_at'::text))::date AS created,
         s.name AS skill,
         sg.name AS sgroup
  FROM ((vacancy
      CROSS JOIN LATERAL jsonb_to_recordset((vacancy.raw_data #> '{key_skills}'::text[])) s(name text))
      LEFT JOIN skill_group sg ON ((s.name = (sg.skill)::text)))
  WHERE ((s.name IS NOT NULL) AND ((sg.name)::text = 'Programming Language'::text))
;

create or replace view prog_lang_hh_by_date as
  SELECT ((vacancy.raw_data ->> 'created_at'::text))::date AS created,
         s.name AS skill,
         count(1) AS cnt
  FROM ((vacancy
      CROSS JOIN LATERAL jsonb_to_recordset((vacancy.raw_data #> '{key_skills}'::text[])) s(name text))
      LEFT JOIN skill_group sg ON ((s.name = (sg.skill)::text)))
  WHERE ((s.name IS NOT NULL) AND ((sg.name)::text = 'Programming Language'::text))
  GROUP BY ((vacancy.raw_data ->> 'created_at'::text))::date, s.name
  ORDER BY ((vacancy.raw_data ->> 'created_at'::text))::date, (count(1)) DESC
;

create or replace view salary_rating_hh as
  SELECT (vacancy.raw_data ->> 'id'::text) AS id,
         (vacancy.raw_data ->> 'name'::text) AS name,
         ((vacancy.raw_data -> 'salary'::text) ->> 'currency'::text) AS cur,
         (((vacancy.raw_data -> 'salary'::text) ->> 'to'::text))::double precision AS sto
  FROM vacancy
  WHERE ((((vacancy.raw_data -> 'salary'::text) ->> 'to'::text) IS NOT NULL) AND (((vacancy.raw_data -> 'salary'::text) ->> 'currency'::text) = 'RUR'::text))
  ORDER BY (((vacancy.raw_data -> 'salary'::text) ->> 'to'::text))::double precision DESC
;

create or replace view top_20_key_skills as
  SELECT i.name,
         count(1) AS cnt
  FROM (vacancy vl
      CROSS JOIN LATERAL jsonb_to_recordset((vl.raw_data #> '{key_skills}'::text[])) i(name text))
  GROUP BY i.name
  ORDER BY (count(1)) DESC
  LIMIT 20
;

create or replace view top_25_employers_hh as
  SELECT ((vacancy.raw_data -> 'employer'::text) ->> 'name'::text) AS employer,
         count(1) AS cnt
  FROM vacancy
  GROUP BY ((vacancy.raw_data -> 'employer'::text) ->> 'name'::text)
  ORDER BY (count(1)) DESC
  LIMIT 25
;

create or replace view top_prog_lang_ru_hh as
  SELECT row_number() OVER () AS id,
         date_trunc('MONTH'::text, ((vacancy.raw_data ->> 'created_at'::text))::timestamp without time zone) AS month,
         s.name AS skill,
         count(1) AS score
  FROM ((vacancy
      CROSS JOIN LATERAL jsonb_to_recordset((vacancy.raw_data #> '{key_skills}'::text[])) s(name text))
      LEFT JOIN skill_group sg ON ((s.name = (sg.skill)::text)))
  WHERE ((s.name IS NOT NULL) AND ((sg.name)::text = 'Programming Language'::text))
  GROUP BY (date_trunc('MONTH'::text, ((vacancy.raw_data ->> 'created_at'::text))::timestamp without time zone)), s.name
  ORDER BY (date_trunc('MONTH'::text, ((vacancy.raw_data ->> 'created_at'::text))::timestamp without time zone)), (count(1)) DESC
;

create or replace view skills_by_month_ru_hh as
  SELECT mv_top_skills_by_month_ru_hh.id,
         mv_top_skills_by_month_ru_hh.month,
         mv_top_skills_by_month_ru_hh.skill,
         mv_top_skills_by_month_ru_hh.score
  FROM mv_top_skills_by_month_ru_hh
;

create or replace view ranking_item as
  SELECT mv_top_skills_by_month_ru_hh.id,
         mv_top_skills_by_month_ru_hh.month,
         mv_top_skills_by_month_ru_hh.skill,
         mv_top_skills_by_month_ru_hh.score
  FROM mv_top_skills_by_month_ru_hh
;

create or replace view skill_ranking_item as
  SELECT mv_top_skills_by_month_ru_hh.id,
         mv_top_skills_by_month_ru_hh.month,
         mv_top_skills_by_month_ru_hh.skill,
         mv_top_skills_by_month_ru_hh.score
  FROM mv_top_skills_by_month_ru_hh
;

