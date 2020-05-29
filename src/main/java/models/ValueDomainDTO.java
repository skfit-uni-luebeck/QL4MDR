package models;

import java.util.List;

public class ValueDomainDTO {

    private String datatype;
    private String format;
    private String unitOfMeasure;
    private int maximumCharacters;
    private String validationType;
    private String validationData;
    private List<PermissiableValueDTO> permissiableValues;

    // TODO Enumeration with the permissible Values are missing

    public ValueDomainDTO() {
    }

    public String getDatatype() {
        return datatype;
    }

    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public int getMaximumCharacters() {
        return maximumCharacters;
    }

    public void setMaximumCharacters(int maximumCharacters) {
        this.maximumCharacters = maximumCharacters;
    }

    public String getValidationType() {
        return validationType;
    }

    public void setValidationType(String validationType) {
        this.validationType = validationType;
    }

    public String getValidationData() {
        return validationData;
    }

    public void setValidationData(String validationData) {
        this.validationData = validationData;
    }

	public List<PermissiableValueDTO> getPermissiableValues() {
		return permissiableValues;
	}

	public void setPermissiableValues(List<PermissiableValueDTO> permissiableValues) {
		this.permissiableValues = permissiableValues;
	}
}

