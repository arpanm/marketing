package com.reliance.retail.jmd.mkg.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.reliance.retail.jmd.mkg.IntegrationTest;
import com.reliance.retail.jmd.mkg.domain.FieldSubmission;
import com.reliance.retail.jmd.mkg.repository.FieldSubmissionRepository;
import com.reliance.retail.jmd.mkg.service.dto.FieldSubmissionDTO;
import com.reliance.retail.jmd.mkg.service.mapper.FieldSubmissionMapper;
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
 * Integration tests for the {@link FieldSubmissionResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class FieldSubmissionResourceIT {

    private static final String DEFAULT_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_VALUE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/field-submissions";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private FieldSubmissionRepository fieldSubmissionRepository;

    @Autowired
    private FieldSubmissionMapper fieldSubmissionMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFieldSubmissionMockMvc;

    private FieldSubmission fieldSubmission;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FieldSubmission createEntity(EntityManager em) {
        FieldSubmission fieldSubmission = new FieldSubmission().value(DEFAULT_VALUE);
        return fieldSubmission;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FieldSubmission createUpdatedEntity(EntityManager em) {
        FieldSubmission fieldSubmission = new FieldSubmission().value(UPDATED_VALUE);
        return fieldSubmission;
    }

    @BeforeEach
    public void initTest() {
        fieldSubmission = createEntity(em);
    }

    @Test
    @Transactional
    void createFieldSubmission() throws Exception {
        int databaseSizeBeforeCreate = fieldSubmissionRepository.findAll().size();
        // Create the FieldSubmission
        FieldSubmissionDTO fieldSubmissionDTO = fieldSubmissionMapper.toDto(fieldSubmission);
        restFieldSubmissionMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(fieldSubmissionDTO))
            )
            .andExpect(status().isCreated());

        // Validate the FieldSubmission in the database
        List<FieldSubmission> fieldSubmissionList = fieldSubmissionRepository.findAll();
        assertThat(fieldSubmissionList).hasSize(databaseSizeBeforeCreate + 1);
        FieldSubmission testFieldSubmission = fieldSubmissionList.get(fieldSubmissionList.size() - 1);
        assertThat(testFieldSubmission.getValue()).isEqualTo(DEFAULT_VALUE);
    }

    @Test
    @Transactional
    void createFieldSubmissionWithExistingId() throws Exception {
        // Create the FieldSubmission with an existing ID
        fieldSubmission.setId(1L);
        FieldSubmissionDTO fieldSubmissionDTO = fieldSubmissionMapper.toDto(fieldSubmission);

        int databaseSizeBeforeCreate = fieldSubmissionRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restFieldSubmissionMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(fieldSubmissionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FieldSubmission in the database
        List<FieldSubmission> fieldSubmissionList = fieldSubmissionRepository.findAll();
        assertThat(fieldSubmissionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllFieldSubmissions() throws Exception {
        // Initialize the database
        fieldSubmissionRepository.saveAndFlush(fieldSubmission);

        // Get all the fieldSubmissionList
        restFieldSubmissionMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fieldSubmission.getId().intValue())))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE)));
    }

    @Test
    @Transactional
    void getFieldSubmission() throws Exception {
        // Initialize the database
        fieldSubmissionRepository.saveAndFlush(fieldSubmission);

        // Get the fieldSubmission
        restFieldSubmissionMockMvc
            .perform(get(ENTITY_API_URL_ID, fieldSubmission.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(fieldSubmission.getId().intValue()))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE));
    }

    @Test
    @Transactional
    void getNonExistingFieldSubmission() throws Exception {
        // Get the fieldSubmission
        restFieldSubmissionMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewFieldSubmission() throws Exception {
        // Initialize the database
        fieldSubmissionRepository.saveAndFlush(fieldSubmission);

        int databaseSizeBeforeUpdate = fieldSubmissionRepository.findAll().size();

        // Update the fieldSubmission
        FieldSubmission updatedFieldSubmission = fieldSubmissionRepository.findById(fieldSubmission.getId()).get();
        // Disconnect from session so that the updates on updatedFieldSubmission are not directly saved in db
        em.detach(updatedFieldSubmission);
        updatedFieldSubmission.value(UPDATED_VALUE);
        FieldSubmissionDTO fieldSubmissionDTO = fieldSubmissionMapper.toDto(updatedFieldSubmission);

        restFieldSubmissionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, fieldSubmissionDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(fieldSubmissionDTO))
            )
            .andExpect(status().isOk());

        // Validate the FieldSubmission in the database
        List<FieldSubmission> fieldSubmissionList = fieldSubmissionRepository.findAll();
        assertThat(fieldSubmissionList).hasSize(databaseSizeBeforeUpdate);
        FieldSubmission testFieldSubmission = fieldSubmissionList.get(fieldSubmissionList.size() - 1);
        assertThat(testFieldSubmission.getValue()).isEqualTo(UPDATED_VALUE);
    }

    @Test
    @Transactional
    void putNonExistingFieldSubmission() throws Exception {
        int databaseSizeBeforeUpdate = fieldSubmissionRepository.findAll().size();
        fieldSubmission.setId(count.incrementAndGet());

        // Create the FieldSubmission
        FieldSubmissionDTO fieldSubmissionDTO = fieldSubmissionMapper.toDto(fieldSubmission);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFieldSubmissionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, fieldSubmissionDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(fieldSubmissionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FieldSubmission in the database
        List<FieldSubmission> fieldSubmissionList = fieldSubmissionRepository.findAll();
        assertThat(fieldSubmissionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchFieldSubmission() throws Exception {
        int databaseSizeBeforeUpdate = fieldSubmissionRepository.findAll().size();
        fieldSubmission.setId(count.incrementAndGet());

        // Create the FieldSubmission
        FieldSubmissionDTO fieldSubmissionDTO = fieldSubmissionMapper.toDto(fieldSubmission);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFieldSubmissionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(fieldSubmissionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FieldSubmission in the database
        List<FieldSubmission> fieldSubmissionList = fieldSubmissionRepository.findAll();
        assertThat(fieldSubmissionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamFieldSubmission() throws Exception {
        int databaseSizeBeforeUpdate = fieldSubmissionRepository.findAll().size();
        fieldSubmission.setId(count.incrementAndGet());

        // Create the FieldSubmission
        FieldSubmissionDTO fieldSubmissionDTO = fieldSubmissionMapper.toDto(fieldSubmission);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFieldSubmissionMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(fieldSubmissionDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the FieldSubmission in the database
        List<FieldSubmission> fieldSubmissionList = fieldSubmissionRepository.findAll();
        assertThat(fieldSubmissionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateFieldSubmissionWithPatch() throws Exception {
        // Initialize the database
        fieldSubmissionRepository.saveAndFlush(fieldSubmission);

        int databaseSizeBeforeUpdate = fieldSubmissionRepository.findAll().size();

        // Update the fieldSubmission using partial update
        FieldSubmission partialUpdatedFieldSubmission = new FieldSubmission();
        partialUpdatedFieldSubmission.setId(fieldSubmission.getId());

        partialUpdatedFieldSubmission.value(UPDATED_VALUE);

        restFieldSubmissionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFieldSubmission.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedFieldSubmission))
            )
            .andExpect(status().isOk());

        // Validate the FieldSubmission in the database
        List<FieldSubmission> fieldSubmissionList = fieldSubmissionRepository.findAll();
        assertThat(fieldSubmissionList).hasSize(databaseSizeBeforeUpdate);
        FieldSubmission testFieldSubmission = fieldSubmissionList.get(fieldSubmissionList.size() - 1);
        assertThat(testFieldSubmission.getValue()).isEqualTo(UPDATED_VALUE);
    }

    @Test
    @Transactional
    void fullUpdateFieldSubmissionWithPatch() throws Exception {
        // Initialize the database
        fieldSubmissionRepository.saveAndFlush(fieldSubmission);

        int databaseSizeBeforeUpdate = fieldSubmissionRepository.findAll().size();

        // Update the fieldSubmission using partial update
        FieldSubmission partialUpdatedFieldSubmission = new FieldSubmission();
        partialUpdatedFieldSubmission.setId(fieldSubmission.getId());

        partialUpdatedFieldSubmission.value(UPDATED_VALUE);

        restFieldSubmissionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFieldSubmission.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedFieldSubmission))
            )
            .andExpect(status().isOk());

        // Validate the FieldSubmission in the database
        List<FieldSubmission> fieldSubmissionList = fieldSubmissionRepository.findAll();
        assertThat(fieldSubmissionList).hasSize(databaseSizeBeforeUpdate);
        FieldSubmission testFieldSubmission = fieldSubmissionList.get(fieldSubmissionList.size() - 1);
        assertThat(testFieldSubmission.getValue()).isEqualTo(UPDATED_VALUE);
    }

    @Test
    @Transactional
    void patchNonExistingFieldSubmission() throws Exception {
        int databaseSizeBeforeUpdate = fieldSubmissionRepository.findAll().size();
        fieldSubmission.setId(count.incrementAndGet());

        // Create the FieldSubmission
        FieldSubmissionDTO fieldSubmissionDTO = fieldSubmissionMapper.toDto(fieldSubmission);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFieldSubmissionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, fieldSubmissionDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(fieldSubmissionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FieldSubmission in the database
        List<FieldSubmission> fieldSubmissionList = fieldSubmissionRepository.findAll();
        assertThat(fieldSubmissionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchFieldSubmission() throws Exception {
        int databaseSizeBeforeUpdate = fieldSubmissionRepository.findAll().size();
        fieldSubmission.setId(count.incrementAndGet());

        // Create the FieldSubmission
        FieldSubmissionDTO fieldSubmissionDTO = fieldSubmissionMapper.toDto(fieldSubmission);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFieldSubmissionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(fieldSubmissionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FieldSubmission in the database
        List<FieldSubmission> fieldSubmissionList = fieldSubmissionRepository.findAll();
        assertThat(fieldSubmissionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamFieldSubmission() throws Exception {
        int databaseSizeBeforeUpdate = fieldSubmissionRepository.findAll().size();
        fieldSubmission.setId(count.incrementAndGet());

        // Create the FieldSubmission
        FieldSubmissionDTO fieldSubmissionDTO = fieldSubmissionMapper.toDto(fieldSubmission);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFieldSubmissionMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(fieldSubmissionDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the FieldSubmission in the database
        List<FieldSubmission> fieldSubmissionList = fieldSubmissionRepository.findAll();
        assertThat(fieldSubmissionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteFieldSubmission() throws Exception {
        // Initialize the database
        fieldSubmissionRepository.saveAndFlush(fieldSubmission);

        int databaseSizeBeforeDelete = fieldSubmissionRepository.findAll().size();

        // Delete the fieldSubmission
        restFieldSubmissionMockMvc
            .perform(delete(ENTITY_API_URL_ID, fieldSubmission.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FieldSubmission> fieldSubmissionList = fieldSubmissionRepository.findAll();
        assertThat(fieldSubmissionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
