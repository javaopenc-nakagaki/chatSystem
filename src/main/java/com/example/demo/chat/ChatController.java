package com.example.demo.chat;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dao.ChatDao;
import com.example.demo.entity.EntChat;

@Controller
public class ChatController {

	private ChatDao chatdao;

	public ChatController(ChatDao chatdao) {
		this.chatdao = chatdao;
	}

	@RequestMapping("/top")
	public String top(Model model) {
		model.addAttribute("message", "トップページ");
		return "chat/index";
	}

	//	@RequestMapping("/chat")
	//	public String chat(Model model) {
	//		model.addAttribute("message", "チャットルーム");
	//		return "chat/view";
	//	}

	@RequestMapping("/chat")
	public String complete(Chat chat, Model model) {
		model.addAttribute("message", "チャットルーム");
		EntChat entchat = new EntChat();
		entchat.setName(chat.getName());
		entchat.setComment(chat.getComment());

		chatdao.insertDb(entchat);
		return "chat/view";
	}

}
