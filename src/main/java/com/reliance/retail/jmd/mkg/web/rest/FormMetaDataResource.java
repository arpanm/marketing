package com.reliance.retail.jmd.mkg.web.rest;

import com.reliance.retail.jmd.mkg.repository.FormMetaDataRepository;
import com.reliance.retail.jmd.mkg.service.FormMetaDataService;
import com.reliance.retail.jmd.mkg.service.dto.FormMetaDataDTO;
import com.reliance.retail.jmd.mkg.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;
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
 * REST controller for managing {@link com.reliance.retail.jmd.mkg.domain.FormMetaData}.
 */
@RestController
@RequestMapping("/api")
public class FormMetaDataResource {

    private final Logger log = LoggerFactory.getLogger(FormMetaDataResource.class);

    private static final String ENTITY_NAME = "formMetaData";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FormMetaDataService formMetaDataService;

    private final FormMetaDataRepository formMetaDataRepository;

    public FormMetaDataResource(FormMetaDataService formMetaDataService, FormMetaDataRepository formMetaDataRepository) {
        this.formMetaDataService = formMetaDataService;
        this.formMetaDataRepository = formMetaDataRepository;
    }

    /**
     * {@code POST  /form-meta-data} : Create a new formMetaData.
     *
     * @param formMetaDataDTO the formMetaDataDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new formMetaDataDTO, or with status {@code 400 (Bad Request)} if the formMetaData has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/form-meta-data")
    public ResponseEntity<FormMetaDataDTO> createFormMetaData(@Valid @RequestBody FormMetaDataDTO formMetaDataDTO)
        throws URISyntaxException {
        log.debug("REST request to save FormMetaData : {}", formMetaDataDTO);
        if (formMetaDataDTO.getId() != null) {
            throw new BadRequestAlertException("A new formMetaData cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FormMetaDataDTO result = formMetaDataService.save(formMetaDataDTO);
        return ResponseEntity
            .created(new URI("/api/form-meta-data/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /form-meta-data/:id} : Updates an existing formMetaData.
     *
     * @param id the id of the formMetaDataDTO to save.
     * @param formMetaDataDTO the formMetaDataDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated formMetaDataDTO,
     * or with status {@code 400 (Bad Request)} if the formMetaDataDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the formMetaDataDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/form-meta-data/{id}")
    public ResponseEntity<FormMetaDataDTO> updateFormMetaData(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody FormMetaDataDTO formMetaDataDTO
    ) throws URISyntaxException {
        log.debug("REST request to update FormMetaData : {}, {}", id, formMetaDataDTO);
        if (formMetaDataDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, formMetaDataDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!formMetaDataRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        FormMetaDataDTO result = formMetaDataService.update(formMetaDataDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, formMetaDataDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /form-meta-data/:id} : Partial updates given fields of an existing formMetaData, field will ignore if it is null
     *
     * @param id the id of the formMetaDataDTO to save.
     * @param formMetaDataDTO the formMetaDataDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated formMetaDataDTO,
     * or with status {@code 400 (Bad Request)} if the formMetaDataDTO is not valid,
     * or with status {@code 404 (Not Found)} if the formMetaDataDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the formMetaDataDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/form-meta-data/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<FormMetaDataDTO> partialUpdateFormMetaData(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody FormMetaDataDTO formMetaDataDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update FormMetaData partially : {}, {}", id, formMetaDataDTO);
        if (formMetaDataDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, formMetaDataDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!formMetaDataRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<FormMetaDataDTO> result = formMetaDataService.partialUpdate(formMetaDataDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, formMetaDataDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /form-meta-data} : get all the formMetaData.
     *
     * @param pageable the pagination information.
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of formMetaData in body.
     */
    @GetMapping("/form-meta-data")
    public ResponseEntity<List<FormMetaDataDTO>> getAllFormMetaData(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        @RequestParam(required = false) String filter
    ) {
        if ("promotion-is-null".equals(filter)) {
            log.debug("REST request to get all FormMetaDatas where promotion is null");
            return new ResponseEntity<>(formMetaDataService.findAllWherePromotionIsNull(), HttpStatus.OK);
        }
        log.debug("REST request to get a page of FormMetaData");
        Page<FormMetaDataDTO> page = formMetaDataService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /form-meta-data/:id} : get the "id" formMetaData.
     *
     * @param id the id of the formMetaDataDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the formMetaDataDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/form-meta-data/{id}")
    public ResponseEntity<FormMetaDataDTO> getFormMetaData(@PathVariable Long id) {
        log.debug("REST request to get FormMetaData : {}", id);
        Optional<FormMetaDataDTO> formMetaDataDTO = formMetaDataService.findOne(id);
        return ResponseUtil.wrapOrNotFound(formMetaDataDTO);
    }

    /**
     * {@code DELETE  /form-meta-data/:id} : delete the "id" formMetaData.
     *
     * @param id the id of the formMetaDataDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/form-meta-data/{id}")
    public ResponseEntity<Void> deleteFormMetaData(@PathVariable Long id) {
        log.debug("REST request to delete FormMetaData : {}", id);
        formMetaDataService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
