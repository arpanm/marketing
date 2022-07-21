package com.reliance.retail.jmd.mkg.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FieldMetaDataMapperTest {

    private FieldMetaDataMapper fieldMetaDataMapper;

    @BeforeEach
    public void setUp() {
        fieldMetaDataMapper = new FieldMetaDataMapperImpl();
    }
}
