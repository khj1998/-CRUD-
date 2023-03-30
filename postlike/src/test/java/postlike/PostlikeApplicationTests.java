package postlike;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import postlike.domain.Comment;
import postlike.domain.PostLike;
import postlike.domain.Member;
import postlike.domain.Post;
import postlike.repository.CommentRepository;
import postlike.repository.LikeRepository;
import postlike.repository.MemberRepository;
import postlike.repository.PostRepository;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class PostlikeApplicationTests {
	@Autowired
	MemberRepository memberRepository;
	@Autowired
	PostRepository postRepository;
	@Autowired
	CommentRepository commentRepository;
	@Autowired
	LikeRepository likeRepository;

	@Order(1)
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

	@Order(2)
	@Test
	void addCommentTest() {
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

		// 맴버1이 post1에 댓글을 달았다 가정
		Comment comment1 = new Comment();
		comment1.setContent("김호진의 댓글!");
		comment1.setMember(member1);
		comment1.setPost(post1);
		commentRepository.save(comment1);

		// 맴버2가 post2에 댓글을 달았다 가정
		Comment comment2 = new Comment();
		comment2.setContent("김수호의 댓글!");
		comment2.setMember(member2);
		comment2.setPost(post2);
		commentRepository.save(comment2);

		List<Comment> commentList1 = post1.getComments();
		List<Comment> commentList2 = post2.getComments();
		
		for(Comment comment : commentList1) {
			if (comment.getContent().equals("김수호의 댓글!")) {
				throw new RuntimeException("댓글이 비정상적으로 저장됨");
			} else if (comment.getContent().equals("김호진의 댓글!")) {
				Assertions.assertThat(comment.getMember().getId()).isEqualTo(member1.getId());
				break;
			}
		}

		for(Comment comment : commentList2) {
			if (comment.getContent().equals("김호진의 댓글!")) {
				throw new RuntimeException("댓글이 비정상적으로 저장됨");
			} else if (comment.getContent().equals("김수호의 댓글!")) {
				Assertions.assertThat(comment.getMember().getId()).isEqualTo(member2.getId());
				break;
			}
		}
	}

	@Order(3)
	@Test
	void addLikeTest() {
		Member member1 = new Member();
		member1.setUsername("김호진");
		memberRepository.save(member1);

		Member member2 = new Member();
		member2.setUsername("김수호");
		memberRepository.save(member2);

		Member member3 = new Member();
		member3.setUsername("이승규");
		memberRepository.save(member3);

		Post post1 = new Post();
		post1.setTitle("김호진의 글");
		post1.setMember(member1);
		postRepository.save(post1);

		Post post2 = new Post();
		post2.setTitle("김수호의 글");
		post2.setMember(member2);
		postRepository.save(post2);



		PostLike postLike1 = new PostLike();
		postLike1.setPost(post1);
		postLike1.setMember(member1);

		member1.getPostLikeList().add(postLike1);
		post1.getPostLikeList().add(postLike1);
		likeRepository.save(postLike1);

		PostLike postLike2 = new PostLike();
		postLike2.setPost(post2);
		postLike2.setMember(member2);

		member2.getPostLikeList().add(postLike2);
		post2.getPostLikeList().add(postLike2);
		likeRepository.save(postLike2);

		PostLike postLike3 = new PostLike();
		postLike3.setPost(post1);
		postLike3.setMember(member3);

		member3.getPostLikeList().add(postLike3);
		post1.getPostLikeList().add(postLike3);
		likeRepository.save(postLike3);

		List<PostLike> postLikeList = member1.getPostLikeList();
		List<PostLike> postLikeList2 = member2.getPostLikeList();

		for (PostLike postLike : postLikeList) {
			if (!postLike.getMember().getId().equals(member1.getId())) {
				throw new RuntimeException("해당 게시글에 좋아요를 누른적 없는 유저");
			}
			System.out.println(member1+"이 좋아요를 누른 게시물: "+postLike.getPost().getTitle());
		}

		for (PostLike postLike : postLikeList2) {
			if (!postLike.getMember().getId().equals(member2.getId())) {
				throw new RuntimeException("해당 게시글에 좋아요를 누른적 없는 유저");
			}
			System.out.println(member2+"이 좋아요를 누른 게시물: "+postLike.getPost().getTitle());
		}

		Assertions.assertThat(post1.getPostLikeList().size()).isEqualTo(2);
	}

	@Order(4)
	@Test
	void likeTest() {
	}
}
