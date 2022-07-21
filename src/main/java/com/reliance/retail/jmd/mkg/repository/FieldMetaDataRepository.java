package com.reliance.retail.jmd.mkg.repository;

import com.reliance.retail.jmd.mkg.domain.FieldMetaData;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the FieldMetaData entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FieldMetaDataRepository extends JpaRepository<FieldMetaData, Long> {}
