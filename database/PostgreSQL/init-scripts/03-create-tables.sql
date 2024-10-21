\c factory_db;

CREATE TABLE public.categories (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    parent_id INT,
    CONSTRAINT fk_parent_category FOREIGN KEY (parent_id)
REFERENCES public.categories (id)
ON DELETE SET NULL
);

CREATE TABLE public.products (
    sku SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    category_id INT,
    CONSTRAINT fk_category FOREIGN KEY (category_id)
REFERENCES public.categories (id)
ON DELETE CASCADE
);

CREATE TABLE public.clients (
    phone_number VARCHAR(20) PRIMARY KEY,
    organization_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    legal_address VARCHAR(255) NOT NULL
);

CREATE TABLE public.materials (
    sku SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    supplier_name VARCHAR(100) NOT NULL
);

CREATE TABLE public.product_orders (
    client_phone_number VARCHAR(20),
    product_sku INT,
    quantity INT NOT NULL,
    PRIMARY KEY (client_phone_number, product_sku),
    CONSTRAINT fk_client FOREIGN KEY (client_phone_number)
REFERENCES public.clients (phone_number)
ON DELETE CASCADE,
    CONSTRAINT fk_product FOREIGN KEY (product_sku)
REFERENCES public.products (sku)
ON DELETE CASCADE
);

CREATE TABLE public.product_materials (
    product_sku INT,
    material_sku INT,
    PRIMARY KEY (product_sku, material_sku),
    CONSTRAINT fk_product_material FOREIGN KEY (product_sku)
REFERENCES public.products (sku)
ON DELETE CASCADE,
    CONSTRAINT fk_material FOREIGN KEY (material_sku)
REFERENCES public.materials (sku)
ON DELETE CASCADE
);