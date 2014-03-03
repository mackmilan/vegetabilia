package br.com.sapientia.vegetabilia.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "despesa")
public class Despesa implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4866528554545682009L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "despesa_cod_despesa_seq")
	@SequenceGenerator(name = "despesa_cod_despesa_seq", sequenceName = "despesa_cod_despesa_seq", allocationSize = 1)
	@Column(name = "cod_despesa")
	private Integer codDespesa;

	@Column(name = "nome", columnDefinition = "string")
	private String nome;

	@Column(name = "descricao", columnDefinition = "string")
	private String descricao;

	@Column(name = "data", columnDefinition = "date")
	private Date data;

	@Column(name = "valor", columnDefinition = "double")
	private Double valor;

	public Integer getCodDespesa() {
		return codDespesa;
	}

	public void setCodDespesa(Integer codDespesa) {
		this.codDespesa = codDespesa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

}
