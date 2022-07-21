package com.reliance.retail.jmd.mkg.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.reliance.retail.jmd.mkg.domain.FormMetaData} entity.
 */
public class FormMetaDataDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String title;

    @NotNull
    private Boolean isActive;

    @NotNull
    private Boolean multiSubmissionAllowed;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getMultiSubmissionAllowed() {
        return multiSubmissionAllowed;
    }

    public void setMultiSubmissionAllowed(Boolean multiSubmissionAllowed) {
        this.multiSubmissionAllowed = multiSubmissionAllowed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FormMetaDataDTO)) {
            return false;
        }

        FormMetaDataDTO formMetaDataDTO = (FormMetaDataDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, formMetaDataDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FormMetaDataDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", title='" + getTitle() + "'" +
            ", isActive='" + getIsActive() + "'" +
            ", multiSubmissionAllowed='" + getMultiSubmissionAllowed() + "'" +
            "}";
    }
}
