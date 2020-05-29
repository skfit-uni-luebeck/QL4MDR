package resolver;

import java.util.List;

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

public class DataElementsResolver implements GraphQLResolver<DataElementDTO> {

	private DataElementRepository dataElementRepository;
	private ValueDomainRepository valueDomainRepository;
	private SlotRepository slotRepository;

	public DataElementsResolver(NamespaceRepository namespaceRepository, ValueDomainRepository valueDomainRepository,
			SlotRepository slotRepository) {

		this.valueDomainRepository = valueDomainRepository;
		this.slotRepository = slotRepository;
	}

	public ValueDomainDTO valueDomain(DataElementDTO dataElementDTO, DataFetchingEnvironment env) {
		System.out.println(dataElementDTO.getId() + "-" + dataElementDTO.getValueDomainId());
		return valueDomainRepository.getValueDomainById(dataElementDTO, env);
	}

	public List<DataElementDTO> dataElements(NamespaceDTO namespaceDTO, DataFetchingEnvironment env) {
		return dataElementRepository.findByNamespaceName(namespaceDTO.getName(), env);
	}

	public List<SlotDTO> slots(DataElementDTO dataElementDTO, DataFetchingEnvironment env) {
		return slotRepository.findSlotByScopeIdentifier(dataElementDTO.getScopedIdentifier(), env);
	}

}
