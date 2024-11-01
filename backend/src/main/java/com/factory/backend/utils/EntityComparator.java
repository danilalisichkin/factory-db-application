package com.factory.backend.utils;

import com.factory.backend.entities.nosql.MongoCategory;
import com.factory.backend.entities.nosql.MongoClient;
import com.factory.backend.entities.nosql.MongoMaterial;
import com.factory.backend.entities.nosql.MongoProduct;
import com.factory.backend.entities.nosql.MongoProductMaterial;
import com.factory.backend.entities.nosql.MongoProductOrder;
import com.factory.backend.entities.sql.Category;
import com.factory.backend.entities.sql.Client;
import com.factory.backend.entities.sql.Material;
import com.factory.backend.entities.sql.Product;
import com.factory.backend.entities.sql.ProductMaterial;
import com.factory.backend.entities.sql.ProductOrder;

public abstract class EntityComparator {
    public static boolean areProductsEqual(Product p1, MongoProduct p2) {
        return (p1.getId().equals(p2.getModelId()) &&
                p1.getCategory().getId().equals(p2.getCategoryId()) &&
                p1.getName().equals(p2.getName()) &&
                p2.getPrice().equals(p1.getPrice().doubleValue())
        );
    }

    public static boolean areCategoriesEqual(Category c1, MongoCategory c2) {
        return (c1.getId().equals(c2.getModelId()) &&
                c1.getParent().getId().equals(c2.getParentId()) &&
                c1.getName().equals(c2.getName())
        );
    }

    public static boolean areMaterialsEqual(Material m1, MongoMaterial m2) {
        return (m1.getId().equals(m2.getModelId()) &&
                m1.getName().equals(m2.getName()) &&
                m1.getSupplierName().equals(m2.getSupplierName())
        );
    }

    public static boolean areClientsEqual(Client c1, MongoClient c2) {
        return (c1.getPhoneNumber().equals(c2.getPhoneNumber()) &&
                c1.getEmail().equals(c2.getEmail()) &&
                c1.getLegalAddress().equals(c2.getLegalAddress()) &&
                c1.getOrganizationName().equals(c2.getOrganizationName())
        );
    }

    public static boolean areProductMaterialsEqual(ProductMaterial pm1, MongoProductMaterial pm2) {
        return (MongoProductMaterial.generateId(pm1.getProductSku().getId(), pm1.getMaterialSku().getId())
                .equals(pm2.getModelId()) &&
                pm1.getProductSku().getId().equals(pm2.getProductSku()) &&
                pm1.getMaterialSku().getId().equals(pm2.getMaterialSku())
        );
    }

    public static boolean areProductOrdersEqual(ProductOrder po1, MongoProductOrder po2) {
        return (MongoProductOrder.generateId(po1.getClientPhoneNumber().getPhoneNumber(), po1.getProductSku().getId())
                .equals(po2.getModelId()) &&
                po1.getProductSku().getId().equals(po2.getProductSku()) &&
                po1.getClientPhoneNumber().getPhoneNumber().equals(po2.getClientPhoneNumber()) &&
                po1.getQuantity().equals(po2.getQuantity())
        );
    }
}
