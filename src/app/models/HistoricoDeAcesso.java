package models;

import javax.persistence.Entity;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class HistoricoDeAcesso extends Model{
	@Required
	private Long idHA;
	
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
	private String url_foto_150x200;
	
	@Required
	private String vinculo;
	
	@Required
	private int horaDeAcesso;
	
	@Required
	private int minutoDeAcesso;
	
	@Required
	private int horaDeAcessoFinal;
	
	@Required
	private int minutoDeAcessoFinal;
	
	@Required
	private String dataDeAcesso;

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

	public String getUrl_foto_150x200() {
		return url_foto_150x200;
	}

	public void setUrl_foto_150x200(String url_foto_150x200) {
		this.url_foto_150x200 = url_foto_150x200;
	}

	public String getVinculo() {
		return vinculo;
	}

	public void setVinculo(String vinculo) {
		this.vinculo = vinculo;
	}

	public int getHoraDeAcesso() {
		return horaDeAcesso;
	}

	public void setHoraDeAcesso(int horaDeAcesso) {
		this.horaDeAcesso = horaDeAcesso;
	}

	public int getMinutoDeAcesso() {
		return minutoDeAcesso;
	}

	public void setMinutoDeAcesso(int minutoDeAcesso) {
		this.minutoDeAcesso = minutoDeAcesso;
	}

	public String getDataDeAcesso() {
		return dataDeAcesso;
	}

	public void setDataDeAcesso(String dataDeAcesso) {
		this.dataDeAcesso = dataDeAcesso;
	}

	public int getHoraDeAcessoFinal() {
		return horaDeAcessoFinal;
	}

	public void setHoraDeAcessoFinal(int horaDeAcessoFinal) {
		this.horaDeAcessoFinal = horaDeAcessoFinal;
	}

	public int getMinutoDeAcessoFinal() {
		return minutoDeAcessoFinal;
	}

	public void setMinutoDeAcessoFinal(int minutoDeAcessoFinal) {
		this.minutoDeAcessoFinal = minutoDeAcessoFinal;
	}

	public Long getIdHA() {
		return idHA;
	}

	public void setIdHA(Long idHA) {
		this.idHA = idHA;
	}
	
}
