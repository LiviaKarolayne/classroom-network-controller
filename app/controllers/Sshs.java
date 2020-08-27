package controllers;

import java.io.IOException;

import models.Computador;
import models.Laboratorio;
import models.Site;
import play.mvc.Controller;
import ssh.Ssh;

public class Sshs extends Controller {

	public static void form(String hostname, String username, String password, String saida) {
		render(hostname, username, password, saida);
	}
	
	public static void habilitarOuDesabilitarSite(Long idLab, Long idSite) throws IOException {
		Laboratorio laboratorio = Laboratorio.findById(idLab);
		Site site = Site.findById(idSite);
		
		String comandoDesligar = "./t2.sh down " + site.getDominio();
		String comandoLigar = "./t2.sh up " + site.getDominio();
		
		try {
			if (site.isHabilitado()) {
				System.out.print("desabilitar: "+executarComandoSSH(laboratorio, comandoDesligar));
				site.setHabilitado(false);
			} else {
				System.out.print("ligar: "+ executarComandoSSH(laboratorio, comandoLigar));
				site.setHabilitado(true);
			}
			site.save();			
			flash.success("Comando executado com sucesso");
		} catch (IOException e) {
			flash.error("Erro de conexão");
		}
		Laboratorios.detalharSites(laboratorio.id, "");
	}
	
	public static void ligarOuDesligarComp(Long idLab, Long idComp) throws IOException {
		Laboratorio laboratorio = Laboratorio.findById(idLab);
		Computador computador = Computador.findById(idComp);

		String comandoDesligar = "./t1.sh down " + computador.getMac();
		String comandoLigar = "./t1.sh up " + computador.getMac();
		System.out.println("mac: "+computador.getMac());
		
		try {
			if (computador.isLigado()) {
				System.out.print("desligar: "+executarComandoSSH(laboratorio, comandoDesligar));
				computador.setLigado(false);
				//System.out.print("desligar: "+ comandoLigar + " " +computador.mac);
			} else {
				System.out.print("ligar: "+ executarComandoSSH(laboratorio, comandoLigar));
				computador.setLigado(true);
				//System.out.print("ligar: "+ comandoLigar + " " +computador.mac);
			}
			computador.save();			
			flash.success("Comando executado com sucesso");
		} catch (IOException e) {
			flash.error("Erro de conexão");
		}	
		Laboratorios.detalharLaboratorio(laboratorio.id, "");
	}

	public static void ligarOuDesligarLab(Long idLab) {
		Laboratorio laboratorio = Laboratorio.findById(idLab);
		String comandoDesligarLab = "./t1.sh down ";
		String comandoLigarLab = "./t1.sh up ";

		try {
			if (laboratorio.isLigado()) {
				executarComandoSSH(laboratorio, comandoDesligarLab);

				for (Computador computador : laboratorio.computadores) {
					computador.setLigado(false);
				}
				laboratorio.setLigado(false);
			} else {
				executarComandoSSH(laboratorio, comandoLigarLab);
				for (Computador computador : laboratorio.computadores) {
					computador.setLigado(true);
				}
				laboratorio.setLigado(true);
			}
			laboratorio.save();
			flash.success("Comando executado com sucesso");
		} catch (IOException e) {
			flash.error("Erro de conexão");
		}
		Laboratorios.listar("");
	}

	private static String executarComandoSSH(Laboratorio laboratorio, String comando)
			throws IOException {
		Ssh ssh;
		String saida = "";
		String useradmin = "root";
		ssh = new Ssh(laboratorio.getIp(), useradmin , laboratorio.getPassword());
		saida += ssh.executar(comando);
		return saida;
	}

}
