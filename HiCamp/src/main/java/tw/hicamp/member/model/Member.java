package tw.hicamp.member.model;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import tw.hicamp.forum.model.bean.Post;
import tw.hicamp.forum.model.bean.PostComment;
import tw.hicamp.product.model.*;


@Entity
@Table(name = "member")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int memberNo;
	private String memberName;
	private String memberGender;
	private String memberEmail;
	private String memberPassword;
	private String memberPhone;
	private String memberAddress;
	private String memberId;
	private String memberBirthday;
	private String memberEmergencyContact;
	private String memberEmergencyPhone;
	private int memberStatus;
	private byte[] memberPhoto;
	
	@JsonIgnore
	@JsonIgnoreProperties("memberNo")
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "memberNo")
	private Set<Post> posts = new HashSet<>();
	
	@JsonIgnore
	@JsonIgnoreProperties("member")
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "member")
	private Set<PostComment> postcomments = new HashSet<>();
	
	@JsonIgnore
	@JsonIgnoreProperties("member")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Orders> orders = new ArrayList<>();

	@Override
	public String toString() {
		return "Member [memberNo=" + memberNo + ", memberName=" + memberName + ", memberGender=" + memberGender
				+ ", memberEmail=" + memberEmail + ", memberPassword=" + memberPassword + ", memberPhone=" + memberPhone
				+ ", memberAddress=" + memberAddress + ", memberId=" + memberId + ", memberBirthday=" + memberBirthday
				+ ", memberEmergencyContact=" + memberEmergencyContact + ", memberEmergencyPhone="
				+ memberEmergencyPhone + ", memberStatus=" + memberStatus + ", memberPhoto="
				+ Arrays.toString(memberPhoto) + ", posts=" + posts + ", postcomments=" + postcomments + ", orders="
				+ orders + "]";
	}
	
	
}
