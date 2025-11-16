CREATE TABLE bookings
(
    id               BIGSERIAL PRIMARY KEY,
    user_name        VARCHAR(100) NOT NULL,
    email            VARCHAR(150) NOT NULL UNIQUE,
    status           VARCHAR(50)  NOT NULL DEFAULT 'PENDING',
    appointment_time TIMESTAMP    NOT NULL,
    created_at       TIMESTAMP             DEFAULT CURRENT_TIMESTAMP,
    updated_at       TIMESTAMP             DEFAULT CURRENT_TIMESTAMP
);
