package com.reliance.retail.jmd.mkg.web.rest;

import com.reliance.retail.jmd.mkg.repository.FormSubmissionRepository;
import com.reliance.retail.jmd.mkg.service.FormSubmissionService;
import com.reliance.retail.jmd.mkg.service.dto.FormSubmissionDTO;
import com.reliance.retail.jmd.mkg.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.reliance.retail.jmd.mkg.domain.FormSubmission}.
 */
@RestController
@RequestMapping("/api")
public class FormSubmissionResource {

    private final Logger log = LoggerFactory.getLogger(FormSubmissionResource.class);

    private static final String ENTITY_NAME = "formSubmission";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FormSubmissionService formSubmissionService;

    private final FormSubmissionRepository formSubmissionRepository;

    public FormSubmissionResource(FormSubmissionService formSubmissionService, FormSubmissionRepository formSubmissionRepository) {
        this.formSubmissionService = formSubmissionService;
        this.formSubmissionRepository = formSubmissionRepository;
    }

    /**
     * {@code POST  /form-submissions} : Create a new formSubmission.
     *
     * @param formSubmissionDTO the formSubmissionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new formSubmissionDTO, or with status {@code 400 (Bad Request)} if the formSubmission has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/form-submissions")
    public ResponseEntity<FormSubmissionDTO> createFormSubmission(@RequestBody FormSubmissionDTO formSubmissionDTO)
        throws URISyntaxException {
        log.debug("REST request to save FormSubmission : {}", formSubmissionDTO);
        if (formSubmissionDTO.getId() != null) {
            throw new BadRequestAlertException("A new formSubmission cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FormSubmissionDTO result = formSubmissionService.save(formSubmissionDTO);
        return ResponseEntity
            .created(new URI("/api/form-submissions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /form-submissions/:id} : Updates an existing formSubmission.
     *
     * @param id the id of the formSubmissionDTO to save.
     * @param formSubmissionDTO the formSubmissionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated formSubmissionDTO,
     * or with status {@code 400 (Bad Request)} if the formSubmissionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the formSubmissionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/form-submissions/{id}")
    public ResponseEntity<FormSubmissionDTO> updateFormSubmission(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody FormSubmissionDTO formSubmissionDTO
    ) throws URISyntaxException {
        log.debug("REST request to update FormSubmission : {}, {}", id, formSubmissionDTO);
        if (formSubmissionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, formSubmissionDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!formSubmissionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        FormSubmissionDTO result = formSubmissionService.update(formSubmissionDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, formSubmissionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /form-submissions/:id} : Partial updates given fields of an existing formSubmission, field will ignore if it is null
     *
     * @param id the id of the formSubmissionDTO to save.
     * @param formSubmissionDTO the formSubmissionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated formSubmissionDTO,
     * or with status {@code 400 (Bad Request)} if the formSubmissionDTO is not valid,
     * or with status {@code 404 (Not Found)} if the formSubmissionDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the formSubmissionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/form-submissions/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<FormSubmissionDTO> partialUpdateFormSubmission(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody FormSubmissionDTO formSubmissionDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update FormSubmission partially : {}, {}", id, formSubmissionDTO);
        if (formSubmissionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, formSubmissionDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!formSubmissionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<FormSubmissionDTO> result = formSubmissionService.partialUpdate(formSubmissionDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, formSubmissionDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /form-submissions} : get all the formSubmissions.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of formSubmissions in body.
     */
    @GetMapping("/form-submissions")
    public ResponseEntity<List<FormSubmissionDTO>> getAllFormSubmissions(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of FormSubmissions");
        Page<FormSubmissionDTO> page = formSubmissionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /form-submissions/:id} : get the "id" formSubmission.
     *
     * @param id the id of the formSubmissionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the formSubmissionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/form-submissions/{id}")
    public ResponseEntity<FormSubmissionDTO> getFormSubmission(@PathVariable Long id) {
        log.debug("REST request to get FormSubmission : {}", id);
        Optional<FormSubmissionDTO> formSubmissionDTO = formSubmissionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(formSubmissionDTO);
    }

    /**
     * {@code DELETE  /form-submissions/:id} : delete the "id" formSubmission.
     *
     * @param id the id of the formSubmissionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/form-submissions/{id}")
    public ResponseEntity<Void> deleteFormSubmission(@PathVariable Long id) {
        log.debug("REST request to delete FormSubmission : {}", id);
        formSubmissionService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
