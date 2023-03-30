package postlike.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import postlike.domain.Like;

public interface LikeRepository extends JpaRepository<Like,Long> {
}
