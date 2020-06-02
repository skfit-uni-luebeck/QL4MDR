package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.samply.mdr.dal.dto.DataElement;
import de.samply.mdr.dal.dto.Definition;
import de.samply.mdr.dal.dto.DescribedElement;
import de.samply.mdr.dal.dto.DescribedValueDomain;
import de.samply.mdr.dal.dto.Element;
import de.samply.mdr.dal.dto.ElementType;
import de.samply.mdr.dal.dto.EnumeratedValueDomain;
import de.samply.mdr.dal.dto.IdentifiedElement;
import de.samply.mdr.dal.dto.Namespace;
import de.samply.mdr.dal.dto.PermissibleValue;
import de.samply.mdr.dal.dto.ScopedIdentifier;
import de.samply.mdr.dal.dto.Slot;
import de.samply.mdr.dal.dto.ValueDomain;
import de.samply.mdr.dao.DefinitionDAO;
import de.samply.mdr.dao.ElementDAO;
import de.samply.mdr.dao.IdentifiedDAO;
import de.samply.mdr.dao.MDRConnection;
import de.samply.mdr.dao.PermissibleCodeDAO;
import de.samply.mdr.dao.ScopedIdentifierDAO;
import de.samply.sdao.DAOException;
import models.DataElementDTO;
import models.DataElementGroupDTO;
import models.IdentificationDTO;
import models.NamespaceDTO;
import models.PermissiableValueDTO;
import models.SlotDTO;
import models.ValueDomainDTO;

public class Converter {

	public static List<DataElementDTO> convert(MDRConnection mdr, List<IdentifiedElement> elements) {
		ArrayList<DataElementDTO> members = new ArrayList<>();
		for (IdentifiedElement e : elements) {
			if (e.getElement().getElementType().equals(ElementType.DATAELEMENT)) {
				DataElementDTO dto = convert(mdr, e);
				members.add(dto);
			}
		}
		return members;
	}

	public static List<DataElementGroupDTO> convertGroup(MDRConnection mdr, List<IdentifiedElement> elements)
			throws Exception {
		ArrayList<DataElementGroupDTO> members = new ArrayList<>();
		for (IdentifiedElement e : elements) {
			if (e.getElement().getElementType().equals(ElementType.DATAELEMENTGROUP)) {
				DataElementGroupDTO dto = new DataElementGroupDTO();
				dto.setIdentification(getIdentification(e));
				dto.setDefinitions(e.getDefinitions());
				dto.setDataElements(convert(mdr, mdr.get(IdentifiedDAO.class).getSubMembers(e.getScoped().toString())));
				members.add(dto);
			}
		}
		return members;
	}

	public static NamespaceDTO convert(DescribedElement de) {
		Namespace n = null;
		if (de.getElement() instanceof Namespace) {
			n = (Namespace) de.getElement();
		}

		NamespaceDTO dto = new NamespaceDTO();
		dto.setName(n.getName());
		dto.setWritable(false);
		dto.setDefinitions(de.getDefinitions());
		return dto;
	}

	public static DataElementDTO convert(MDRConnection mdr, IdentifiedElement e) {
		DataElementDTO dto = new DataElementDTO();
		DataElement de = (DataElement) e.getElement();
		dto.setDefinitions(e.getDefinitions());
		dto.setIdentification(getIdentification(e));
		dto.setId(e.getId());
		dto.setValueDomainId(de.getValueDomainId());
		dto.setScopedIdentifier(e.getScoped());
		return dto;

	}

	public static List<SlotDTO> getSlots(List<Slot> slots) {
		List<SlotDTO> sDTOs = new ArrayList<>();
		for (Slot s : slots) {
			sDTOs.add(new SlotDTO(s.getKey(), s.getValue()));
		}
		return sDTOs;
	}

	public static ValueDomainDTO getValueDomain(MDRConnection mdr, DataElementDTO dataDTO, Element e) {

		ValueDomainDTO dto = new ValueDomainDTO();
		ValueDomain domain = (ValueDomain) e;

		dto.setDatatype(domain.getDatatype());
		dto.setFormat(domain.getFormat());
		dto.setUnitOfMeasure(domain.getUnitOfMeasure());
		dto.setMaximumCharacters(domain.getMaxCharacters());

		if (domain instanceof DescribedValueDomain) {
			DescribedValueDomain dVa = (DescribedValueDomain) domain;
			dto.setValidationType(dVa.getValidationType().name());
			dto.setValidationData(dVa.getValidationData());
		}

		if (domain instanceof EnumeratedValueDomain) {
			List<PermissiableValueDTO> permissiableValueDTOs = new ArrayList<PermissiableValueDTO>();
			try {
				for (Element element : mdr.get(ElementDAO.class).getPermissibleValues(domain.getId())) {
					PermissibleValue permissibleValue = (PermissibleValue) element;
					permissiableValueDTOs.add(getPermissiableValue(mdr, permissibleValue,
							dataDTO.getDefinitions().get(0).getScopedIdentifierId()));
				}
				dto.setPermissiableValues(permissiableValueDTOs);
			} catch (DAOException e1) {
				e1.printStackTrace();
			}
		}
		return dto;
	}

	private static PermissiableValueDTO getPermissiableValue(MDRConnection mdr, PermissibleValue permissibleValue,
			int scopeId) {
		List<Definition> definitions;
		try {
			definitions = mdr.get(DefinitionDAO.class).getDefinitions(permissibleValue.getId(), scopeId);
			PermissiableValueDTO dto = new PermissiableValueDTO(permissibleValue.getPermittedValue(), definitions);
			return dto;
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static IdentificationDTO getIdentification(IdentifiedElement e) {
		return new IdentificationDTO(e.getScoped().toString(), e.getScoped().getStatus().name());
	}

}
