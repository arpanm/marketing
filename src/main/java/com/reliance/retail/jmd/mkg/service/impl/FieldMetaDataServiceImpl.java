package com.reliance.retail.jmd.mkg.service.impl;

import com.reliance.retail.jmd.mkg.domain.FieldMetaData;
import com.reliance.retail.jmd.mkg.repository.FieldMetaDataRepository;
import com.reliance.retail.jmd.mkg.service.FieldMetaDataService;
import com.reliance.retail.jmd.mkg.service.dto.FieldMetaDataDTO;
import com.reliance.retail.jmd.mkg.service.mapper.FieldMetaDataMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link FieldMetaData}.
 */
@Service
@Transactional
public class FieldMetaDataServiceImpl implements FieldMetaDataService {

    private final Logger log = LoggerFactory.getLogger(FieldMetaDataServiceImpl.class);

    private final FieldMetaDataRepository fieldMetaDataRepository;

    private final FieldMetaDataMapper fieldMetaDataMapper;

    public FieldMetaDataServiceImpl(FieldMetaDataRepository fieldMetaDataRepository, FieldMetaDataMapper fieldMetaDataMapper) {
        this.fieldMetaDataRepository = fieldMetaDataRepository;
        this.fieldMetaDataMapper = fieldMetaDataMapper;
    }

    @Override
    public FieldMetaDataDTO save(FieldMetaDataDTO fieldMetaDataDTO) {
        log.debug("Request to save FieldMetaData : {}", fieldMetaDataDTO);
        FieldMetaData fieldMetaData = fieldMetaDataMapper.toEntity(fieldMetaDataDTO);
        fieldMetaData = fieldMetaDataRepository.save(fieldMetaData);
        return fieldMetaDataMapper.toDto(fieldMetaData);
    }

    @Override
    public FieldMetaDataDTO update(FieldMetaDataDTO fieldMetaDataDTO) {
        log.debug("Request to save FieldMetaData : {}", fieldMetaDataDTO);
        FieldMetaData fieldMetaData = fieldMetaDataMapper.toEntity(fieldMetaDataDTO);
        fieldMetaData = fieldMetaDataRepository.save(fieldMetaData);
        return fieldMetaDataMapper.toDto(fieldMetaData);
    }

    @Override
    public Optional<FieldMetaDataDTO> partialUpdate(FieldMetaDataDTO fieldMetaDataDTO) {
        log.debug("Request to partially update FieldMetaData : {}", fieldMetaDataDTO);

        return fieldMetaDataRepository
            .findById(fieldMetaDataDTO.getId())
            .map(existingFieldMetaData -> {
                fieldMetaDataMapper.partialUpdate(existingFieldMetaData, fieldMetaDataDTO);

                return existingFieldMetaData;
            })
            .map(fieldMetaDataRepository::save)
            .map(fieldMetaDataMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<FieldMetaDataDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FieldMetaData");
        return fieldMetaDataRepository.findAll(pageable).map(fieldMetaDataMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<FieldMetaDataDTO> findOne(Long id) {
        log.debug("Request to get FieldMetaData : {}", id);
        return fieldMetaDataRepository.findById(id).map(fieldMetaDataMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete FieldMetaData : {}", id);
        fieldMetaDataRepository.deleteById(id);
    }
}
