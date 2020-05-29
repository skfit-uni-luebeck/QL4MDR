package resolver;

import com.coxautodev.graphql.tools.GraphQLRootResolver;
import db.DataElementRepository;
import db.NamespaceRepository;
import db.SlotRepository;
import db.ValueDomainRepository;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.DataFetchingFieldSelectionSet;
import kotlin.reflect.jvm.internal.ReflectProperties;
import models.DataElementDTO;
import models.NamespaceDTO;
import models.SlotDTO;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public class Query implements GraphQLRootResolver {

	private NamespaceRepository namespaceRepository;
	private DataElementRepository dataElementRepository;
	private SlotRepository slotRepository;
	private ValueDomainRepository valueDomainRepository;

	public Query(NamespaceRepository namespaceRepository, DataElementRepository dataElementRepository,
			SlotRepository slotRepository, ValueDomainRepository valueDomainRepository) {
		this.namespaceRepository = namespaceRepository;
		this.dataElementRepository = dataElementRepository;
		this.slotRepository = slotRepository;
		this.valueDomainRepository = valueDomainRepository;
	}

	public List<NamespaceDTO> namespaces(DataFetchingEnvironment env) {
		return namespaceRepository.getNamespaces(env);
	}

	public NamespaceDTO namespace(String name, DataFetchingEnvironment env) {
		return namespaceRepository.getNamespace(name, env);
	}

	public DataElementDTO dataElement(String urn, DataFetchingEnvironment env) {
		return dataElementRepository.getDataElement(urn, env);
	}

	public List<DataElementDTO> dataElements(String key, String value, DataFetchingEnvironment env) {
		return dataElementRepository.getDataElement(key, value, env);
	}

	public List<SlotDTO> slots(String key, DataFetchingEnvironment env) {
		return slotRepository.getSlots(key, env);
	}

}
