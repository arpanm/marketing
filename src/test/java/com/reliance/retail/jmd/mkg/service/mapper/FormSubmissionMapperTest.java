package com.reliance.retail.jmd.mkg.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FormSubmissionMapperTest {

    private FormSubmissionMapper formSubmissionMapper;

    @BeforeEach
    public void setUp() {
        formSubmissionMapper = new FormSubmissionMapperImpl();
    }
}
