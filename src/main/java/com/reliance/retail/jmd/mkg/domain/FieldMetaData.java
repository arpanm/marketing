package com.reliance.retail.jmd.mkg.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.reliance.retail.jmd.mkg.domain.enumeration.DataType;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A FieldMetaData.
 */
@Entity
@Table(name = "field_meta_data")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FieldMetaData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "jhi_key", nullable = false)
    private String key;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private DataType type;

    @NotNull
    @Column(name = "is_mandatory", nullable = false)
    private Boolean isMandatory;

    @OneToMany(mappedBy = "field")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "field" }, allowSetters = true)
    private Set<Option> options = new HashSet<>();

    @OneToMany(mappedBy = "fieldKey")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "fieldKey", "formSub" }, allowSetters = true)
    private Set<FieldSubmission> fieldSubmissions = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = { "fieldMetaData", "formSubmissions", "promotion" }, allowSetters = true)
    private FormMetaData form;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public FieldMetaData id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return this.key;
    }

    public FieldMetaData key(String key) {
        this.setKey(key);
        return this;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public DataType getType() {
        return this.type;
    }

    public FieldMetaData type(DataType type) {
        this.setType(type);
        return this;
    }

    public void setType(DataType type) {
        this.type = type;
    }

    public Boolean getIsMandatory() {
        return this.isMandatory;
    }

    public FieldMetaData isMandatory(Boolean isMandatory) {
        this.setIsMandatory(isMandatory);
        return this;
    }

    public void setIsMandatory(Boolean isMandatory) {
        this.isMandatory = isMandatory;
    }

    public Set<Option> getOptions() {
        return this.options;
    }

    public void setOptions(Set<Option> options) {
        if (this.options != null) {
            this.options.forEach(i -> i.setField(null));
        }
        if (options != null) {
            options.forEach(i -> i.setField(this));
        }
        this.options = options;
    }

    public FieldMetaData options(Set<Option> options) {
        this.setOptions(options);
        return this;
    }

    public FieldMetaData addOption(Option option) {
        this.options.add(option);
        option.setField(this);
        return this;
    }

    public FieldMetaData removeOption(Option option) {
        this.options.remove(option);
        option.setField(null);
        return this;
    }

    public Set<FieldSubmission> getFieldSubmissions() {
        return this.fieldSubmissions;
    }

    public void setFieldSubmissions(Set<FieldSubmission> fieldSubmissions) {
        if (this.fieldSubmissions != null) {
            this.fieldSubmissions.forEach(i -> i.setFieldKey(null));
        }
        if (fieldSubmissions != null) {
            fieldSubmissions.forEach(i -> i.setFieldKey(this));
        }
        this.fieldSubmissions = fieldSubmissions;
    }

    public FieldMetaData fieldSubmissions(Set<FieldSubmission> fieldSubmissions) {
        this.setFieldSubmissions(fieldSubmissions);
        return this;
    }

    public FieldMetaData addFieldSubmission(FieldSubmission fieldSubmission) {
        this.fieldSubmissions.add(fieldSubmission);
        fieldSubmission.setFieldKey(this);
        return this;
    }

    public FieldMetaData removeFieldSubmission(FieldSubmission fieldSubmission) {
        this.fieldSubmissions.remove(fieldSubmission);
        fieldSubmission.setFieldKey(null);
        return this;
    }

    public FormMetaData getForm() {
        return this.form;
    }

    public void setForm(FormMetaData formMetaData) {
        this.form = formMetaData;
    }

    public FieldMetaData form(FormMetaData formMetaData) {
        this.setForm(formMetaData);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FieldMetaData)) {
            return false;
        }
        return id != null && id.equals(((FieldMetaData) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FieldMetaData{" +
            "id=" + getId() +
            ", key='" + getKey() + "'" +
            ", type='" + getType() + "'" +
            ", isMandatory='" + getIsMandatory() + "'" +
            "}";
    }
}
