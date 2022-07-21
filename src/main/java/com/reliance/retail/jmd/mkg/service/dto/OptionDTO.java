package com.reliance.retail.jmd.mkg.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.reliance.retail.jmd.mkg.domain.Option} entity.
 */
public class OptionDTO implements Serializable {

    private Long id;

    @NotNull
    private String valueStr;

    @NotNull
    private String title;

    @NotNull
    private Boolean isDefault;

    private FieldMetaDataDTO field;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public FieldMetaDataDTO getField() {
        return field;
    }

    public void setField(FieldMetaDataDTO field) {
        this.field = field;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OptionDTO)) {
            return false;
        }

        OptionDTO optionDTO = (OptionDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, optionDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OptionDTO{" +
            "id=" + getId() +
            ", valueStr='" + getValueStr() + "'" +
            ", title='" + getTitle() + "'" +
            ", isDefault='" + getIsDefault() + "'" +
            ", field=" + getField() +
            "}";
    }
}
