package com.reliance.retail.jmd.mkg.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.reliance.retail.jmd.mkg.domain.FormSubmission} entity.
 */
public class FormSubmissionDTO implements Serializable {

    private Long id;

    private String sessionidentifier;

    private FormMetaDataDTO form;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSessionidentifier() {
        return sessionidentifier;
    }

    public void setSessionidentifier(String sessionidentifier) {
        this.sessionidentifier = sessionidentifier;
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
        if (!(o instanceof FormSubmissionDTO)) {
            return false;
        }

        FormSubmissionDTO formSubmissionDTO = (FormSubmissionDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, formSubmissionDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FormSubmissionDTO{" +
            "id=" + getId() +
            ", sessionidentifier='" + getSessionidentifier() + "'" +
            ", form=" + getForm() +
            "}";
    }
}
