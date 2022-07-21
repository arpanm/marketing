package com.reliance.retail.jmd.mkg.repository;

import com.reliance.retail.jmd.mkg.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the {@link Authority} entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {}
