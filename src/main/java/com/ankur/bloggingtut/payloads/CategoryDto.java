package com.ankur.bloggingtut.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
	
	private Integer id;
	
	@NotEmpty
	@Size(min=3)
	private String title;
}
