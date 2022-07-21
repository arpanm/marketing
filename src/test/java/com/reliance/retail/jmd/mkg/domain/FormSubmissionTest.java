package com.reliance.retail.jmd.mkg.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.reliance.retail.jmd.mkg.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class FormSubmissionTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FormSubmission.class);
        FormSubmission formSubmission1 = new FormSubmission();
        formSubmission1.setId(1L);
        FormSubmission formSubmission2 = new FormSubmission();
        formSubmission2.setId(formSubmission1.getId());
        assertThat(formSubmission1).isEqualTo(formSubmission2);
        formSubmission2.setId(2L);
        assertThat(formSubmission1).isNotEqualTo(formSubmission2);
        formSubmission1.setId(null);
        assertThat(formSubmission1).isNotEqualTo(formSubmission2);
    }
}
