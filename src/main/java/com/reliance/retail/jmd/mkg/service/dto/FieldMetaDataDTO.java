package com.reliance.retail.jmd.mkg.service.dto;

import com.reliance.retail.jmd.mkg.domain.enumeration.DataType;
import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.reliance.retail.jmd.mkg.domain.FieldMetaData} entity.
 */
public class FieldMetaDataDTO implements Serializable {

    private Long id;

    @NotNull
    private String key;

    @NotNull
    private DataType type;

    @NotNull
    private Boolean isMandatory;

    private FormMetaDataDTO form;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public DataType getType() {
        return type;
    }

    public void setType(DataType type) {
        this.type = type;
    }

    public Boolean getIsMandatory() {
        return isMandatory;
    }

    public void setIsMandatory(Boolean isMandatory) {
        this.isMandatory = isMandatory;
    }

    public FormMetaDataDTO getForm() {
        return form;
    }

    public void setForm(FormMetaDataDTO form) {
        this.form = form;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FieldMetaDataDTO)) {
            return false;
        }

        FieldMetaDataDTO fieldMetaDataDTO = (FieldMetaDataDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, fieldMetaDataDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FieldMetaDataDTO{" +
            "id=" + getId() +
            ", key='" + getKey() + "'" +
            ", type='" + getType() + "'" +
            ", isMandatory='" + getIsMandatory() + "'" +
            ", form=" + getForm() +
            "}";
    }
}
