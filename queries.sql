--Part 1
SELECT FIELDS
FROM job
--Part 2
SELECT name
FROM employers
WHERE location = 'St. Louis City';
--Part 3
-- Part 3: SQL Task
DROP TABLE JOB

--Part 4
SELECT * FROM skill
JOIN job_skills ON skill.id = job_skills.skills_id
WHERE job_skills.jobs_id IS NOT NULL
ORDER BY name 