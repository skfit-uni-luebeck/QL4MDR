package resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import db.DataElementRepository;
import db.NamespaceRepository;
import db.SlotRepository;
import db.ValueDomainRepository;
import graphql.schema.DataFetchingEnvironment;
import models.DataElementDTO;
import models.NamespaceDTO;
import models.SlotDTO;
import models.ValueDomainDTO;

import java.util.List;

public class DataElementsResolver implements GraphQLResolver<DataElementDTO> {

    private final NamespaceRepository namespaceRepository;
    private DataElementRepository dataElementRepository;
    private ValueDomainRepository valueDomainRepository;
    private SlotRepository slotRepository;

    public DataElementsResolver(NamespaceRepository namespaceRepository, ValueDomainRepository valueDomainRepository, SlotRepository slotRepository) {

        this.namespaceRepository = namespaceRepository;
        this.valueDomainRepository=valueDomainRepository;
        this.slotRepository=slotRepository;
    }

    public ValueDomainDTO valueDomain(DataElementDTO dataElementDTO,DataFetchingEnvironment env){
        return valueDomainRepository.getValueDomainById(dataElementDTO.getValueDomainId(),env);
    }

     public List<DataElementDTO> dataElements(NamespaceDTO namespaceDTO, DataFetchingEnvironment env) {
        return dataElementRepository.findByNamespaceName(namespaceDTO.getName(),env);
}
    public List<SlotDTO> slots(DataElementDTO dataElementDTO,DataFetchingEnvironment env){
        return slotRepository.findSlotByScopeIdentifier(dataElementDTO.getScopedIdentifier(),env);
    }



}
