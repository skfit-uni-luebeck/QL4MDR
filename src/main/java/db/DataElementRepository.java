package db;

import java.util.ArrayList;
import java.util.List;

import de.samply.mdr.dal.dto.ElementType;
import de.samply.mdr.dal.dto.IdentifiedElement;
import de.samply.mdr.dao.IdentifiedDAO;
import de.samply.mdr.dao.MDRConnection;
import de.samply.sdao.DAOException;
import graphql.schema.DataFetchingEnvironment;
import models.DataElementDTO;
import util.AuthContext;
import util.Converter;

public class DataElementRepository {

	public DataElementRepository() {
	}

	public DataElementDTO getDataElement(String urn, DataFetchingEnvironment env) {
		DataElementDTO dto = new DataElementDTO();
		AuthContext auth = env.getContext();
		try {
			MDRConnection mdr = ConnectionFactory.get(auth.getUser().getId());
			IdentifiedElement element = mdr.get(IdentifiedDAO.class).getElement(urn);
			if (element != null) {
				dto = Converter.convert(mdr, element);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	public List<DataElementDTO> getDataElement(String key, String value, DataFetchingEnvironment env) {
		AuthContext auth = env.getContext();
		List<DataElementDTO> dataElements = new ArrayList<>();

		try {
			MDRConnection mdr = ConnectionFactory.get(auth.getUser().getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataElements;
	}

	public List<DataElementDTO> findByNamespaceName(String name, DataFetchingEnvironment env) {
		AuthContext auth = env.getContext();
		List<DataElementDTO> dataElementDTOS = new ArrayList<>();
		try {
			MDRConnection mdr = ConnectionFactory.get(auth.getUser().getId());
			List<IdentifiedElement> identifiedElements = mdr.get(IdentifiedDAO.class).getRootElements(name);
			for (IdentifiedElement e : identifiedElements) {
				if (e.getElementType().equals(ElementType.DATAELEMENT)) {
					dataElementDTOS.add(Converter.convert(mdr, e));
				}
			}
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return dataElementDTOS;
	}

}
