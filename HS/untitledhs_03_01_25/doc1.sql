
INSERT INTO languages (name)
VALUES
    ('EN'),   -- English
    ('UA');   -- Ukrainian

--task 2
--2.1
--beverages
WITH inserted_beverages AS (
INSERT INTO beverages (price)
    VALUES
        (50.00),    -- Jasmine Tea price
        (60.00),    -- Chicory Glacé price
        (70.00),    -- Chocolate Frappe price
        (80.00),    -- Sweet Tales Latte price
        (90.00)     -- Everest Hot Chocolate price
    RETURNING id
)

INSERT INTO beverages_localization (beverage_id, language_id, name)
VALUES
    -- For Jasmine Tea
    ((SELECT id FROM inserted_beverages LIMIT 1), (SELECT id FROM languages WHERE name = 'EN'), 'Jasmine Tea'),
    ((SELECT id FROM inserted_beverages LIMIT 1), (SELECT id FROM languages WHERE name = 'UA'), 'Жасминовий чай'),

    -- For Chicory Glacé
    ((SELECT id FROM inserted_beverages OFFSET 1 LIMIT 1), (SELECT id FROM languages WHERE name = 'EN'), 'Chicory Glacé'),
    ((SELECT id FROM inserted_beverages OFFSET 1 LIMIT 1), (SELECT id FROM languages WHERE name = 'UA'), 'Цикорій Глясе'),

    -- For Chocolate Frappe
    ((SELECT id FROM inserted_beverages OFFSET 2 LIMIT 1), (SELECT id FROM languages WHERE name = 'EN'), 'Chocolate Frappe'),
    ((SELECT id FROM inserted_beverages OFFSET 2 LIMIT 1), (SELECT id FROM languages WHERE name = 'UA'), 'Шоколадний Фраппе'),

    -- For Sweet Tales Latte
    ((SELECT id FROM inserted_beverages OFFSET 3 LIMIT 1), (SELECT id FROM languages WHERE name = 'EN'), 'Sweet Tales Latte'),
    ((SELECT id FROM inserted_beverages OFFSET 3 LIMIT 1), (SELECT id FROM languages WHERE name = 'UA'), 'Лате Солодкі Історії'),

    -- For Everest Hot Chocolate
    ((SELECT id FROM inserted_beverages OFFSET 4 LIMIT 1), (SELECT id FROM languages WHERE name = 'EN'), 'Everest Hot Chocolate'),
    ((SELECT id FROM inserted_beverages OFFSET 4 LIMIT 1), (SELECT id FROM languages WHERE name = 'UA'), 'Гарячий шоколад Еверест');

--desserts
WITH inserted_desserts AS (
    INSERT INTO desserts (price)
        VALUES (30.00),    -- Apple Pie with Cinnamon price
               (35.00),    -- Canele with Vanilla and Honey price
               (40.00),    -- Autumn in New York Cake price
               (45.00),    -- Calgary Raspberry Cake price
               (50.00),    -- Gifts of the Forest Coffee Muffin price
               (55.00),    -- Maple English Pudding price
               (60.00)     -- Alpine Winter Tiramisu price
        RETURNING id
)

INSERT INTO desserts_localization (dessert_id, language_id, name)
VALUES
    -- For Apple Pie with Cinnamon
    ((SELECT id FROM inserted_desserts LIMIT 1), (SELECT id FROM languages WHERE name = 'EN'), 'Apple Pie with Cinnamon'),
    ((SELECT id FROM inserted_desserts LIMIT 1), (SELECT id FROM languages WHERE name = 'UA'), 'Яблучний пиріг з корицею'),

    -- For Canele with Vanilla and Honey
    ((SELECT id FROM inserted_desserts OFFSET 1 LIMIT 1), (SELECT id FROM languages WHERE name = 'EN'), 'Canele with Vanilla and Honey'),
    ((SELECT id FROM inserted_desserts OFFSET 1 LIMIT 1), (SELECT id FROM languages WHERE name = 'UA'), 'Канеле з ваніллю та медом'),

    -- For Autumn in New York Cake
    ((SELECT id FROM inserted_desserts OFFSET 2 LIMIT 1), (SELECT id FROM languages WHERE name = 'EN'), 'Autumn in New York Cake'),
    ((SELECT id FROM inserted_desserts OFFSET 2 LIMIT 1), (SELECT id FROM languages WHERE name = 'UA'), 'Осінь в Нью-Йорку: Торт'),

    -- For Calgary Raspberry Cake
    ((SELECT id FROM inserted_desserts OFFSET 3 LIMIT 1), (SELECT id FROM languages WHERE name = 'EN'), 'Calgary Raspberry Cake'),
    ((SELECT id FROM inserted_desserts OFFSET 3 LIMIT 1), (SELECT id FROM languages WHERE name = 'UA'), 'Малиновий торт Calgary'),

    -- For Gifts of the Forest Coffee Muffin
    ((SELECT id FROM inserted_desserts OFFSET 4 LIMIT 1), (SELECT id FROM languages WHERE name = 'EN'), 'Gifts of the Forest Coffee Muffin'),
    ((SELECT id FROM inserted_desserts OFFSET 4 LIMIT 1), (SELECT id FROM languages WHERE name = 'UA'), 'Кавовий кекс «Подарунки лісу»'),

    -- For Maple English Pudding
    ((SELECT id FROM inserted_desserts OFFSET 5 LIMIT 1), (SELECT id FROM languages WHERE name = 'EN'), 'Maple English Pudding'),
    ((SELECT id FROM inserted_desserts OFFSET 5 LIMIT 1), (SELECT id FROM languages WHERE name = 'UA'), 'Кленовий англійський пудинг'),

    -- For Alpine Winter Tiramisu
    ((SELECT id FROM inserted_desserts OFFSET 6 LIMIT 1), (SELECT id FROM languages WHERE name = 'EN'), 'Alpine Winter Tiramisu'),
    ((SELECT id FROM inserted_desserts OFFSET 6 LIMIT 1), (SELECT id FROM languages WHERE name = 'UA'), 'Альпійський зимовий тирамісу');

--2.2
INSERT INTO job_positions (name, salary_range, description)
VALUES
    ('Бариста', '[8000, 10000)', 'Приготування кави та чаю'),
    ('Кондитер', '[7000, 9000)', 'Приготування десертів');

INSERT INTO staff (firstname, surname, job_position_id, salary, phone, address)
VALUES ('Іван', 'Іванов', (SELECT id FROM job_positions WHERE name = 'Бариста'), 8000, '+380456789', 'Вулиця 1');

--2.3
INSERT INTO staff (firstname, surname, job_position_id, salary, phone, address)
VALUES ('Олена', 'Оленова', (SELECT id FROM job_positions WHERE name = 'Кондитер'), 7000, '+380654321', 'Вулиця 2');

--2.4
INSERT INTO clients (firstname, surname, birthdate, phone, address, discount)
VALUES ('Марія', 'Петренко', '1980-02-05', '+3802334455', 'Вулиця 3', 10.00);

--task 3
--3.1
UPDATE beverages
SET price = price * 1.10
WHERE id = (
    SELECT beverage_id
    FROM beverages_localization
    WHERE name = 'Chicory Glacé'
);

--3.2
UPDATE staff
SET phone = '222333444', address = 'Вулиця 4'
WHERE id = (SELECT id FROM staff WHERE firstname = 'Олена' AND surname = 'Оленова');

--3.3
UPDATE staff
SET phone = '555666777'
WHERE id = (SELECT id FROM staff WHERE firstname = 'Іван' AND surname = 'Іванов');

--3.4
UPDATE clients
SET discount = 15.00
WHERE id = (SELECT id FROM clients WHERE firstname = 'Марія' AND surname = 'Петренко');

--task 4
--4.1
UPDATE beverages
SET deleted_at = NOW()
WHERE id = (
    SELECT beverage_id
    FROM beverages_localization
    WHERE name = 'Chocolate Frappe'
);

--4.2
WITH updated_staff AS (
    UPDATE staff
    SET deleted_at = NOW()
    WHERE firstname = 'Олена' AND surname = 'Оленова'
    RETURNING id
)

UPDATE deleted_entities
SET reason = 'Звільнення'
WHERE entity_name = 'staff'
  AND entity_id = (SELECT id FROM updated_staff);

--4.3
WITH updated_staff AS (
    UPDATE staff
    SET deleted_at = NOW()
    WHERE firstname = 'Іван' AND surname = 'Іванов'
    RETURNING id
)

UPDATE deleted_entities
SET reason = 'Звільнення'
WHERE entity_name = 'staff'
  AND entity_id = (SELECT id FROM updated_staff);

--4.4
UPDATE clients
SET deleted_at = NOW()
WHERE firstname = 'Марія' AND surname = 'Петренко';

--task 5
--5.1
SELECT
    b.*,
    bl.name AS beverage_name
FROM
    beverages b
        LEFT JOIN
    beverages_localization bl ON b.id = bl.beverage_id
WHERE language_id = (SELECT id FROM languages WHERE name = 'EN');

--5.2
SELECT
    d.*,
    dl.name AS dessert_name
FROM
    desserts d
        LEFT JOIN
    desserts_localization dl ON d.id = dl.dessert_id
WHERE language_id = (SELECT id FROM languages WHERE name = 'EN');

--5.3
SELECT *
FROM staff
WHERE job_position_id = (
    SELECT id
    FROM job_positions
    WHERE name = 'Бариста'
);

--5.4
SELECT *
FROM staff
WHERE job_position_id = (
    SELECT id
    FROM job_positions
    WHERE name = 'Офіціант'
);
