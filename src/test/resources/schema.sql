CREATE TABLE url
(
    original_url VARCHAR(255) NOT NULL,
    short_url    VARCHAR(255),
    CONSTRAINT pk_url PRIMARY KEY (original_url)
);