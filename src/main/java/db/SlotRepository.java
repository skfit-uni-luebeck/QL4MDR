package db;

import de.samply.mdr.dal.dto.ScopedIdentifier;
import de.samply.mdr.dao.MDRConnection;
import de.samply.mdr.dao.SlotDAO;
import de.samply.sdao.DAOException;
import graphql.schema.DataFetchingEnvironment;
import models.SlotDTO;
import util.AuthContext;
import util.Converter;

import java.util.ArrayList;
import java.util.List;

public class SlotRepository {

    public SlotRepository() {
    }

    public List<SlotDTO> findSlotByScopeIdentifier(ScopedIdentifier scopedIdentifier, DataFetchingEnvironment env) {
        AuthContext auth = env.getContext();
        try {
            MDRConnection mdr  = ConnectionFactory.get(auth.getUser().getId());
            return Converter.getSlots(mdr.get(SlotDAO.class).getSlots(scopedIdentifier.toString()));
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
