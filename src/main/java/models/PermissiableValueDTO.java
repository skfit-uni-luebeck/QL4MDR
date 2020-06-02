package models;

import java.util.List;

import de.samply.mdr.dal.dto.Definition;

public class PermissiableValueDTO {

	private String permissiableValue;
	private List<Definition> definitions;

	public PermissiableValueDTO(String permissableValue, List<Definition> definitions) {
		this.permissiableValue = permissableValue;
		this.definitions = definitions;
	}

	public String getPermissiableValue() {
		return permissiableValue;
	}

	public void setPermissiableValue(String permissableValue) {
		this.permissiableValue = permissableValue;
	}

	public List<Definition> getDefinitions() {
		return definitions;
	}

	public void setDefinitions(List<Definition> definitions) {
		this.definitions = definitions;
	}
}
