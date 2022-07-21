package com.reliance.retail.jmd.mkg.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Promotion.
 */
@Entity
@Table(name = "promotion")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Promotion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "url_name", nullable = false)
    private String urlName;

    @NotNull
    @Column(name = "desktop_image_url", nullable = false)
    private String desktopImageUrl;

    @NotNull
    @Column(name = "tablet_image_url", nullable = false)
    private String tabletImageUrl;

    @NotNull
    @Column(name = "mobile_image_url", nullable = false)
    private String mobileImageUrl;

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "tnc")
    private String tnc;

    @Column(name = "tnc_link")
    private String tncLink;

    @NotNull
    @Column(name = "landing_url", nullable = false)
    private String landingUrl;

    @NotNull
    @Column(name = "position", nullable = false)
    private Integer position;

    @NotNull
    @Column(name = "is_enabled", nullable = false)
    private Boolean isEnabled;

    @NotNull
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @NotNull
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @NotNull
    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @NotNull
    @Column(name = "created_date", nullable = false)
    private LocalDate createdDate;

    @NotNull
    @Column(name = "updated_by", nullable = false)
    private String updatedBy;

    @NotNull
    @Column(name = "updated_date", nullable = false)
    private LocalDate updatedDate;

    @JsonIgnoreProperties(value = { "fieldMetaData", "formSubmissions", "promotion" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private FormMetaData form;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Promotion id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Promotion name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlName() {
        return this.urlName;
    }

    public Promotion urlName(String urlName) {
        this.setUrlName(urlName);
        return this;
    }

    public void setUrlName(String urlName) {
        this.urlName = urlName;
    }

    public String getDesktopImageUrl() {
        return this.desktopImageUrl;
    }

    public Promotion desktopImageUrl(String desktopImageUrl) {
        this.setDesktopImageUrl(desktopImageUrl);
        return this;
    }

    public void setDesktopImageUrl(String desktopImageUrl) {
        this.desktopImageUrl = desktopImageUrl;
    }

    public String getTabletImageUrl() {
        return this.tabletImageUrl;
    }

    public Promotion tabletImageUrl(String tabletImageUrl) {
        this.setTabletImageUrl(tabletImageUrl);
        return this;
    }

    public void setTabletImageUrl(String tabletImageUrl) {
        this.tabletImageUrl = tabletImageUrl;
    }

    public String getMobileImageUrl() {
        return this.mobileImageUrl;
    }

    public Promotion mobileImageUrl(String mobileImageUrl) {
        this.setMobileImageUrl(mobileImageUrl);
        return this;
    }

    public void setMobileImageUrl(String mobileImageUrl) {
        this.mobileImageUrl = mobileImageUrl;
    }

    public String getTitle() {
        return this.title;
    }

    public Promotion title(String title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public Promotion description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTnc() {
        return this.tnc;
    }

    public Promotion tnc(String tnc) {
        this.setTnc(tnc);
        return this;
    }

    public void setTnc(String tnc) {
        this.tnc = tnc;
    }

    public String getTncLink() {
        return this.tncLink;
    }

    public Promotion tncLink(String tncLink) {
        this.setTncLink(tncLink);
        return this;
    }

    public void setTncLink(String tncLink) {
        this.tncLink = tncLink;
    }

    public String getLandingUrl() {
        return this.landingUrl;
    }

    public Promotion landingUrl(String landingUrl) {
        this.setLandingUrl(landingUrl);
        return this;
    }

    public void setLandingUrl(String landingUrl) {
        this.landingUrl = landingUrl;
    }

    public Integer getPosition() {
        return this.position;
    }

    public Promotion position(Integer position) {
        this.setPosition(position);
        return this;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Boolean getIsEnabled() {
        return this.isEnabled;
    }

    public Promotion isEnabled(Boolean isEnabled) {
        this.setIsEnabled(isEnabled);
        return this;
    }

    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public Promotion startDate(LocalDate startDate) {
        this.setStartDate(startDate);
        return this;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

    public Promotion endDate(LocalDate endDate) {
        this.setEndDate(endDate);
        return this;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public Promotion createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public Promotion createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public Promotion updatedBy(String updatedBy) {
        this.setUpdatedBy(updatedBy);
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public Promotion updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public FormMetaData getForm() {
        return this.form;
    }

    public void setForm(FormMetaData formMetaData) {
        this.form = formMetaData;
    }

    public Promotion form(FormMetaData formMetaData) {
        this.setForm(formMetaData);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Promotion)) {
            return false;
        }
        return id != null && id.equals(((Promotion) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Promotion{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", urlName='" + getUrlName() + "'" +
            ", desktopImageUrl='" + getDesktopImageUrl() + "'" +
            ", tabletImageUrl='" + getTabletImageUrl() + "'" +
            ", mobileImageUrl='" + getMobileImageUrl() + "'" +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", tnc='" + getTnc() + "'" +
            ", tncLink='" + getTncLink() + "'" +
            ", landingUrl='" + getLandingUrl() + "'" +
            ", position=" + getPosition() +
            ", isEnabled='" + getIsEnabled() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            "}";
    }
}
