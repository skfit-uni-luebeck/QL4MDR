package resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import db.DataElementRepository;
import db.SlotRepository;
import graphql.schema.DataFetchingEnvironment;
import models.DataElementDTO;
import models.NamespaceDTO;

import java.util.List;

public class NamespaceResolver implements GraphQLResolver<NamespaceDTO> {

    private final DataElementRepository dataElementRepository;

    public NamespaceResolver(DataElementRepository dataElementRepository) {
        this.dataElementRepository = dataElementRepository;
    }

    public List<DataElementDTO> dataElements(NamespaceDTO namespaceDTO,DataFetchingEnvironment env) {
        return dataElementRepository.findByNamespaceName(namespaceDTO.getName(),env);
    }


}
