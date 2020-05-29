package resolver;

import java.util.List;

import com.coxautodev.graphql.tools.GraphQLResolver;

import db.DataElementGroupRepository;
import db.DataElementRepository;
import graphql.schema.DataFetchingEnvironment;
import models.DataElementDTO;
import models.DataElementGroupDTO;
import models.NamespaceDTO;

public class NamespaceResolver implements GraphQLResolver<NamespaceDTO> {

    private final DataElementRepository dataElementRepository;
    private final DataElementGroupRepository dataElementGroupRepository;

    public NamespaceResolver(DataElementRepository dataElementRepository, DataElementGroupRepository dataElementGroupRepository) {
        this.dataElementRepository = dataElementRepository;
        this.dataElementGroupRepository = dataElementGroupRepository;
    }

    public List<DataElementDTO> dataElements(NamespaceDTO namespaceDTO,DataFetchingEnvironment env) {
        return dataElementRepository.findByNamespaceName(namespaceDTO.getName(),env);
    }

    public List<DataElementGroupDTO> dataElementGroups(NamespaceDTO namespaceDTO,DataFetchingEnvironment env) {
    	return dataElementGroupRepository.findByNamespaceName(namespaceDTO.getName(),env);
    }
    

}
