package com.reliance.retail.jmd.mkg.repository;

import com.reliance.retail.jmd.mkg.domain.FieldSubmission;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the FieldSubmission entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FieldSubmissionRepository extends JpaRepository<FieldSubmission, Long> {}
