/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author yuricampos
 */
public class Paciente {
    private int id;
    private String nome;
    private String chaveMedico;
    private String chaveFamilia;
    private String login;
    private String senha;
    private String email;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the chaveMedico
     */
    public String getChaveMedico() {
        return chaveMedico;
    }

    /**
     * @param chaveMedico the chaveMedico to set
     */
    public void setChaveMedico(String chaveMedico) {
        this.chaveMedico = chaveMedico;
    }

    /**
     * @return the chaveFamilia
     */
    public String getChaveFamilia() {
        return chaveFamilia;
    }

    /**
     * @param chaveFamilia the chaveFamilia to set
     */
    public void setChaveFamilia(String chaveFamilia) {
        this.chaveFamilia = chaveFamilia;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
}
