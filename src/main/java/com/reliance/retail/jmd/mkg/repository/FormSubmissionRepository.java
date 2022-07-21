package com.reliance.retail.jmd.mkg.repository;

import com.reliance.retail.jmd.mkg.domain.FormSubmission;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the FormSubmission entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FormSubmissionRepository extends JpaRepository<FormSubmission, Long> {}
