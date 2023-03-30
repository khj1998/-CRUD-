package postlike.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import postlike.domain.Category;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
