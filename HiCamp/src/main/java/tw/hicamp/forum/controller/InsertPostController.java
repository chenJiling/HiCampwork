package tw.hicamp.forum.controller;

import java.util.Date;

import tw.hicamp.member.model.Member;
import tw.hicamp.member.service.MemberService;
import tw.hicamp.forum.model.bean.Post;
import tw.hicamp.forum.model.bean.PostComment;
import tw.hicamp.forum.model.dto.PostCommentDTO;
import tw.hicamp.forum.service.PostCommentService;
import tw.hicamp.forum.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import jakarta.servlet.http.HttpSession;


@Controller
public class InsertPostController {

    @Autowired
    private PostService pService;
    
    @Autowired
    private PostCommentService pcService;
    
    @Autowired
    private MemberService mService;
    
    // 新增貼文
    @GetMapping("/forum/add")
    public String insertPostMain(Model model) {
    	model.addAttribute("post",new Post());
        return "/forum/InsertPost2";
    }

    @PostMapping("/forum/added")
    public String insertForum(@ModelAttribute("post")Post post,HttpSession session,Model model) {
    	Integer memberNo = (Integer) session.getAttribute("memberNo");
    	if (memberNo == null) {
            return "redirect:/projectmemberlogin";
        }
    	Member member = mService.findByNo(memberNo);
    	String memberName = member.getMemberName();
    	
    	post.setMember(member);
    	post.setPostDate(new Date());
    	
    	pService.insertPost(post);    
             
    	model.addAttribute("memberName", memberName);

        return "redirect:/forum/showall";
    }
    
    // 新增留言
    @ResponseBody
    @PostMapping("/forum/addcomment")
    public PostComment insertPostComment(@RequestBody PostCommentDTO pcDTO,HttpSession session) {
    	
    	Integer memberNo = (Integer) session.getAttribute("memberNo");
    	if (memberNo == null) {
    		 throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "請登入會員");
        }
    	
    	Member member = mService.findByNo(memberNo);
    	
        Post post = pService.getPostbyNo(pcDTO.getPostNo());
        
        PostComment postComment = new PostComment();
        postComment.setPost(post);
        postComment.setMember(member);
        postComment.setPostComment(pcDTO.getPostCommentText());
        postComment.setPostCommentDate(new Date());
        
        pcService.insertComment(postComment);
        
        return postComment;
    }
}
