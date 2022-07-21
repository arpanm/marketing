package com.reliance.retail.jmd.mkg.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.reliance.retail.jmd.mkg.IntegrationTest;
import com.reliance.retail.jmd.mkg.domain.FormSubmission;
import com.reliance.retail.jmd.mkg.repository.FormSubmissionRepository;
import com.reliance.retail.jmd.mkg.service.dto.FormSubmissionDTO;
import com.reliance.retail.jmd.mkg.service.mapper.FormSubmissionMapper;
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
 * Integration tests for the {@link FormSubmissionResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class FormSubmissionResourceIT {

    private static final String DEFAULT_SESSIONIDENTIFIER = "AAAAAAAAAA";
    private static final String UPDATED_SESSIONIDENTIFIER = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/form-submissions";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private FormSubmissionRepository formSubmissionRepository;

    @Autowired
    private FormSubmissionMapper formSubmissionMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFormSubmissionMockMvc;

    private FormSubmission formSubmission;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FormSubmission createEntity(EntityManager em) {
        FormSubmission formSubmission = new FormSubmission().sessionidentifier(DEFAULT_SESSIONIDENTIFIER);
        return formSubmission;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FormSubmission createUpdatedEntity(EntityManager em) {
        FormSubmission formSubmission = new FormSubmission().sessionidentifier(UPDATED_SESSIONIDENTIFIER);
        return formSubmission;
    }

    @BeforeEach
    public void initTest() {
        formSubmission = createEntity(em);
    }

    @Test
    @Transactional
    void createFormSubmission() throws Exception {
        int databaseSizeBeforeCreate = formSubmissionRepository.findAll().size();
        // Create the FormSubmission
        FormSubmissionDTO formSubmissionDTO = formSubmissionMapper.toDto(formSubmission);
        restFormSubmissionMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(formSubmissionDTO))
            )
            .andExpect(status().isCreated());

        // Validate the FormSubmission in the database
        List<FormSubmission> formSubmissionList = formSubmissionRepository.findAll();
        assertThat(formSubmissionList).hasSize(databaseSizeBeforeCreate + 1);
        FormSubmission testFormSubmission = formSubmissionList.get(formSubmissionList.size() - 1);
        assertThat(testFormSubmission.getSessionidentifier()).isEqualTo(DEFAULT_SESSIONIDENTIFIER);
    }

    @Test
    @Transactional
    void createFormSubmissionWithExistingId() throws Exception {
        // Create the FormSubmission with an existing ID
        formSubmission.setId(1L);
        FormSubmissionDTO formSubmissionDTO = formSubmissionMapper.toDto(formSubmission);

        int databaseSizeBeforeCreate = formSubmissionRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restFormSubmissionMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(formSubmissionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FormSubmission in the database
        List<FormSubmission> formSubmissionList = formSubmissionRepository.findAll();
        assertThat(formSubmissionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllFormSubmissions() throws Exception {
        // Initialize the database
        formSubmissionRepository.saveAndFlush(formSubmission);

        // Get all the formSubmissionList
        restFormSubmissionMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(formSubmission.getId().intValue())))
            .andExpect(jsonPath("$.[*].sessionidentifier").value(hasItem(DEFAULT_SESSIONIDENTIFIER)));
    }

    @Test
    @Transactional
    void getFormSubmission() throws Exception {
        // Initialize the database
        formSubmissionRepository.saveAndFlush(formSubmission);

        // Get the formSubmission
        restFormSubmissionMockMvc
            .perform(get(ENTITY_API_URL_ID, formSubmission.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(formSubmission.getId().intValue()))
            .andExpect(jsonPath("$.sessionidentifier").value(DEFAULT_SESSIONIDENTIFIER));
    }

    @Test
    @Transactional
    void getNonExistingFormSubmission() throws Exception {
        // Get the formSubmission
        restFormSubmissionMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewFormSubmission() throws Exception {
        // Initialize the database
        formSubmissionRepository.saveAndFlush(formSubmission);

        int databaseSizeBeforeUpdate = formSubmissionRepository.findAll().size();

        // Update the formSubmission
        FormSubmission updatedFormSubmission = formSubmissionRepository.findById(formSubmission.getId()).get();
        // Disconnect from session so that the updates on updatedFormSubmission are not directly saved in db
        em.detach(updatedFormSubmission);
        updatedFormSubmission.sessionidentifier(UPDATED_SESSIONIDENTIFIER);
        FormSubmissionDTO formSubmissionDTO = formSubmissionMapper.toDto(updatedFormSubmission);

        restFormSubmissionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, formSubmissionDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(formSubmissionDTO))
            )
            .andExpect(status().isOk());

        // Validate the FormSubmission in the database
        List<FormSubmission> formSubmissionList = formSubmissionRepository.findAll();
        assertThat(formSubmissionList).hasSize(databaseSizeBeforeUpdate);
        FormSubmission testFormSubmission = formSubmissionList.get(formSubmissionList.size() - 1);
        assertThat(testFormSubmission.getSessionidentifier()).isEqualTo(UPDATED_SESSIONIDENTIFIER);
    }

    @Test
    @Transactional
    void putNonExistingFormSubmission() throws Exception {
        int databaseSizeBeforeUpdate = formSubmissionRepository.findAll().size();
        formSubmission.setId(count.incrementAndGet());

        // Create the FormSubmission
        FormSubmissionDTO formSubmissionDTO = formSubmissionMapper.toDto(formSubmission);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFormSubmissionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, formSubmissionDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(formSubmissionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FormSubmission in the database
        List<FormSubmission> formSubmissionList = formSubmissionRepository.findAll();
        assertThat(formSubmissionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchFormSubmission() throws Exception {
        int databaseSizeBeforeUpdate = formSubmissionRepository.findAll().size();
        formSubmission.setId(count.incrementAndGet());

        // Create the FormSubmission
        FormSubmissionDTO formSubmissionDTO = formSubmissionMapper.toDto(formSubmission);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFormSubmissionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(formSubmissionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FormSubmission in the database
        List<FormSubmission> formSubmissionList = formSubmissionRepository.findAll();
        assertThat(formSubmissionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamFormSubmission() throws Exception {
        int databaseSizeBeforeUpdate = formSubmissionRepository.findAll().size();
        formSubmission.setId(count.incrementAndGet());

        // Create the FormSubmission
        FormSubmissionDTO formSubmissionDTO = formSubmissionMapper.toDto(formSubmission);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFormSubmissionMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(formSubmissionDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the FormSubmission in the database
        List<FormSubmission> formSubmissionList = formSubmissionRepository.findAll();
        assertThat(formSubmissionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateFormSubmissionWithPatch() throws Exception {
        // Initialize the database
        formSubmissionRepository.saveAndFlush(formSubmission);

        int databaseSizeBeforeUpdate = formSubmissionRepository.findAll().size();

        // Update the formSubmission using partial update
        FormSubmission partialUpdatedFormSubmission = new FormSubmission();
        partialUpdatedFormSubmission.setId(formSubmission.getId());

        partialUpdatedFormSubmission.sessionidentifier(UPDATED_SESSIONIDENTIFIER);

        restFormSubmissionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFormSubmission.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedFormSubmission))
            )
            .andExpect(status().isOk());

        // Validate the FormSubmission in the database
        List<FormSubmission> formSubmissionList = formSubmissionRepository.findAll();
        assertThat(formSubmissionList).hasSize(databaseSizeBeforeUpdate);
        FormSubmission testFormSubmission = formSubmissionList.get(formSubmissionList.size() - 1);
        assertThat(testFormSubmission.getSessionidentifier()).isEqualTo(UPDATED_SESSIONIDENTIFIER);
    }

    @Test
    @Transactional
    void fullUpdateFormSubmissionWithPatch() throws Exception {
        // Initialize the database
        formSubmissionRepository.saveAndFlush(formSubmission);

        int databaseSizeBeforeUpdate = formSubmissionRepository.findAll().size();

        // Update the formSubmission using partial update
        FormSubmission partialUpdatedFormSubmission = new FormSubmission();
        partialUpdatedFormSubmission.setId(formSubmission.getId());

        partialUpdatedFormSubmission.sessionidentifier(UPDATED_SESSIONIDENTIFIER);

        restFormSubmissionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFormSubmission.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedFormSubmission))
            )
            .andExpect(status().isOk());

        // Validate the FormSubmission in the database
        List<FormSubmission> formSubmissionList = formSubmissionRepository.findAll();
        assertThat(formSubmissionList).hasSize(databaseSizeBeforeUpdate);
        FormSubmission testFormSubmission = formSubmissionList.get(formSubmissionList.size() - 1);
        assertThat(testFormSubmission.getSessionidentifier()).isEqualTo(UPDATED_SESSIONIDENTIFIER);
    }

    @Test
    @Transactional
    void patchNonExistingFormSubmission() throws Exception {
        int databaseSizeBeforeUpdate = formSubmissionRepository.findAll().size();
        formSubmission.setId(count.incrementAndGet());

        // Create the FormSubmission
        FormSubmissionDTO formSubmissionDTO = formSubmissionMapper.toDto(formSubmission);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFormSubmissionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, formSubmissionDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(formSubmissionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FormSubmission in the database
        List<FormSubmission> formSubmissionList = formSubmissionRepository.findAll();
        assertThat(formSubmissionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchFormSubmission() throws Exception {
        int databaseSizeBeforeUpdate = formSubmissionRepository.findAll().size();
        formSubmission.setId(count.incrementAndGet());

        // Create the FormSubmission
        FormSubmissionDTO formSubmissionDTO = formSubmissionMapper.toDto(formSubmission);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFormSubmissionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(formSubmissionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FormSubmission in the database
        List<FormSubmission> formSubmissionList = formSubmissionRepository.findAll();
        assertThat(formSubmissionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamFormSubmission() throws Exception {
        int databaseSizeBeforeUpdate = formSubmissionRepository.findAll().size();
        formSubmission.setId(count.incrementAndGet());

        // Create the FormSubmission
        FormSubmissionDTO formSubmissionDTO = formSubmissionMapper.toDto(formSubmission);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFormSubmissionMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(formSubmissionDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the FormSubmission in the database
        List<FormSubmission> formSubmissionList = formSubmissionRepository.findAll();
        assertThat(formSubmissionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteFormSubmission() throws Exception {
        // Initialize the database
        formSubmissionRepository.saveAndFlush(formSubmission);

        int databaseSizeBeforeDelete = formSubmissionRepository.findAll().size();

        // Delete the formSubmission
        restFormSubmissionMockMvc
            .perform(delete(ENTITY_API_URL_ID, formSubmission.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FormSubmission> formSubmissionList = formSubmissionRepository.findAll();
        assertThat(formSubmissionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
