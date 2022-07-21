package com.reliance.retail.jmd.mkg.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Option.
 */
@Entity
@Table(name = "jhi_option")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Option implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "value_str", nullable = false)
    private String valueStr;

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @NotNull
    @Column(name = "is_default", nullable = false)
    private Boolean isDefault;

    @ManyToOne
    @JsonIgnoreProperties(value = { "options", "fieldSubmissions", "form" }, allowSetters = true)
    private FieldMetaData field;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Option id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValueStr() {
        return this.valueStr;
    }

    public Option valueStr(String valueStr) {
        this.setValueStr(valueStr);
        return this;
    }

    public void setValueStr(String valueStr) {
        this.valueStr = valueStr;
    }

    public String getTitle() {
        return this.title;
    }

    public Option title(String title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getIsDefault() {
        return this.isDefault;
    }

    public Option isDefault(Boolean isDefault) {
        this.setIsDefault(isDefault);
        return this;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public FieldMetaData getField() {
        return this.field;
    }

    public void setField(FieldMetaData fieldMetaData) {
        this.field = fieldMetaData;
    }

    public Option field(FieldMetaData fieldMetaData) {
        this.setField(fieldMetaData);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Option)) {
            return false;
        }
        return id != null && id.equals(((Option) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Option{" +
            "id=" + getId() +
            ", valueStr='" + getValueStr() + "'" +
            ", title='" + getTitle() + "'" +
            ", isDefault='" + getIsDefault() + "'" +
            "}";
    }
}
