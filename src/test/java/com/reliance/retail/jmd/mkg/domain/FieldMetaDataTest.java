package com.reliance.retail.jmd.mkg.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.reliance.retail.jmd.mkg.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class FieldMetaDataTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FieldMetaData.class);
        FieldMetaData fieldMetaData1 = new FieldMetaData();
        fieldMetaData1.setId(1L);
        FieldMetaData fieldMetaData2 = new FieldMetaData();
        fieldMetaData2.setId(fieldMetaData1.getId());
        assertThat(fieldMetaData1).isEqualTo(fieldMetaData2);
        fieldMetaData2.setId(2L);
        assertThat(fieldMetaData1).isNotEqualTo(fieldMetaData2);
        fieldMetaData1.setId(null);
        assertThat(fieldMetaData1).isNotEqualTo(fieldMetaData2);
    }
}
