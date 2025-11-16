CREATE TABLE notifications
(
    id         BIGSERIAL PRIMARY KEY,
    booking_id BIGINT,
    user_name  VARCHAR(100) NOT NULL,
    message    VARCHAR(500) NOT NULL,
    created_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    status     VARCHAR(50)
);
