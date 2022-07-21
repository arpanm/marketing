package com.reliance.retail.jmd.mkg.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.reliance.retail.jmd.mkg.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class FormSubmissionDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FormSubmissionDTO.class);
        FormSubmissionDTO formSubmissionDTO1 = new FormSubmissionDTO();
        formSubmissionDTO1.setId(1L);
        FormSubmissionDTO formSubmissionDTO2 = new FormSubmissionDTO();
        assertThat(formSubmissionDTO1).isNotEqualTo(formSubmissionDTO2);
        formSubmissionDTO2.setId(formSubmissionDTO1.getId());
        assertThat(formSubmissionDTO1).isEqualTo(formSubmissionDTO2);
        formSubmissionDTO2.setId(2L);
        assertThat(formSubmissionDTO1).isNotEqualTo(formSubmissionDTO2);
        formSubmissionDTO1.setId(null);
        assertThat(formSubmissionDTO1).isNotEqualTo(formSubmissionDTO2);
    }
}
