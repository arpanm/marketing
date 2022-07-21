package com.reliance.retail.jmd.mkg.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A FieldSubmission.
 */
@Entity
@Table(name = "field_submission")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FieldSubmission implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "value_str")
    private String valueStr;

    @ManyToOne
    @JsonIgnoreProperties(value = { "options", "fieldSubmissions", "form" }, allowSetters = true)
    private FieldMetaData fieldKey;

    @ManyToOne
    @JsonIgnoreProperties(value = { "fieldSubmissions", "formKey" }, allowSetters = true)
    private FormSubmission formSub;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public FieldSubmission id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValueStr() {
        return this.valueStr;
    }

    public FieldSubmission valueStr(String valueStr) {
        this.setValueStr(valueStr);
        return this;
    }

    public void setValueStr(String valueStr) {
        this.valueStr = valueStr;
    }

    public FieldMetaData getFieldKey() {
        return this.fieldKey;
    }

    public void setFieldKey(FieldMetaData fieldMetaData) {
        this.fieldKey = fieldMetaData;
    }

    public FieldSubmission fieldKey(FieldMetaData fieldMetaData) {
        this.setFieldKey(fieldMetaData);
        return this;
    }

    public FormSubmission getFormSub() {
        return this.formSub;
    }

    public void setFormSub(FormSubmission formSubmission) {
        this.formSub = formSubmission;
    }

    public FieldSubmission formSub(FormSubmission formSubmission) {
        this.setFormSub(formSubmission);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FieldSubmission)) {
            return false;
        }
        return id != null && id.equals(((FieldSubmission) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FieldSubmission{" +
            "id=" + getId() +
            ", valueStr='" + getValueStr() + "'" +
            "}";
    }
}
