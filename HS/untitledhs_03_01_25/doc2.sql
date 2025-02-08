
--task1
--1.1
DO $$
    DECLARE
        client_id INT;
        staff_id INT;
        ordered_beverage_id INT;
        beverage_price NUMERIC;
        quantity INT;
        order_discount NUMERIC;
        order_id INT;
    BEGIN
        SELECT id INTO client_id
        FROM clients
        WHERE firstname = 'Марія' AND surname = 'Петренко';

        SELECT id INTO staff_id
        FROM staff
        WHERE firstname = 'Іван' AND surname = 'Іванов';

        SELECT beverage_id INTO ordered_beverage_id
        FROM beverages_localization
        WHERE name = 'Chicory Glacé';

        SELECT price INTO beverage_price
        FROM beverages
        WHERE id = ordered_beverage_id;

        quantity := 2;

        SELECT discount INTO order_discount
        FROM clients
        WHERE id = client_id;

        INSERT INTO orders (client_id, staff_id, order_date, total_price, discount)
        VALUES (
           client_id,
           staff_id,
           NOW(),
           beverage_price * quantity - order_discount,
           order_discount
        )
        RETURNING id INTO order_id;

        INSERT INTO order_items (order_id, beverage_id, quantity, price_per_unit)
        VALUES (order_id, ordered_beverage_id, quantity, beverage_price);
    END $$;

--1.2
DO $$
    DECLARE
        client_id INT;
        staff_id INT;
        ordered_dessert_id INT;
        dessert_price NUMERIC;
        quantity INT;
        order_discount NUMERIC;
        order_id INT;
    BEGIN
        SELECT id INTO client_id
        FROM clients
        WHERE firstname = 'Марія' AND surname = 'Петренко';

        SELECT id INTO staff_id
        FROM staff
        WHERE firstname = 'Іван' AND surname = 'Іванов';

        SELECT dessert_id INTO ordered_dessert_id
        FROM desserts_localization
        WHERE name = 'Apple Pie with Cinnamon';

        SELECT price INTO dessert_price
        FROM desserts
        WHERE id = ordered_dessert_id;

        quantity := 1;

        SELECT discount INTO order_discount
        FROM clients
        WHERE id = client_id;

        INSERT INTO orders (client_id, staff_id, order_date, total_price, discount)
        VALUES (
            client_id,
            staff_id,
            NOW(),
            dessert_price * quantity - order_discount,
            order_discount
        )
        RETURNING id INTO order_id;

        INSERT INTO order_items (order_id, dessert_id, quantity, price_per_unit)
        VALUES (order_id, ordered_dessert_id, quantity, dessert_price);
    END $$;

--1.3
WITH nearest_monday AS (
    SELECT current_date + ((8 - EXTRACT(DOW FROM current_date)) % 7) * INTERVAL '1 day' AS monday_date
)

INSERT INTO work_schedule (staff_id, work_date, shift_start, shift_end)
SELECT
    id,
    monday_date,
    '08:00',
    '16:00'
FROM staff, nearest_monday
WHERE firstname = 'Іван' AND surname = 'Іванов';

WITH nearest_monday AS (
    SELECT current_date + ((8 - EXTRACT(DOW FROM current_date)) % 7) * INTERVAL '1 day' AS monday_date
)

UPDATE work_schedule
SET shift_start = '09:00', shift_end = '17:00'
WHERE staff_id = (SELECT id FROM staff WHERE firstname = 'Іван' AND surname = 'Іванов')
  AND work_date = (SELECT monday_date FROM nearest_monday);

--1.4
WITH inserted_beverages AS (
    INSERT INTO beverages (price)
        VALUES (90.00)
        RETURNING id
)

INSERT INTO beverages_localization (beverage_id, language_id, name)
VALUES
    ((SELECT id FROM inserted_beverages LIMIT 1), (SELECT id FROM languages WHERE name = 'EN'), '"Eastern Star" Coffee with Cardamom'),
    ((SELECT id FROM inserted_beverages LIMIT 1), (SELECT id FROM languages WHERE name = 'UA'), '"Східна Зірка" Кава з Кардамоном');

--task 2
--2.1
WITH nearest_monday AS (
    SELECT current_date + ((9 - EXTRACT(DOW FROM current_date)) % 7) * INTERVAL '1 day' AS monday_date
)

INSERT INTO work_schedule (staff_id, work_date, shift_start, shift_end)
SELECT
    id,
    monday_date,
    '08:00',
    '16:00'
FROM staff, nearest_monday
WHERE firstname = 'Іван' AND surname = 'Іванов';

WITH nearest_monday AS (
    SELECT current_date + ((9 - EXTRACT(DOW FROM current_date)) % 7) * INTERVAL '1 day' AS monday_date
)

UPDATE work_schedule
SET shift_start = '09:00', shift_end = '17:00'
WHERE staff_id = (SELECT id FROM staff WHERE firstname = 'Іван' AND surname = 'Іванов')
  AND work_date = (SELECT monday_date FROM nearest_monday);

--2.2
UPDATE beverages_localization
SET name = '"Everest" Hot Chocolate'
WHERE name = 'Everest Hot Chocolate'
  AND language_id = (SELECT id FROM languages WHERE name = 'EN');

UPDATE beverages_localization
SET name = 'Гарячий шоколад "Еверест"'
WHERE name = 'Гарячий шоколад Еверест'
  AND language_id = (SELECT id FROM languages WHERE name = 'UA');

--2.3
WITH first_order AS (
    SELECT id
    FROM orders
    ORDER BY id
    LIMIT 1
)

UPDATE orders
SET total_price = 60.00
WHERE id = (SELECT id FROM first_order);

WITH first_order AS (
    SELECT id
    FROM orders
    ORDER BY id
    LIMIT 1
)

UPDATE order_items
SET quantity = 2, price_per_unit = 30.00
WHERE order_id = (SELECT id FROM first_order);


--2.4
UPDATE desserts_localization
SET name = 'Autumn in New York - Cake'
WHERE name = 'Autumn in New York Cake'
  AND language_id = (SELECT id FROM languages WHERE name = 'EN');

--task 3
--3.1
WITH targeted_order AS (
    SELECT o.id
    FROM orders o
             JOIN order_items oi ON o.id = oi.order_id
             JOIN beverages_localization bl ON oi.beverage_id = bl.beverage_id
    WHERE bl.name = 'Цикорій Глясе'
)
UPDATE orders
SET deleted_at = NOW()
WHERE id = (SELECT id FROM targeted_order);

--3.2
WITH targeted_order AS (
    SELECT o.id
    FROM orders o
             JOIN order_items oi ON o.id = oi.order_id
             JOIN desserts_localization dl ON oi.dessert_id = dl.dessert_id
    WHERE dl.name = 'Яблучний пиріг з корицею'
)
UPDATE orders
SET deleted_at = NOW()
WHERE id = (SELECT id FROM targeted_order);

--3.3
WITH nearest_monday AS (
    SELECT current_date + ((8 - EXTRACT(DOW FROM current_date)) % 7) * INTERVAL '1 day' AS monday_date
)

DELETE FROM work_schedule
WHERE staff_id = (SELECT id FROM staff WHERE firstname = 'Іван' AND surname = 'Іванов')
  AND work_date = (SELECT monday_date FROM nearest_monday);

--3.4
DELETE FROM work_schedule
WHERE work_date BETWEEN '2025-02-01' AND '2025-02-07'
  AND staff_id = (SELECT id FROM staff WHERE firstname = 'Іван' AND surname = 'Іванов');

--task 4
--4.1
SELECT * FROM order_items od
WHERE dessert_id = (
    SELECT desserts_localization.dessert_id
    FROM desserts_localization
    WHERE name = 'Apple Pie with Cinnamon');

--4.2
SELECT * FROM work_schedule
WHERE work_date = '2025-02-10';

--4.3
SELECT * FROM orders
WHERE staff_id = (
    SELECT staff.id
    FROM staff
    WHERE firstname = 'Іван' AND surname = 'Іванов'
    );

--4.4
SELECT * FROM orders
WHERE client_id = (
    SELECT clients.id
    FROM clients
    WHERE firstname = 'Марія' AND surname = 'Петренко'
);

