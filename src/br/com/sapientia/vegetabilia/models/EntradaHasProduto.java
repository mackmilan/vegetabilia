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
@Table(name = "entrada_has_produto")
public class EntradaHasProduto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1530083327128784957L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "entrada_has_produto_cod_entrada_has_produto_seq")
	@SequenceGenerator(name = "entrada_has_produto_cod_entrada_has_produto_seq", sequenceName = "entrada_has_produto_cod_entrada_has_produto_seq", allocationSize = 1)
	@Column(name = "cod_entrada_has_produto")
	private Integer codEntradaHasProduto;

	@ManyToOne
	@JoinColumn(name = "cod_entrada")
	private Entrada entrada;

	@ManyToOne
	@JoinColumn(name = "cod_produto")
	private Produto produto;

	@Column(name = "quantidade")
	private Integer qnt;

	public Integer getCodEntradaHasProduto() {
		return codEntradaHasProduto;
	}

	public void setCodEntradaHasProduto(Integer codEntradaHasProduto) {
		this.codEntradaHasProduto = codEntradaHasProduto;
	}

	public Entrada getEntrada() {
		return entrada;
	}

	public void setEntrada(Entrada entrada) {
		this.entrada = entrada;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Integer getQnt() {
		return qnt;
	}

	public void setQnt(Integer qnt) {
		this.qnt = qnt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((codEntradaHasProduto == null) ? 0 : codEntradaHasProduto
						.hashCode());
		result = prime * result + ((entrada == null) ? 0 : entrada.hashCode());
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		result = prime * result + ((qnt == null) ? 0 : qnt.hashCode());
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
		EntradaHasProduto other = (EntradaHasProduto) obj;
		if (codEntradaHasProduto == null) {
			if (other.codEntradaHasProduto != null)
				return false;
		} else if (!codEntradaHasProduto.equals(other.codEntradaHasProduto))
			return false;
		if (entrada == null) {
			if (other.entrada != null)
				return false;
		} else if (!entrada.equals(other.entrada))
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
		return true;
	}

}
