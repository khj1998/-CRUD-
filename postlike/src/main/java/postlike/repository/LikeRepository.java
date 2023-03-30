package postlike.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import postlike.domain.PostLike;

public interface LikeRepository extends JpaRepository<PostLike,Long> {
}
