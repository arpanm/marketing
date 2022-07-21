package com.reliance.retail.jmd.mkg.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.reliance.retail.jmd.mkg.domain.FieldSubmission} entity.
 */
public class FieldSubmissionDTO implements Serializable {

    private Long id;

    private String valueStr;

    private FieldMetaDataDTO fieldKey;

    private FormSubmissionDTO formSub;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValueStr() {
        return valueStr;
    }

    public void setValueStr(String valueStr) {
        this.valueStr = valueStr;
    }

    public FieldMetaDataDTO getFieldKey() {
        return fieldKey;
    }

    public void setFieldKey(FieldMetaDataDTO fieldKey) {
        this.fieldKey = fieldKey;
    }

    public FormSubmissionDTO getFormSub() {
        return formSub;
    }

    public void setFormSub(FormSubmissionDTO formSub) {
        this.formSub = formSub;
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
            ", valueStr='" + getValueStr() + "'" +
            ", fieldKey=" + getFieldKey() +
            ", formSub=" + getFormSub() +
            "}";
    }
}
