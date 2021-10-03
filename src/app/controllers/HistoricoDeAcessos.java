package controllers;

import java.util.List;

import models.HistoricoDeAcesso;
import play.mvc.Controller;

public class HistoricoDeAcessos extends Controller{
	
	public static void listarHistoricoUsuario() {
		if(session.get("HANome") == null) {
			redirect("../../Autenticacao/login");
		}/*else if (session.get("HATipo").equals("Aluno")) {
			redirect("../../Laboratorios/listar");
		}*/
		List<HistoricoDeAcesso> HAs = HistoricoDeAcesso.findAll();
		render(HAs);
	}
	
	public static void remover(Long id) {
		HistoricoDeAcesso HAs = HistoricoDeAcesso.findById(id);
		HAs.delete();
		listarHistoricoUsuario();
	}

}
