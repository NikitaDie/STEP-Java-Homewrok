CREATE TYPE store_category AS ENUM ('GROCERY', 'HOUSEHOLD', 'SPORTS', 'ELECTRONICS', 'CLOTHING');

CREATE TABLE IF NOT EXISTS stores (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255),
    phone VARCHAR(20),
    email VARCHAR(255),
    website VARCHAR(255),
    category store_category,
    description VARCHAR(2000),
    image_path VARCHAR(255)
);