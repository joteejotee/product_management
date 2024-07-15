package com.example.demo.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

//今回のitemForm.javaはフォームであり、エンティティである。
@Data //これがついてたらフォーム。
@Entity //これはテーブルの1レコードの意。@Tableアノテーションとセット。
@Table(name = "item") //DBのitemテーブルに紐づいてると明示してる。@TableはDBをJavaクラスで扱えるようにするアノテ。
public class ItemForm implements Serializable {
	private static final long serialVersionUID = -6647247658748349084L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	//あとでitem_を外してみる
	@NotBlank
	@Size(max = 10)
	private String name;

	@NotNull
	@PositiveOrZero(message = "価格は0円以上でなければなりません")
	private Integer price;

	@NotBlank
	@Size(max = 400)
	private String description;

	public void clear() {
		name = null;
		price = null;
		description = null;
	}
}
