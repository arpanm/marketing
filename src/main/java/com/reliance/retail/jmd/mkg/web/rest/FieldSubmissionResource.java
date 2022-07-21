package com.reliance.retail.jmd.mkg.web.rest;

import com.reliance.retail.jmd.mkg.repository.FieldSubmissionRepository;
import com.reliance.retail.jmd.mkg.service.FieldSubmissionService;
import com.reliance.retail.jmd.mkg.service.dto.FieldSubmissionDTO;
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
 * REST controller for managing {@link com.reliance.retail.jmd.mkg.domain.FieldSubmission}.
 */
@RestController
@RequestMapping("/api")
public class FieldSubmissionResource {

    private final Logger log = LoggerFactory.getLogger(FieldSubmissionResource.class);

    private static final String ENTITY_NAME = "fieldSubmission";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FieldSubmissionService fieldSubmissionService;

    private final FieldSubmissionRepository fieldSubmissionRepository;

    public FieldSubmissionResource(FieldSubmissionService fieldSubmissionService, FieldSubmissionRepository fieldSubmissionRepository) {
        this.fieldSubmissionService = fieldSubmissionService;
        this.fieldSubmissionRepository = fieldSubmissionRepository;
    }

    /**
     * {@code POST  /field-submissions} : Create a new fieldSubmission.
     *
     * @param fieldSubmissionDTO the fieldSubmissionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fieldSubmissionDTO, or with status {@code 400 (Bad Request)} if the fieldSubmission has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/field-submissions")
    public ResponseEntity<FieldSubmissionDTO> createFieldSubmission(@RequestBody FieldSubmissionDTO fieldSubmissionDTO)
        throws URISyntaxException {
        log.debug("REST request to save FieldSubmission : {}", fieldSubmissionDTO);
        if (fieldSubmissionDTO.getId() != null) {
            throw new BadRequestAlertException("A new fieldSubmission cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FieldSubmissionDTO result = fieldSubmissionService.save(fieldSubmissionDTO);
        return ResponseEntity
            .created(new URI("/api/field-submissions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /field-submissions/:id} : Updates an existing fieldSubmission.
     *
     * @param id the id of the fieldSubmissionDTO to save.
     * @param fieldSubmissionDTO the fieldSubmissionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fieldSubmissionDTO,
     * or with status {@code 400 (Bad Request)} if the fieldSubmissionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fieldSubmissionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/field-submissions/{id}")
    public ResponseEntity<FieldSubmissionDTO> updateFieldSubmission(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody FieldSubmissionDTO fieldSubmissionDTO
    ) throws URISyntaxException {
        log.debug("REST request to update FieldSubmission : {}, {}", id, fieldSubmissionDTO);
        if (fieldSubmissionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, fieldSubmissionDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!fieldSubmissionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        FieldSubmissionDTO result = fieldSubmissionService.update(fieldSubmissionDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fieldSubmissionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /field-submissions/:id} : Partial updates given fields of an existing fieldSubmission, field will ignore if it is null
     *
     * @param id the id of the fieldSubmissionDTO to save.
     * @param fieldSubmissionDTO the fieldSubmissionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fieldSubmissionDTO,
     * or with status {@code 400 (Bad Request)} if the fieldSubmissionDTO is not valid,
     * or with status {@code 404 (Not Found)} if the fieldSubmissionDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the fieldSubmissionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/field-submissions/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<FieldSubmissionDTO> partialUpdateFieldSubmission(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody FieldSubmissionDTO fieldSubmissionDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update FieldSubmission partially : {}, {}", id, fieldSubmissionDTO);
        if (fieldSubmissionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, fieldSubmissionDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!fieldSubmissionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<FieldSubmissionDTO> result = fieldSubmissionService.partialUpdate(fieldSubmissionDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fieldSubmissionDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /field-submissions} : get all the fieldSubmissions.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fieldSubmissions in body.
     */
    @GetMapping("/field-submissions")
    public ResponseEntity<List<FieldSubmissionDTO>> getAllFieldSubmissions(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of FieldSubmissions");
        Page<FieldSubmissionDTO> page = fieldSubmissionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /field-submissions/:id} : get the "id" fieldSubmission.
     *
     * @param id the id of the fieldSubmissionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fieldSubmissionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/field-submissions/{id}")
    public ResponseEntity<FieldSubmissionDTO> getFieldSubmission(@PathVariable Long id) {
        log.debug("REST request to get FieldSubmission : {}", id);
        Optional<FieldSubmissionDTO> fieldSubmissionDTO = fieldSubmissionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(fieldSubmissionDTO);
    }

    /**
     * {@code DELETE  /field-submissions/:id} : delete the "id" fieldSubmission.
     *
     * @param id the id of the fieldSubmissionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/field-submissions/{id}")
    public ResponseEntity<Void> deleteFieldSubmission(@PathVariable Long id) {
        log.debug("REST request to delete FieldSubmission : {}", id);
        fieldSubmissionService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
