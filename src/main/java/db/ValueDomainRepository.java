package db;

import de.samply.mdr.dal.dto.Element;
import de.samply.mdr.dao.ElementDAO;
import de.samply.mdr.dao.MDRConnection;
import de.samply.sdao.DAOException;
import graphql.schema.DataFetchingEnvironment;
import models.ValueDomainDTO;
import util.AuthContext;
import util.Converter;

public class ValueDomainRepository {
    public ValueDomainDTO getValueDomainById(int valueDomainId,DataFetchingEnvironment env) {
        AuthContext auth = env.getContext();
        try {
            MDRConnection mdr = ConnectionFactory.get(auth.getUser().getId());
            Element element=mdr.get(ElementDAO.class).getElement(valueDomainId);
            return Converter.getValueDomain(element);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
