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
public class atualizarChaveMedico extends HttpServlet {

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
            String idReq = (String) request.getSession(false).getAttribute("id");
            int id = Integer.parseInt(idReq);
            PacienteDAO pdao = new PacienteDAO();
            Paciente p = new Paciente();
            p.setId(id);
            pdao.atualizarKeyMedico(p);
            Paciente p1 = (Paciente) pdao.recuperar(p);
            HttpSession httpSession = request.getSession();
            httpSession.removeAttribute("chaveFamilia");
            httpSession.removeAttribute("chaveMedico");
            httpSession.setAttribute("chaveFamilia", p1.getChaveFamilia());
            httpSession.setAttribute("chaveMedico", p1.getChaveMedico());
            Paciente p2 = new Paciente();
            Status s = new Status();
            p2.setId(id);
            s.setPaciente(p2);
            StatusDAO sd = new StatusDAO();
            List top = (List) sd.listar(s);
            request.setAttribute("top", top);
            getServletContext().getRequestDispatcher("/homePaciente.jsp").forward(request, response);


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
        } catch (SQLException ex) {
            Logger.getLogger(atualizarChaveMedico.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(atualizarChaveMedico.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (SQLException ex) {
            Logger.getLogger(atualizarChaveMedico.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(atualizarChaveMedico.class.getName()).log(Level.SEVERE, null, ex);
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
