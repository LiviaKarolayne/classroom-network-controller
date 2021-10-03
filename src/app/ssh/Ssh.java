package ssh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

public class Ssh {
	
	private String hostname;
	private String username;
	private String password;	
	
	public Ssh(String hostname, String username, String password) throws IOException {
		 
		this.hostname = hostname;
		this.username = username;
		this.password = password;		

	}	
	
	public String executar(String cmd) throws IOException {		
		
		String exit = "";
		
		Connection conexao = new Connection(hostname);		
		conexao.connect();
		
		boolean isAuthenticated = conexao.authenticateWithPassword(username, password);
		if (isAuthenticated == false)
			throw new IOException("Authentication failed.");
		
		Session sess = conexao.openSession();
		sess.execCommand(cmd);
		System.out.println("Here is some information about the remote host:");
				
		InputStream stdout = new StreamGobbler(sess.getStdout());
		BufferedReader br = new BufferedReader(new InputStreamReader(stdout));	
		
		while (true)
		{
			String line = br.readLine();
			if (line == null)
				break;
			System.out.println(line);
			exit += line + "\n";
		}

		System.out.println("ExitCode: " + sess.getExitStatus());
		sess.close();
		conexao.close();
		
		return exit;

	}
	

}
