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
@Table(name = "estado")
public class Estado implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6426813329655065859L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "estado_cod_estado_seq_1")
	@SequenceGenerator(name = "estado_cod_estado_seq_1", sequenceName = "estado_cod_estado_seq_1", allocationSize = 1)
	@Column(name = "cod_estado")
	private Integer codEstado;

	@Column(name = "nome")
	private String nome;

	@Column(name = "sigla")
	private String sigla;

	public Integer getCodEstado() {
		return codEstado;
	}

	public void setCodEstado(Integer codEstado) {
		this.codEstado = codEstado;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codEstado == null) ? 0 : codEstado.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((sigla == null) ? 0 : sigla.hashCode());
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
		Estado other = (Estado) obj;
		if (codEstado == null) {
			if (other.codEstado != null)
				return false;
		} else if (!codEstado.equals(other.codEstado))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (sigla == null) {
			if (other.sigla != null)
				return false;
		} else if (!sigla.equals(other.sigla))
			return false;
		return true;
	}

}
