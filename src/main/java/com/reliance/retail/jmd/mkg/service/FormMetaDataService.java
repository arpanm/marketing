package com.reliance.retail.jmd.mkg.service;

import com.reliance.retail.jmd.mkg.service.dto.FormMetaDataDTO;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.reliance.retail.jmd.mkg.domain.FormMetaData}.
 */
public interface FormMetaDataService {
    /**
     * Save a formMetaData.
     *
     * @param formMetaDataDTO the entity to save.
     * @return the persisted entity.
     */
    FormMetaDataDTO save(FormMetaDataDTO formMetaDataDTO);

    /**
     * Updates a formMetaData.
     *
     * @param formMetaDataDTO the entity to update.
     * @return the persisted entity.
     */
    FormMetaDataDTO update(FormMetaDataDTO formMetaDataDTO);

    /**
     * Partially updates a formMetaData.
     *
     * @param formMetaDataDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<FormMetaDataDTO> partialUpdate(FormMetaDataDTO formMetaDataDTO);

    /**
     * Get all the formMetaData.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<FormMetaDataDTO> findAll(Pageable pageable);
    /**
     * Get all the FormMetaDataDTO where Promotion is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<FormMetaDataDTO> findAllWherePromotionIsNull();

    /**
     * Get the "id" formMetaData.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FormMetaDataDTO> findOne(Long id);

    /**
     * Delete the "id" formMetaData.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
