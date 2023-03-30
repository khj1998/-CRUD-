package postlike;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import postlike.domain.Member;
import postlike.domain.Post;
import postlike.repository.LikeRepository;
import postlike.repository.MemberRepository;
import postlike.repository.PostRepository;

import java.util.List;

@SpringBootTest
class PostlikeApplicationTests {
	@Autowired
	MemberRepository memberRepository;
	@Autowired
	PostRepository postRepository;
	@Autowired
	LikeRepository likeRepository;

	@Test
	void addPostTest() {
		Member member1 = new Member();
		member1.setUsername("김호진");
		memberRepository.save(member1);

		Member member2 = new Member();
		member2.setUsername("김수호");
		memberRepository.save(member2);

		Post post1 = new Post();
		post1.setTitle("김호진의 글");
		post1.setMember(member1);
		postRepository.save(post1);

		Post post2 = new Post();
		post2.setTitle("김수호의 글");
		post2.setMember(member2);
		postRepository.save(post2);

		Assertions.assertThat(post1.getMember().getId()).isEqualTo(member1.getId());
		System.out.println(post1.getTitle());
		Assertions.assertThat(post2.getMember().getId()).isEqualTo(member2.getId());
		System.out.println(post2.getTitle());
	}
}
