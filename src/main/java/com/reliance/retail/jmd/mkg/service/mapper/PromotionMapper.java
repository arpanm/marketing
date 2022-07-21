package com.reliance.retail.jmd.mkg.service.mapper;

import com.reliance.retail.jmd.mkg.domain.FormMetaData;
import com.reliance.retail.jmd.mkg.domain.Promotion;
import com.reliance.retail.jmd.mkg.service.dto.FormMetaDataDTO;
import com.reliance.retail.jmd.mkg.service.dto.PromotionDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Promotion} and its DTO {@link PromotionDTO}.
 */
@Mapper(componentModel = "spring")
public interface PromotionMapper extends EntityMapper<PromotionDTO, Promotion> {
    @Mapping(target = "form", source = "form", qualifiedByName = "formMetaDataId")
    PromotionDTO toDto(Promotion s);

    @Named("formMetaDataId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    FormMetaDataDTO toDtoFormMetaDataId(FormMetaData formMetaData);
}
