SELECT pg_size_pretty(pg_database_size('skillsmonster') );

SELECT *, pg_size_pretty(total_bytes) AS total
  , pg_size_pretty(index_bytes) AS INDEX
  , pg_size_pretty(toast_bytes) AS toast
  , pg_size_pretty(table_bytes) AS TABLE
FROM (
       SELECT *, total_bytes-index_bytes-COALESCE(toast_bytes,0) AS table_bytes FROM (
                                                                                       SELECT c.oid,nspname AS table_schema, relname AS TABLE_NAME
                                                                                         , c.reltuples AS row_estimate
                                                                                         , pg_total_relation_size(c.oid) AS total_bytes
                                                                                         , pg_indexes_size(c.oid) AS index_bytes
                                                                                         , pg_total_relation_size(reltoastrelid) AS toast_bytes
                                                                                       FROM pg_class c
                                                                                         LEFT JOIN pg_namespace n ON n.oid = c.relnamespace
                                                                                       WHERE relkind = 'r'
                                                                                     ) a
     ) a;

SELECT nspname || '.' || relname AS "relation",
       pg_size_pretty(pg_relation_size(C.oid)) AS "size"
FROM pg_class C
  LEFT JOIN pg_namespace N ON (N.oid = C.relnamespace)
WHERE nspname NOT IN ('pg_catalog', 'information_schema')
ORDER BY pg_relation_size(C.oid) DESC
LIMIT 20;