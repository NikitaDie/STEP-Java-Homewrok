CREATE DATABASE cafe;

CREATE TABLE IF NOT EXISTS languages (
    id SERIAL PRIMARY KEY,
    name VARCHAR(2) NOT NULL UNIQUE
);


CREATE TABLE IF NOT EXISTS beverages (
    id SERIAL PRIMARY KEY,
    deleted_at TIMESTAMP WITH TIME ZONE,
    price DECIMAL(10,2) NOT NULL

    CONSTRAINT beverages_price_check CHECK(price > 0)
);

CREATE TABLE IF NOT EXISTS beverages_localization (
    id SERIAL PRIMARY KEY,
    beverage_id INT NOT NULL,
    language_id INT NOT NULL,
    name VARCHAR(255) NOT NULL UNIQUE,

    FOREIGN KEY (beverage_id) REFERENCES beverages(id),
    FOREIGN KEY (language_id) REFERENCES languages(id)
);

CREATE TABLE IF NOT EXISTS desserts (
    id SERIAL PRIMARY KEY,
    deleted_at TIMESTAMP WITH TIME ZONE,
    price DECIMAL(10,2) NOT NULL

    CONSTRAINT desserts_price_check CHECK(price > 0)
);

CREATE TABLE IF NOT EXISTS desserts_localization (
    id SERIAL PRIMARY KEY,
    dessert_id INT NOT NULL,
    language_id INT NOT NULL,
    name VARCHAR(255) NOT NULL UNIQUE,

    FOREIGN KEY (dessert_id) REFERENCES desserts(id),
    FOREIGN KEY (language_id) REFERENCES languages(id)
);

--------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS job_positions (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    salary_range numrange NOT NULL,
    description TEXT NOT NULL,

    CONSTRAINT job_positions_salary_range_check CHECK(lower(salary_range) > 0 AND upper(salary_range) > 0)
);

CREATE TABLE IF NOT EXISTS staff (
    id SERIAL PRIMARY KEY,
    deleted_at TIMESTAMP WITH TIME ZONE,
    firstname VARCHAR(255) NOT NULL,
    middle_name VARCHAR(255),
    surname VARCHAR(255) NOT NULL,
    job_position_id INT NOT NULL,
    salary DECIMAL(10,2) NOT NULL,
    phone VARCHAR(20) NOT NULL UNIQUE,
    address VARCHAR(255) NOT NULL,

    FOREIGN KEY (job_position_id) REFERENCES job_positions(id),
    CONSTRAINT staff_salary_check CHECK(salary > 0)
);

CREATE TABLE IF NOT EXISTS work_schedule (
    id SERIAL PRIMARY KEY,
    staff_id INT NOT NULL,
    work_date DATE NOT NULL,
    shift_start TIME NOT NULL,
    shift_end TIME NOT NULL,

    FOREIGN KEY (staff_id) REFERENCES staff(id),
    CONSTRAINT work_schedule_shift_check CHECK(shift_start < shift_end)
);

--------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS clients (
    id SERIAL PRIMARY KEY,
    deleted_at TIMESTAMP WITH TIME ZONE,
    firstname VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    middle_name VARCHAR(255),
    birthdate DATE NOT NULL,
    phone VARCHAR(20) NOT NULL UNIQUE,
    address VARCHAR(255) NOT NULL,
    discount DECIMAL(10,2) NOT NULL DEFAULT 0,

    CONSTRAINT clients_discount_check CHECK(discount >= 0)
);

--------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS orders (
    id SERIAL PRIMARY KEY,
    deleted_at TIMESTAMP WITH TIME ZONE,
    client_id INT NOT NULL,
    staff_id INT,
    order_date TIMESTAMP WITH TIME ZONE NOT NULL,
    total_price DECIMAL(10,2) NOT NULL,
    discount DECIMAL(10,2) NOT NULL DEFAULT 0,

    FOREIGN KEY (staff_id) REFERENCES staff(id),
    FOREIGN KEY (client_id) REFERENCES clients(id),
    CONSTRAINT orders_total_price_check CHECK(total_price > 0),
    CONSTRAINT orders_discount_check CHECK(discount >= 0)
);

CREATE TABLE IF NOT EXISTS order_items (
    id SERIAL PRIMARY KEY,
    order_id INT NOT NULL,
    beverage_id INT,
    dessert_id INT,
    quantity INT NOT NULL,
    price_per_unit DECIMAL(10,2) NOT NULL,

    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (beverage_id) REFERENCES beverages(id),
    FOREIGN KEY (dessert_id) REFERENCES desserts(id),

    CONSTRAINT order_items_quantity_check CHECK(quantity > 0),
    CONSTRAINT order_items_price_check CHECK(price_per_unit > 0),
    CONSTRAINT order_items_beverage_or_dessert_check
    CHECK (
        (beverage_id IS NOT NULL AND dessert_id IS NULL) OR
        (dessert_id IS NOT NULL AND beverage_id IS NULL)
    )
);

CREATE INDEX idx_orders_client_id ON orders(client_id);
CREATE INDEX idx_orders_staff_id ON orders(staff_id);

--------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS deleted_entities (
    id SERIAL PRIMARY KEY,
    entity_name VARCHAR(255) NOT NULL,
    entity_id INT NOT NULL,
    deleted_by VARCHAR(255) NOT NULL,
    reason TEXT NOT NULL,
    deleted_at TIMESTAMP WITH TIME ZONE NOT NULL,

    CONSTRAINT deletes_entities_entity_name_check CHECK(entity_name IN ('beverages', 'desserts', 'staff', 'clients', 'orders'))
);

CREATE TABLE IF NOT EXISTS updated_entities (
    id SERIAL PRIMARY KEY,
    entity_name VARCHAR(255) NOT NULL,
    entity_id INT NOT NULL,
    updated_by VARCHAR(255) NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE,
    changes JSONB

    CONSTRAINT updated_entities_entity_name_check CHECK(entity_name IN ('beverages', 'desserts', 'staff', 'clients', 'orders'))
);

--------------------------------------------------------------------

