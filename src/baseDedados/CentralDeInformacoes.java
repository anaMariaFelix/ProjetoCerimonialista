package baseDedados;

import java.time.LocalDateTime;
import java.util.ArrayList;

import model.Cliente;
import model.Evento;

public class CentralDeInformacoes {
	private ArrayList<Cliente> todosOsClientes = new ArrayList();
	private ArrayList<Evento> todosEvento = new ArrayList();
	
	
	public ArrayList<Evento> getTodosEvento() {
		return todosEvento;
	}

	public ArrayList<Cliente> getTodosOsClientes(){
		return todosOsClientes;
	}
	
	public void setTodosOsClientes(ArrayList<Cliente> todosOsClientes) {
		this.todosOsClientes = todosOsClientes;
	}
	
	public boolean adicionarCliente(Cliente cliente) {
		if(recuperarClientePorEmail(cliente.getEmail()) == null) {
			todosOsClientes.add(cliente);
			return true;
		}
		
		return false;
	}
	
	public Cliente recuperarClientePorEmail(String email) {
		for(Cliente cliente: todosOsClientes) {
			if(cliente.getEmail().equals(email)){
				return cliente;
			}
		}
		return null;
	}
	
	public boolean adicionarEvento(Evento evento) {
		LocalDateTime dataHoraAtual = LocalDateTime.now();
		if(!todosEvento.contains(evento) && evento.getDataHora().isAfter(dataHoraAtual)) {
			todosEvento.add(evento);
			return true;
			
		}
		return false;
	}
	
	public Evento recuperarEventoPeloId(long id) {
		for(Evento e: todosEvento) {
			if(e.getId() == id) {
				return e;
			}
		}
		
		return null;
	}
	
	public boolean existeCliente(String email) {
		for(Cliente c: todosOsClientes) {
			if(c.getEmail().equals(email)){
				return true;
			}
		}
		return false;
	
	}
	
	public ArrayList recuperarEventoDeUmCliente(String nomeDoCliente) {

		if(!existeCliente(nomeDoCliente)) {
			return null;
		}else {
			ArrayList<Evento> eventosDoCliente = new ArrayList();
			for(Evento eventos: todosEvento) {
				if(eventos.getClienteAssociado().getNome().equals(nomeDoCliente)) {
					eventosDoCliente.add(eventos);
				}
			}
			return eventosDoCliente;
		}
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
