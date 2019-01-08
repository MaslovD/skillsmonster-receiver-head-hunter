create materialized view if not exists mv_top_prog_lang_ru_hh as
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

create unique index if not exists mv_top_prog_lang_ru_hh_id_idx
  on mv_top_prog_lang_ru_hh (id)
;

create materialized view if not exists mv_top_skills_by_month_ru_hh as
  SELECT row_number() OVER () AS id,
         date_trunc('MONTH'::text, ((vacancy.raw_data ->> 'created_at'::text))::timestamp without time zone) AS month,
         s.name AS skill,
         count(1) AS score
  FROM (vacancy
      CROSS JOIN LATERAL jsonb_to_recordset((vacancy.raw_data #> '{key_skills}'::text[])) s(name text))
  GROUP BY (date_trunc('MONTH'::text, ((vacancy.raw_data ->> 'created_at'::text))::timestamp without time zone)), s.name
  ORDER BY (date_trunc('MONTH'::text, ((vacancy.raw_data ->> 'created_at'::text))::timestamp without time zone)), (count(1)) DESC
;

