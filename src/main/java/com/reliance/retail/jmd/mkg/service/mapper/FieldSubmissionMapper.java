package com.reliance.retail.jmd.mkg.service.mapper;

import com.reliance.retail.jmd.mkg.domain.FieldMetaData;
import com.reliance.retail.jmd.mkg.domain.FieldSubmission;
import com.reliance.retail.jmd.mkg.domain.FormSubmission;
import com.reliance.retail.jmd.mkg.service.dto.FieldMetaDataDTO;
import com.reliance.retail.jmd.mkg.service.dto.FieldSubmissionDTO;
import com.reliance.retail.jmd.mkg.service.dto.FormSubmissionDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link FieldSubmission} and its DTO {@link FieldSubmissionDTO}.
 */
@Mapper(componentModel = "spring")
public interface FieldSubmissionMapper extends EntityMapper<FieldSubmissionDTO, FieldSubmission> {
    @Mapping(target = "fieldKey", source = "fieldKey", qualifiedByName = "fieldMetaDataId")
    @Mapping(target = "formSub", source = "formSub", qualifiedByName = "formSubmissionId")
    FieldSubmissionDTO toDto(FieldSubmission s);

    @Named("fieldMetaDataId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    FieldMetaDataDTO toDtoFieldMetaDataId(FieldMetaData fieldMetaData);

    @Named("formSubmissionId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    FormSubmissionDTO toDtoFormSubmissionId(FormSubmission formSubmission);
}
