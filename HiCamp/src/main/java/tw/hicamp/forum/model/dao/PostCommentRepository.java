package tw.hicamp.forum.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.hicamp.forum.model.bean.Post;
import tw.hicamp.forum.model.bean.PostComment;

public interface PostCommentRepository extends JpaRepository<PostComment, Integer> {
	
	List<PostComment> findByPostOrderByPostCommentNo(Post post);

}
