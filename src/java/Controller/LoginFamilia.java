/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.MedicoDAO;
import DAO.PacienteDAO;
import DAO.StatusDAO;
import Model.Medico;
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
public class LoginFamilia extends HttpServlet {

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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String idReq = request.getParameter("id");
            int id = Integer.parseInt(idReq);
            String chaveFamilia = request.getParameter("chaveFamilia");
            System.out.println(id);
            System.out.println(chaveFamilia);
            HttpSession httpSession = request.getSession();
            PacienteDAO d = new PacienteDAO();
            Paciente p = new Paciente();
            p.setId(id);
            p.setChaveFamilia(chaveFamilia);
            Paciente resultado = (Paciente) d.recuperarKeyFamilia(p);

            if (resultado == null) {
                System.out.println("null!");
                request.setAttribute("status", "erro");
                getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            } else {
                System.out.println("correto!");
                httpSession.removeAttribute("nomePaciente");
                httpSession.removeAttribute("idPaciente");
                httpSession.removeAttribute("chaveMedico");
                httpSession.setAttribute("nomePaciente", resultado.getNome());
                httpSession.setAttribute("chaveMedico", resultado.getChaveFamilia());
                httpSession.setAttribute("idPaciente", String.valueOf(resultado.getId()));
                httpSession.setAttribute("chaveFamilia", resultado.getChaveFamilia());
                httpSession.setAttribute("loginPaciente", resultado.getLogin());
                Status s = new Status();
                p.setId(resultado.getId());
                s.setPaciente(p);
                StatusDAO sdao = new StatusDAO();
                List top = (List) sdao.listar(s);
                request.setAttribute("top", top);
                getServletContext().getRequestDispatcher("/homeFamilia.jsp").forward(request, response);



            }

        } catch (Exception e) {
            System.out.println("null!");
            request.setAttribute("status", "erro");
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
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
