/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.PacienteDAO;
import Model.Paciente;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
public class AcessarPaciente extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String idRequest = request.getParameter("id");
            int id = Integer.parseInt(idRequest);
            String chaveMedico = request.getParameter("chaveMedico");
            HttpSession httpSession = request.getSession();
            PacienteDAO d = new PacienteDAO();
            Paciente p = new Paciente();
            p.setChaveMedico(chaveMedico);
            p.setId(id);
            Paciente resultado = (Paciente) d.recuperarKeyMedico(p);
            if (resultado == null) {
                request.setAttribute("status", "naencontrado");
                getServletContext().getRequestDispatcher("/homeMedico.jsp").forward(request, response);
            } else {
                httpSession.removeAttribute("nomePaciente");
                httpSession.removeAttribute("idPaciente");
                httpSession.removeAttribute("chaveMedico");
                httpSession.setAttribute("nomePaciente", resultado.getNome());
                httpSession.setAttribute("chaveMedico", resultado.getChaveFamilia());
                httpSession.setAttribute("idPaciente", String.valueOf(resultado.getId()));
                getServletContext().getRequestDispatcher("/ListarStatus").forward(request, response);


            }
        } catch(Exception e){
                request.setAttribute("status", "naencontrado");
                getServletContext().getRequestDispatcher("/homeMedico.jsp").forward(request, response);
        }
            finally {
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
            processRequest(request, response);

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
            processRequest(request, response);

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
