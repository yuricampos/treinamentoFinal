/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Medico;
import Model.Paciente;
import Model.Status;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author yuricampos
 */
public class StatusDAO implements IObjectDAO {

    private final String SQL_INSERT = "INSERT INTO status VALUES(DEFAULT,?,?,?,DEFAULT,DEFAULT);";
    private final String SQL_GET = "SELECT s.id as s_id, s.paciente as s_paciente, s.medico as s_medico, "
            + " s.descricao as s_descricao, s.data as s_data, s.hora as s_hora FROM status AS s WHERE s.paciente = ?";
    
    private PreparedStatement ps;
    private ResultSet rs;

    public Object criarObjetoTemplate() throws SQLException, Exception {
        Status output = new Status();
        output.setId(this.rs.getInt("s_id"));
        Medico m = new Medico();
        MedicoDAO mdao = new MedicoDAO();
        Medico m1 = new Medico();
        m.setCrm(this.rs.getString("s_medico"));
        m1 = (Medico) mdao.recuperar(m);
        output.setMedico(m1);
        Paciente p = new Paciente();
        p.setId(this.rs.getInt("s_paciente"));
        output.setPaciente(p);
        output.setDescricao(this.rs.getString("s_descricao"));
        String data = converteData(this.rs.getDate("s_data"));
        output.setData(data);
        Format formatter = new SimpleDateFormat("HH:mm:ss");
        String hora = formatter.format(this.rs.getTime("s_hora"));
        output.setHora(hora);
        return output;
    }

    public ArrayList<Object> buscarVariosObjetosTemplate() throws SQLException, Exception {
        ArrayList<Object> output = new ArrayList<>();
        while (this.rs.next()) {
            output.add((Status) this.criarObjetoTemplate());
        }

        return output;
    }

    public void inserir(Object input) throws Exception {
        Status s = (Status) input;
        this.ps = Conexao.getInstance().getConexao().prepareStatement(SQL_INSERT);
        this.ps.setInt(1, s.getPaciente().getId());
        this.ps.setString(2, s.getMedico().getCrm());
        this.ps.setString(3, s.getDescricao());
        this.ps.execute();
    }

    public void atualizar(Object input) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void apagar(Object input) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Object recuperar(Object input) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Object listar(Object input) throws Exception {
        Status s = (Status) input;
        this.ps = Conexao.getInstance().getConexao().prepareStatement(SQL_GET);
        this.ps.setInt(1, s.getPaciente().getId());
        this.rs = this.ps.executeQuery();
        return this.buscarVariosObjetosTemplate();
    }

    public Object buscar(Object input) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
     public static String converteData(Date d) {
        if (d == null) {
            return new String("");
        }
        return new SimpleDateFormat("dd/MM/yyyy").format(d);
    }
}
