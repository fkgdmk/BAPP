ALTER TABLE users MODIFY COLUMN password VARCHAR (120);

ALTER TABLE users ADD email VARCHAR(60) AFTER name;
