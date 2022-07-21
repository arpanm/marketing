package com.reliance.retail.jmd.mkg.service.mapper;

import com.reliance.retail.jmd.mkg.domain.FieldMetaData;
import com.reliance.retail.jmd.mkg.domain.FormMetaData;
import com.reliance.retail.jmd.mkg.service.dto.FieldMetaDataDTO;
import com.reliance.retail.jmd.mkg.service.dto.FormMetaDataDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link FieldMetaData} and its DTO {@link FieldMetaDataDTO}.
 */
@Mapper(componentModel = "spring")
public interface FieldMetaDataMapper extends EntityMapper<FieldMetaDataDTO, FieldMetaData> {
    @Mapping(target = "form", source = "form", qualifiedByName = "formMetaDataId")
    FieldMetaDataDTO toDto(FieldMetaData s);

    @Named("formMetaDataId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    FormMetaDataDTO toDtoFormMetaDataId(FormMetaData formMetaData);
}
