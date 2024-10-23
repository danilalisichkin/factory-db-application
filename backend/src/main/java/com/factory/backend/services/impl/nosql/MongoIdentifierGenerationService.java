package com.factory.backend.services.impl.nosql;

import com.factory.backend.entities.nosql.Identifier;
import com.factory.backend.exceptions.InternalServerException;
import com.factory.backend.repository.nosql.MongoIdentifierRepository;
import com.factory.backend.services.IIdentifierGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class MongoIdentifierGenerationService implements IIdentifierGenerationService {

    private final MongoIdentifierRepository identifierRepository;

    @Autowired
    public MongoIdentifierGenerationService(MongoIdentifierRepository identifierRepository) {
        this.identifierRepository = identifierRepository;
    }

    @Override
    public Integer generateCategoryIdentifier() {
        Identifier identifier = getCurrentIdentifiers();

        Integer categoryId = identifier.getCategoryId();
        identifier.setCategoryId(categoryId + 1);

        identifierRepository.save(identifier);

        return categoryId;
    }

    @Override
    public Integer generateProductIdentifier() {
        Identifier identifier = getCurrentIdentifiers();

        Integer productId = identifier.getProductId();
        identifier.setProductId(productId + 1);

        identifierRepository.save(identifier);

        return productId;
    }

    @Override
    public Integer generateMaterialIdentifier() {
        Identifier identifier = getCurrentIdentifiers();

        Integer materialId = identifier.getMaterialId();
        identifier.setMaterialId(materialId + 1);

        identifierRepository.save(identifier);

        return materialId;
    }

    private Identifier getCurrentIdentifiers() {
        List<Identifier> currentIdentifiers = identifierRepository.findAll();
        if (currentIdentifiers.isEmpty()) {
            Identifier identifier =  Identifier.builder()
                    .categoryId(1)
                    .productId(1)
                    .materialId(1)
                    .build();
            return identifierRepository.save(identifier);
        } else if (currentIdentifiers.size() > 1) {
            throw new InternalServerException("Found more than one current identifiers for collections in MongoDB");
        } else {
            return currentIdentifiers.get(0);
        }
    }
}
