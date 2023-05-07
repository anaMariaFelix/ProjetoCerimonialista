package servirce;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

public class Persistencia {
	private XStream xstream = new XStream(new DomDriver());
	private File arquivo;
	
	public Persistencia() {
		xstream.addPermission(AnyTypePermission.ANY);
	}
	
	public void salvarCentral(CentralDeInformacoes centralDeinformacoes,String nomeDoArquivo) {
		arquivo = new File(nomeDoArquivo+".xml");
		
		String xml = xstream.toXML(centralDeinformacoes);
		try {
			if(!arquivo.exists()) {
				arquivo.createNewFile();
			}
			PrintWriter gravar = new PrintWriter(arquivo);
			gravar.print(xml);
			gravar.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public CentralDeInformacoes recupearCentral(String nome) {
		arquivo = new File(nome+".xml");
		try {
			if(arquivo.exists()) {
				FileInputStream converte = new FileInputStream(arquivo);
				return (CentralDeInformacoes) xstream.fromXML(converte);
			}
		}catch (FileNotFoundException e){
			e.printStackTrace();
		}
		return new CentralDeInformacoes();
	}
	
	
	
	
	
	
	
	
	
}
