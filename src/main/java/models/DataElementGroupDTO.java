package models;

import de.samply.mdr.dal.dto.Definition;

import java.util.List;

public class DataElementGroupDTO {

	private IdentificationDTO identification;
	private List<Definition> definitions;
	private List<DataElementGroupDTO> dataElementGroups;
	private List<DataElementDTO> dataElements;

	public DataElementGroupDTO() {
	}

	public IdentificationDTO getIdentification() {
		return identification;
	}

	public void setIdentification(IdentificationDTO identification) {
		this.identification = identification;
	}

	public List<Definition> getDefinitions() {
		return definitions;
	}

	public void setDefinitions(List<Definition> definitions) {
		this.definitions = definitions;
	}

	public List<DataElementGroupDTO> getDataElementGroups() {
		return dataElementGroups;
	}

	public void setDataElementGroups(List<DataElementGroupDTO> dataElementGroups) {
		this.dataElementGroups = dataElementGroups;
	}

	public List<DataElementDTO> getDataElements() {
		return dataElements;
	}

	public void setDataElements(List<DataElementDTO> dataElements) {
		this.dataElements = dataElements;
	}
}
