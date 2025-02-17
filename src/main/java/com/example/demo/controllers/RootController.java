package com.example.demo.controllers;
import java.net.URI;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;
import com.example.demo.forms.ItemForm;
import com.example.demo.services.ItemService;

@Controller
@RequestMapping("/")
public class RootController {

	private final ItemService itemService;
	
	public RootController(ItemService itemService) {
		this.itemService = itemService;
	}

	// /にアクセスした時
	@GetMapping
	public String index() {
		return "item/index";
	}

	//グローバルメニューの商品登録をクリックした時
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
		// RDBと連携できることを確認しておく。
		//バリデーションが成功した場合の処理
		itemService.seveCreate(itemForm);
		itemForm.clear();
		model.addAttribute("message", "登録が完了しました。");

		//redirectするとデフォルトでhttpになってしまうためhttpsを指定してる
		UriComponentsBuilder builder = UriComponentsBuilder.newInstance();
		URI location = builder.scheme("https").host("127.0.0.1").path("/list").build().toUri();
		return "redirect:" + location.toString();
	}

	//グローバルメニューの商品一覧をクリック
	@GetMapping("/list")
	public String showItems(Model model) {
		List<ItemForm> items = itemService.findAllItems();
		model.addAttribute("items", items);

		//商品一覧画面を返す
		return "item/list";
	}

	//商品一覧画面で編集リンクをクリック
	@GetMapping("/update/{id}")
	public String showUpdate(@PathVariable("id") Long id, Model model) {
		// 商品IDを受け取り、商品情報を取得する
		ItemForm itemForm = itemService.findItemById(id);
		model.addAttribute("itemForm", itemForm);
		model.addAttribute("message", "id " + id + " の商品を編集します");

		//商品編集画面を返す
		return "item/update";
	}

	//商品編集画面で不正な入力をした時
	public String updateError(@ModelAttribute ItemForm itemForm) {
		return "item/update";
	}

	//商品編集画面で更新ボタンをクリック
	@PostMapping("/update")    //↓@ModelAttributeの直後に("form")としたら、名前:form、値:itemFormでモデルに格納される。("form")を省略したら、名前も値もitemFormでモデルに格納される。
	public String showConfirmUpdate(@Validated @ModelAttribute ItemForm itemForm, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) { //バリデーションエラーが発生した場合の処理
			return updateError(itemForm);
		}
		// 商品編集確認画面を返す
		return "item/confirm-update";
	}

	//商品編集確認画面で更新ボタンをクリック
	@PostMapping("/confirm-update")
	public String updateExecuteAndResult(ItemForm itemForm, Model model) {
		itemService.seveUpdate(itemForm);
		itemForm.clear();
		model.addAttribute("message", "更新が完了しました。");

		//redirectするとデフォルトでhttpになってしまうためhttpsを指定してる
		UriComponentsBuilder builder = UriComponentsBuilder.newInstance();
		URI location = builder.scheme("https").host("127.0.0.1").path("/list").build().toUri();
		return "redirect:" + location.toString();
	}

	//商品一覧画面で削除ボタンをクリック
	@GetMapping("/delete/{id}")
	public String confirmDelete(@PathVariable("id") Long id, Model model) {
		ItemForm itemForm = itemService.findItemById(id);
		model.addAttribute("itemForm", itemForm);
		return "item/confirm-delete";
	}

	//商品削除確認画面で削除ボタンをクリック
	@PostMapping("/confirm-delete")
	public String deleteConfirmed(@ModelAttribute ItemForm itemForm, Model model) {
		itemService.seveDelete(itemForm);

		//redirectするとデフォルトでhttpになってしまうためhttpsを指定してる
		UriComponentsBuilder builder = UriComponentsBuilder.newInstance();
		URI location = builder.scheme("https").host("127.0.0.1").path("/list").build().toUri();
		return "redirect:" + location.toString();
	}
}