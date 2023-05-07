package servirce;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import model.Evento;

public class GeradorDeRelatorio {
	public static void obterProgramacaoDoMes(Month mes,CentralDeInformacoes centralDeInformacoes) {
		Document doc = new Document(PageSize.A4);
		
		try {
			PdfWriter.getInstance(doc, new FileOutputStream("relatorio.pdf"));
			
			doc.open();
			
			ArrayList<Evento> arrayEventos = verificaSeTemEventoComUmMesEspecifico(mes,centralDeInformacoes);

			if( !arrayEventos.isEmpty()) {
				for(int i = 0;i < arrayEventos.size();i++) {
					Paragraph p = new Paragraph(arrayEventos.get(i).getNome());
					doc.add(p);
					p = new Paragraph(arrayEventos.get(i).getLocal());
					doc.add(p);
					p = new Paragraph(Long.toString(arrayEventos.get(i).getId()));
					doc.add(p);
					//p = new Paragraph(arrayEventos.get(i).getDataHora());
					//doc.add(p);
					//doc.add(p);
				}
			}
			doc.close();
			
		} catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();
		}
	
	}
	public static ArrayList<Evento> verificaSeTemEventoComUmMesEspecifico(Month mes,CentralDeInformacoes centralDeInformacoes) {
		
		ArrayList<Evento> eventos = centralDeInformacoes.getTodosEvento();
		ArrayList<Evento> eventosDoMes =  new ArrayList();
		
		for(int i = 0;i < eventos.size();i++) {
			LocalDateTime dataEvento =  eventos.get(i).getDataHora();
			Month mesEvento = dataEvento.getMonth();
			if(mesEvento.equals(mes)) {
				eventosDoMes.add(eventos.get(i));
				
			}
		}
	
		return eventosDoMes;
	}

}
