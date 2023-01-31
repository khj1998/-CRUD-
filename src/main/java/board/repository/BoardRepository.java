package board.repository;

import board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board,Integer> {

    List<Board> findByTitle(String title);
    List<Board> findByTitleOrContent(String title,String content);
}
