package com.reliance.retail.jmd.mkg.service;

import com.reliance.retail.jmd.mkg.service.dto.FieldSubmissionDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.reliance.retail.jmd.mkg.domain.FieldSubmission}.
 */
public interface FieldSubmissionService {
    /**
     * Save a fieldSubmission.
     *
     * @param fieldSubmissionDTO the entity to save.
     * @return the persisted entity.
     */
    FieldSubmissionDTO save(FieldSubmissionDTO fieldSubmissionDTO);

    /**
     * Updates a fieldSubmission.
     *
     * @param fieldSubmissionDTO the entity to update.
     * @return the persisted entity.
     */
    FieldSubmissionDTO update(FieldSubmissionDTO fieldSubmissionDTO);

    /**
     * Partially updates a fieldSubmission.
     *
     * @param fieldSubmissionDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<FieldSubmissionDTO> partialUpdate(FieldSubmissionDTO fieldSubmissionDTO);

    /**
     * Get all the fieldSubmissions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<FieldSubmissionDTO> findAll(Pageable pageable);

    /**
     * Get the "id" fieldSubmission.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FieldSubmissionDTO> findOne(Long id);

    /**
     * Delete the "id" fieldSubmission.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
