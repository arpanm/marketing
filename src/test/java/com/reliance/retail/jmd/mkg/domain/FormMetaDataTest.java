package com.reliance.retail.jmd.mkg.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.reliance.retail.jmd.mkg.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class FormMetaDataTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FormMetaData.class);
        FormMetaData formMetaData1 = new FormMetaData();
        formMetaData1.setId(1L);
        FormMetaData formMetaData2 = new FormMetaData();
        formMetaData2.setId(formMetaData1.getId());
        assertThat(formMetaData1).isEqualTo(formMetaData2);
        formMetaData2.setId(2L);
        assertThat(formMetaData1).isNotEqualTo(formMetaData2);
        formMetaData1.setId(null);
        assertThat(formMetaData1).isNotEqualTo(formMetaData2);
    }
}
