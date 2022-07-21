package com.reliance.retail.jmd.mkg.web.rest;

import com.reliance.retail.jmd.mkg.repository.FieldMetaDataRepository;
import com.reliance.retail.jmd.mkg.service.FieldMetaDataService;
import com.reliance.retail.jmd.mkg.service.dto.FieldMetaDataDTO;
import com.reliance.retail.jmd.mkg.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
 * REST controller for managing {@link com.reliance.retail.jmd.mkg.domain.FieldMetaData}.
 */
@RestController
@RequestMapping("/api")
public class FieldMetaDataResource {

    private final Logger log = LoggerFactory.getLogger(FieldMetaDataResource.class);

    private static final String ENTITY_NAME = "fieldMetaData";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FieldMetaDataService fieldMetaDataService;

    private final FieldMetaDataRepository fieldMetaDataRepository;

    public FieldMetaDataResource(FieldMetaDataService fieldMetaDataService, FieldMetaDataRepository fieldMetaDataRepository) {
        this.fieldMetaDataService = fieldMetaDataService;
        this.fieldMetaDataRepository = fieldMetaDataRepository;
    }

    /**
     * {@code POST  /field-meta-data} : Create a new fieldMetaData.
     *
     * @param fieldMetaDataDTO the fieldMetaDataDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fieldMetaDataDTO, or with status {@code 400 (Bad Request)} if the fieldMetaData has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/field-meta-data")
    public ResponseEntity<FieldMetaDataDTO> createFieldMetaData(@Valid @RequestBody FieldMetaDataDTO fieldMetaDataDTO)
        throws URISyntaxException {
        log.debug("REST request to save FieldMetaData : {}", fieldMetaDataDTO);
        if (fieldMetaDataDTO.getId() != null) {
            throw new BadRequestAlertException("A new fieldMetaData cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FieldMetaDataDTO result = fieldMetaDataService.save(fieldMetaDataDTO);
        return ResponseEntity
            .created(new URI("/api/field-meta-data/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /field-meta-data/:id} : Updates an existing fieldMetaData.
     *
     * @param id the id of the fieldMetaDataDTO to save.
     * @param fieldMetaDataDTO the fieldMetaDataDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fieldMetaDataDTO,
     * or with status {@code 400 (Bad Request)} if the fieldMetaDataDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fieldMetaDataDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/field-meta-data/{id}")
    public ResponseEntity<FieldMetaDataDTO> updateFieldMetaData(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody FieldMetaDataDTO fieldMetaDataDTO
    ) throws URISyntaxException {
        log.debug("REST request to update FieldMetaData : {}, {}", id, fieldMetaDataDTO);
        if (fieldMetaDataDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, fieldMetaDataDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!fieldMetaDataRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        FieldMetaDataDTO result = fieldMetaDataService.update(fieldMetaDataDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fieldMetaDataDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /field-meta-data/:id} : Partial updates given fields of an existing fieldMetaData, field will ignore if it is null
     *
     * @param id the id of the fieldMetaDataDTO to save.
     * @param fieldMetaDataDTO the fieldMetaDataDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fieldMetaDataDTO,
     * or with status {@code 400 (Bad Request)} if the fieldMetaDataDTO is not valid,
     * or with status {@code 404 (Not Found)} if the fieldMetaDataDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the fieldMetaDataDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/field-meta-data/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<FieldMetaDataDTO> partialUpdateFieldMetaData(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody FieldMetaDataDTO fieldMetaDataDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update FieldMetaData partially : {}, {}", id, fieldMetaDataDTO);
        if (fieldMetaDataDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, fieldMetaDataDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!fieldMetaDataRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<FieldMetaDataDTO> result = fieldMetaDataService.partialUpdate(fieldMetaDataDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fieldMetaDataDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /field-meta-data} : get all the fieldMetaData.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fieldMetaData in body.
     */
    @GetMapping("/field-meta-data")
    public ResponseEntity<List<FieldMetaDataDTO>> getAllFieldMetaData(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of FieldMetaData");
        Page<FieldMetaDataDTO> page = fieldMetaDataService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /field-meta-data/:id} : get the "id" fieldMetaData.
     *
     * @param id the id of the fieldMetaDataDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fieldMetaDataDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/field-meta-data/{id}")
    public ResponseEntity<FieldMetaDataDTO> getFieldMetaData(@PathVariable Long id) {
        log.debug("REST request to get FieldMetaData : {}", id);
        Optional<FieldMetaDataDTO> fieldMetaDataDTO = fieldMetaDataService.findOne(id);
        return ResponseUtil.wrapOrNotFound(fieldMetaDataDTO);
    }

    /**
     * {@code DELETE  /field-meta-data/:id} : delete the "id" fieldMetaData.
     *
     * @param id the id of the fieldMetaDataDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/field-meta-data/{id}")
    public ResponseEntity<Void> deleteFieldMetaData(@PathVariable Long id) {
        log.debug("REST request to delete FieldMetaData : {}", id);
        fieldMetaDataService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
