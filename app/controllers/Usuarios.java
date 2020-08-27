package controllers;

import java.util.List;

import javax.validation.Valid;

import models.HistoricoDeAcesso;
import models.Usuario;
import play.mvc.Controller;

public class Usuarios extends Controller{
	private static String mensagem;
	
	public static void form() {
		if(session.get("HANome") == null) {
			redirect("../../Autenticacao/login");
		}
		render();
	}
	
	public static void salvar(@Valid Usuario usuario) {		
		usuario.setOriginador(session.get("HANome"));
		usuario.setIdOriginador(session.get("HAId"));
		
		if(usuario.id == null) {
			mensagem = "Usuário Adicionado com Sucesso";
		}else {
			mensagem = "Usuário Editado com Sucesso";
		}
		
		if(validation.hasErrors()) {
			validation.keep();
			flash.error("Campo vazio");
			params.flash();	         
	    } else {	    	
			usuario.save();
			listarUsuario(mensagem);
			flash.success("Usuário Cadastrado com sucesso");
			params.flash();
	    }
	}	
	
	public static void listarUsuario(String mensagem) {
		if(session.get("HANome") == null) {
			redirect("../../Autenticacao/login");
		}
		if(session.get("isAluno").equals("true")) {			
			flash.error("Acesso Negado à Página de Usuários");
			params.flash();
		}
		List<Usuario> usuarios = Usuario.findAll();				
		render(usuarios, mensagem);
	}
	
	public static void remover(Long id) {
		Usuario usuario = Usuario.findById(id);
		usuario.delete();
		listarUsuario("Usuário Removido com Sucesso");
	}
	
	public void editar(Long id) {
		if(session.get("HANome") == null) {
			redirect("../../Autenticacao/login");
		}
		Usuario usuario = Usuario.findById(id);
		renderTemplate("Usuarios/form.html", usuario);
	}
	
}
