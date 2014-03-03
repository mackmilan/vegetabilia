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
@Table(name = "permissao")
public class Permissao implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7578543303000686697L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "permissao_cod_permissao_seq_1")
	@SequenceGenerator(name = "permissao_cod_permissao_seq_1", sequenceName = "permissao_cod_permissao_seq_1", allocationSize = 1)
	@Column(name = "cod_permissao")
	private Integer codPermissao;

	@Column(name = "nome")
	private String nome;

	public Integer getCodPermissao() {
		return codPermissao;
	}

	public void setCodPermissao(Integer codPermissao) {
		this.codPermissao = codPermissao;
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
				+ ((codPermissao == null) ? 0 : codPermissao.hashCode());
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
		Permissao other = (Permissao) obj;
		if (codPermissao == null) {
			if (other.codPermissao != null)
				return false;
		} else if (!codPermissao.equals(other.codPermissao))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

}
