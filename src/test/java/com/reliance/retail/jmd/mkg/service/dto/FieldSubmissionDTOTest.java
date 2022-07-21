package com.reliance.retail.jmd.mkg.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.reliance.retail.jmd.mkg.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class FieldSubmissionDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FieldSubmissionDTO.class);
        FieldSubmissionDTO fieldSubmissionDTO1 = new FieldSubmissionDTO();
        fieldSubmissionDTO1.setId(1L);
        FieldSubmissionDTO fieldSubmissionDTO2 = new FieldSubmissionDTO();
        assertThat(fieldSubmissionDTO1).isNotEqualTo(fieldSubmissionDTO2);
        fieldSubmissionDTO2.setId(fieldSubmissionDTO1.getId());
        assertThat(fieldSubmissionDTO1).isEqualTo(fieldSubmissionDTO2);
        fieldSubmissionDTO2.setId(2L);
        assertThat(fieldSubmissionDTO1).isNotEqualTo(fieldSubmissionDTO2);
        fieldSubmissionDTO1.setId(null);
        assertThat(fieldSubmissionDTO1).isNotEqualTo(fieldSubmissionDTO2);
    }
}
