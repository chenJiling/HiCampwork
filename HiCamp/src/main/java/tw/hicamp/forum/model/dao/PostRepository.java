package tw.hicamp.forum.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.hicamp.forum.model.bean.Post;


public interface PostRepository extends JpaRepository<Post, Integer> {

	List<Post> findByPostTitle(String postTitle);

	List<Post> findAllByOrderByPostNoDesc();
}
