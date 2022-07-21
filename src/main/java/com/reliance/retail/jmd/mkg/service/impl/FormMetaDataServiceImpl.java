package com.reliance.retail.jmd.mkg.service.impl;

import com.reliance.retail.jmd.mkg.domain.FormMetaData;
import com.reliance.retail.jmd.mkg.repository.FormMetaDataRepository;
import com.reliance.retail.jmd.mkg.service.FormMetaDataService;
import com.reliance.retail.jmd.mkg.service.dto.FormMetaDataDTO;
import com.reliance.retail.jmd.mkg.service.mapper.FormMetaDataMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link FormMetaData}.
 */
@Service
@Transactional
public class FormMetaDataServiceImpl implements FormMetaDataService {

    private final Logger log = LoggerFactory.getLogger(FormMetaDataServiceImpl.class);

    private final FormMetaDataRepository formMetaDataRepository;

    private final FormMetaDataMapper formMetaDataMapper;

    public FormMetaDataServiceImpl(FormMetaDataRepository formMetaDataRepository, FormMetaDataMapper formMetaDataMapper) {
        this.formMetaDataRepository = formMetaDataRepository;
        this.formMetaDataMapper = formMetaDataMapper;
    }

    @Override
    public FormMetaDataDTO save(FormMetaDataDTO formMetaDataDTO) {
        log.debug("Request to save FormMetaData : {}", formMetaDataDTO);
        FormMetaData formMetaData = formMetaDataMapper.toEntity(formMetaDataDTO);
        formMetaData = formMetaDataRepository.save(formMetaData);
        return formMetaDataMapper.toDto(formMetaData);
    }

    @Override
    public FormMetaDataDTO update(FormMetaDataDTO formMetaDataDTO) {
        log.debug("Request to save FormMetaData : {}", formMetaDataDTO);
        FormMetaData formMetaData = formMetaDataMapper.toEntity(formMetaDataDTO);
        formMetaData = formMetaDataRepository.save(formMetaData);
        return formMetaDataMapper.toDto(formMetaData);
    }

    @Override
    public Optional<FormMetaDataDTO> partialUpdate(FormMetaDataDTO formMetaDataDTO) {
        log.debug("Request to partially update FormMetaData : {}", formMetaDataDTO);

        return formMetaDataRepository
            .findById(formMetaDataDTO.getId())
            .map(existingFormMetaData -> {
                formMetaDataMapper.partialUpdate(existingFormMetaData, formMetaDataDTO);

                return existingFormMetaData;
            })
            .map(formMetaDataRepository::save)
            .map(formMetaDataMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<FormMetaDataDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FormMetaData");
        return formMetaDataRepository.findAll(pageable).map(formMetaDataMapper::toDto);
    }

    /**
     *  Get all the formMetaData where Promotion is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<FormMetaDataDTO> findAllWherePromotionIsNull() {
        log.debug("Request to get all formMetaData where Promotion is null");
        return StreamSupport
            .stream(formMetaDataRepository.findAll().spliterator(), false)
            .filter(formMetaData -> formMetaData.getPromotion() == null)
            .map(formMetaDataMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<FormMetaDataDTO> findOne(Long id) {
        log.debug("Request to get FormMetaData : {}", id);
        return formMetaDataRepository.findById(id).map(formMetaDataMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete FormMetaData : {}", id);
        formMetaDataRepository.deleteById(id);
    }
}
