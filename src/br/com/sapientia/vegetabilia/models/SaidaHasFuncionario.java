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
@Table(name = "saida_has_funcionario")
public class SaidaHasFuncionario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9030345552790103071L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "saida_has_funcionario_cod_saida_has_funcionario_seq")
	@SequenceGenerator(name = "saida_has_funcionario_cod_saida_has_funcionario_seq", sequenceName = "saida_has_funcionario_cod_saida_has_funcionario_seq", allocationSize = 1)
	@Column(name = "cod_saida_has_funcionario")
	private Integer codSaidaHasFuncionario;

	@ManyToOne
	@JoinColumn(name = "cod_funcionario")
	private Funcionario funcionario;

	@ManyToOne
	@JoinColumn(name = "cod_saida")
	private Saida saida;

	public Integer getCodSaidaHasFuncionario() {
		return codSaidaHasFuncionario;
	}

	public void setCodSaidaHasFuncionario(Integer codSaidaHasFuncionario) {
		this.codSaidaHasFuncionario = codSaidaHasFuncionario;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
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
				+ ((codSaidaHasFuncionario == null) ? 0
						: codSaidaHasFuncionario.hashCode());
		result = prime * result
				+ ((funcionario == null) ? 0 : funcionario.hashCode());
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
		SaidaHasFuncionario other = (SaidaHasFuncionario) obj;
		if (codSaidaHasFuncionario == null) {
			if (other.codSaidaHasFuncionario != null)
				return false;
		} else if (!codSaidaHasFuncionario.equals(other.codSaidaHasFuncionario))
			return false;
		if (funcionario == null) {
			if (other.funcionario != null)
				return false;
		} else if (!funcionario.equals(other.funcionario))
			return false;
		if (saida == null) {
			if (other.saida != null)
				return false;
		} else if (!saida.equals(other.saida))
			return false;
		return true;
	}

}
