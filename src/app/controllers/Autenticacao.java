package controllers;

import java.nio.file.WatchService;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.sun.javafx.scene.traversal.Hueristic2D;

import models.CalendarClass;
import models.DadosSUAP;
import models.HistoricoDeAcesso;
import models.Usuario;
import play.libs.WS;
import play.mvc.Controller;

public class Autenticacao extends Controller {		
	public static void login() {
		render();
	}

	public static void autenticar(String matricula, String senha) {
		WS.HttpResponse resposta;

		String urlToken = "https://suap.ifrn.edu.br/api/v2/autenticacao/token/";
		String urlDados = "https://suap.ifrn.edu.br/api/v2/minhas-informacoes/meus-dados/";

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("username", matricula);
		parametros.put("password", senha);

		resposta = WS.url(urlToken).params(parametros).post();
		if (resposta.success()) {
			String token = resposta.getJson().getAsJsonObject().get("token").getAsString();
			Map<String, String> header = new HashMap<String, String>();
			header.put("X-CSRFToken", token);
			header.put("Authorization", "JWT " + token);

			resposta = WS.url(urlDados).headers(header).get();

			DadosSUAP dadosSUAP = new Gson().fromJson(resposta.getString(), DadosSUAP.class);
			HistoricoDeAcesso HA = HistoricoDeAcesso.find("matricula = ?", dadosSUAP.getMatricula()).first();
			Usuario usuario = Usuario.find("matricula = ? and senha = ?", matricula, senha).first();			
			
			HistoricoDeAcesso historicoDeAcesso = new HistoricoDeAcesso();
			CalendarClass calendar = new CalendarClass();
			
			historicoDeAcesso.setDataDeAcesso(calendar.getData());
			historicoDeAcesso.setHoraDeAcesso(calendar.getHour());			
			historicoDeAcesso.setMinutoDeAcesso(calendar.getMinute());
			historicoDeAcesso.setHoraDeAcessoFinal(calendar.getHour());
			historicoDeAcesso.setMinutoDeAcessoFinal(calendar.getMinute());
			
			Long id = Long.parseLong(dadosSUAP.getId());
			historicoDeAcesso.setIdHA(id);
			historicoDeAcesso.setNome(dadosSUAP.getNome_usual());
			historicoDeAcesso.setMatricula(dadosSUAP.getMatricula());
			// historicoDeAcesso.vinculo = dadosSUAP.vinculo.cargo.toLowerCase();
			historicoDeAcesso.setTipo(dadosSUAP.getTipo_vinculo().toLowerCase());
			historicoDeAcesso.setEmail(dadosSUAP.getEmail());
			historicoDeAcesso.setUrl_foto_150x200("http://suap.ifrn.edu.br" + dadosSUAP.getUrl_foto_150x200());

			//renderText(resposta.getString());
			//renderText(dadosSUAP.getTipo_vinculo() + " " + historicoDeAcesso.getTipo());			
			
			if (!historicoDeAcesso.getTipo().equals("aluno") || usuario != null){
				session.put("HANome", historicoDeAcesso.getNome());	
				session.put("HATipo", historicoDeAcesso.getTipo());	
				session.put("HAFoto", historicoDeAcesso.getUrl_foto_150x200());
				session.put("HAId", historicoDeAcesso.getIdHA());
				historicoDeAcesso.save();
				session.put("BDId", historicoDeAcesso.id);
				
				if(historicoDeAcesso.getTipo().equals("aluno")){
					session.put("isAluno", "true");
				}else {
					session.put("isAluno", "false");
				}
				
				Laboratorios.listar("");			
			}else{ 
				flash.error("Acesso Negado"); 
				params.flash(); 
				login();
			}			 

		} else {
			flash.error("Matrícula ou senha incorretos");
			params.flash();
			login();
		}
	}

	public static void logout() {
		CalendarClass calendar = new CalendarClass();
		Long id = Long.parseLong(session.get("BDId"));
		HistoricoDeAcesso historicoDeAcesso = HistoricoDeAcesso.findById(id);		
		historicoDeAcesso.setHoraDeAcessoFinal(calendar.getHour());
		historicoDeAcesso.setMinutoDeAcessoFinal(calendar.getMinute());
		historicoDeAcesso.save();		
		session.clear();
		login();
	}		
}
