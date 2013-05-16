/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Administrador;
import Model.Autorizacao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Model.Medico;
import Model.Status;
import java.util.Random;

/**
 *
 * @author yuricampos
 */
public class MedicoDAO implements IObjectDAO {

    private final String SQL_LOGIN = "SELECT m.crm as m_crm, m.nome as m_nome, m.email as m_email FROM medico AS m WHERE m.crm = ? AND m.senha = ?";
    private final String SQL_INSERT = "INSERT INTO medico VALUES(?,?,?,?);";
    private final String SQL_INSERT_KEY = "INSERT INTO autorizacoes VALUES(?,?)";
    private final String SQL_INSERT_ADMIN =  "INSERT INTO administrador VALUES(?,?)";
    private final String SQL_LOGIN_ADMIN = "SELECT a.usuario as a_usuario, a.senha as a_senha FROM administrador as a WHERE a.usuario = ? AND a.senha= ?";
    private final String SQL_GET_KEYS = "SELECT a.crm as a_crm, a.chaveAutorizacao as a_chaveAutorizacao FROM autorizacoes as a";
     private final String SQL_GET_KEY = "SELECT a.crm as a_crm, a.chaveAutorizacao as a_chaveAutorizacao FROM autorizacoes as a WHERE a.crm = ? AND a.chaveAutorizacao = ?";
    private final String SQL_UPDATE = "UPDATE medico SET email = ?, senha = ? WHERE crm = ?";
    private final String SQL_BUSCAR = "SELECT m.crm as m_crm, m.nome as m_nome, m.email as m_email FROM medico AS m WHERE m.nome LIKE ?";
    private final String SQL_GETALL = "SELECT m.crm as m_crm, m.nome as m_nome, m.email as m_email FROM medico AS m";
    private final String SQL_GET = "SELECT m.crm as m_crm, m.nome as m_nome, m.email as m_email FROM medico AS m WHERE m.crm = ?";
    private PreparedStatement ps;
    private ResultSet rs;

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
    
        public Object verificaAutorizacao(String crm, String chave) throws SQLException {
        this.ps = Conexao.getInstance().getConexao().prepareStatement(SQL_GET_KEY);
        this.ps.setString(1, crm);
        this.ps.setString(2, chave);
        this.rs = this.ps.executeQuery();
        this.rs.next();
        int size = -1;
        size = rs.getRow();
        if (size == 0) {
            return null;
        }
        return this.criarObjetoTemplateAutorizacao();
    }
    
        public Object verificaLoginAdmin(String usuario, String senha) throws SQLException {
        this.ps = Conexao.getInstance().getConexao().prepareStatement(SQL_LOGIN_ADMIN);
        this.ps.setString(1, usuario);
        this.ps.setString(2, senha);
        this.rs = this.ps.executeQuery();
        this.rs.next();
        int size = -1;
        size = rs.getRow();
        if (size == 0) {
            return null;
        }
        Administrador a = new Administrador();
        a.setUsuario(usuario);
        a.setSenha(senha);
        return a;
    }

    public Object criarObjetoTemplate() throws SQLException {
        Medico output = new Medico();
        output.setNome(this.rs.getString("m_nome"));
        output.setCrm(this.rs.getString("m_crm"));
        output.setEmail(this.rs.getString("m_email"));
        return output;
    }

    public Object criarObjetoTemplateAutorizacao() throws SQLException {
        Autorizacao output = new Autorizacao();
        output.setCrm(this.rs.getString("a_crm"));
        output.setChaveAutorizacao(this.rs.getString("a_chaveAutorizacao"));
        return output;
    }

    public ArrayList<Object> buscarVariosObjetosTemplate() throws SQLException, Exception {
        ArrayList<Object> output = new ArrayList<>();
        while (this.rs.next()) {
            output.add((Status) this.criarObjetoTemplate());
        }

        return output;
    }

    public ArrayList<Object> buscarVariosObjetosTemplateAutorizacao() throws SQLException {
        ArrayList<Object> output = new ArrayList<>();
        while (this.rs.next()) {
            output.add((Autorizacao) this.criarObjetoTemplateAutorizacao());
        }

        return output;
    }

    @Override
    public void inserir(Object input) throws Exception {
        Medico m = (Medico) input;
        this.ps = Conexao.getInstance().getConexao().prepareStatement(SQL_INSERT);
        this.ps.setString(1, m.getCrm());
        this.ps.setString(2, m.getNome());
        this.ps.setString(3, m.getEmail());
        this.ps.setString(4, m.getSenha());
        this.ps.execute();
    }

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

    public void inserirKey(String crm) throws Exception {
        this.ps = Conexao.getInstance().getConexao().prepareStatement(SQL_INSERT_KEY);
        this.ps.setString(1, crm);
        this.ps.setString(2, gerarKey());
        this.ps.execute();
    }

    @Override
    public void atualizar(Object input) throws Exception {
        Medico m = (Medico) input;
        this.ps = Conexao.getInstance().getConexao().prepareStatement(SQL_UPDATE);
        this.ps.setString(3, m.getCrm());
        this.ps.setString(1, m.getEmail());
        this.ps.setString(2, m.getSenha());
        this.ps.execute();
    }

    @Override
    public void apagar(Object input) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object recuperar(Object input) throws Exception {
        this.ps = Conexao.getInstance().getConexao().prepareStatement(SQL_GET);
        Medico m = (Medico) input;
        this.ps.setString(1, m.getCrm());
        this.rs = this.ps.executeQuery();
        if (!this.rs.next()) {
            throw new Exception("nao encontrado.");
        }

        return this.criarObjetoTemplate();
    }

    @Override
    public ArrayList<Object> listar(Object input) throws Exception {
        this.ps = Conexao.getInstance().getConexao().prepareStatement(SQL_GETALL);
        this.rs = this.ps.executeQuery();
        return this.buscarVariosObjetosTemplate();
    }

    public Object listarAutorizacoes() throws Exception {
        this.ps = Conexao.getInstance().getConexao().prepareStatement(SQL_GET_KEYS);
        this.rs = this.ps.executeQuery();
        return this.buscarVariosObjetosTemplateAutorizacao();
    }

    @Override
    public Object buscar(Object input) throws Exception {
        String aux = "%" + (String) input + "%";
        this.ps = Conexao.getInstance().getConexao().prepareStatement(SQL_BUSCAR);
        this.ps.setString(1, aux);
        this.rs = this.ps.executeQuery();
        if (this.rs.isLast()) {
            return this.criarObjetoTemplate();
        } else {
            return this.buscarVariosObjetosTemplate();
        }
    }
}
