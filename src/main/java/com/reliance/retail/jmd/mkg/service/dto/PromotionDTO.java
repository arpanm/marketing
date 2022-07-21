package com.reliance.retail.jmd.mkg.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.reliance.retail.jmd.mkg.domain.Promotion} entity.
 */
public class PromotionDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String urlName;

    @NotNull
    private String desktopImageUrl;

    @NotNull
    private String tabletImageUrl;

    @NotNull
    private String mobileImageUrl;

    @NotNull
    private String title;

    @NotNull
    private String description;

    private String tnc;

    private String tncLink;

    @NotNull
    private String landingUrl;

    @NotNull
    private Integer position;

    @NotNull
    private Boolean isEnabled;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    @NotNull
    private String createdBy;

    @NotNull
    private LocalDate createdDate;

    @NotNull
    private String updatedBy;

    @NotNull
    private LocalDate updatedDate;

    private FormMetaDataDTO formId;

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

    public String getUrlName() {
        return urlName;
    }

    public void setUrlName(String urlName) {
        this.urlName = urlName;
    }

    public String getDesktopImageUrl() {
        return desktopImageUrl;
    }

    public void setDesktopImageUrl(String desktopImageUrl) {
        this.desktopImageUrl = desktopImageUrl;
    }

    public String getTabletImageUrl() {
        return tabletImageUrl;
    }

    public void setTabletImageUrl(String tabletImageUrl) {
        this.tabletImageUrl = tabletImageUrl;
    }

    public String getMobileImageUrl() {
        return mobileImageUrl;
    }

    public void setMobileImageUrl(String mobileImageUrl) {
        this.mobileImageUrl = mobileImageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTnc() {
        return tnc;
    }

    public void setTnc(String tnc) {
        this.tnc = tnc;
    }

    public String getTncLink() {
        return tncLink;
    }

    public void setTncLink(String tncLink) {
        this.tncLink = tncLink;
    }

    public String getLandingUrl() {
        return landingUrl;
    }

    public void setLandingUrl(String landingUrl) {
        this.landingUrl = landingUrl;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Boolean getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public FormMetaDataDTO getFormId() {
        return formId;
    }

    public void setFormId(FormMetaDataDTO formId) {
        this.formId = formId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PromotionDTO)) {
            return false;
        }

        PromotionDTO promotionDTO = (PromotionDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, promotionDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PromotionDTO{" +
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
            ", formId=" + getFormId() +
            "}";
    }
}
