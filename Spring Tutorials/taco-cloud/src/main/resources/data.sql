DELETE FROM
    ingredient_ref;
DELETE FROM
    taco;
DELETE FROM
    taco_order;
DELETE FROM
    ingredient;
INSERT INTO ingredient (id, name, type)
VALUES
    ('FLTO', 'Flour Tortilla', 'WRAP');
INSERT INTO ingredient (id, name, type)
VALUES
    ('COTO', 'Corn Tortilla', 'WRAP');
INSERT INTO ingredient (id, name, type)
VALUES
    ('GRBF', 'Ground Beef', 'PROTEIN');
INSERT INTO ingredient (id, name, type)
VALUES
    ('CARN', 'Carnitas', 'PROTEIN');
INSERT INTO ingredient (id, name, type)
VALUES
    (
        'TMTO', 'Diced Tomatoes', 'VEGGIES'
    );
INSERT INTO ingredient (id, name, type)
VALUES
    ('LETC', 'Lettuce', 'VEGGIES');
INSERT INTO ingredient (id, name, type)
VALUES
    ('CHED', 'Cheddar', 'CHEESE');
INSERT INTO ingredient (id, name, type)
VALUES
    (
        'JACK', 'Monterrey Jack', 'CHEESE'
    );
INSERT INTO ingredient (id, name, type)
VALUES
    ('SLSA', 'Salsa', 'SAUCE');
INSERT INTO ingredient (id, name, type)
VALUES
    ('SRCR', 'Sour Cream', 'SAUCE');
