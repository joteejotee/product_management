package com.example.demo.services;
import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.forms.ItemForm;
import com.example.demo.repositries.ItemRepository;

@Service
public class ItemService {

	private final ItemRepository repository;
	
	public ItemService(ItemRepository repository) {
		this.repository = repository;
	}

	public void seveCreate(ItemForm itemForm) {
		repository.saveAndFlush(itemForm);
	}

	public List<ItemForm> findAllItems() {
		return repository.findAll();
	}

	public ItemForm findItemById(Long id) {
		return repository.findById(id).orElse(null);
	}

	public void seveUpdate(ItemForm itemForm) {
		repository.saveAndFlush(itemForm);
	}

	public void seveDelete(ItemForm itemForm) {
		repository.deleteById(itemForm.getId());
	}


}
