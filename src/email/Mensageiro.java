package email;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class Mensageiro {
	public static void enviarEmailParaCliente() {
		Email email = new SimpleEmail();

		try {
			email.setDebug(true);
			email.setSmtpPort(587);
			email.setHostName("smtp.gmail.com");
			email.setAuthentication("mensageiro811@gmail.com", "yazgcogoqjxgaxag");
			email.addTo("sidcleywilker@gmail.com"); // pode ser qualquer email
			email.setFrom("mensageiro811@gmail.com"); // será passado o email que você fará a autenticação
			email.setSubject("testando 123");
			email.setMsg("Teste de envio de email");
			email.send();
			System.out.println("Enviado com sucesso");

		} catch (EmailException e) {
			//e.printStackTrace();
			System.out.println("ERRO:\nFalha ao enviar email");
			System.out.println(e.getMessage());

		}

	}
	public static void main(String[] args) {
		Mensageiro.enviarEmailParaCliente();
	  }

	
	
	
}
