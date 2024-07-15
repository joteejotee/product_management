package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.ItemForm;
import com.example.demo.repositries.ItemRepository;

@Controller
@RequestMapping("/")
public class RootController {

	@Autowired
	ItemRepository repository;

	@GetMapping
	public String index() {
		return "item/index";
	}

	//@GetMapping("/create")と設定すると、https://127.0.0.1/createに対するGETリクエストを待ち受ける。
	//https://127.0.0.1/createへのリクエストがこのメソッドにマッピングされる。
	@GetMapping("/create")
	public String form(ItemForm itemForm) { //フレームワークからパラメータにitemFormオブジェクトを受け取る
		return "item/create";
	}

	@PostMapping("/create")
	public String form(@Validated ItemForm itemForm, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) { //バリデーションエラーが発生した場合の処理
			return "item/create";
		}

		// RDBと連携できることを確認しておきます。
		//バリデーションが成功した場合の処理
		repository.saveAndFlush(itemForm);//このメソッドはitemRepositoyの継承元のJpaRepository（のもっと親）にあるメソッド。
		itemForm.clear();
		model.addAttribute("message", "登録が完了しました。");
		return "item/create";
	}
}