package model;

import java.sql.Date;;

public class Usuario {

	private String nome,cpf,sexo,dataNascimento,data_cadastro;
	private Cargo cargo;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	public String getData_cadastro() {
		return data_cadastro;
	}
	public void setData_cadastro(String data_cadastro) {
		this.data_cadastro = data_cadastro;
	}
	public Cargo getCargo() {
		return cargo;
	}
	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	
	
	
}
