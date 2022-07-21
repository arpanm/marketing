package reliance.retail.jmd.mkg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reliance.retail.jmd.mkg.domain.Authority;

/**
 * Spring Data JPA repository for the {@link Authority} entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {}
