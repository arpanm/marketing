package com.reliance.retail.jmd.mkg.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A FormMetaData.
 */
@Entity
@Table(name = "form_meta_data")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FormMetaData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @NotNull
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @NotNull
    @Column(name = "multi_submission_allowed", nullable = false)
    private Boolean multiSubmissionAllowed;

    @OneToMany(mappedBy = "form")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "options", "fieldSubmissions", "form" }, allowSetters = true)
    private Set<FieldMetaData> fieldMetaData = new HashSet<>();

    @OneToMany(mappedBy = "formKey")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "fieldSubmissions", "formKey" }, allowSetters = true)
    private Set<FormSubmission> formSubmissions = new HashSet<>();

    @JsonIgnoreProperties(value = { "formId" }, allowSetters = true)
    @OneToOne(mappedBy = "formId")
    private Promotion promotion;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public FormMetaData id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public FormMetaData name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return this.title;
    }

    public FormMetaData title(String title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getIsActive() {
        return this.isActive;
    }

    public FormMetaData isActive(Boolean isActive) {
        this.setIsActive(isActive);
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getMultiSubmissionAllowed() {
        return this.multiSubmissionAllowed;
    }

    public FormMetaData multiSubmissionAllowed(Boolean multiSubmissionAllowed) {
        this.setMultiSubmissionAllowed(multiSubmissionAllowed);
        return this;
    }

    public void setMultiSubmissionAllowed(Boolean multiSubmissionAllowed) {
        this.multiSubmissionAllowed = multiSubmissionAllowed;
    }

    public Set<FieldMetaData> getFieldMetaData() {
        return this.fieldMetaData;
    }

    public void setFieldMetaData(Set<FieldMetaData> fieldMetaData) {
        if (this.fieldMetaData != null) {
            this.fieldMetaData.forEach(i -> i.setForm(null));
        }
        if (fieldMetaData != null) {
            fieldMetaData.forEach(i -> i.setForm(this));
        }
        this.fieldMetaData = fieldMetaData;
    }

    public FormMetaData fieldMetaData(Set<FieldMetaData> fieldMetaData) {
        this.setFieldMetaData(fieldMetaData);
        return this;
    }

    public FormMetaData addFieldMetaData(FieldMetaData fieldMetaData) {
        this.fieldMetaData.add(fieldMetaData);
        fieldMetaData.setForm(this);
        return this;
    }

    public FormMetaData removeFieldMetaData(FieldMetaData fieldMetaData) {
        this.fieldMetaData.remove(fieldMetaData);
        fieldMetaData.setForm(null);
        return this;
    }

    public Set<FormSubmission> getFormSubmissions() {
        return this.formSubmissions;
    }

    public void setFormSubmissions(Set<FormSubmission> formSubmissions) {
        if (this.formSubmissions != null) {
            this.formSubmissions.forEach(i -> i.setFormKey(null));
        }
        if (formSubmissions != null) {
            formSubmissions.forEach(i -> i.setFormKey(this));
        }
        this.formSubmissions = formSubmissions;
    }

    public FormMetaData formSubmissions(Set<FormSubmission> formSubmissions) {
        this.setFormSubmissions(formSubmissions);
        return this;
    }

    public FormMetaData addFormSubmission(FormSubmission formSubmission) {
        this.formSubmissions.add(formSubmission);
        formSubmission.setFormKey(this);
        return this;
    }

    public FormMetaData removeFormSubmission(FormSubmission formSubmission) {
        this.formSubmissions.remove(formSubmission);
        formSubmission.setFormKey(null);
        return this;
    }

    public Promotion getPromotion() {
        return this.promotion;
    }

    public void setPromotion(Promotion promotion) {
        if (this.promotion != null) {
            this.promotion.setFormId(null);
        }
        if (promotion != null) {
            promotion.setFormId(this);
        }
        this.promotion = promotion;
    }

    public FormMetaData promotion(Promotion promotion) {
        this.setPromotion(promotion);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FormMetaData)) {
            return false;
        }
        return id != null && id.equals(((FormMetaData) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FormMetaData{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", title='" + getTitle() + "'" +
            ", isActive='" + getIsActive() + "'" +
            ", multiSubmissionAllowed='" + getMultiSubmissionAllowed() + "'" +
            "}";
    }
}
