package com.reliance.retail.jmd.mkg.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PromotionMapperTest {

    private PromotionMapper promotionMapper;

    @BeforeEach
    public void setUp() {
        promotionMapper = new PromotionMapperImpl();
    }
}
