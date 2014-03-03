package br.com.sapientia.vegetabilia.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tipo_produto")
public class TipoProduto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3211163980893975790L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "tipo_produto_cod_tipo_produto_seq_1")
	@SequenceGenerator(name = "tipo_produto_cod_tipo_produto_seq_1", sequenceName = "tipo_produto_cod_tipo_produto_seq_1", allocationSize = 1)
	@Column(name = "cod_tipo_produto")
	private Integer codTipoProduto;

	@Column(name = "nome")
	private String nome;

	public Integer getCodTipoProduto() {
		return codTipoProduto;
	}

	public void setCodTipoProduto(Integer codTipoProduto) {
		this.codTipoProduto = codTipoProduto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codTipoProduto == null) ? 0 : codTipoProduto.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoProduto other = (TipoProduto) obj;
		if (codTipoProduto == null) {
			if (other.codTipoProduto != null)
				return false;
		} else if (!codTipoProduto.equals(other.codTipoProduto))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

}
