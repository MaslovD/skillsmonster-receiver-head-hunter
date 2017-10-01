CREATE OR REPLACE FUNCTION add_vacancy_to_queue()
  RETURNS INTEGER
AS $$
DECLARE
  query_txt TEXT;
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
                              queued_at TIMESTAMP WITHOUT TIME ZONE


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
    current_timestamp TIMESTAMP WITHOUT TIME ZONE
  FROM
    tmp_vac vac;

UPDATE search_result sreq SET status = ''QEUED'' FROM tmp_vac tv WHERE sreq.id = tv.res_id;

SELECT count(*) FROM tmp_vac;';

  EXECUTE query_txt;

END
$$ LANGUAGE PLPGSQL;