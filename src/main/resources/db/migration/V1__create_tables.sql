CREATE TABLE IF NOT EXISTS candidates(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    gender VARCHAR(50),
    salary_expected DECIMAL(10,2),
    position VARCHAR(100),
    status VARCHAR(50)
);