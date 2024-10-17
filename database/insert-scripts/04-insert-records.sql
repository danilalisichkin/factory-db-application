\c factory_db;

INSERT INTO public.categories (name, parent_id) VALUES
    ('Furniture', NULL),            --1
    ('Bedroom Furniture', 1),       --2
    ('Beds', 2),                    --3
    ('Nightstands', 2),             --4
    ('Living Room Furniture', 1),   --5
    ('Coffee Tables', 5),           --6
    ('Cabinets', 5),                --7
    ('Kitchen Furniture', 1),       --8
    ('Bar Stools', 8),              --9
    ('Dining Tables', 8);           --10

INSERT INTO public.products (name, category_id, price) VALUES
    ('"Royal" Bed', 3, 3200.00),    --1
    ('"Slavyanka" Bed', 3, 2200.00), --2
    ('"Feast" Table', 10, 1400.00),  --3
    ('"Two Nightstands Plus" Table', 4, 400.00), --4
    ('"Glass Cube" Cabinet', 7, 2000.00);  --5

INSERT INTO public.materials (name, supplier_name) VALUES
    ('Wood - Birch', 'LLC "Lesogryzy"'),               --1
    ('Wood - Oak', 'LLC "Lesogryzy"'),                 --2
    ('Acrylic Glass', 'OJSC "Glass Factory "Neman""'), --3
    ('Fiberboard', 'OJSC "MaterialPlus"'),             --4
    ('Particle Board', 'OJSC "MaterialPlus"'),         --5
    ('Gold', 'OJSC "BelJewelryTrade"'),                 --6
    ('Natural Leather', 'LLC "Wild Hunter"'),           --7
    ('Textile', 'LLC "Minsk Textile Factory"');         --8

INSERT INTO public.product_materials (product_sku, material_sku) VALUES
    (1, 2),
    (1, 4),
    (1, 6),
    (1, 8),
    (1, 7),
    (2, 1),
    (2, 4),
    (2, 8),
    (3, 4),
    (3, 5),
    (4, 4),
    (4, 5),
    (5, 3),
    (5, 5);

INSERT INTO public.clients (phone_number, organization_name, email, legal_address) VALUES
    ('375291111111', 'OJSC "AMI Furniture"', 'ami@gmail.com', 'Dzerzhinsk, Lenin St. 12'),
    ('375331223445', 'LLC "IT Solutions"', 'itsolutions@gmail.com', 'Minsk, International St. 14, office 4'),
    ('375441114444', 'State Educational Institution "School 15 of Minsk"', 'shool15minsk@mail.ru', 'Minsk, Kalinin St. 52');

INSERT INTO public.product_orders (client_phone_number, product_sku, quantity) VALUES
    ('375291111111', 1, 2),
    ('375291111111', 2, 10),
    ('375291111111', 3, 10),
    ('375291111111', 4, 4),
    ('375331223445', 5, 10),
    ('375331223445', 3, 3),
    ('375441114444', 5, 10);