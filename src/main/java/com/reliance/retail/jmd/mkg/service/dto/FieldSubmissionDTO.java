package com.reliance.retail.jmd.mkg.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.reliance.retail.jmd.mkg.domain.FieldSubmission} entity.
 */
public class FieldSubmissionDTO implements Serializable {

    private Long id;

    private String value;

    private FieldMetaDataDTO field;

    private FormSubmissionDTO field;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public FieldMetaDataDTO getField() {
        return field;
    }

    public void setField(FieldMetaDataDTO field) {
        this.field = field;
    }

    public FormSubmissionDTO getField() {
        return field;
    }

    public void setField(FormSubmissionDTO field) {
        this.field = field;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FieldSubmissionDTO)) {
            return false;
        }

        FieldSubmissionDTO fieldSubmissionDTO = (FieldSubmissionDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, fieldSubmissionDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FieldSubmissionDTO{" +
            "id=" + getId() +
            ", value='" + getValue() + "'" +
            ", field=" + getField() +
            ", field=" + getField() +
            "}";
    }
}
