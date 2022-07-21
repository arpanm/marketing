package com.reliance.retail.jmd.mkg.service.impl;

import com.reliance.retail.jmd.mkg.domain.Option;
import com.reliance.retail.jmd.mkg.repository.OptionRepository;
import com.reliance.retail.jmd.mkg.service.OptionService;
import com.reliance.retail.jmd.mkg.service.dto.OptionDTO;
import com.reliance.retail.jmd.mkg.service.mapper.OptionMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Option}.
 */
@Service
@Transactional
public class OptionServiceImpl implements OptionService {

    private final Logger log = LoggerFactory.getLogger(OptionServiceImpl.class);

    private final OptionRepository optionRepository;

    private final OptionMapper optionMapper;

    public OptionServiceImpl(OptionRepository optionRepository, OptionMapper optionMapper) {
        this.optionRepository = optionRepository;
        this.optionMapper = optionMapper;
    }

    @Override
    public OptionDTO save(OptionDTO optionDTO) {
        log.debug("Request to save Option : {}", optionDTO);
        Option option = optionMapper.toEntity(optionDTO);
        option = optionRepository.save(option);
        return optionMapper.toDto(option);
    }

    @Override
    public OptionDTO update(OptionDTO optionDTO) {
        log.debug("Request to save Option : {}", optionDTO);
        Option option = optionMapper.toEntity(optionDTO);
        option = optionRepository.save(option);
        return optionMapper.toDto(option);
    }

    @Override
    public Optional<OptionDTO> partialUpdate(OptionDTO optionDTO) {
        log.debug("Request to partially update Option : {}", optionDTO);

        return optionRepository
            .findById(optionDTO.getId())
            .map(existingOption -> {
                optionMapper.partialUpdate(existingOption, optionDTO);

                return existingOption;
            })
            .map(optionRepository::save)
            .map(optionMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<OptionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Options");
        return optionRepository.findAll(pageable).map(optionMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<OptionDTO> findOne(Long id) {
        log.debug("Request to get Option : {}", id);
        return optionRepository.findById(id).map(optionMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Option : {}", id);
        optionRepository.deleteById(id);
    }
}
