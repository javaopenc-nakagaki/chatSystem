package com.example.demo.chat;

import jakarta.validation.constraints.Size;

public class Chat {
	@Size(min = 1, message = "名前を入力してください。")
	private String name;
	@Size(min = 1, max = 500, message = "500文字以内にしてください")
	private String comment;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
