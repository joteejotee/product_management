package com.example.demo.repositries;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.forms.ItemForm;


//JpaRepositoryが基本のCRUD操作を提供する

//@Repositoryはデータアクセス層（リポジトリ）を示す。これでSpringが自動でBeanとして登録し、例外をスプリングデータアクセス例外に変換可能にする。
@Repository          //エンティティクラスでもあるItemFormとその主キーの型Longを指定。↓。
public interface ItemRepository extends JpaRepository<ItemForm, Long>{
	Optional<ItemForm> findById(String id);//指定されたidのItemFormをDBから検索
	List<ItemForm> findAll(); //複数のitemFormを持つリスト
}