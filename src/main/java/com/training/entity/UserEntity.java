package com.training.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "USER_ID")
	private Long userId;

	private String username;
	
	private String lastname;
	
	private String firstname;

	private String password;
	
	@Column(name = "IMAGE")
	private String image;

	@Column(name = "PHONE_NUMBER")
	private String phoneNumber;

	private String role;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "CREATE_DATE")
	private Date createDate;

	@JsonIgnore
	@OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY)
	private Set<CommentEntity> commentSet;
	
	@Transient
    private String passwordConfirm;
	
	@Transient
	private MultipartFile[] imageFiles;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Set<CommentEntity> getCommentSet() {
		return commentSet;
	}

	public void setCommentSet(Set<CommentEntity> commentSet) {
		this.commentSet = commentSet;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	
	public MultipartFile[] getImageFiles() {
		return imageFiles;
	}

	public void setImageFiles(MultipartFile[] imageFiles) {
		this.imageFiles = imageFiles;
	}

	public UserEntity() {
		super();
		this.createDate = new Date();
	}

	@Override
	public String toString() {
		return "[" + this.username + "," + this.password + "," + this.role + "]";
	}
}
