package boguslavskii.leonid.carrouselcustomercounter.repository;

import boguslavskii.leonid.carrouselcustomercounter.entities.RecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordRepository extends JpaRepository<RecordEntity, Long> {
}
