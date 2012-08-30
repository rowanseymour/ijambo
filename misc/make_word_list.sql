-- Queries a Kumva database for suitable words:
--  * Nouns
--  * No flags
--  * Length between 5 and 12 characters
--  * First meaning between 2 and 16 characters
--
SELECT * FROM (
  SELECT
    r.entry_id,
    CONCAT_WS('', `prefix`, `lemma`) as `word`, 
    SUBSTRING_INDEX(m.`meaning`, ',', 1) AS `meaning` 
  FROM `rw_revision` r
  INNER JOIN `rw_meaning` m ON m.`revision_id` = r.`revision_id` AND m.`order` = 0
  WHERE r.`wordclass` = 'n' AND r.`status` = 1 AND m.`flags` = 0
) w
WHERE LENGTH(w.`word`) BETWEEN 5 AND 12 
  AND LENGTH(w.`meaning`) BETWEEN 2 AND 16
ORDER BY word ASC
LIMIT 0, 99999;