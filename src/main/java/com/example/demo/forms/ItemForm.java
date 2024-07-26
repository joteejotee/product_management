package com.example.demo.forms;

import javax.validation.constraints.NotBlank;
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
@Data //これがついてたらフォーム。これはフォームクラス。
@Entity //これはテーブルの1レコードの意。@Tableアノテーションとセット。@Entityと@Tableアノテはエンティティクラスに。
@Table(name = "item") //DBのitemテーブルに紐づいてると明示してる。@TableはDBをJavaクラスで扱えるようにするアノテ。
public class ItemForm implements Serializable {
	private static final long serialVersionUID = -6647247658748349084L;

	//ここはエンティティクラスに。
	@Id//これは主キーのアノテ。
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	//ここからはフォームクラス。
	@NotBlank(message = "商品名を入力してください。")
	@Size(max = 20,message = "商品名は20文字以下で入力してください。")
	private String name;

	@NotNull(message = "価格を入力してください。")
	@PositiveOrZero(message = "価格は0円以上でなければなりません")
	private Integer price;

	@NotBlank(message = "商品説明を入力してください。")
	@Size(max = 300,message = "商品説明は300文字以下で入力してください。")
	private String description;

	public void clear() {
		name = null;
		price = null;
		description = null;
	}

    public Long getId() {
        return id;
    }
}
