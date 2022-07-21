package com.reliance.retail.jmd.mkg.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.reliance.retail.jmd.mkg.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class FieldMetaDataDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FieldMetaDataDTO.class);
        FieldMetaDataDTO fieldMetaDataDTO1 = new FieldMetaDataDTO();
        fieldMetaDataDTO1.setId(1L);
        FieldMetaDataDTO fieldMetaDataDTO2 = new FieldMetaDataDTO();
        assertThat(fieldMetaDataDTO1).isNotEqualTo(fieldMetaDataDTO2);
        fieldMetaDataDTO2.setId(fieldMetaDataDTO1.getId());
        assertThat(fieldMetaDataDTO1).isEqualTo(fieldMetaDataDTO2);
        fieldMetaDataDTO2.setId(2L);
        assertThat(fieldMetaDataDTO1).isNotEqualTo(fieldMetaDataDTO2);
        fieldMetaDataDTO1.setId(null);
        assertThat(fieldMetaDataDTO1).isNotEqualTo(fieldMetaDataDTO2);
    }
}
