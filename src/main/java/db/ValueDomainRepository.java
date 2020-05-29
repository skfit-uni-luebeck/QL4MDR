package db;

import de.samply.mdr.dal.dto.Element;
import de.samply.mdr.dao.ElementDAO;
import de.samply.mdr.dao.MDRConnection;
import de.samply.sdao.DAOException;
import graphql.schema.DataFetchingEnvironment;
import models.DataElementDTO;
import models.ValueDomainDTO;
import util.AuthContext;
import util.Converter;

public class ValueDomainRepository {
	public ValueDomainDTO getValueDomainById(DataElementDTO dataDTO, DataFetchingEnvironment env) {
		AuthContext auth = env.getContext();
		try {
			MDRConnection mdr = ConnectionFactory.get(auth.getUser().getId());
			Element element = mdr.get(ElementDAO.class).getElement(dataDTO.getValueDomainId());
			return Converter.getValueDomain(mdr,dataDTO, element);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
