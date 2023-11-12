--Part 1
SELECT *
FROM job
--Part 2
SELECT name
FROM employers
WHERE location = 'St. Louis City';
--Part 3
-- Part 3: SQL Task
DROP TABLE IF EXISTS job;

--Part 4
SELECT DISTINCT skill.name
FROM skill
JOIN job_skill ON skill.id = job_skill.skill_id
JOIN job ON job_skill.job_id = job.id
ORDER BY skill.name ASC;