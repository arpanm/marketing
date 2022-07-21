package com.reliance.retail.jmd.mkg.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.reliance.retail.jmd.mkg.IntegrationTest;
import com.reliance.retail.jmd.mkg.domain.Promotion;
import com.reliance.retail.jmd.mkg.repository.PromotionRepository;
import com.reliance.retail.jmd.mkg.service.dto.PromotionDTO;
import com.reliance.retail.jmd.mkg.service.mapper.PromotionMapper;
import java.time.LocalDate;
import java.time.ZoneId;
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
 * Integration tests for the {@link PromotionResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PromotionResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_URL_NAME = "AAAAAAAAAA";
    private static final String UPDATED_URL_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESKTOP_IMAGE_URL = "AAAAAAAAAA";
    private static final String UPDATED_DESKTOP_IMAGE_URL = "BBBBBBBBBB";

    private static final String DEFAULT_TABLET_IMAGE_URL = "AAAAAAAAAA";
    private static final String UPDATED_TABLET_IMAGE_URL = "BBBBBBBBBB";

    private static final String DEFAULT_MOBILE_IMAGE_URL = "AAAAAAAAAA";
    private static final String UPDATED_MOBILE_IMAGE_URL = "BBBBBBBBBB";

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_TNC = "AAAAAAAAAA";
    private static final String UPDATED_TNC = "BBBBBBBBBB";

    private static final String DEFAULT_TNC_LINK = "AAAAAAAAAA";
    private static final String UPDATED_TNC_LINK = "BBBBBBBBBB";

    private static final String DEFAULT_LANDING_URL = "AAAAAAAAAA";
    private static final String UPDATED_LANDING_URL = "BBBBBBBBBB";

    private static final Integer DEFAULT_POSITION = 1;
    private static final Integer UPDATED_POSITION = 2;

    private static final Boolean DEFAULT_IS_ENABLED = false;
    private static final Boolean UPDATED_IS_ENABLED = true;

    private static final LocalDate DEFAULT_START_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_START_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_END_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_END_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_UPDATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_UPDATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/promotions";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PromotionRepository promotionRepository;

    @Autowired
    private PromotionMapper promotionMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPromotionMockMvc;

    private Promotion promotion;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Promotion createEntity(EntityManager em) {
        Promotion promotion = new Promotion()
            .name(DEFAULT_NAME)
            .urlName(DEFAULT_URL_NAME)
            .desktopImageUrl(DEFAULT_DESKTOP_IMAGE_URL)
            .tabletImageUrl(DEFAULT_TABLET_IMAGE_URL)
            .mobileImageUrl(DEFAULT_MOBILE_IMAGE_URL)
            .title(DEFAULT_TITLE)
            .description(DEFAULT_DESCRIPTION)
            .tnc(DEFAULT_TNC)
            .tncLink(DEFAULT_TNC_LINK)
            .landingUrl(DEFAULT_LANDING_URL)
            .position(DEFAULT_POSITION)
            .isEnabled(DEFAULT_IS_ENABLED)
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedBy(DEFAULT_UPDATED_BY)
            .updatedDate(DEFAULT_UPDATED_DATE);
        return promotion;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Promotion createUpdatedEntity(EntityManager em) {
        Promotion promotion = new Promotion()
            .name(UPDATED_NAME)
            .urlName(UPDATED_URL_NAME)
            .desktopImageUrl(UPDATED_DESKTOP_IMAGE_URL)
            .tabletImageUrl(UPDATED_TABLET_IMAGE_URL)
            .mobileImageUrl(UPDATED_MOBILE_IMAGE_URL)
            .title(UPDATED_TITLE)
            .description(UPDATED_DESCRIPTION)
            .tnc(UPDATED_TNC)
            .tncLink(UPDATED_TNC_LINK)
            .landingUrl(UPDATED_LANDING_URL)
            .position(UPDATED_POSITION)
            .isEnabled(UPDATED_IS_ENABLED)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedDate(UPDATED_UPDATED_DATE);
        return promotion;
    }

    @BeforeEach
    public void initTest() {
        promotion = createEntity(em);
    }

    @Test
    @Transactional
    void createPromotion() throws Exception {
        int databaseSizeBeforeCreate = promotionRepository.findAll().size();
        // Create the Promotion
        PromotionDTO promotionDTO = promotionMapper.toDto(promotion);
        restPromotionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(promotionDTO)))
            .andExpect(status().isCreated());

        // Validate the Promotion in the database
        List<Promotion> promotionList = promotionRepository.findAll();
        assertThat(promotionList).hasSize(databaseSizeBeforeCreate + 1);
        Promotion testPromotion = promotionList.get(promotionList.size() - 1);
        assertThat(testPromotion.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testPromotion.getUrlName()).isEqualTo(DEFAULT_URL_NAME);
        assertThat(testPromotion.getDesktopImageUrl()).isEqualTo(DEFAULT_DESKTOP_IMAGE_URL);
        assertThat(testPromotion.getTabletImageUrl()).isEqualTo(DEFAULT_TABLET_IMAGE_URL);
        assertThat(testPromotion.getMobileImageUrl()).isEqualTo(DEFAULT_MOBILE_IMAGE_URL);
        assertThat(testPromotion.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testPromotion.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testPromotion.getTnc()).isEqualTo(DEFAULT_TNC);
        assertThat(testPromotion.getTncLink()).isEqualTo(DEFAULT_TNC_LINK);
        assertThat(testPromotion.getLandingUrl()).isEqualTo(DEFAULT_LANDING_URL);
        assertThat(testPromotion.getPosition()).isEqualTo(DEFAULT_POSITION);
        assertThat(testPromotion.getIsEnabled()).isEqualTo(DEFAULT_IS_ENABLED);
        assertThat(testPromotion.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testPromotion.getEndDate()).isEqualTo(DEFAULT_END_DATE);
        assertThat(testPromotion.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testPromotion.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testPromotion.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
        assertThat(testPromotion.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
    }

    @Test
    @Transactional
    void createPromotionWithExistingId() throws Exception {
        // Create the Promotion with an existing ID
        promotion.setId(1L);
        PromotionDTO promotionDTO = promotionMapper.toDto(promotion);

        int databaseSizeBeforeCreate = promotionRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPromotionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(promotionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Promotion in the database
        List<Promotion> promotionList = promotionRepository.findAll();
        assertThat(promotionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = promotionRepository.findAll().size();
        // set the field null
        promotion.setName(null);

        // Create the Promotion, which fails.
        PromotionDTO promotionDTO = promotionMapper.toDto(promotion);

        restPromotionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(promotionDTO)))
            .andExpect(status().isBadRequest());

        List<Promotion> promotionList = promotionRepository.findAll();
        assertThat(promotionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUrlNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = promotionRepository.findAll().size();
        // set the field null
        promotion.setUrlName(null);

        // Create the Promotion, which fails.
        PromotionDTO promotionDTO = promotionMapper.toDto(promotion);

        restPromotionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(promotionDTO)))
            .andExpect(status().isBadRequest());

        List<Promotion> promotionList = promotionRepository.findAll();
        assertThat(promotionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDesktopImageUrlIsRequired() throws Exception {
        int databaseSizeBeforeTest = promotionRepository.findAll().size();
        // set the field null
        promotion.setDesktopImageUrl(null);

        // Create the Promotion, which fails.
        PromotionDTO promotionDTO = promotionMapper.toDto(promotion);

        restPromotionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(promotionDTO)))
            .andExpect(status().isBadRequest());

        List<Promotion> promotionList = promotionRepository.findAll();
        assertThat(promotionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTabletImageUrlIsRequired() throws Exception {
        int databaseSizeBeforeTest = promotionRepository.findAll().size();
        // set the field null
        promotion.setTabletImageUrl(null);

        // Create the Promotion, which fails.
        PromotionDTO promotionDTO = promotionMapper.toDto(promotion);

        restPromotionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(promotionDTO)))
            .andExpect(status().isBadRequest());

        List<Promotion> promotionList = promotionRepository.findAll();
        assertThat(promotionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkMobileImageUrlIsRequired() throws Exception {
        int databaseSizeBeforeTest = promotionRepository.findAll().size();
        // set the field null
        promotion.setMobileImageUrl(null);

        // Create the Promotion, which fails.
        PromotionDTO promotionDTO = promotionMapper.toDto(promotion);

        restPromotionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(promotionDTO)))
            .andExpect(status().isBadRequest());

        List<Promotion> promotionList = promotionRepository.findAll();
        assertThat(promotionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTitleIsRequired() throws Exception {
        int databaseSizeBeforeTest = promotionRepository.findAll().size();
        // set the field null
        promotion.setTitle(null);

        // Create the Promotion, which fails.
        PromotionDTO promotionDTO = promotionMapper.toDto(promotion);

        restPromotionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(promotionDTO)))
            .andExpect(status().isBadRequest());

        List<Promotion> promotionList = promotionRepository.findAll();
        assertThat(promotionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = promotionRepository.findAll().size();
        // set the field null
        promotion.setDescription(null);

        // Create the Promotion, which fails.
        PromotionDTO promotionDTO = promotionMapper.toDto(promotion);

        restPromotionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(promotionDTO)))
            .andExpect(status().isBadRequest());

        List<Promotion> promotionList = promotionRepository.findAll();
        assertThat(promotionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkLandingUrlIsRequired() throws Exception {
        int databaseSizeBeforeTest = promotionRepository.findAll().size();
        // set the field null
        promotion.setLandingUrl(null);

        // Create the Promotion, which fails.
        PromotionDTO promotionDTO = promotionMapper.toDto(promotion);

        restPromotionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(promotionDTO)))
            .andExpect(status().isBadRequest());

        List<Promotion> promotionList = promotionRepository.findAll();
        assertThat(promotionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPositionIsRequired() throws Exception {
        int databaseSizeBeforeTest = promotionRepository.findAll().size();
        // set the field null
        promotion.setPosition(null);

        // Create the Promotion, which fails.
        PromotionDTO promotionDTO = promotionMapper.toDto(promotion);

        restPromotionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(promotionDTO)))
            .andExpect(status().isBadRequest());

        List<Promotion> promotionList = promotionRepository.findAll();
        assertThat(promotionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkIsEnabledIsRequired() throws Exception {
        int databaseSizeBeforeTest = promotionRepository.findAll().size();
        // set the field null
        promotion.setIsEnabled(null);

        // Create the Promotion, which fails.
        PromotionDTO promotionDTO = promotionMapper.toDto(promotion);

        restPromotionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(promotionDTO)))
            .andExpect(status().isBadRequest());

        List<Promotion> promotionList = promotionRepository.findAll();
        assertThat(promotionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkStartDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = promotionRepository.findAll().size();
        // set the field null
        promotion.setStartDate(null);

        // Create the Promotion, which fails.
        PromotionDTO promotionDTO = promotionMapper.toDto(promotion);

        restPromotionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(promotionDTO)))
            .andExpect(status().isBadRequest());

        List<Promotion> promotionList = promotionRepository.findAll();
        assertThat(promotionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkEndDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = promotionRepository.findAll().size();
        // set the field null
        promotion.setEndDate(null);

        // Create the Promotion, which fails.
        PromotionDTO promotionDTO = promotionMapper.toDto(promotion);

        restPromotionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(promotionDTO)))
            .andExpect(status().isBadRequest());

        List<Promotion> promotionList = promotionRepository.findAll();
        assertThat(promotionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = promotionRepository.findAll().size();
        // set the field null
        promotion.setCreatedBy(null);

        // Create the Promotion, which fails.
        PromotionDTO promotionDTO = promotionMapper.toDto(promotion);

        restPromotionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(promotionDTO)))
            .andExpect(status().isBadRequest());

        List<Promotion> promotionList = promotionRepository.findAll();
        assertThat(promotionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = promotionRepository.findAll().size();
        // set the field null
        promotion.setCreatedDate(null);

        // Create the Promotion, which fails.
        PromotionDTO promotionDTO = promotionMapper.toDto(promotion);

        restPromotionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(promotionDTO)))
            .andExpect(status().isBadRequest());

        List<Promotion> promotionList = promotionRepository.findAll();
        assertThat(promotionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUpdatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = promotionRepository.findAll().size();
        // set the field null
        promotion.setUpdatedBy(null);

        // Create the Promotion, which fails.
        PromotionDTO promotionDTO = promotionMapper.toDto(promotion);

        restPromotionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(promotionDTO)))
            .andExpect(status().isBadRequest());

        List<Promotion> promotionList = promotionRepository.findAll();
        assertThat(promotionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUpdatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = promotionRepository.findAll().size();
        // set the field null
        promotion.setUpdatedDate(null);

        // Create the Promotion, which fails.
        PromotionDTO promotionDTO = promotionMapper.toDto(promotion);

        restPromotionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(promotionDTO)))
            .andExpect(status().isBadRequest());

        List<Promotion> promotionList = promotionRepository.findAll();
        assertThat(promotionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllPromotions() throws Exception {
        // Initialize the database
        promotionRepository.saveAndFlush(promotion);

        // Get all the promotionList
        restPromotionMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(promotion.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].urlName").value(hasItem(DEFAULT_URL_NAME)))
            .andExpect(jsonPath("$.[*].desktopImageUrl").value(hasItem(DEFAULT_DESKTOP_IMAGE_URL)))
            .andExpect(jsonPath("$.[*].tabletImageUrl").value(hasItem(DEFAULT_TABLET_IMAGE_URL)))
            .andExpect(jsonPath("$.[*].mobileImageUrl").value(hasItem(DEFAULT_MOBILE_IMAGE_URL)))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].tnc").value(hasItem(DEFAULT_TNC)))
            .andExpect(jsonPath("$.[*].tncLink").value(hasItem(DEFAULT_TNC_LINK)))
            .andExpect(jsonPath("$.[*].landingUrl").value(hasItem(DEFAULT_LANDING_URL)))
            .andExpect(jsonPath("$.[*].position").value(hasItem(DEFAULT_POSITION)))
            .andExpect(jsonPath("$.[*].isEnabled").value(hasItem(DEFAULT_IS_ENABLED.booleanValue())))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())));
    }

    @Test
    @Transactional
    void getPromotion() throws Exception {
        // Initialize the database
        promotionRepository.saveAndFlush(promotion);

        // Get the promotion
        restPromotionMockMvc
            .perform(get(ENTITY_API_URL_ID, promotion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(promotion.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.urlName").value(DEFAULT_URL_NAME))
            .andExpect(jsonPath("$.desktopImageUrl").value(DEFAULT_DESKTOP_IMAGE_URL))
            .andExpect(jsonPath("$.tabletImageUrl").value(DEFAULT_TABLET_IMAGE_URL))
            .andExpect(jsonPath("$.mobileImageUrl").value(DEFAULT_MOBILE_IMAGE_URL))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.tnc").value(DEFAULT_TNC))
            .andExpect(jsonPath("$.tncLink").value(DEFAULT_TNC_LINK))
            .andExpect(jsonPath("$.landingUrl").value(DEFAULT_LANDING_URL))
            .andExpect(jsonPath("$.position").value(DEFAULT_POSITION))
            .andExpect(jsonPath("$.isEnabled").value(DEFAULT_IS_ENABLED.booleanValue()))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()));
    }

    @Test
    @Transactional
    void getNonExistingPromotion() throws Exception {
        // Get the promotion
        restPromotionMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewPromotion() throws Exception {
        // Initialize the database
        promotionRepository.saveAndFlush(promotion);

        int databaseSizeBeforeUpdate = promotionRepository.findAll().size();

        // Update the promotion
        Promotion updatedPromotion = promotionRepository.findById(promotion.getId()).get();
        // Disconnect from session so that the updates on updatedPromotion are not directly saved in db
        em.detach(updatedPromotion);
        updatedPromotion
            .name(UPDATED_NAME)
            .urlName(UPDATED_URL_NAME)
            .desktopImageUrl(UPDATED_DESKTOP_IMAGE_URL)
            .tabletImageUrl(UPDATED_TABLET_IMAGE_URL)
            .mobileImageUrl(UPDATED_MOBILE_IMAGE_URL)
            .title(UPDATED_TITLE)
            .description(UPDATED_DESCRIPTION)
            .tnc(UPDATED_TNC)
            .tncLink(UPDATED_TNC_LINK)
            .landingUrl(UPDATED_LANDING_URL)
            .position(UPDATED_POSITION)
            .isEnabled(UPDATED_IS_ENABLED)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedDate(UPDATED_UPDATED_DATE);
        PromotionDTO promotionDTO = promotionMapper.toDto(updatedPromotion);

        restPromotionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, promotionDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(promotionDTO))
            )
            .andExpect(status().isOk());

        // Validate the Promotion in the database
        List<Promotion> promotionList = promotionRepository.findAll();
        assertThat(promotionList).hasSize(databaseSizeBeforeUpdate);
        Promotion testPromotion = promotionList.get(promotionList.size() - 1);
        assertThat(testPromotion.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testPromotion.getUrlName()).isEqualTo(UPDATED_URL_NAME);
        assertThat(testPromotion.getDesktopImageUrl()).isEqualTo(UPDATED_DESKTOP_IMAGE_URL);
        assertThat(testPromotion.getTabletImageUrl()).isEqualTo(UPDATED_TABLET_IMAGE_URL);
        assertThat(testPromotion.getMobileImageUrl()).isEqualTo(UPDATED_MOBILE_IMAGE_URL);
        assertThat(testPromotion.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testPromotion.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testPromotion.getTnc()).isEqualTo(UPDATED_TNC);
        assertThat(testPromotion.getTncLink()).isEqualTo(UPDATED_TNC_LINK);
        assertThat(testPromotion.getLandingUrl()).isEqualTo(UPDATED_LANDING_URL);
        assertThat(testPromotion.getPosition()).isEqualTo(UPDATED_POSITION);
        assertThat(testPromotion.getIsEnabled()).isEqualTo(UPDATED_IS_ENABLED);
        assertThat(testPromotion.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testPromotion.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testPromotion.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testPromotion.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testPromotion.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        assertThat(testPromotion.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
    }

    @Test
    @Transactional
    void putNonExistingPromotion() throws Exception {
        int databaseSizeBeforeUpdate = promotionRepository.findAll().size();
        promotion.setId(count.incrementAndGet());

        // Create the Promotion
        PromotionDTO promotionDTO = promotionMapper.toDto(promotion);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPromotionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, promotionDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(promotionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Promotion in the database
        List<Promotion> promotionList = promotionRepository.findAll();
        assertThat(promotionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPromotion() throws Exception {
        int databaseSizeBeforeUpdate = promotionRepository.findAll().size();
        promotion.setId(count.incrementAndGet());

        // Create the Promotion
        PromotionDTO promotionDTO = promotionMapper.toDto(promotion);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPromotionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(promotionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Promotion in the database
        List<Promotion> promotionList = promotionRepository.findAll();
        assertThat(promotionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPromotion() throws Exception {
        int databaseSizeBeforeUpdate = promotionRepository.findAll().size();
        promotion.setId(count.incrementAndGet());

        // Create the Promotion
        PromotionDTO promotionDTO = promotionMapper.toDto(promotion);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPromotionMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(promotionDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Promotion in the database
        List<Promotion> promotionList = promotionRepository.findAll();
        assertThat(promotionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePromotionWithPatch() throws Exception {
        // Initialize the database
        promotionRepository.saveAndFlush(promotion);

        int databaseSizeBeforeUpdate = promotionRepository.findAll().size();

        // Update the promotion using partial update
        Promotion partialUpdatedPromotion = new Promotion();
        partialUpdatedPromotion.setId(promotion.getId());

        partialUpdatedPromotion
            .urlName(UPDATED_URL_NAME)
            .desktopImageUrl(UPDATED_DESKTOP_IMAGE_URL)
            .tabletImageUrl(UPDATED_TABLET_IMAGE_URL)
            .mobileImageUrl(UPDATED_MOBILE_IMAGE_URL)
            .title(UPDATED_TITLE)
            .description(UPDATED_DESCRIPTION)
            .tnc(UPDATED_TNC)
            .landingUrl(UPDATED_LANDING_URL)
            .position(UPDATED_POSITION)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE);

        restPromotionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPromotion.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPromotion))
            )
            .andExpect(status().isOk());

        // Validate the Promotion in the database
        List<Promotion> promotionList = promotionRepository.findAll();
        assertThat(promotionList).hasSize(databaseSizeBeforeUpdate);
        Promotion testPromotion = promotionList.get(promotionList.size() - 1);
        assertThat(testPromotion.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testPromotion.getUrlName()).isEqualTo(UPDATED_URL_NAME);
        assertThat(testPromotion.getDesktopImageUrl()).isEqualTo(UPDATED_DESKTOP_IMAGE_URL);
        assertThat(testPromotion.getTabletImageUrl()).isEqualTo(UPDATED_TABLET_IMAGE_URL);
        assertThat(testPromotion.getMobileImageUrl()).isEqualTo(UPDATED_MOBILE_IMAGE_URL);
        assertThat(testPromotion.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testPromotion.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testPromotion.getTnc()).isEqualTo(UPDATED_TNC);
        assertThat(testPromotion.getTncLink()).isEqualTo(DEFAULT_TNC_LINK);
        assertThat(testPromotion.getLandingUrl()).isEqualTo(UPDATED_LANDING_URL);
        assertThat(testPromotion.getPosition()).isEqualTo(UPDATED_POSITION);
        assertThat(testPromotion.getIsEnabled()).isEqualTo(DEFAULT_IS_ENABLED);
        assertThat(testPromotion.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testPromotion.getEndDate()).isEqualTo(DEFAULT_END_DATE);
        assertThat(testPromotion.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testPromotion.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testPromotion.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
        assertThat(testPromotion.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
    }

    @Test
    @Transactional
    void fullUpdatePromotionWithPatch() throws Exception {
        // Initialize the database
        promotionRepository.saveAndFlush(promotion);

        int databaseSizeBeforeUpdate = promotionRepository.findAll().size();

        // Update the promotion using partial update
        Promotion partialUpdatedPromotion = new Promotion();
        partialUpdatedPromotion.setId(promotion.getId());

        partialUpdatedPromotion
            .name(UPDATED_NAME)
            .urlName(UPDATED_URL_NAME)
            .desktopImageUrl(UPDATED_DESKTOP_IMAGE_URL)
            .tabletImageUrl(UPDATED_TABLET_IMAGE_URL)
            .mobileImageUrl(UPDATED_MOBILE_IMAGE_URL)
            .title(UPDATED_TITLE)
            .description(UPDATED_DESCRIPTION)
            .tnc(UPDATED_TNC)
            .tncLink(UPDATED_TNC_LINK)
            .landingUrl(UPDATED_LANDING_URL)
            .position(UPDATED_POSITION)
            .isEnabled(UPDATED_IS_ENABLED)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedDate(UPDATED_UPDATED_DATE);

        restPromotionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPromotion.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPromotion))
            )
            .andExpect(status().isOk());

        // Validate the Promotion in the database
        List<Promotion> promotionList = promotionRepository.findAll();
        assertThat(promotionList).hasSize(databaseSizeBeforeUpdate);
        Promotion testPromotion = promotionList.get(promotionList.size() - 1);
        assertThat(testPromotion.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testPromotion.getUrlName()).isEqualTo(UPDATED_URL_NAME);
        assertThat(testPromotion.getDesktopImageUrl()).isEqualTo(UPDATED_DESKTOP_IMAGE_URL);
        assertThat(testPromotion.getTabletImageUrl()).isEqualTo(UPDATED_TABLET_IMAGE_URL);
        assertThat(testPromotion.getMobileImageUrl()).isEqualTo(UPDATED_MOBILE_IMAGE_URL);
        assertThat(testPromotion.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testPromotion.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testPromotion.getTnc()).isEqualTo(UPDATED_TNC);
        assertThat(testPromotion.getTncLink()).isEqualTo(UPDATED_TNC_LINK);
        assertThat(testPromotion.getLandingUrl()).isEqualTo(UPDATED_LANDING_URL);
        assertThat(testPromotion.getPosition()).isEqualTo(UPDATED_POSITION);
        assertThat(testPromotion.getIsEnabled()).isEqualTo(UPDATED_IS_ENABLED);
        assertThat(testPromotion.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testPromotion.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testPromotion.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testPromotion.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testPromotion.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        assertThat(testPromotion.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
    }

    @Test
    @Transactional
    void patchNonExistingPromotion() throws Exception {
        int databaseSizeBeforeUpdate = promotionRepository.findAll().size();
        promotion.setId(count.incrementAndGet());

        // Create the Promotion
        PromotionDTO promotionDTO = promotionMapper.toDto(promotion);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPromotionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, promotionDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(promotionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Promotion in the database
        List<Promotion> promotionList = promotionRepository.findAll();
        assertThat(promotionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPromotion() throws Exception {
        int databaseSizeBeforeUpdate = promotionRepository.findAll().size();
        promotion.setId(count.incrementAndGet());

        // Create the Promotion
        PromotionDTO promotionDTO = promotionMapper.toDto(promotion);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPromotionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(promotionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Promotion in the database
        List<Promotion> promotionList = promotionRepository.findAll();
        assertThat(promotionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPromotion() throws Exception {
        int databaseSizeBeforeUpdate = promotionRepository.findAll().size();
        promotion.setId(count.incrementAndGet());

        // Create the Promotion
        PromotionDTO promotionDTO = promotionMapper.toDto(promotion);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPromotionMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(promotionDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Promotion in the database
        List<Promotion> promotionList = promotionRepository.findAll();
        assertThat(promotionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePromotion() throws Exception {
        // Initialize the database
        promotionRepository.saveAndFlush(promotion);

        int databaseSizeBeforeDelete = promotionRepository.findAll().size();

        // Delete the promotion
        restPromotionMockMvc
            .perform(delete(ENTITY_API_URL_ID, promotion.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Promotion> promotionList = promotionRepository.findAll();
        assertThat(promotionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
