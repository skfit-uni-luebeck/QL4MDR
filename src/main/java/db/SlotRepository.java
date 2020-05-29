package db;

import java.util.ArrayList;
import java.util.List;

import de.samply.mdr.dal.dto.ScopedIdentifier;
import de.samply.mdr.dao.MDRConnection;
import de.samply.mdr.dao.SlotDAO;
import de.samply.sdao.DAOException;
import graphql.schema.DataFetchingEnvironment;
import models.SlotDTO;
import util.AuthContext;
import util.Converter;

public class SlotRepository {

	public SlotRepository() {
	}

	public List<SlotDTO> findSlotByScopeIdentifier(ScopedIdentifier scopedIdentifier, DataFetchingEnvironment env) {
		List<SlotDTO> slots = new ArrayList<>();
		AuthContext auth = env.getContext();
		try {
			MDRConnection mdr = ConnectionFactory.get(auth.getUser().getId());
			slots = Converter.getSlots(mdr.get(SlotDAO.class).getSlots(scopedIdentifier.toString()));
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return slots;
	}

	public List<SlotDTO> getSlots(String key, DataFetchingEnvironment env) {
		AuthContext auth = env.getContext();
		List<SlotDTO> slots = new ArrayList<SlotDTO>();
		try {
			MDRConnection mdr = ConnectionFactory.get(auth.getUser().getId());
			slots = Converter.getSlots(mdr.get(SlotDAO.class).getSlots(""));
			return null;
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return slots;
	}
}
