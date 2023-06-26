package tw.hicamp.forum.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tw.hicamp.forum.model.bean.Post;
import tw.hicamp.forum.model.bean.PostComment;
import tw.hicamp.forum.service.PostCommentService;
import tw.hicamp.forum.service.PostService;


@Controller
public class UpdatePostController {
	
	@Autowired
	private PostService pService;
	
	@Autowired
	private PostCommentService pcService;
	
	// 修改貼文
	@GetMapping("/forum/update/{postNo}")
	public String updatePostMain(@PathVariable("postNo") Integer postNo, Model m) {
		Post originalForum = pService.getPostbyNo(postNo);
		m.addAttribute("originalForum", originalForum);
		m.addAttribute("post", originalForum);
		return "/forum/UpdatePost";
	}
	
	@PostMapping("/forum/updated/{postNo}")
	public String updatePost(@ModelAttribute("post") Post post,Model m) {
		post.setMemberNo(1);
		post.setPostDate(new Date());
		
		pService.updatePost(post);
		
		m.addAttribute("result", "修改成功");
		m.addAttribute("post", post);
		return "redirect:/forum/showallmanager";
	}
	
	// 修改留言
	@PostMapping("/UpdatePostComment")
	    public String updatePostComment(
	    		@RequestParam("postNo") Integer postNo,
	    		@RequestParam("postCommentNo") Integer postCommentNo,
	    		@RequestParam("updatedPostComment") String updatedPostComment,
	    		@ModelAttribute("postComment")PostComment postComment) {
	    	
	    	int memberNo = 1;
	        Date postCommentDate = new Date();
	        
	        Post post = pService.getPostbyNo(postNo);
	        
	        postComment.setPost(post);
	        postComment.setMemberNo(memberNo);
	        postComment.setPostCommentNo(postCommentNo);
	        postComment.setPostComment(updatedPostComment);
	        postComment.setPostCommentDate(postCommentDate);
	        
	        pcService.updateComment(postComment);

	        return "redirect:/forum/showpostbyno/" + postNo;
	    }
}
