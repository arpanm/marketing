package com.reliance.retail.jmd.mkg.service.mapper;

import com.reliance.retail.jmd.mkg.domain.FormMetaData;
import com.reliance.retail.jmd.mkg.domain.FormSubmission;
import com.reliance.retail.jmd.mkg.service.dto.FormMetaDataDTO;
import com.reliance.retail.jmd.mkg.service.dto.FormSubmissionDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link FormSubmission} and its DTO {@link FormSubmissionDTO}.
 */
@Mapper(componentModel = "spring")
public interface FormSubmissionMapper extends EntityMapper<FormSubmissionDTO, FormSubmission> {
    @Mapping(target = "formKey", source = "formKey", qualifiedByName = "formMetaDataId")
    FormSubmissionDTO toDto(FormSubmission s);

    @Named("formMetaDataId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    FormMetaDataDTO toDtoFormMetaDataId(FormMetaData formMetaData);
}
