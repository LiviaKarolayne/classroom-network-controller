package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Computador;
import models.Laboratorio;
import models.Site;
import play.mvc.Controller;

public class Laboratorios extends Controller {
	private static String mensagem;
	
	public static void form() {
		if(session.get("HANome") == null) {
			redirect("../../Autenticacao/login");
		}
		render();
	}

	public static void detalharLaboratorio(Long id, String mensagem) {
		if(session.get("HANome") == null) {
			redirect("../../Autenticacao/login");
		}
		Laboratorio laboratorio = Laboratorio.findById(id);
		render(laboratorio, mensagem);
	}
	
	public static void detalharSites(Long idLaboratorio, String mensagem) {
		if(session.get("HANome") == null) {
			redirect("../../Autenticacao/login");
		}
		Laboratorio laboratorio = Laboratorio.findById(idLaboratorio);
		List<Site> siteAll = Site.findAll();
		ArrayList<Site> sites = new ArrayList<Site>();
		for (Site s : siteAll) {			
			if(s.laboratorio.id == idLaboratorio) {
				sites.add(s);
			}
		}
		render(laboratorio, sites, mensagem);
	}

	public static void salvar(Laboratorio laboratorio) {
		laboratorio.setLigado(true);
		if(laboratorio.id == null) {
			mensagem = "Laboratório Adicionado com Sucesso";
		}else {
			mensagem = "Laboratório Editado com Sucesso";
		}
		laboratorio.save();
		listar(mensagem);
		
	}

	public static void listar(String mensagem) {
		if(session.get("HANome") == null) {
			redirect("../../Autenticacao/login");
		}
		List<Laboratorio> laboratorios = Laboratorio.findAll();		
		render(laboratorios, mensagem);
	}

	public static void remover(Long id) {
		Laboratorio laboratorio = Laboratorio.findById(id);
		
		for (Computador computador:  laboratorio.computadores) {
			computador.delete();
		}
		
		laboratorio.delete();				
		listar("Laboratório Removido com Sucesso");
	}
	
	public static void editar (Long id) {
		if(session.get("HANome") == null) {
			redirect("../../Autenticacao/login");
		}
		Laboratorio laboratorio = Laboratorio.findById(id);
		render("Laboratorios/form.html", laboratorio);
	}

}
