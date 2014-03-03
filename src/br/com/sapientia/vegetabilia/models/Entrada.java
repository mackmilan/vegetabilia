package br.com.sapientia.vegetabilia.models;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "entrada")
public class Entrada implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6671274302323265624L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "entrada_cod_entrada_seq")
	@SequenceGenerator(name = "entrada_cod_entrada_seq", sequenceName = "entrada_cod_entrada_seq", allocationSize = 1)
	@Column(name = "cod_entrada")
	private Integer codEntrada;

	@Column(name = "data")
	private Date data;

	@ManyToOne
	@JoinColumn(name = "cod_usuario")
	private Usuario usuario;

	public Integer getCodEntrada() {
		return codEntrada;
	}

	public void setCodEntrada(Integer codEntrada) {
		this.codEntrada = codEntrada;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codEntrada == null) ? 0 : codEntrada.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		Entrada other = (Entrada) obj;
		if (codEntrada == null) {
			if (other.codEntrada != null)
				return false;
		} else if (!codEntrada.equals(other.codEntrada))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

}
