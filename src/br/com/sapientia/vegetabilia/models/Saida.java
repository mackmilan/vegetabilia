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
@Table(name = "saida")
public class Saida implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4370342542673176840L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "saida_cod_saida_seq")
	@SequenceGenerator(name = "saida_cod_saida_seq", sequenceName = "saida_cod_saida_seq", allocationSize = 1)
	@Column(name = "cod_saida")
	private Integer codSaida;

	@Column(name = "data")
	private Date data;

	@Column(name = "valor")
	private Double valor;

	@ManyToOne
	@JoinColumn(name = "cod_cliente")
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "cod_usuario")
	private Usuario usuario;

	public Integer getCodSaida() {
		return codSaida;
	}

	public void setCodSaida(Integer codSaida) {
		this.codSaida = codSaida;
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result
				+ ((codSaida == null) ? 0 : codSaida.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		Saida other = (Saida) obj;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (codSaida == null) {
			if (other.codSaida != null)
				return false;
		} else if (!codSaida.equals(other.codSaida))
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
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}

}
