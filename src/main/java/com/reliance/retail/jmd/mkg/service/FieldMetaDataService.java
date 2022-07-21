package com.reliance.retail.jmd.mkg.service;

import com.reliance.retail.jmd.mkg.service.dto.FieldMetaDataDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.reliance.retail.jmd.mkg.domain.FieldMetaData}.
 */
public interface FieldMetaDataService {
    /**
     * Save a fieldMetaData.
     *
     * @param fieldMetaDataDTO the entity to save.
     * @return the persisted entity.
     */
    FieldMetaDataDTO save(FieldMetaDataDTO fieldMetaDataDTO);

    /**
     * Updates a fieldMetaData.
     *
     * @param fieldMetaDataDTO the entity to update.
     * @return the persisted entity.
     */
    FieldMetaDataDTO update(FieldMetaDataDTO fieldMetaDataDTO);

    /**
     * Partially updates a fieldMetaData.
     *
     * @param fieldMetaDataDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<FieldMetaDataDTO> partialUpdate(FieldMetaDataDTO fieldMetaDataDTO);

    /**
     * Get all the fieldMetaData.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<FieldMetaDataDTO> findAll(Pageable pageable);

    /**
     * Get the "id" fieldMetaData.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FieldMetaDataDTO> findOne(Long id);

    /**
     * Delete the "id" fieldMetaData.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
