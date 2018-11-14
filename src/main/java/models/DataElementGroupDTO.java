package models;

import de.samply.mdr.dal.dto.Definition;

import java.util.List;

public class DataElementGroupDTO {

    private List<Definition> definitions;
    private List<DataElementDTO> dataElements;

    public DataElementGroupDTO() {
    }

    public List<Definition> getDefinitions() {
        return definitions;
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
}
