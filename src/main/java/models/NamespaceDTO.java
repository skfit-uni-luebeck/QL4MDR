package models;

import java.util.List;

import de.samply.mdr.dal.dto.Definition;

public class NamespaceDTO {

    private String name;
    private boolean writable;
    private List<Definition> definitions;
    private List<DataElementDTO> dataElements;
    private List<DataElementGroupDTO> dataElementGroups;


    public NamespaceDTO() {
    }

    public String getName() {
        return name;
    }

    public boolean isWritable() {
        return writable;
    }

    public List<Definition> getDefinitions() {
        return definitions;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWritable(boolean writable) {
        this.writable = writable;
    }

    public void setDefinitions(List<Definition> definitions) {
        this.definitions = definitions;
    }

    public List<DataElementDTO> getDataElements() {
        return dataElements;
    }

    public void setDataElements(List<DataElementDTO> dataElements) {
        this.dataElements = dataElements;
    }

    public List<DataElementGroupDTO> getDataElementGroups() {
        return dataElementGroups;
    }

    public void setDataElementGroups(List<DataElementGroupDTO> dataElementGroups) {
        this.dataElementGroups = dataElementGroups;
    }
}

