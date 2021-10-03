package models;

public class DadosSUAP {
	private String id, matricula, nome_usual, tipo_vinculo, email, categoria, url_foto_150x200;	
	private Vinculo vinculo;
	
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getNome_usual() {
		return nome_usual;
	}
	public void setNome_usual(String nome_usual) {
		this.nome_usual = nome_usual;
	}
	public String getTipo_vinculo() {
		return tipo_vinculo;
	}
	public void setTipo_vinculo(String tipo_vinculo) {
		this.tipo_vinculo = tipo_vinculo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getUrl_foto_150x200() {
		return url_foto_150x200;
	}
	public void setUrl_foto_150x200(String url_foto_150x200) {
		this.url_foto_150x200 = url_foto_150x200;
	}
	public Vinculo getVinculo() {
		return vinculo;
	}
	public void setVinculo(Vinculo vinculo) {
		this.vinculo = vinculo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	} 
}
