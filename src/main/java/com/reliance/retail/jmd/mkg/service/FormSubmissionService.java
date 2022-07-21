package com.reliance.retail.jmd.mkg.service;

import com.reliance.retail.jmd.mkg.service.dto.FormSubmissionDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.reliance.retail.jmd.mkg.domain.FormSubmission}.
 */
public interface FormSubmissionService {
    /**
     * Save a formSubmission.
     *
     * @param formSubmissionDTO the entity to save.
     * @return the persisted entity.
     */
    FormSubmissionDTO save(FormSubmissionDTO formSubmissionDTO);

    /**
     * Updates a formSubmission.
     *
     * @param formSubmissionDTO the entity to update.
     * @return the persisted entity.
     */
    FormSubmissionDTO update(FormSubmissionDTO formSubmissionDTO);

    /**
     * Partially updates a formSubmission.
     *
     * @param formSubmissionDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<FormSubmissionDTO> partialUpdate(FormSubmissionDTO formSubmissionDTO);

    /**
     * Get all the formSubmissions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<FormSubmissionDTO> findAll(Pageable pageable);

    /**
     * Get the "id" formSubmission.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FormSubmissionDTO> findOne(Long id);

    /**
     * Delete the "id" formSubmission.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
