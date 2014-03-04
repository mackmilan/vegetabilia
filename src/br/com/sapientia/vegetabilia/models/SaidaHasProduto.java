package br.com.sapientia.vegetabilia.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "saida_has_produto")
public class SaidaHasProduto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1427463631000261540L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "saida_has_produto_cod_saida_has_produto_seq")
	@SequenceGenerator(name = "saida_has_produto_cod_saida_has_produto_seq", sequenceName = "saida_has_produto_cod_saida_has_produto_seq", allocationSize = 1)
	@Column(name = "cod_saida_has_produto")
	private Integer codSaidaHasProduto;

	@Column(name = "quantidade")
	private Integer qnt;

	@ManyToOne
	@JoinColumn(name = "cod_produto")
	private Produto produto;

	@ManyToOne
	@JoinColumn(name = "cod_saida")
	private Saida saida;

	public Integer getCodSaidaHasProduto() {
		return codSaidaHasProduto;
	}

	public void setCodSaidaHasProduto(Integer codSaidaHasProduto) {
		this.codSaidaHasProduto = codSaidaHasProduto;
	}

	public Integer getQnt() {
		return qnt;
	}

	public void setQnt(Integer qnt) {
		this.qnt = qnt;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Saida getSaida() {
		return saida;
	}

	public void setSaida(Saida saida) {
		this.saida = saida;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((codSaidaHasProduto == null) ? 0 : codSaidaHasProduto
						.hashCode());
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		result = prime * result + ((qnt == null) ? 0 : qnt.hashCode());
		result = prime * result + ((saida == null) ? 0 : saida.hashCode());
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
		SaidaHasProduto other = (SaidaHasProduto) obj;
		if (codSaidaHasProduto == null) {
			if (other.codSaidaHasProduto != null)
				return false;
		} else if (!codSaidaHasProduto.equals(other.codSaidaHasProduto))
			return false;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		if (qnt == null) {
			if (other.qnt != null)
				return false;
		} else if (!qnt.equals(other.qnt))
			return false;
		if (saida == null) {
			if (other.saida != null)
				return false;
		} else if (!saida.equals(other.saida))
			return false;
		return true;
	}

}
