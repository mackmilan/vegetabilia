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
@Table(name = "desperdicio")
public class Desperdicio implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1945453291596287302L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "desperdicio_cod_desperdicio_seq")
	@SequenceGenerator(name = "desperdicio_cod_desperdicio_seq", sequenceName = "desperdicio_cod_desperdicio_seq", allocationSize = 1)
	@Column(name = "cod_desperdicio")
	private Integer codDesperdicio;

	@Column(name = "data")
	private Date data;

	@ManyToOne
	@JoinColumn(name = "cod_usuario")
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "cod_funcionario")
	private Funcionario funcionario;

	public Integer getCodDesperdicio() {
		return codDesperdicio;
	}

	public void setCodDesperdicio(Integer codDesperdicio) {
		this.codDesperdicio = codDesperdicio;
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

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codDesperdicio == null) ? 0 : codDesperdicio.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result
				+ ((funcionario == null) ? 0 : funcionario.hashCode());
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
		Desperdicio other = (Desperdicio) obj;
		if (codDesperdicio == null) {
			if (other.codDesperdicio != null)
				return false;
		} else if (!codDesperdicio.equals(other.codDesperdicio))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (funcionario == null) {
			if (other.funcionario != null)
				return false;
		} else if (!funcionario.equals(other.funcionario))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

}
