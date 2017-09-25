SELECT
  sreq.id                            r_id,
  sreq.found                         found,
  i.id                               v_id,
  i.name,
  i.created_at,
  (i.employer ->> 'name' :: TEXT) AS emp_name,
  (i.address ->> 'city' :: TEXT)  AS emp_city,
  (i.area ->> 'id' :: TEXT)       AS area_id,
  (i.area ->> 'name' :: TEXT)     AS area_name,
  (i.salary -> 'to' :: TEXT)      AS salary_to

INTO vacancy_hh

FROM (search_result sr
  CROSS JOIN LATERAL jsonb_to_recordset((sr.raw_response #> '{items}' :: TEXT []))
             i(id TEXT,
             name TEXT,
             url TEXT,
             created_at TIMESTAMP WITHOUT TIME ZONE,
             employer JSONB,
             address JSONB,
             area JSONB,
             salary JSONB
             )
  INNER JOIN search_request sreq ON sr.search_request_id = sreq.id AND found > 0  AND sr.status='NEW'

)