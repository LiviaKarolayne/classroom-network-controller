package models;

import javax.persistence.Entity;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Usuario extends Model{
	
	@Required
	private String nome;
	
	@Required
	private String sobrenome;
	
	@Required
	private String tipo;
	
	@Required
	private String email;
	
	@Required
	private String senha;
	
	@Required
	private String matricula;
	
	@Required
	private String originador;
	
	@Required
	private String idOriginador;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getOriginador() {
		return originador;
	}

	public void setOriginador(String originador) {
		this.originador = originador;
	}

	public String getIdOriginador() {
		return idOriginador;
	}

	public void setIdOriginador(String idOriginador) {
		this.idOriginador = idOriginador;
	}
	
	
}
