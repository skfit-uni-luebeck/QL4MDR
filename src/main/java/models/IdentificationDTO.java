package models;

public class IdentificationDTO {

    private String urn;
    private String status;

    public IdentificationDTO() {
    }

    public IdentificationDTO(String urn, String status) {
        this.urn = urn;
        this.status = status;
    }

    public String getUrn() {
        return urn;
    }

    public void setUrn(String urn) {
        this.urn = urn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
