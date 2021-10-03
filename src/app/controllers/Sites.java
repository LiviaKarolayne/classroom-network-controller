package controllers;

import models.Computador;
import models.Laboratorio;
import models.Site;
import play.mvc.Controller;

public class Sites extends Controller{
	private static String mensagem;
	
	public static void form() {
		if(session.get("HANome") == null) {
			redirect("../../Autenticacao/login");
		}
		render();
	}
	
	public static void editar(Long idSite, Long idLaboratorio) {
		if(session.get("HANome") == null) {
			redirect("../../Autenticacao/login");
		}
		Site site = Site.findById(idSite);
		Laboratorio laboratorio = Laboratorio.findById(idLaboratorio);
		renderTemplate("Sites/form.html",site, laboratorio);
	}
	
	public static void remover(Long id, Long idLaboratorio) {
		Site site = Site.findById(id);
		Laboratorio laboratorio = Laboratorio.findById(idLaboratorio);
		site.delete();
		Laboratorios.detalharSites(laboratorio.id, "Site Removido com Sucesso");
	}
	
	public static void salvar(Site site) {
		site.setHabilitado(true);
		if(site.id == null) {
			mensagem = "Site Adicionado com Sucesso";
		}else {
			mensagem = "Site Editado com Sucesso";
		}
		site.save();		
		Laboratorios.detalharSites(site.laboratorio.id, mensagem);
	}

}
