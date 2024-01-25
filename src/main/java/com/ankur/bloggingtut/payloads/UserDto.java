package com.ankur.bloggingtut.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	private Integer id;
	
	private String name;
	
	private String email;
	
	private String bio;

	private String password;
}
