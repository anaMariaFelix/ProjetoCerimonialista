package model;

import enuns.TipoSexo;

public class Cliente {
	
	private String nome;
	private TipoSexo sexo;
	private String CPF;
	private String email;
	
	public Cliente(String nome,TipoSexo sexo,String cpf,String email) {
		this.nome = nome;
		this.sexo = sexo;
		CPF = cpf;
		this.email = email;
	}
	

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public TipoSexo getSexo() {
		return sexo;
	}
	public void setSexo(TipoSexo sexo) {
		this.sexo = sexo;
	}
	public String getCPF() {
		return CPF;
	}
	public void setCPF(String cPF) {
		CPF = cPF;
	}
	public String getEmail() {
		return email;
	}
	
	public String toString() {
		return "Cliente: "+ nome;
	}
	
	
	public static void main(String[] args) {
		System.out.print(System.currentTimeMillis());
	}
	
}
