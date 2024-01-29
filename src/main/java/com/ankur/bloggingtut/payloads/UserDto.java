package com.ankur.bloggingtut.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	private Integer id;

	@Size(min=3, message="Name length should be more than 3 characters")
	private String name;
	
	@Email(message="Not a valid email")
	private String email;

	@NotNull(message = "Bio field is mandatory")
	private String bio;

	@NotNull(message = "Password field is mandatory")
	private String password;
}
