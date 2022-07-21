package com.reliance.retail.jmd.mkg.service.impl;

import com.reliance.retail.jmd.mkg.domain.FieldSubmission;
import com.reliance.retail.jmd.mkg.repository.FieldSubmissionRepository;
import com.reliance.retail.jmd.mkg.service.FieldSubmissionService;
import com.reliance.retail.jmd.mkg.service.dto.FieldSubmissionDTO;
import com.reliance.retail.jmd.mkg.service.mapper.FieldSubmissionMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link FieldSubmission}.
 */
@Service
@Transactional
public class FieldSubmissionServiceImpl implements FieldSubmissionService {

    private final Logger log = LoggerFactory.getLogger(FieldSubmissionServiceImpl.class);

    private final FieldSubmissionRepository fieldSubmissionRepository;

    private final FieldSubmissionMapper fieldSubmissionMapper;

    public FieldSubmissionServiceImpl(FieldSubmissionRepository fieldSubmissionRepository, FieldSubmissionMapper fieldSubmissionMapper) {
        this.fieldSubmissionRepository = fieldSubmissionRepository;
        this.fieldSubmissionMapper = fieldSubmissionMapper;
    }

    @Override
    public FieldSubmissionDTO save(FieldSubmissionDTO fieldSubmissionDTO) {
        log.debug("Request to save FieldSubmission : {}", fieldSubmissionDTO);
        FieldSubmission fieldSubmission = fieldSubmissionMapper.toEntity(fieldSubmissionDTO);
        fieldSubmission = fieldSubmissionRepository.save(fieldSubmission);
        return fieldSubmissionMapper.toDto(fieldSubmission);
    }

    @Override
    public FieldSubmissionDTO update(FieldSubmissionDTO fieldSubmissionDTO) {
        log.debug("Request to save FieldSubmission : {}", fieldSubmissionDTO);
        FieldSubmission fieldSubmission = fieldSubmissionMapper.toEntity(fieldSubmissionDTO);
        fieldSubmission = fieldSubmissionRepository.save(fieldSubmission);
        return fieldSubmissionMapper.toDto(fieldSubmission);
    }

    @Override
    public Optional<FieldSubmissionDTO> partialUpdate(FieldSubmissionDTO fieldSubmissionDTO) {
        log.debug("Request to partially update FieldSubmission : {}", fieldSubmissionDTO);

        return fieldSubmissionRepository
            .findById(fieldSubmissionDTO.getId())
            .map(existingFieldSubmission -> {
                fieldSubmissionMapper.partialUpdate(existingFieldSubmission, fieldSubmissionDTO);

                return existingFieldSubmission;
            })
            .map(fieldSubmissionRepository::save)
            .map(fieldSubmissionMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<FieldSubmissionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FieldSubmissions");
        return fieldSubmissionRepository.findAll(pageable).map(fieldSubmissionMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<FieldSubmissionDTO> findOne(Long id) {
        log.debug("Request to get FieldSubmission : {}", id);
        return fieldSubmissionRepository.findById(id).map(fieldSubmissionMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete FieldSubmission : {}", id);
        fieldSubmissionRepository.deleteById(id);
    }
}
