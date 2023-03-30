package postlike.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import postlike.domain.Post;

public interface PostRepository extends JpaRepository<Post,Long> {
}
