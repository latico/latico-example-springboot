package com.latico.example.springboot.postgresql.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pg_demo")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class PgDemo implements Serializable {

	@Id
	@Column(name = "auto_id")
	private Integer autoId;

	private String id;

	private String name;

	private Double price;

}
