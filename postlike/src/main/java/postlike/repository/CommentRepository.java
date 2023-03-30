package postlike.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import postlike.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
