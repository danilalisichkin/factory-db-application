package com.factory.backend.services.impl.converter;

import com.factory.backend.core.mappers.category.CategoryMapper;
import com.factory.backend.core.mappers.client.ClientMapper;
import com.factory.backend.core.mappers.material.MaterialMapper;
import com.factory.backend.core.mappers.product.ProductMapper;
import com.factory.backend.core.mappers.product.material.ProductMaterialMapper;
import com.factory.backend.core.mappers.product.order.ProductOrderMapper;
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
import com.factory.backend.repository.nosql.MongoCategoryRepository;
import com.factory.backend.repository.nosql.MongoClientRepository;
import com.factory.backend.repository.nosql.MongoMaterialRepository;
import com.factory.backend.repository.nosql.MongoProductMaterialRepository;
import com.factory.backend.repository.nosql.MongoProductOrderRepository;
import com.factory.backend.repository.nosql.MongoProductRepository;
import com.factory.backend.repository.sql.CategoryRepository;
import com.factory.backend.repository.sql.ClientRepository;
import com.factory.backend.repository.sql.MaterialRepository;
import com.factory.backend.repository.sql.ProductMaterialRepository;
import com.factory.backend.repository.sql.ProductOrderRepository;
import com.factory.backend.repository.sql.ProductRepository;
import com.factory.backend.services.IConverterService;
import com.factory.backend.utils.EntityComparator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConverterService implements IConverterService {
    private final ProductRepository productRepository;
    private final MongoProductRepository mongoProductRepository;
    private final ProductMapper productMapper;

    private final CategoryRepository categoryRepository;
    private final MongoCategoryRepository mongoCategoryRepository;
    private final CategoryMapper categoryMapper;

    private final MaterialRepository materialRepository;
    private final MongoMaterialRepository mongoMaterialRepository;
    private final MaterialMapper materialMapper;

    private final ClientRepository clientRepository;
    private final MongoClientRepository mongoClientRepository;
    private final ClientMapper clientMapper;

    private final ProductOrderRepository productOrderRepository;
    private final MongoProductOrderRepository mongoProductOrderRepository;
    private final ProductOrderMapper productOrderMapper;

    private final ProductMaterialRepository productMaterialRepository;
    private final MongoProductMaterialRepository mongoProductMaterialRepository;
    private final ProductMaterialMapper productMaterialMapper;

    @Override
    public void ConvertPostgreToMongo() {
        convertProductTable();
        convertCategoryTable();
        convertMaterialTable();
        convertClientTable();
        convertProductOrderTable();
        convertProductMaterialTable();
    }

    private void convertProductTable() {
        List<Product> products = productRepository.findAll();

        for (Product product : products) {
            Optional<MongoProduct> productOptional = mongoProductRepository.findByModelId(product.getId());
            if (productOptional.isPresent() && !EntityComparator.areProductsEqual(product, productOptional.get())) {
                MongoProduct mongoProduct = productMapper.entityToMongo(product);
                mongoProduct.setId(productOptional.get().getId());
                mongoProductRepository.save(mongoProduct);
            } else if (productOptional.isEmpty()) {
                MongoProduct mongoProduct = productMapper.entityToMongo(product);
                mongoProductRepository.save(mongoProduct);
            }
        }
    }

    private void convertCategoryTable() {
        List<Category> categories = categoryRepository.findAll();

        for (Category category : categories) {
            Optional<MongoCategory> categoryOptional = mongoCategoryRepository.findByModelId(category.getId());
            if (categoryOptional.isPresent() && !EntityComparator.areCategoriesEqual(category, categoryOptional.get())) {
                MongoCategory mongoCategory = categoryMapper.entityToMongo(category);
                mongoCategory.setId(categoryOptional.get().getId());
                mongoCategoryRepository.save(mongoCategory);
            } else if (categoryOptional.isEmpty()) {
                MongoCategory mongoCategory = categoryMapper.entityToMongo(category);
                mongoCategoryRepository.save(mongoCategory);
            }
        }
    }

    private void convertMaterialTable() {
        List<Material> materials = materialRepository.findAll();

        for (Material material : materials) {
            Optional<MongoMaterial> materialOptional = mongoMaterialRepository.findByModelId(material.getId());
            if (materialOptional.isPresent() && !EntityComparator.areMaterialsEqual(material, materialOptional.get())) {
                MongoMaterial mongoMaterial = materialMapper.entityToMongo(material);
                mongoMaterial.setId(materialOptional.get().getId());
                mongoMaterialRepository.save(mongoMaterial);
            } else if (materialOptional.isEmpty()) {
                MongoMaterial mongoMaterial = materialMapper.entityToMongo(material);
                mongoMaterialRepository.save(mongoMaterial);
            }
        }
    }

    private void convertClientTable() {
        List<Client> clients = clientRepository.findAll();

        for (Client client : clients) {
            Optional<MongoClient> clientOptional = mongoClientRepository.findByPhoneNumber(client.getPhoneNumber());
            if (clientOptional.isPresent() && !EntityComparator.areClientsEqual(client, clientOptional.get())) {
                MongoClient mongoClient = clientMapper.entityToMongo(client);
                mongoClient.setId(clientOptional.get().getId());
                mongoClientRepository.save(mongoClient);
            } else if (clientOptional.isEmpty()) {
                MongoClient mongoClient = clientMapper.entityToMongo(client);
                mongoClientRepository.save(mongoClient);
            }
        }
    }

    private void convertProductOrderTable() {
        List<ProductOrder> productOrders = productOrderRepository.findAll();

        for (ProductOrder productOrder : productOrders) {
            Optional<MongoProductOrder> productOrderOptional = mongoProductOrderRepository.findByModelId(
                    MongoProductOrder.generateId(
                            productOrder.getClientPhoneNumber().getPhoneNumber(),
                            productOrder.getProductSku().getId())
            );
            if (productOrderOptional.isPresent() && !EntityComparator.areProductOrdersEqual(productOrder, productOrderOptional.get())) {
                MongoProductOrder mongoProductOrder = productOrderMapper.entityToMongo(productOrder);
                mongoProductOrder.setId(productOrderOptional.get().getId());
                mongoProductOrderRepository.save(mongoProductOrder);
            } else if (productOrderOptional.isEmpty()) {
                MongoProductOrder mongoProductOrder = productOrderMapper.entityToMongo(productOrder);
                mongoProductOrderRepository.save(mongoProductOrder);
            }
        }
    }

    private void convertProductMaterialTable() {
        List<ProductMaterial> productMaterials = productMaterialRepository.findAll();

        for (ProductMaterial productMaterial : productMaterials) {
            Optional<MongoProductMaterial> productMaterialOptional = mongoProductMaterialRepository.findByModelId(
                    MongoProductMaterial.generateId(
                            productMaterial.getId().getProductSku(),
                            productMaterial.getId().getMaterialSku()));
            if (productMaterialOptional.isPresent() && !EntityComparator.areProductMaterialsEqual(productMaterial, productMaterialOptional.get())) {
                MongoProductMaterial mongoProductMaterial = productMaterialMapper.entityToMongo(productMaterial);
                mongoProductMaterial.setId(productMaterialOptional.get().getId());
                mongoProductMaterialRepository.save(mongoProductMaterial);
            } else if (productMaterialOptional.isEmpty()) {
                MongoProductMaterial mongoProductMaterial = productMaterialMapper.entityToMongo(productMaterial);
                mongoProductMaterialRepository.save(mongoProductMaterial);
            }
        }
    }
}
