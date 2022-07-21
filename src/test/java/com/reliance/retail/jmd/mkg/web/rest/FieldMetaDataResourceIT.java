package com.reliance.retail.jmd.mkg.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.reliance.retail.jmd.mkg.IntegrationTest;
import com.reliance.retail.jmd.mkg.domain.FieldMetaData;
import com.reliance.retail.jmd.mkg.domain.enumeration.DataType;
import com.reliance.retail.jmd.mkg.repository.FieldMetaDataRepository;
import com.reliance.retail.jmd.mkg.service.dto.FieldMetaDataDTO;
import com.reliance.retail.jmd.mkg.service.mapper.FieldMetaDataMapper;
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
 * Integration tests for the {@link FieldMetaDataResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class FieldMetaDataResourceIT {

    private static final String DEFAULT_KEY = "AAAAAAAAAA";
    private static final String UPDATED_KEY = "BBBBBBBBBB";

    private static final DataType DEFAULT_TYPE = DataType.TextBox;
    private static final DataType UPDATED_TYPE = DataType.ContactNumber;

    private static final Boolean DEFAULT_IS_MANDATORY = false;
    private static final Boolean UPDATED_IS_MANDATORY = true;

    private static final String ENTITY_API_URL = "/api/field-meta-data";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private FieldMetaDataRepository fieldMetaDataRepository;

    @Autowired
    private FieldMetaDataMapper fieldMetaDataMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFieldMetaDataMockMvc;

    private FieldMetaData fieldMetaData;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FieldMetaData createEntity(EntityManager em) {
        FieldMetaData fieldMetaData = new FieldMetaData().key(DEFAULT_KEY).type(DEFAULT_TYPE).isMandatory(DEFAULT_IS_MANDATORY);
        return fieldMetaData;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FieldMetaData createUpdatedEntity(EntityManager em) {
        FieldMetaData fieldMetaData = new FieldMetaData().key(UPDATED_KEY).type(UPDATED_TYPE).isMandatory(UPDATED_IS_MANDATORY);
        return fieldMetaData;
    }

    @BeforeEach
    public void initTest() {
        fieldMetaData = createEntity(em);
    }

    @Test
    @Transactional
    void createFieldMetaData() throws Exception {
        int databaseSizeBeforeCreate = fieldMetaDataRepository.findAll().size();
        // Create the FieldMetaData
        FieldMetaDataDTO fieldMetaDataDTO = fieldMetaDataMapper.toDto(fieldMetaData);
        restFieldMetaDataMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(fieldMetaDataDTO))
            )
            .andExpect(status().isCreated());

        // Validate the FieldMetaData in the database
        List<FieldMetaData> fieldMetaDataList = fieldMetaDataRepository.findAll();
        assertThat(fieldMetaDataList).hasSize(databaseSizeBeforeCreate + 1);
        FieldMetaData testFieldMetaData = fieldMetaDataList.get(fieldMetaDataList.size() - 1);
        assertThat(testFieldMetaData.getKey()).isEqualTo(DEFAULT_KEY);
        assertThat(testFieldMetaData.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testFieldMetaData.getIsMandatory()).isEqualTo(DEFAULT_IS_MANDATORY);
    }

    @Test
    @Transactional
    void createFieldMetaDataWithExistingId() throws Exception {
        // Create the FieldMetaData with an existing ID
        fieldMetaData.setId(1L);
        FieldMetaDataDTO fieldMetaDataDTO = fieldMetaDataMapper.toDto(fieldMetaData);

        int databaseSizeBeforeCreate = fieldMetaDataRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restFieldMetaDataMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(fieldMetaDataDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FieldMetaData in the database
        List<FieldMetaData> fieldMetaDataList = fieldMetaDataRepository.findAll();
        assertThat(fieldMetaDataList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkKeyIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldMetaDataRepository.findAll().size();
        // set the field null
        fieldMetaData.setKey(null);

        // Create the FieldMetaData, which fails.
        FieldMetaDataDTO fieldMetaDataDTO = fieldMetaDataMapper.toDto(fieldMetaData);

        restFieldMetaDataMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(fieldMetaDataDTO))
            )
            .andExpect(status().isBadRequest());

        List<FieldMetaData> fieldMetaDataList = fieldMetaDataRepository.findAll();
        assertThat(fieldMetaDataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldMetaDataRepository.findAll().size();
        // set the field null
        fieldMetaData.setType(null);

        // Create the FieldMetaData, which fails.
        FieldMetaDataDTO fieldMetaDataDTO = fieldMetaDataMapper.toDto(fieldMetaData);

        restFieldMetaDataMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(fieldMetaDataDTO))
            )
            .andExpect(status().isBadRequest());

        List<FieldMetaData> fieldMetaDataList = fieldMetaDataRepository.findAll();
        assertThat(fieldMetaDataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkIsMandatoryIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldMetaDataRepository.findAll().size();
        // set the field null
        fieldMetaData.setIsMandatory(null);

        // Create the FieldMetaData, which fails.
        FieldMetaDataDTO fieldMetaDataDTO = fieldMetaDataMapper.toDto(fieldMetaData);

        restFieldMetaDataMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(fieldMetaDataDTO))
            )
            .andExpect(status().isBadRequest());

        List<FieldMetaData> fieldMetaDataList = fieldMetaDataRepository.findAll();
        assertThat(fieldMetaDataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllFieldMetaData() throws Exception {
        // Initialize the database
        fieldMetaDataRepository.saveAndFlush(fieldMetaData);

        // Get all the fieldMetaDataList
        restFieldMetaDataMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fieldMetaData.getId().intValue())))
            .andExpect(jsonPath("$.[*].key").value(hasItem(DEFAULT_KEY)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].isMandatory").value(hasItem(DEFAULT_IS_MANDATORY.booleanValue())));
    }

    @Test
    @Transactional
    void getFieldMetaData() throws Exception {
        // Initialize the database
        fieldMetaDataRepository.saveAndFlush(fieldMetaData);

        // Get the fieldMetaData
        restFieldMetaDataMockMvc
            .perform(get(ENTITY_API_URL_ID, fieldMetaData.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(fieldMetaData.getId().intValue()))
            .andExpect(jsonPath("$.key").value(DEFAULT_KEY))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.isMandatory").value(DEFAULT_IS_MANDATORY.booleanValue()));
    }

    @Test
    @Transactional
    void getNonExistingFieldMetaData() throws Exception {
        // Get the fieldMetaData
        restFieldMetaDataMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewFieldMetaData() throws Exception {
        // Initialize the database
        fieldMetaDataRepository.saveAndFlush(fieldMetaData);

        int databaseSizeBeforeUpdate = fieldMetaDataRepository.findAll().size();

        // Update the fieldMetaData
        FieldMetaData updatedFieldMetaData = fieldMetaDataRepository.findById(fieldMetaData.getId()).get();
        // Disconnect from session so that the updates on updatedFieldMetaData are not directly saved in db
        em.detach(updatedFieldMetaData);
        updatedFieldMetaData.key(UPDATED_KEY).type(UPDATED_TYPE).isMandatory(UPDATED_IS_MANDATORY);
        FieldMetaDataDTO fieldMetaDataDTO = fieldMetaDataMapper.toDto(updatedFieldMetaData);

        restFieldMetaDataMockMvc
            .perform(
                put(ENTITY_API_URL_ID, fieldMetaDataDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(fieldMetaDataDTO))
            )
            .andExpect(status().isOk());

        // Validate the FieldMetaData in the database
        List<FieldMetaData> fieldMetaDataList = fieldMetaDataRepository.findAll();
        assertThat(fieldMetaDataList).hasSize(databaseSizeBeforeUpdate);
        FieldMetaData testFieldMetaData = fieldMetaDataList.get(fieldMetaDataList.size() - 1);
        assertThat(testFieldMetaData.getKey()).isEqualTo(UPDATED_KEY);
        assertThat(testFieldMetaData.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testFieldMetaData.getIsMandatory()).isEqualTo(UPDATED_IS_MANDATORY);
    }

    @Test
    @Transactional
    void putNonExistingFieldMetaData() throws Exception {
        int databaseSizeBeforeUpdate = fieldMetaDataRepository.findAll().size();
        fieldMetaData.setId(count.incrementAndGet());

        // Create the FieldMetaData
        FieldMetaDataDTO fieldMetaDataDTO = fieldMetaDataMapper.toDto(fieldMetaData);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFieldMetaDataMockMvc
            .perform(
                put(ENTITY_API_URL_ID, fieldMetaDataDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(fieldMetaDataDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FieldMetaData in the database
        List<FieldMetaData> fieldMetaDataList = fieldMetaDataRepository.findAll();
        assertThat(fieldMetaDataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchFieldMetaData() throws Exception {
        int databaseSizeBeforeUpdate = fieldMetaDataRepository.findAll().size();
        fieldMetaData.setId(count.incrementAndGet());

        // Create the FieldMetaData
        FieldMetaDataDTO fieldMetaDataDTO = fieldMetaDataMapper.toDto(fieldMetaData);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFieldMetaDataMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(fieldMetaDataDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FieldMetaData in the database
        List<FieldMetaData> fieldMetaDataList = fieldMetaDataRepository.findAll();
        assertThat(fieldMetaDataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamFieldMetaData() throws Exception {
        int databaseSizeBeforeUpdate = fieldMetaDataRepository.findAll().size();
        fieldMetaData.setId(count.incrementAndGet());

        // Create the FieldMetaData
        FieldMetaDataDTO fieldMetaDataDTO = fieldMetaDataMapper.toDto(fieldMetaData);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFieldMetaDataMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(fieldMetaDataDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the FieldMetaData in the database
        List<FieldMetaData> fieldMetaDataList = fieldMetaDataRepository.findAll();
        assertThat(fieldMetaDataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateFieldMetaDataWithPatch() throws Exception {
        // Initialize the database
        fieldMetaDataRepository.saveAndFlush(fieldMetaData);

        int databaseSizeBeforeUpdate = fieldMetaDataRepository.findAll().size();

        // Update the fieldMetaData using partial update
        FieldMetaData partialUpdatedFieldMetaData = new FieldMetaData();
        partialUpdatedFieldMetaData.setId(fieldMetaData.getId());

        partialUpdatedFieldMetaData.key(UPDATED_KEY).type(UPDATED_TYPE).isMandatory(UPDATED_IS_MANDATORY);

        restFieldMetaDataMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFieldMetaData.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedFieldMetaData))
            )
            .andExpect(status().isOk());

        // Validate the FieldMetaData in the database
        List<FieldMetaData> fieldMetaDataList = fieldMetaDataRepository.findAll();
        assertThat(fieldMetaDataList).hasSize(databaseSizeBeforeUpdate);
        FieldMetaData testFieldMetaData = fieldMetaDataList.get(fieldMetaDataList.size() - 1);
        assertThat(testFieldMetaData.getKey()).isEqualTo(UPDATED_KEY);
        assertThat(testFieldMetaData.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testFieldMetaData.getIsMandatory()).isEqualTo(UPDATED_IS_MANDATORY);
    }

    @Test
    @Transactional
    void fullUpdateFieldMetaDataWithPatch() throws Exception {
        // Initialize the database
        fieldMetaDataRepository.saveAndFlush(fieldMetaData);

        int databaseSizeBeforeUpdate = fieldMetaDataRepository.findAll().size();

        // Update the fieldMetaData using partial update
        FieldMetaData partialUpdatedFieldMetaData = new FieldMetaData();
        partialUpdatedFieldMetaData.setId(fieldMetaData.getId());

        partialUpdatedFieldMetaData.key(UPDATED_KEY).type(UPDATED_TYPE).isMandatory(UPDATED_IS_MANDATORY);

        restFieldMetaDataMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFieldMetaData.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedFieldMetaData))
            )
            .andExpect(status().isOk());

        // Validate the FieldMetaData in the database
        List<FieldMetaData> fieldMetaDataList = fieldMetaDataRepository.findAll();
        assertThat(fieldMetaDataList).hasSize(databaseSizeBeforeUpdate);
        FieldMetaData testFieldMetaData = fieldMetaDataList.get(fieldMetaDataList.size() - 1);
        assertThat(testFieldMetaData.getKey()).isEqualTo(UPDATED_KEY);
        assertThat(testFieldMetaData.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testFieldMetaData.getIsMandatory()).isEqualTo(UPDATED_IS_MANDATORY);
    }

    @Test
    @Transactional
    void patchNonExistingFieldMetaData() throws Exception {
        int databaseSizeBeforeUpdate = fieldMetaDataRepository.findAll().size();
        fieldMetaData.setId(count.incrementAndGet());

        // Create the FieldMetaData
        FieldMetaDataDTO fieldMetaDataDTO = fieldMetaDataMapper.toDto(fieldMetaData);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFieldMetaDataMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, fieldMetaDataDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(fieldMetaDataDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FieldMetaData in the database
        List<FieldMetaData> fieldMetaDataList = fieldMetaDataRepository.findAll();
        assertThat(fieldMetaDataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchFieldMetaData() throws Exception {
        int databaseSizeBeforeUpdate = fieldMetaDataRepository.findAll().size();
        fieldMetaData.setId(count.incrementAndGet());

        // Create the FieldMetaData
        FieldMetaDataDTO fieldMetaDataDTO = fieldMetaDataMapper.toDto(fieldMetaData);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFieldMetaDataMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(fieldMetaDataDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FieldMetaData in the database
        List<FieldMetaData> fieldMetaDataList = fieldMetaDataRepository.findAll();
        assertThat(fieldMetaDataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamFieldMetaData() throws Exception {
        int databaseSizeBeforeUpdate = fieldMetaDataRepository.findAll().size();
        fieldMetaData.setId(count.incrementAndGet());

        // Create the FieldMetaData
        FieldMetaDataDTO fieldMetaDataDTO = fieldMetaDataMapper.toDto(fieldMetaData);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFieldMetaDataMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(fieldMetaDataDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the FieldMetaData in the database
        List<FieldMetaData> fieldMetaDataList = fieldMetaDataRepository.findAll();
        assertThat(fieldMetaDataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteFieldMetaData() throws Exception {
        // Initialize the database
        fieldMetaDataRepository.saveAndFlush(fieldMetaData);

        int databaseSizeBeforeDelete = fieldMetaDataRepository.findAll().size();

        // Delete the fieldMetaData
        restFieldMetaDataMockMvc
            .perform(delete(ENTITY_API_URL_ID, fieldMetaData.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FieldMetaData> fieldMetaDataList = fieldMetaDataRepository.findAll();
        assertThat(fieldMetaDataList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
