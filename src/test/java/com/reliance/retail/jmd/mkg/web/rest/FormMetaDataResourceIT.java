package com.reliance.retail.jmd.mkg.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.reliance.retail.jmd.mkg.IntegrationTest;
import com.reliance.retail.jmd.mkg.domain.FormMetaData;
import com.reliance.retail.jmd.mkg.repository.FormMetaDataRepository;
import com.reliance.retail.jmd.mkg.service.dto.FormMetaDataDTO;
import com.reliance.retail.jmd.mkg.service.mapper.FormMetaDataMapper;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link FormMetaDataResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class FormMetaDataResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_ACTIVE = false;
    private static final Boolean UPDATED_IS_ACTIVE = true;

    private static final Boolean DEFAULT_MULTI_SUBMISSION_ALLOWED = false;
    private static final Boolean UPDATED_MULTI_SUBMISSION_ALLOWED = true;

    private static final String ENTITY_API_URL = "/api/form-meta-data";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private FormMetaDataRepository formMetaDataRepository;

    @Autowired
    private FormMetaDataMapper formMetaDataMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFormMetaDataMockMvc;

    private FormMetaData formMetaData;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FormMetaData createEntity(EntityManager em) {
        FormMetaData formMetaData = new FormMetaData()
            .name(DEFAULT_NAME)
            .title(DEFAULT_TITLE)
            .isActive(DEFAULT_IS_ACTIVE)
            .multiSubmissionAllowed(DEFAULT_MULTI_SUBMISSION_ALLOWED);
        return formMetaData;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FormMetaData createUpdatedEntity(EntityManager em) {
        FormMetaData formMetaData = new FormMetaData()
            .name(UPDATED_NAME)
            .title(UPDATED_TITLE)
            .isActive(UPDATED_IS_ACTIVE)
            .multiSubmissionAllowed(UPDATED_MULTI_SUBMISSION_ALLOWED);
        return formMetaData;
    }

    @BeforeEach
    public void initTest() {
        formMetaData = createEntity(em);
    }

    @Test
    @Transactional
    void createFormMetaData() throws Exception {
        int databaseSizeBeforeCreate = formMetaDataRepository.findAll().size();
        // Create the FormMetaData
        FormMetaDataDTO formMetaDataDTO = formMetaDataMapper.toDto(formMetaData);
        restFormMetaDataMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(formMetaDataDTO))
            )
            .andExpect(status().isCreated());

        // Validate the FormMetaData in the database
        List<FormMetaData> formMetaDataList = formMetaDataRepository.findAll();
        assertThat(formMetaDataList).hasSize(databaseSizeBeforeCreate + 1);
        FormMetaData testFormMetaData = formMetaDataList.get(formMetaDataList.size() - 1);
        assertThat(testFormMetaData.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testFormMetaData.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testFormMetaData.getIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testFormMetaData.getMultiSubmissionAllowed()).isEqualTo(DEFAULT_MULTI_SUBMISSION_ALLOWED);
    }

    @Test
    @Transactional
    void createFormMetaDataWithExistingId() throws Exception {
        // Create the FormMetaData with an existing ID
        formMetaData.setId(1L);
        FormMetaDataDTO formMetaDataDTO = formMetaDataMapper.toDto(formMetaData);

        int databaseSizeBeforeCreate = formMetaDataRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restFormMetaDataMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(formMetaDataDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FormMetaData in the database
        List<FormMetaData> formMetaDataList = formMetaDataRepository.findAll();
        assertThat(formMetaDataList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = formMetaDataRepository.findAll().size();
        // set the field null
        formMetaData.setName(null);

        // Create the FormMetaData, which fails.
        FormMetaDataDTO formMetaDataDTO = formMetaDataMapper.toDto(formMetaData);

        restFormMetaDataMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(formMetaDataDTO))
            )
            .andExpect(status().isBadRequest());

        List<FormMetaData> formMetaDataList = formMetaDataRepository.findAll();
        assertThat(formMetaDataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTitleIsRequired() throws Exception {
        int databaseSizeBeforeTest = formMetaDataRepository.findAll().size();
        // set the field null
        formMetaData.setTitle(null);

        // Create the FormMetaData, which fails.
        FormMetaDataDTO formMetaDataDTO = formMetaDataMapper.toDto(formMetaData);

        restFormMetaDataMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(formMetaDataDTO))
            )
            .andExpect(status().isBadRequest());

        List<FormMetaData> formMetaDataList = formMetaDataRepository.findAll();
        assertThat(formMetaDataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkIsActiveIsRequired() throws Exception {
        int databaseSizeBeforeTest = formMetaDataRepository.findAll().size();
        // set the field null
        formMetaData.setIsActive(null);

        // Create the FormMetaData, which fails.
        FormMetaDataDTO formMetaDataDTO = formMetaDataMapper.toDto(formMetaData);

        restFormMetaDataMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(formMetaDataDTO))
            )
            .andExpect(status().isBadRequest());

        List<FormMetaData> formMetaDataList = formMetaDataRepository.findAll();
        assertThat(formMetaDataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkMultiSubmissionAllowedIsRequired() throws Exception {
        int databaseSizeBeforeTest = formMetaDataRepository.findAll().size();
        // set the field null
        formMetaData.setMultiSubmissionAllowed(null);

        // Create the FormMetaData, which fails.
        FormMetaDataDTO formMetaDataDTO = formMetaDataMapper.toDto(formMetaData);

        restFormMetaDataMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(formMetaDataDTO))
            )
            .andExpect(status().isBadRequest());

        List<FormMetaData> formMetaDataList = formMetaDataRepository.findAll();
        assertThat(formMetaDataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllFormMetaData() throws Exception {
        // Initialize the database
        formMetaDataRepository.saveAndFlush(formMetaData);

        // Get all the formMetaDataList
        restFormMetaDataMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(formMetaData.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].multiSubmissionAllowed").value(hasItem(DEFAULT_MULTI_SUBMISSION_ALLOWED.booleanValue())));
    }

    @Test
    @Transactional
    void getFormMetaData() throws Exception {
        // Initialize the database
        formMetaDataRepository.saveAndFlush(formMetaData);

        // Get the formMetaData
        restFormMetaDataMockMvc
            .perform(get(ENTITY_API_URL_ID, formMetaData.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(formMetaData.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.multiSubmissionAllowed").value(DEFAULT_MULTI_SUBMISSION_ALLOWED.booleanValue()));
    }

    @Test
    @Transactional
    void getNonExistingFormMetaData() throws Exception {
        // Get the formMetaData
        restFormMetaDataMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewFormMetaData() throws Exception {
        // Initialize the database
        formMetaDataRepository.saveAndFlush(formMetaData);

        int databaseSizeBeforeUpdate = formMetaDataRepository.findAll().size();

        // Update the formMetaData
        FormMetaData updatedFormMetaData = formMetaDataRepository.findById(formMetaData.getId()).get();
        // Disconnect from session so that the updates on updatedFormMetaData are not directly saved in db
        em.detach(updatedFormMetaData);
        updatedFormMetaData
            .name(UPDATED_NAME)
            .title(UPDATED_TITLE)
            .isActive(UPDATED_IS_ACTIVE)
            .multiSubmissionAllowed(UPDATED_MULTI_SUBMISSION_ALLOWED);
        FormMetaDataDTO formMetaDataDTO = formMetaDataMapper.toDto(updatedFormMetaData);

        restFormMetaDataMockMvc
            .perform(
                put(ENTITY_API_URL_ID, formMetaDataDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(formMetaDataDTO))
            )
            .andExpect(status().isOk());

        // Validate the FormMetaData in the database
        List<FormMetaData> formMetaDataList = formMetaDataRepository.findAll();
        assertThat(formMetaDataList).hasSize(databaseSizeBeforeUpdate);
        FormMetaData testFormMetaData = formMetaDataList.get(formMetaDataList.size() - 1);
        assertThat(testFormMetaData.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testFormMetaData.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testFormMetaData.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testFormMetaData.getMultiSubmissionAllowed()).isEqualTo(UPDATED_MULTI_SUBMISSION_ALLOWED);
    }

    @Test
    @Transactional
    void putNonExistingFormMetaData() throws Exception {
        int databaseSizeBeforeUpdate = formMetaDataRepository.findAll().size();
        formMetaData.setId(count.incrementAndGet());

        // Create the FormMetaData
        FormMetaDataDTO formMetaDataDTO = formMetaDataMapper.toDto(formMetaData);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFormMetaDataMockMvc
            .perform(
                put(ENTITY_API_URL_ID, formMetaDataDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(formMetaDataDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FormMetaData in the database
        List<FormMetaData> formMetaDataList = formMetaDataRepository.findAll();
        assertThat(formMetaDataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchFormMetaData() throws Exception {
        int databaseSizeBeforeUpdate = formMetaDataRepository.findAll().size();
        formMetaData.setId(count.incrementAndGet());

        // Create the FormMetaData
        FormMetaDataDTO formMetaDataDTO = formMetaDataMapper.toDto(formMetaData);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFormMetaDataMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(formMetaDataDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FormMetaData in the database
        List<FormMetaData> formMetaDataList = formMetaDataRepository.findAll();
        assertThat(formMetaDataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamFormMetaData() throws Exception {
        int databaseSizeBeforeUpdate = formMetaDataRepository.findAll().size();
        formMetaData.setId(count.incrementAndGet());

        // Create the FormMetaData
        FormMetaDataDTO formMetaDataDTO = formMetaDataMapper.toDto(formMetaData);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFormMetaDataMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(formMetaDataDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the FormMetaData in the database
        List<FormMetaData> formMetaDataList = formMetaDataRepository.findAll();
        assertThat(formMetaDataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateFormMetaDataWithPatch() throws Exception {
        // Initialize the database
        formMetaDataRepository.saveAndFlush(formMetaData);

        int databaseSizeBeforeUpdate = formMetaDataRepository.findAll().size();

        // Update the formMetaData using partial update
        FormMetaData partialUpdatedFormMetaData = new FormMetaData();
        partialUpdatedFormMetaData.setId(formMetaData.getId());

        partialUpdatedFormMetaData.name(UPDATED_NAME).title(UPDATED_TITLE).multiSubmissionAllowed(UPDATED_MULTI_SUBMISSION_ALLOWED);

        restFormMetaDataMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFormMetaData.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedFormMetaData))
            )
            .andExpect(status().isOk());

        // Validate the FormMetaData in the database
        List<FormMetaData> formMetaDataList = formMetaDataRepository.findAll();
        assertThat(formMetaDataList).hasSize(databaseSizeBeforeUpdate);
        FormMetaData testFormMetaData = formMetaDataList.get(formMetaDataList.size() - 1);
        assertThat(testFormMetaData.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testFormMetaData.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testFormMetaData.getIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testFormMetaData.getMultiSubmissionAllowed()).isEqualTo(UPDATED_MULTI_SUBMISSION_ALLOWED);
    }

    @Test
    @Transactional
    void fullUpdateFormMetaDataWithPatch() throws Exception {
        // Initialize the database
        formMetaDataRepository.saveAndFlush(formMetaData);

        int databaseSizeBeforeUpdate = formMetaDataRepository.findAll().size();

        // Update the formMetaData using partial update
        FormMetaData partialUpdatedFormMetaData = new FormMetaData();
        partialUpdatedFormMetaData.setId(formMetaData.getId());

        partialUpdatedFormMetaData
            .name(UPDATED_NAME)
            .title(UPDATED_TITLE)
            .isActive(UPDATED_IS_ACTIVE)
            .multiSubmissionAllowed(UPDATED_MULTI_SUBMISSION_ALLOWED);

        restFormMetaDataMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFormMetaData.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedFormMetaData))
            )
            .andExpect(status().isOk());

        // Validate the FormMetaData in the database
        List<FormMetaData> formMetaDataList = formMetaDataRepository.findAll();
        assertThat(formMetaDataList).hasSize(databaseSizeBeforeUpdate);
        FormMetaData testFormMetaData = formMetaDataList.get(formMetaDataList.size() - 1);
        assertThat(testFormMetaData.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testFormMetaData.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testFormMetaData.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testFormMetaData.getMultiSubmissionAllowed()).isEqualTo(UPDATED_MULTI_SUBMISSION_ALLOWED);
    }

    @Test
    @Transactional
    void patchNonExistingFormMetaData() throws Exception {
        int databaseSizeBeforeUpdate = formMetaDataRepository.findAll().size();
        formMetaData.setId(count.incrementAndGet());

        // Create the FormMetaData
        FormMetaDataDTO formMetaDataDTO = formMetaDataMapper.toDto(formMetaData);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFormMetaDataMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, formMetaDataDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(formMetaDataDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FormMetaData in the database
        List<FormMetaData> formMetaDataList = formMetaDataRepository.findAll();
        assertThat(formMetaDataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchFormMetaData() throws Exception {
        int databaseSizeBeforeUpdate = formMetaDataRepository.findAll().size();
        formMetaData.setId(count.incrementAndGet());

        // Create the FormMetaData
        FormMetaDataDTO formMetaDataDTO = formMetaDataMapper.toDto(formMetaData);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFormMetaDataMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(formMetaDataDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FormMetaData in the database
        List<FormMetaData> formMetaDataList = formMetaDataRepository.findAll();
        assertThat(formMetaDataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamFormMetaData() throws Exception {
        int databaseSizeBeforeUpdate = formMetaDataRepository.findAll().size();
        formMetaData.setId(count.incrementAndGet());

        // Create the FormMetaData
        FormMetaDataDTO formMetaDataDTO = formMetaDataMapper.toDto(formMetaData);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFormMetaDataMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(formMetaDataDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the FormMetaData in the database
        List<FormMetaData> formMetaDataList = formMetaDataRepository.findAll();
        assertThat(formMetaDataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteFormMetaData() throws Exception {
        // Initialize the database
        formMetaDataRepository.saveAndFlush(formMetaData);

        int databaseSizeBeforeDelete = formMetaDataRepository.findAll().size();

        // Delete the formMetaData
        restFormMetaDataMockMvc
            .perform(delete(ENTITY_API_URL_ID, formMetaData.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FormMetaData> formMetaDataList = formMetaDataRepository.findAll();
        assertThat(formMetaDataList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
