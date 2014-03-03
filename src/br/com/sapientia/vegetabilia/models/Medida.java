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
@Table(name = "medida")
public class Medida implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3918127122621865377L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "medida_cod_medida_seq_1")
	@SequenceGenerator(name = "medida_cod_medida_seq_1", sequenceName = "medida_cod_medida_seq_1", allocationSize = 1)
	@Column(name = "cod_medida")
	private Integer codMedida;

	@Column(name = "nome")
	private String nome;

	@Column(name = "peso")
	private Double peso;

	@Column(name = "unidades")
	private Integer unidades;

	@Column(name = "valor")
	private Double valor;

	public Integer getCodMedida() {
		return codMedida;
	}

	public void setCodMedida(Integer codMedida) {
		this.codMedida = codMedida;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public Integer getUnidades() {
		return unidades;
	}

	public void setUnidades(Integer unidades) {
		this.unidades = unidades;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codMedida == null) ? 0 : codMedida.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((peso == null) ? 0 : peso.hashCode());
		result = prime * result
				+ ((unidades == null) ? 0 : unidades.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
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
		Medida other = (Medida) obj;
		if (codMedida == null) {
			if (other.codMedida != null)
				return false;
		} else if (!codMedida.equals(other.codMedida))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (peso == null) {
			if (other.peso != null)
				return false;
		} else if (!peso.equals(other.peso))
			return false;
		if (unidades == null) {
			if (other.unidades != null)
				return false;
		} else if (!unidades.equals(other.unidades))
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}

}
