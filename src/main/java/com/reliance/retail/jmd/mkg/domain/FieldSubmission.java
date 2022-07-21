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

    @Column(name = "value")
    private String value;

    @ManyToOne
    @ManyToOne
    @JsonIgnoreProperties(value = { "options", "fieldSubmissions", "form" }, allowSetters = true)
    private FieldMetaData field;

    @ManyToOne
    @ManyToOne
    @JsonIgnoreProperties(value = { "fieldSubmissions", "form" }, allowSetters = true)
    private FormSubmission field;

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

    public String getValue() {
        return this.value;
    }

    public FieldSubmission value(String value) {
        this.setValue(value);
        return this;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public FieldMetaData getField() {
        return this.field;
    }

    public void setField(FieldMetaData fieldMetaData) {
        this.field = fieldMetaData;
    }

    public FieldSubmission field(FieldMetaData fieldMetaData) {
        this.setField(fieldMetaData);
        return this;
    }

    public FormSubmission getField() {
        return this.field;
    }

    public void setField(FormSubmission formSubmission) {
        this.field = formSubmission;
    }

    public FieldSubmission field(FormSubmission formSubmission) {
        this.setField(formSubmission);
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
            ", value='" + getValue() + "'" +
            "}";
    }
}
