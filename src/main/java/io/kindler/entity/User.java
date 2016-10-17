package io.kindler.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by kyj on 2016-10-17.
 */
@Entity
@Table
@Data
public class User {
	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	private String passwd;
	private String telephone;
}
