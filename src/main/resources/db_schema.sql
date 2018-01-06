DROP TABLE IF EXISTS open_payment;

CREATE TABLE open_payment (
  id INTEGER IDENTITY,
  provider_id VARCHAR(20),
  provider_name VARCHAR(100),
  payment_amount DECIMAL,
  payer_id VARCHAR(20),
  payer_name VARCHAR(100)
)