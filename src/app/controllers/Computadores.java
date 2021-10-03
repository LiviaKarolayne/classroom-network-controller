package controllers;

import java.util.List;

import models.Computador;
import models.Laboratorio;
import play.mvc.Controller;

public class Computadores extends Controller{
	private static String mensagem;
	
	public static void form() {
		if(session.get("HANome") == null) {
			redirect("../../Autenticacao/login");
		}
		List<Laboratorio> laboratorios = Laboratorio.findAll();
		render(laboratorios);
	}
	
	public static void salvar(Computador computador) {		
		computador.setLigado(true);
		if(computador.id == null) {
			mensagem = "Computador Adicionado com Sucesso";
		}else {
			mensagem = "Computador Editado com Sucesso";
		}
		computador.save();
		Laboratorios.detalharLaboratorio(computador.laboratorio.id, mensagem);
	}
	
	public static void listar() {
		if(session.get("HANome") == null) {
			redirect("../../Autenticacao/login");
		}
		List<Computador> computadores = Computador.findAll();
		render(computadores);
	}
	
	public static void remover(Long id) {
		Computador computador = Computador.findById(id);
		computador.delete();
		Laboratorios.detalharLaboratorio(computador.laboratorio.id, "Computador Removido com Sucesso");
	}
	
	public static void editar(Long id) {
		if(session.get("HANome") == null) {
			redirect("../../Autenticacao/login");
		}
		Computador computador = Computador.findById(id);
		renderTemplate("Computadores/form.html", computador);
	}
	

}
