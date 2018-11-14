package util;

import de.samply.mdr.dal.dto.*;
import de.samply.mdr.dao.ElementDAO;
import de.samply.mdr.dao.IdentifiedDAO;
import de.samply.mdr.dao.MDRConnection;
import de.samply.mdr.dao.SlotDAO;
import models.*;

import java.util.ArrayList;
import java.util.List;

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

    public static List<DataElementGroupDTO> convertG(MDRConnection mdr, List<IdentifiedElement> elements) throws Exception {
        ArrayList<DataElementGroupDTO> members = new ArrayList<>();
        for (IdentifiedElement e : elements) {
            if (e.getElement().getElementType().equals(ElementType.DATAELEMENTGROUP)) {
                DataElementGroupDTO dto = new DataElementGroupDTO();
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
        DataElement de = (DataElement) e.getElement();
        DataElementDTO dto = new DataElementDTO();
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

    public static ValueDomainDTO getValueDomain(Element e){

        ValueDomainDTO dto = new ValueDomainDTO();
        ValueDomain domain = (ValueDomain) e;

        dto.setDatatype(domain.getDatatype());
        dto.setFormat(domain.getFormat());
        dto.setUnitOfMeasure(domain.getUnitOfMeasure());
        dto.setMaximumCharacters(domain.getMaxCharacters());

        if(domain instanceof DescribedValueDomain) {
            DescribedValueDomain dVa = (DescribedValueDomain) domain;
            dto.setValidationType(dVa.getValidationType().name());
            dto.setValidationData(dVa.getValidationData());
        }
        return dto;
    }

    private static IdentificationDTO getIdentification(IdentifiedElement e) {
        return new IdentificationDTO(e.getScoped().toString(), e.getScoped().getStatus().name());
    }

}
