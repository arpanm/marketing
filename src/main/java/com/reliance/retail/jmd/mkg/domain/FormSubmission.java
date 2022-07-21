package com.reliance.retail.jmd.mkg.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A FormSubmission.
 */
@Entity
@Table(name = "form_submission")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FormSubmission implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "sessionidentifier")
    private String sessionidentifier;

    @OneToMany(mappedBy = "field")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "field", "field" }, allowSetters = true)
    private Set<FieldSubmission> fieldSubmissions = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = { "fieldMetaData", "formSubmissions", "promotion" }, allowSetters = true)
    private FormMetaData form;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public FormSubmission id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSessionidentifier() {
        return this.sessionidentifier;
    }

    public FormSubmission sessionidentifier(String sessionidentifier) {
        this.setSessionidentifier(sessionidentifier);
        return this;
    }

    public void setSessionidentifier(String sessionidentifier) {
        this.sessionidentifier = sessionidentifier;
    }

    public Set<FieldSubmission> getFieldSubmissions() {
        return this.fieldSubmissions;
    }

    public void setFieldSubmissions(Set<FieldSubmission> fieldSubmissions) {
        if (this.fieldSubmissions != null) {
            this.fieldSubmissions.forEach(i -> i.setField(null));
        }
        if (fieldSubmissions != null) {
            fieldSubmissions.forEach(i -> i.setField(this));
        }
        this.fieldSubmissions = fieldSubmissions;
    }

    public FormSubmission fieldSubmissions(Set<FieldSubmission> fieldSubmissions) {
        this.setFieldSubmissions(fieldSubmissions);
        return this;
    }

    public FormSubmission addFieldSubmission(FieldSubmission fieldSubmission) {
        this.fieldSubmissions.add(fieldSubmission);
        fieldSubmission.setField(this);
        return this;
    }

    public FormSubmission removeFieldSubmission(FieldSubmission fieldSubmission) {
        this.fieldSubmissions.remove(fieldSubmission);
        fieldSubmission.setField(null);
        return this;
    }

    public FormMetaData getForm() {
        return this.form;
    }

    public void setForm(FormMetaData formMetaData) {
        this.form = formMetaData;
    }

    public FormSubmission form(FormMetaData formMetaData) {
        this.setForm(formMetaData);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FormSubmission)) {
            return false;
        }
        return id != null && id.equals(((FormSubmission) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FormSubmission{" +
            "id=" + getId() +
            ", sessionidentifier='" + getSessionidentifier() + "'" +
            "}";
    }
}
