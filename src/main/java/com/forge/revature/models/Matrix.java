package com.forge.revature.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "matrices")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Matrix {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@Column
	private String header;
	
	@ManyToOne
	@JoinColumn(name = "portfolio_id")
    private Portfolio portfolio;

	public Matrix(String header, Portfolio portfolio) {
		super();
		this.header = header;
		this.portfolio = portfolio;
	}
}