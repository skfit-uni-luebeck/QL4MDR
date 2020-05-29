package db;

import java.util.ArrayList;
import java.util.List;

import de.samply.mdr.dal.dto.DescribedElement;
import de.samply.mdr.dal.dto.Namespace;
import de.samply.mdr.dao.MDRConnection;
import de.samply.mdr.dao.NamespaceDAO;
import graphql.schema.DataFetchingEnvironment;
import models.NamespaceDTO;
import util.AuthContext;
import util.Converter;

public class NamespaceRepository {

	private List<NamespaceDTO> namespaces;

	public NamespaceRepository() {
	}

	public NamespaceDTO getNamespace(String name, DataFetchingEnvironment env) {
		AuthContext auth = env.getContext();
		NamespaceDTO dto = new NamespaceDTO();
		System.out.println(auth.getUser().getId());
		try {
			MDRConnection mdr = ConnectionFactory.get(auth.getUser().getId());
			DescribedElement de = mdr.get(NamespaceDAO.class).getNamespace(name);

			if (de != null) {
				dto = Converter.convert(de);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	public List<NamespaceDTO> getNamespaces(DataFetchingEnvironment env) {
		AuthContext auth = env.getContext();
		namespaces = new ArrayList<NamespaceDTO>();
		try {
			MDRConnection mdr = ConnectionFactory.get(auth.getUser().getId());
			List<DescribedElement> readable = mdr.get(NamespaceDAO.class).getReadableNamespaces();
			for (DescribedElement d : readable) {
				Namespace n = (Namespace) d.getElement();
				NamespaceDTO dto = Converter.convert(d);
				namespaces.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return namespaces;
	}
}
