package com.reliance.retail.jmd.mkg.service.impl;

import com.reliance.retail.jmd.mkg.domain.Promotion;
import com.reliance.retail.jmd.mkg.repository.PromotionRepository;
import com.reliance.retail.jmd.mkg.service.PromotionService;
import com.reliance.retail.jmd.mkg.service.dto.PromotionDTO;
import com.reliance.retail.jmd.mkg.service.mapper.PromotionMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Promotion}.
 */
@Service
@Transactional
public class PromotionServiceImpl implements PromotionService {

    private final Logger log = LoggerFactory.getLogger(PromotionServiceImpl.class);

    private final PromotionRepository promotionRepository;

    private final PromotionMapper promotionMapper;

    public PromotionServiceImpl(PromotionRepository promotionRepository, PromotionMapper promotionMapper) {
        this.promotionRepository = promotionRepository;
        this.promotionMapper = promotionMapper;
    }

    @Override
    public PromotionDTO save(PromotionDTO promotionDTO) {
        log.debug("Request to save Promotion : {}", promotionDTO);
        Promotion promotion = promotionMapper.toEntity(promotionDTO);
        promotion = promotionRepository.save(promotion);
        return promotionMapper.toDto(promotion);
    }

    @Override
    public PromotionDTO update(PromotionDTO promotionDTO) {
        log.debug("Request to save Promotion : {}", promotionDTO);
        Promotion promotion = promotionMapper.toEntity(promotionDTO);
        promotion = promotionRepository.save(promotion);
        return promotionMapper.toDto(promotion);
    }

    @Override
    public Optional<PromotionDTO> partialUpdate(PromotionDTO promotionDTO) {
        log.debug("Request to partially update Promotion : {}", promotionDTO);

        return promotionRepository
            .findById(promotionDTO.getId())
            .map(existingPromotion -> {
                promotionMapper.partialUpdate(existingPromotion, promotionDTO);

                return existingPromotion;
            })
            .map(promotionRepository::save)
            .map(promotionMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PromotionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Promotions");
        return promotionRepository.findAll(pageable).map(promotionMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PromotionDTO> findOne(Long id) {
        log.debug("Request to get Promotion : {}", id);
        return promotionRepository.findById(id).map(promotionMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Promotion : {}", id);
        promotionRepository.deleteById(id);
    }
}
