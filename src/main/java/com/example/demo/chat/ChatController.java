package com.example.demo.chat;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
	public String chat(@Validated Chat chat, BindingResult result, Model model) {

		if (result.hasErrors()) {
			model.addAttribute("message", "チャットルーム");
			return "chat/view";
		}
		model.addAttribute("message", "チャットルーム");
		EntChat entchat = new EntChat();
		entchat.setName(chat.getName());
		entchat.setComment(chat.getComment());

		chatdao.insertDb(entchat);

		//全件検索(SELECT)
		List<EntChat> list = chatdao.searchDb();
		model.addAttribute("dbList", list);

		return "chat/view";
	}

	@RequestMapping("/manager")
	public String manager(Model model) {
		model.addAttribute("message", "管理画面");
		List<EntChat> list = chatdao.searchDb();
		model.addAttribute("dbList", list);

		return "chat/manager";
	}
	
//	@RequestMapping("/del/{id}")
//	public String del(Long id, Model model,Chat chat) {
//		//フォームの値をエンティティに入れ直し
//		EntChat entform = new EntChat();
//		//更新の実行
//		chatdao.deleteDb(id);
//		//一覧画面へリダイレクト
//		return "redirect:/view";
//	}
	

}
