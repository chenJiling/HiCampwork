package tw.hicamp.forum.model.dto;

import lombok.Data;

@Data
public class PostCommentDTO {
	
	private Integer postNo;
	
	private String postCommentText;
}
