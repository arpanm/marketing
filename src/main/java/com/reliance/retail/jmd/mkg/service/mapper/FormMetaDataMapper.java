package com.reliance.retail.jmd.mkg.service.mapper;

import com.reliance.retail.jmd.mkg.domain.FormMetaData;
import com.reliance.retail.jmd.mkg.service.dto.FormMetaDataDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link FormMetaData} and its DTO {@link FormMetaDataDTO}.
 */
@Mapper(componentModel = "spring")
public interface FormMetaDataMapper extends EntityMapper<FormMetaDataDTO, FormMetaData> {}
