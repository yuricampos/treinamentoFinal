/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import Model.Medico;
import Model.Paciente;

/**
 *
 * @author yuricampos
 */
public class PacienteDAO implements IObjectDAO {

    private final String SQL_LOGIN = "SELECT p.id as p_id, p.nome as p_nome, p.chaveMedico as p_chaveMedico, "
            + "p.chaveFamilia as p_chaveFamilia, "
            + "p.login as p_login, p.email as p_email FROM paciente AS p WHERE p.login = ? AND p.senha = ?";
    private final String SQL_INSERT = "INSERT INTO paciente VALUES(DEFAULT,?,?,?,?,?,?);";
    private final String SQL_UPDATE = "UPDATE paciente SET senha = ?, email = ? WHERE id = ?";
    private final String SQL_GET = "SELECT p.id as p_id, p.nome as p_nome, p.chaveMedico as p_chaveMedico, "
            + "p.chaveFamilia as p_chaveFamilia, "
            + "p.login as p_login, p.email as p_email FROM paciente AS p WHERE p.id = ?";
    private final String SQL_GET_BY_KEY_MEDICO = "SELECT p.id as p_id, p.nome as p_nome, p.chaveMedico as p_chaveMedico, "
            + "p.chaveFamilia as p_chaveFamilia, "
            + "p.login as p_login, p.email as p_email FROM paciente AS p WHERE p.chaveMedico = ? and p.id = ?";
    private final String SQL_GET_BY_KEY_FAMILIA = "SELECT p.id as p_id, p.nome as p_nome, p.chaveMedico as p_chaveMedico, "
            + "p.chaveFamilia as p_chaveFamilia, "
            + "p.login as p_login, p.email as p_email FROM paciente AS p WHERE p.chaveFamilia = ? and p.id = ?";
    private final String SQLUPDATEKEY_MEDICO = "UPDATE paciente SET chaveMedico = ? WHERE id = ?";
    private final String SQLUPDATEKEY_FAMILIA = "UPDATE paciente SET chaveFamilia = ? WHERE id = ?";
    private PreparedStatement ps;
    private ResultSet rs;

    public String gerarKey() {
        String letras = "ABCDEFGHIJKLMNOPQRSTUVYWXZ";

        Random random = new Random();

        String armazenaChaves = "";
        int index = -1;
        for (int i = 0; i < 9; i++) {
            index = random.nextInt(letras.length());
            armazenaChaves += letras.substring(index, index + 1);
        }
        return armazenaChaves;
    }

    public Object verificaLogin(String usuario, String senha) throws SQLException {
        this.ps = Conexao.getInstance().getConexao().prepareStatement(SQL_LOGIN);
        this.ps.setString(1, usuario);
        this.ps.setString(2, senha);
        this.rs = this.ps.executeQuery();
        this.rs.next();
        int size = -1;
        size = rs.getRow();
        if (size == 0) {
            return null;
        }
        return this.criarObjetoTemplate();
    }

    public Object criarObjetoTemplate() throws SQLException {
        Paciente output = new Paciente();
        output.setId(this.rs.getInt("p_id"));
        output.setNome(this.rs.getString("p_nome"));
        output.setChaveMedico(this.rs.getString("p_chaveMedico"));
        output.setLogin(this.rs.getString("p_login"));
        output.setEmail(this.rs.getString("p_email"));
        output.setChaveFamilia(this.rs.getString("p_chaveFamilia"));
        return output;
    }

    public void atualizarKeyMedico(Object input) throws SQLException {
        Paciente p = (Paciente) input;
        this.ps = Conexao.getInstance().getConexao().prepareStatement(SQLUPDATEKEY_MEDICO);
        this.ps.setString(1, gerarKey());
        this.ps.setInt(2, p.getId());
        this.ps.execute();
    }

    public void atualizarKeyFamilia(Object input) throws SQLException {
        Paciente p = (Paciente) input;
        this.ps = Conexao.getInstance().getConexao().prepareStatement(SQLUPDATEKEY_FAMILIA);
        this.ps.setString(1, gerarKey());
        this.ps.setInt(2, p.getId());
        this.ps.execute();
    }

    @Override
    public void inserir(Object input) throws Exception {
        Paciente p = (Paciente) input;
        this.ps = Conexao.getInstance().getConexao().prepareStatement(SQL_INSERT);
        this.ps.setString(1, p.getNome());
        this.ps.setString(2, gerarKey());
        this.ps.setString(3, gerarKey());
        this.ps.setString(4, p.getLogin());
        this.ps.setString(5, p.getSenha());
        this.ps.setString(6, p.getEmail());
        this.ps.execute();
    }

    @Override
    public void atualizar(Object input) throws Exception {
        Paciente p = (Paciente) input;
        this.ps = Conexao.getInstance().getConexao().prepareStatement(SQL_UPDATE);
        this.ps.setString(1, p.getSenha());
        this.ps.setString(2, p.getEmail());
        this.ps.setInt(3, p.getId());
        System.out.println(p.getId());
        System.out.println(p.getEmail());
        System.out.println(p.getSenha());
        this.ps.execute();
    }

    @Override
    public void apagar(Object input) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object recuperar(Object input) throws Exception {
        Paciente p = (Paciente) input;
        this.ps = Conexao.getInstance().getConexao().prepareStatement(SQL_GET);
        this.ps.setInt(1, p.getId());
        this.rs = this.ps.executeQuery();
        rs.next();
        return this.criarObjetoTemplate();
    }

    public Object recuperarKeyFamilia(Object input) throws Exception {
        Paciente p = (Paciente) input;
        this.ps = Conexao.getInstance().getConexao().prepareStatement(SQL_GET_BY_KEY_FAMILIA);
        this.ps.setString(1, p.getChaveFamilia());
        this.ps.setInt(2, p.getId());
        this.rs = this.ps.executeQuery();
        rs.next();
        int size = -1;
        size = rs.getRow();
        if (size == 0) {
            return null;
        }
        return this.criarObjetoTemplate();
    }

    public Object recuperarKeyMedico(Object input) throws Exception {
        Paciente p = (Paciente) input;
        this.ps = Conexao.getInstance().getConexao().prepareStatement(SQL_GET_BY_KEY_MEDICO);
        this.ps.setString(1, p.getChaveMedico());
        this.ps.setInt(2, p.getId());
        this.rs = this.ps.executeQuery();
        rs.next();
        int size = -1;
        size = rs.getRow();
        if (size == 0) {
            System.out.println("NAO ACHEI NADA");
            return null;
            
        }
        return this.criarObjetoTemplate();
    }

    @Override
    public Object listar(Object input) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object buscar(Object input) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
