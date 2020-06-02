package db;

import java.util.ArrayList;
import java.util.List;

import de.samply.mdr.dal.dto.IdentifiedElement;
import de.samply.mdr.dao.IdentifiedDAO;
import de.samply.mdr.dao.MDRConnection;
import graphql.schema.DataFetchingEnvironment;
import models.DataElementGroupDTO;
import util.AuthContext;
import util.Converter;

public class DataElementGroupRepository {

	public DataElementGroupRepository() {
	}

	public List<DataElementGroupDTO> findByNamespaceName(String name, DataFetchingEnvironment env) {
		AuthContext auth = env.getContext();
		List<DataElementGroupDTO> dataElementGroupDTOs = new ArrayList<>();
		try {
			MDRConnection mdr = ConnectionFactory.get(auth.getUser().getId());
			List<IdentifiedElement> identifiedElements = mdr.get(IdentifiedDAO.class).getRootElements(name);
			dataElementGroupDTOs = Converter.convertGroup(mdr, identifiedElements);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataElementGroupDTOs;
	}
	
	public List<DataElementGroupDTO> findSubMember(String urn, DataFetchingEnvironment env){
		AuthContext auth = env.getContext();
		List<DataElementGroupDTO> dataElementGroupDTOs = new ArrayList<>();
		try {
			MDRConnection mdr = ConnectionFactory.get(auth.getUser().getId());
			List<IdentifiedElement> identifiedElements = mdr.get(IdentifiedDAO.class).getSubMembers(urn);
			dataElementGroupDTOs = Converter.convertGroup(mdr, identifiedElements);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataElementGroupDTOs;
	}

}
