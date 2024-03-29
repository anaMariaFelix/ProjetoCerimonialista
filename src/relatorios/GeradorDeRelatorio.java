package relatorios;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfWriter;

import baseDedados.CentralDeInformacoes;
import model.Evento;
import util.MetodosUtilizadosNoPrograma;

public class GeradorDeRelatorio {
	public static <PdfPTable> void obterProgramacaoDoMes(Month mes,CentralDeInformacoes centralDeInformacoes) {
		Document doc = new Document(PageSize.A4);
		
		try {
			PdfWriter.getInstance(doc, new FileOutputStream("relatorio.pdf"));
			
			doc.open();
			com.itextpdf.text.pdf.PdfPTable tabela = new com.itextpdf.text.pdf.PdfPTable(2);
			PdfPCell cabe = new PdfPCell(new Paragraph("                                               -Eventos do mês"));
			cabe.setColspan(2);
			cabe.setBackgroundColor(BaseColor.CYAN);
			tabela.addCell(cabe);
			
			ArrayList<Evento> arrayEventos = verificaSeTemEventoComUmMesEspecifico(mes,centralDeInformacoes);

			if( !arrayEventos.isEmpty()) {
				for(int i = 0;i < arrayEventos.size();i++) {
					Paragraph p = new Paragraph("-Evento:");
					tabela.addCell(p);
					p = new Paragraph("-Cliente:");
					tabela.addCell(p);
					p = new Paragraph("Nome: "+arrayEventos.get(i).getNome());
					tabela.addCell(p);
					p = new Paragraph("Nome: "+arrayEventos.get(i).getClienteAssociado().getNome());
					tabela.addCell(p);
					p = new Paragraph("Local: "+arrayEventos.get(i).getLocal());
					tabela.addCell(p);
					p = new Paragraph("Sexo: "+arrayEventos.get(i).getClienteAssociado().getSexo().name());
					tabela.addCell(p);
					p = new Paragraph("Data e hora: "+MetodosUtilizadosNoPrograma.converteDataLocalDateParaUmaDataString(arrayEventos.get(i).getDataHora()));
					tabela.addCell(p);
					p = new Paragraph("CPF: "+arrayEventos.get(i).getClienteAssociado().getCPF());
					tabela.addCell(p);
					p = new Paragraph("Id: "+Long.toString(arrayEventos.get(i).getId()));
					tabela.addCell(p);
					p = new Paragraph("Email: "+arrayEventos.get(i).getClienteAssociado().getEmail());
					tabela.addCell(p);
					p = new Paragraph("Foi Contratado: "+arrayEventos.get(i).getFoiContradoOuNao());
					tabela.addCell(p);
					p = new Paragraph(" ");
					tabela.addCell(p);
					p = new Paragraph(" ");
					tabela.addCell(p);
					p = new Paragraph(" ");
					tabela.addCell(p);
				
				
					
				}
			}
			doc.add(tabela);
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
