/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.PacienteDAO;
import DAO.StatusDAO;
import Model.Paciente;
import Model.Status;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author yuricampos
 */
public class LoginPaciente extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String usuario = request.getParameter("usuario");
            System.out.println(usuario);
            String senha = request.getParameter("senha");
            System.out.println(senha);
            HttpSession httpSession = request.getSession();
            PacienteDAO d = new PacienteDAO();
            Paciente resultado = (Paciente) d.verificaLogin(usuario, senha);
            
            if (resultado == null) {
                System.out.println("null");
                request.setAttribute("status", "erro");
                getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            } else {
                System.out.println("Entrou");
                httpSession.removeAttribute("nome");
                httpSession.removeAttribute("chaveFamilia");
                httpSession.removeAttribute("chaveMedico");
                httpSession.removeAttribute("email");
                httpSession.removeAttribute("id");
                httpSession.removeAttribute("login");
                httpSession.removeAttribute("senha");
                httpSession.setAttribute("nome", resultado.getNome());
                httpSession.setAttribute("chaveFamilia", resultado.getChaveFamilia());
                httpSession.setAttribute("chaveMedico", resultado.getChaveMedico());
                httpSession.setAttribute("email",resultado.getEmail());
                httpSession.setAttribute("senha", senha);
                Paciente p = new Paciente();
                Status s = new Status();
                p.setId(resultado.getId());
                s.setPaciente(p);
                StatusDAO sd =  new StatusDAO();
                List top = (List) sd.listar(s);
                request.setAttribute("top", top);
                int idString = resultado.getId();
                httpSession.setAttribute("id",String.valueOf(resultado.getId()));
                httpSession.setAttribute("login",resultado.getLogin());
                getServletContext().getRequestDispatcher("/homePaciente.jsp").forward(request, response);
                System.out.println("FOI!");


            }
            
            

        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            try {
                processRequest(request, response);
            } catch (Exception ex) {
                Logger.getLogger(LoginPaciente.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            try {
                processRequest(request, response);
            } catch (Exception ex) {
                Logger.getLogger(LoginPaciente.class.getName()).log(Level.SEVERE, null, ex);
            }
      
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
