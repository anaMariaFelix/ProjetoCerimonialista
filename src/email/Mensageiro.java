package email;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import model.Evento;
import util.MetodosUtilizadosNoPrograma;

public class Mensageiro {
	public static void enviarEmailParaCliente(Evento evento,String msg) {
		Email email = new SimpleEmail();

		try {
			System.out.println("enviando email ...");
//			email.setDebug(true);
			email.setHostName("smtp.gmail.com");
			email.setAuthentication("mensageiro811@gmail.com", "yazgcogoqjxgaxag");
			email.setSSL(true);
			email.addTo(evento.getClienteAssociado().getEmail()); // pode ser qualquer email
			email.setFrom("mensageiro811@gmail.com"); // será passado o email que você fará a autenticação
			email.setSubject("Contrato");
			email.setMsg("Evento: "+evento.getNome()+""
					+ "\nId:"+String.valueOf(evento.getId())+""
					+ "\nLocal: "+evento.getLocal()+""
					+ "\nData e Hora: "+MetodosUtilizadosNoPrograma.converteDataLocalDateParaUmaDataString(evento.getDataHora())+"\n"+msg);
			email.send();
			
			System.out.println("Enviado com sucesso");

		} catch (EmailException e) {
			System.out.println("ERRO:\nFalha ao enviar email");
			System.out.println(e.getMessage());

		}

	}
	
	
}
