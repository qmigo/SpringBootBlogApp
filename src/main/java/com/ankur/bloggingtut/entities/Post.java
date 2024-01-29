package com.ankur.bloggingtut.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String title;
	
	private String content;
	
	private LocalDateTime timestamp;
	
	@ManyToOne
	@JoinColumn(name="author_id", nullable = false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name="post_id", nullable = false)
	private Category category;
}
