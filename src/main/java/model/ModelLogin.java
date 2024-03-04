package model;

import java.io.Serializable;

public class ModelLogin implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private String email;
	private String login;
	private String senha;
	
	private boolean useradmin;
	
	private String perfil;
	
	
	
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	
	public String getPerfil() {
		return perfil;
	}
	
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public boolean getUseradmin() {
		return useradmin;
	}

	
	/*Lógica para vericar se o usuário já existe ou não*/
	public boolean isNovo() {
		if (this.id == null) {
			return true; /*Usuário é novo*/
		} else if (this.id != null && this.id > 0) {
			return false; /*Usuário já existe e será atualizado*/
		}
		return id == null;
	}
	
	


	public void setUseradmin(boolean useradmin) {
		this.useradmin = useradmin;
	}


	public String getSenha() {
		return senha;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getLogin() {
		return login;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	

}
