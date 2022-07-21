package com.reliance.retail.jmd.mkg.service.impl;

import com.reliance.retail.jmd.mkg.domain.FormSubmission;
import com.reliance.retail.jmd.mkg.repository.FormSubmissionRepository;
import com.reliance.retail.jmd.mkg.service.FormSubmissionService;
import com.reliance.retail.jmd.mkg.service.dto.FormSubmissionDTO;
import com.reliance.retail.jmd.mkg.service.mapper.FormSubmissionMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link FormSubmission}.
 */
@Service
@Transactional
public class FormSubmissionServiceImpl implements FormSubmissionService {

    private final Logger log = LoggerFactory.getLogger(FormSubmissionServiceImpl.class);

    private final FormSubmissionRepository formSubmissionRepository;

    private final FormSubmissionMapper formSubmissionMapper;

    public FormSubmissionServiceImpl(FormSubmissionRepository formSubmissionRepository, FormSubmissionMapper formSubmissionMapper) {
        this.formSubmissionRepository = formSubmissionRepository;
        this.formSubmissionMapper = formSubmissionMapper;
    }

    @Override
    public FormSubmissionDTO save(FormSubmissionDTO formSubmissionDTO) {
        log.debug("Request to save FormSubmission : {}", formSubmissionDTO);
        FormSubmission formSubmission = formSubmissionMapper.toEntity(formSubmissionDTO);
        formSubmission = formSubmissionRepository.save(formSubmission);
        return formSubmissionMapper.toDto(formSubmission);
    }

    @Override
    public FormSubmissionDTO update(FormSubmissionDTO formSubmissionDTO) {
        log.debug("Request to save FormSubmission : {}", formSubmissionDTO);
        FormSubmission formSubmission = formSubmissionMapper.toEntity(formSubmissionDTO);
        formSubmission = formSubmissionRepository.save(formSubmission);
        return formSubmissionMapper.toDto(formSubmission);
    }

    @Override
    public Optional<FormSubmissionDTO> partialUpdate(FormSubmissionDTO formSubmissionDTO) {
        log.debug("Request to partially update FormSubmission : {}", formSubmissionDTO);

        return formSubmissionRepository
            .findById(formSubmissionDTO.getId())
            .map(existingFormSubmission -> {
                formSubmissionMapper.partialUpdate(existingFormSubmission, formSubmissionDTO);

                return existingFormSubmission;
            })
            .map(formSubmissionRepository::save)
            .map(formSubmissionMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<FormSubmissionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FormSubmissions");
        return formSubmissionRepository.findAll(pageable).map(formSubmissionMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<FormSubmissionDTO> findOne(Long id) {
        log.debug("Request to get FormSubmission : {}", id);
        return formSubmissionRepository.findById(id).map(formSubmissionMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete FormSubmission : {}", id);
        formSubmissionRepository.deleteById(id);
    }
}
