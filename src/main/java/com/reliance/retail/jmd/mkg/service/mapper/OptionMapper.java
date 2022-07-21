package com.reliance.retail.jmd.mkg.service.mapper;

import com.reliance.retail.jmd.mkg.domain.FieldMetaData;
import com.reliance.retail.jmd.mkg.domain.Option;
import com.reliance.retail.jmd.mkg.service.dto.FieldMetaDataDTO;
import com.reliance.retail.jmd.mkg.service.dto.OptionDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Option} and its DTO {@link OptionDTO}.
 */
@Mapper(componentModel = "spring")
public interface OptionMapper extends EntityMapper<OptionDTO, Option> {
    @Mapping(target = "field", source = "field", qualifiedByName = "fieldMetaDataId")
    OptionDTO toDto(Option s);

    @Named("fieldMetaDataId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    FieldMetaDataDTO toDtoFieldMetaDataId(FieldMetaData fieldMetaData);
}
