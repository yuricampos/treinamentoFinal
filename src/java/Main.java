
import DAO.MedicoDAO;
import DAO.PacienteDAO;
import DAO.StatusDAO;
import Model.Medico;
import Model.Paciente;
import Model.Status;
import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author yuricampos
 */
public class Main {

    public static void main(String[] args) throws Exception {
        /**
         *
         *
         * Medico m = new Medico(); m.setCrm("123"); m.setNome("Medico 1");
         * m.setEmail("medico@medico1.com"); m.setSenha("123"); MedicoDAO mdao =
         * new MedicoDAO(); mdao.inserir(m); Paciente p = new Paciente();
         * p.setLogin("456"); p.setNome("Paciente 1"); p.setSenha("456");
         * p.setEmail("paciente@paciente1.com"); PacienteDAO pdao = new
         * PacienteDAO(); pdao.inserir(p);
         */
        /**
         * Medico m = new Medico(); m.setCrm("123"); m.setNome("Medico 1
         * Alterado"); m.setEmail("medico@medico1.com"); m.setSenha("123");
         * MedicoDAO mdao = new MedicoDAO(); mdao.atualizar(m);
         */
        /**
        Paciente p = new Paciente();
        p.setId(1);
        p.setLogin("456");
        p.setNome("Paciente 1 ATUALIZADO");
        p.setSenha("456");
        p.setEmail("paciente@paciente1.comaaa");
        PacienteDAO pdao = new PacienteDAO();
        pdao.atualizar(p);
         */
        
        /**
        Paciente p = new Paciente();
        p.setId(1);
        Paciente p1 = new Paciente();
        PacienteDAO pdao = new PacienteDAO();
        p1 = (Paciente) pdao.recuperar(p);
        System.out.println(p1.getNome());        
         */
        /**
        Paciente p = new Paciente();
        p.setId(1);
        p.setChaveFamilia("CJJQBZSHQ");
        Paciente p1 = new Paciente();
        PacienteDAO pdao = new PacienteDAO();
        p1 = (Paciente) pdao.recuperarKeyFamilia(p);
        System.out.println(p1.getNome());
         */
        /*
        Medico m = new Medico();
        m.setCrm("123");
        MedicoDAO mdao = new MedicoDAO();
        Medico m1 = new Medico();
        m1 = (Medico) mdao.recuperar(m);
        System.out.println(m1.getNome());
         */
  
        
        /**
         Status s = new Status();
         s.setDescricao("ABC");
         Medico m = new Medico();
         m.setCrm("123");
         s.setMedico(m);
         Paciente p = new Paciente();
         p.setId(1);
         s.setPaciente(p);
         StatusDAO sdao = new StatusDAO();
         sdao.inserir(s);
         */
        
        Status s = new Status();
        Paciente p = new Paciente();
        p.setId(1);
        s.setPaciente(p);
        StatusDAO sdao = new StatusDAO();
        ArrayList<Object> statuss = new ArrayList<Object>();
        statuss = (ArrayList<Object>) sdao.listar(s);
        Status h1 = new Status();
        for (int i = 0; i < statuss.size(); i++) {
            h1 = (Status) statuss.get(i);
            System.out.println(h1.getDescricao());
            System.out.println(h1.getData());
            System.out.println(h1.getHora());
        }
        



        /**
         * Status s = new Status(); s.setMedico(m); p.setId(1);
         * s.setPaciente(p); s.setDescricao("BLA BLA");
         */
    }
}
