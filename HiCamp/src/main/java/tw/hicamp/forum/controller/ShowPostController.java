package tw.hicamp.forum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import tw.hicamp.forum.model.bean.Post;
import tw.hicamp.forum.model.bean.PostComment;
import tw.hicamp.forum.service.PostCommentService;
import tw.hicamp.forum.service.PostService;


@Controller
public class ShowPostController{
	
	@Autowired
	private PostService pService;
	
	@Autowired
	private PostCommentService pcService;
	
	// 查全部貼文(管理者頁面)
	@GetMapping("/forum/showallmanager")
    public String getAllPostMain(Model m) {
        List<Post> posts = pService.getAllPosts();
        m.addAttribute("posts", posts);
        return "/forum/ManagerHomepage";
    }
	
	// 查全部貼文 (使用者頁面) 
	@GetMapping("/forum/showall")
	public String showAllPostMain(Model m) {
		List<Post> posts = pService.getAllPosts();
		m.addAttribute("posts", posts);
		return "/forum/UserHomepage";
	}
   
	// 查單一貼文
	@GetMapping("/forum/showpostbyno/{postNo}")
	public String getForumPostDetail1(@PathVariable("postNo") Integer postNo, Model model) {
		Post post = pService.getPostbyNo(postNo);
		List<PostComment> comments = pcService.getCommentsByPostSortedByNo(post);
		
		model.addAttribute("post", post);
		model.addAttribute("comments", comments);
		
		return "/forum/PostContentByNo";
	}
}
