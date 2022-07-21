package com.reliance.retail.jmd.mkg.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.reliance.retail.jmd.mkg.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class FieldSubmissionTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FieldSubmission.class);
        FieldSubmission fieldSubmission1 = new FieldSubmission();
        fieldSubmission1.setId(1L);
        FieldSubmission fieldSubmission2 = new FieldSubmission();
        fieldSubmission2.setId(fieldSubmission1.getId());
        assertThat(fieldSubmission1).isEqualTo(fieldSubmission2);
        fieldSubmission2.setId(2L);
        assertThat(fieldSubmission1).isNotEqualTo(fieldSubmission2);
        fieldSubmission1.setId(null);
        assertThat(fieldSubmission1).isNotEqualTo(fieldSubmission2);
    }
}
