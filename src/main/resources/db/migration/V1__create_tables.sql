CREATE TABLE account (
    account_id VARCHAR(50) NOT NULL PRIMARY KEY,
    food_balance DOUBLE DEFAULT 0.0,
    meal_balance DOUBLE DEFAULT 0.0,
    cash_balance DOUBLE DEFAULT 0.0
);