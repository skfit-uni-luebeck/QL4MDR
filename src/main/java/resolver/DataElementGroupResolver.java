package resolver;

import java.util.List;

import com.coxautodev.graphql.tools.GraphQLResolver;

import db.DataElementGroupRepository;
import db.DataElementRepository;
import graphql.schema.DataFetchingEnvironment;
import models.DataElementDTO;
import models.DataElementGroupDTO;
import models.NamespaceDTO;

public class DataElementGroupResolver implements GraphQLResolver<DataElementGroupDTO> {

	private DataElementGroupRepository dataElementGroupRepository;

	public DataElementGroupResolver(DataElementGroupRepository dataElementGroupRepository) {
		this.dataElementGroupRepository = dataElementGroupRepository;
	}

	public List<DataElementGroupDTO> dataElementGroups(DataElementGroupDTO dto, DataFetchingEnvironment env) {
		return dataElementGroupRepository.findSubMember(dto.getIdentification().getUrn(), env);
	}
	
}
