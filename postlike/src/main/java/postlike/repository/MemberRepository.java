package postlike.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import postlike.domain.Member;

public interface MemberRepository extends JpaRepository<Member,Long> {
}
