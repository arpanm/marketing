package com.reliance.retail.jmd.mkg.repository;

import com.reliance.retail.jmd.mkg.domain.FormMetaData;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the FormMetaData entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FormMetaDataRepository extends JpaRepository<FormMetaData, Long> {}
