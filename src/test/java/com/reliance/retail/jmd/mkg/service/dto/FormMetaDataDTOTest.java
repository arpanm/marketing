package com.reliance.retail.jmd.mkg.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.reliance.retail.jmd.mkg.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class FormMetaDataDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FormMetaDataDTO.class);
        FormMetaDataDTO formMetaDataDTO1 = new FormMetaDataDTO();
        formMetaDataDTO1.setId(1L);
        FormMetaDataDTO formMetaDataDTO2 = new FormMetaDataDTO();
        assertThat(formMetaDataDTO1).isNotEqualTo(formMetaDataDTO2);
        formMetaDataDTO2.setId(formMetaDataDTO1.getId());
        assertThat(formMetaDataDTO1).isEqualTo(formMetaDataDTO2);
        formMetaDataDTO2.setId(2L);
        assertThat(formMetaDataDTO1).isNotEqualTo(formMetaDataDTO2);
        formMetaDataDTO1.setId(null);
        assertThat(formMetaDataDTO1).isNotEqualTo(formMetaDataDTO2);
    }
}
