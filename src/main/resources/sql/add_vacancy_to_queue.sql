DROP TABLE IF EXISTS tmp_vac;

CREATE TEMP TABLE IF NOT EXISTS tmp_vac AS
  SELECT
    sres.id                            res_id,
    sres.found                         found,
    i.id                               v_id,
    i.name,
    i.created_at,
    i.published_at,
    (i.employer ->> 'name' :: TEXT) AS emp_name,
    (i.address ->> 'city' :: TEXT)  AS emp_city,
    (i.area ->> 'id' :: TEXT)       AS area_id,
    (i.area ->> 'name' :: TEXT)     AS area_name,
    (i.salary -> 'to' :: TEXT)      AS salary_to

  FROM (search_result sres
    CROSS JOIN
      LATERAL jsonb_to_recordset((sres.raw_response #> '{items}' :: TEXT []))
      i(id TEXT,
      name TEXT,
      url TEXT,
      created_at TIMESTAMP WITHOUT TIME ZONE,
      published_at TIMESTAMP WITHOUT TIME ZONE,
      employer JSONB,
      address JSONB,
      area JSONB,
      salary JSONB
      )
  )
  WHERE sres.status = 'NEW';

SELECT count(*) FROM tmp_vac;

INSERT INTO processing_queue (search_result_id,
                              found,
                              vacancy_id,
                              name,
                              created_at,
                              published_at,
                              emp_name,
                              emp_city,
                              area_id,
                              area_name,
                              salary_to,
                              status,
                              queued_at,
                              source
)
  SELECT
    vac.res_id,
    vac.found,
    vac.v_id,
    vac.name,
    vac.created_at,
    vac.published_at,
    vac.emp_name,
    vac.emp_city,
    vac.area_id,
    vac.area_name,
    vac.salary_to,
    'NEW',
    current_timestamp,
    'HH'
  FROM
    tmp_vac vac
ON CONFLICT (vacancy_id, created_at, published_at)
  DO NOTHING;

SELECT count(*) FROM tmp_vac;

UPDATE search_result sreq
SET status = 'QUEUED' FROM tmp_vac tv
WHERE sreq.id = tv.res_id;

UPDATE search_result sreq
SET status = 'SKIPPED'
WHERE sreq.status = 'NEW' and sreq.found=0;

SELECT count(*) FROM tmp_vac;