DELETE FROM your_table
WHERE ctid IN (
  SELECT unnest(array_remove(all_ctids, actid))
  FROM (
         SELECT
           min(b.ctid)     AS actid,
           array_agg(ctid) AS all_ctids
         FROM your_table b
         GROUP BY key1, key2, key3, key4
         HAVING count(*) > 1) c);