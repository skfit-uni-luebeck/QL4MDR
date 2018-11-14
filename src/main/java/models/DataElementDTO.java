package models;

import de.samply.mdr.dal.dto.Definition;
import de.samply.mdr.dal.dto.ScopedIdentifier;
import sun.reflect.generics.scope.Scope;

import java.util.List;

public class DataElementDTO {

    private List<Definition> definitions;
    private IdentificationDTO identification;
    private ValueDomainDTO valueDomain;
    private List<SlotDTO> slots;
    private int id;
    private int valueDomainId;
    private ScopedIdentifier scopedIdentifier;

    public DataElementDTO() {
    }

    public List<Definition> getDefinitions() {
        return definitions;
    }

    public void setDefinitions(List<Definition> definitions) {
        this.definitions = definitions;
    }

    public IdentificationDTO getIdentification() {
        return identification;
    }

    public void setIdentification(IdentificationDTO identification) {
        this.identification = identification;
    }

    public ValueDomainDTO getValueDomain() {
        return valueDomain;
    }

    public void setValueDomain(ValueDomainDTO valueDomain) {
        this.valueDomain = valueDomain;
    }

    public List<SlotDTO> getSlots() {
        return slots;
    }

    public void setSlots(List<SlotDTO> slots) {
        this.slots = slots;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValueDomainId() {
        return valueDomainId;
    }

    public void setValueDomainId(int valueDomainId) {
        this.valueDomainId = valueDomainId;
    }

    public ScopedIdentifier getScopedIdentifier() {
        return scopedIdentifier;
    }

    public void setScopedIdentifier(ScopedIdentifier scopedIdentifier) {
        this.scopedIdentifier = scopedIdentifier;
    }
}
