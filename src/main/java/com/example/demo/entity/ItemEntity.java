package com.example.demo.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity //これはテーブルの1レコードの意。@Tableアノテーションとセット。
@Table(name = "item") //DBのitemテーブルに紐づいてると明示してる。@TableはDBをJavaクラスで扱えるようにするアノテ。
public class ItemEntity implements Serializable {
	private static final long serialVersionUID = -6647247658748349084L;

	@Id//これは主キーのアノテ。
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private Integer price;
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
