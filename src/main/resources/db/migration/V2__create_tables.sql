CREATE TABLE transactions (
    account_id VARCHAR(50) NOT NULL,
    total_amount DECIMAL(15, 2) NOT NULL,
    mcc VARCHAR(10) NOT NULL,
    merchant VARCHAR(100) NOT NULL,
    idempotency VARCHAR(100) NOT NULL,
    PRIMARY KEY (account_id, idempotency)
);