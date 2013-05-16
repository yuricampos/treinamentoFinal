/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.MedicoDAO;
import DAO.PacienteDAO;
import Model.Medico;
import Model.Paciente;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author yuricampos
 */
public class AtualizarCadastroMedico extends HttpServlet {

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
            String crm = (String) request.getSession(false).getAttribute("crm");
            String mail = request.getParameter("mail");
            String pass = request.getParameter("pass");
            if (mail.isEmpty() || pass.isEmpty()) {
                request.setAttribute("status", "embranco");
                getServletContext().getRequestDispatcher("/homeMedico.jsp").forward(request, response);
            } else {
                Medico m = new Medico();
                m.setCrm(crm);
                m.setEmail(mail);
                m.setSenha(pass);
                MedicoDAO mdao = new MedicoDAO();
                mdao.atualizar(m);
                HttpSession httpSession = request.getSession();
                httpSession.removeAttribute("email");
                httpSession.removeAttribute("senha");
                httpSession.setAttribute("email", mail);
                httpSession.setAttribute("senha", pass);
                getServletContext().getRequestDispatcher("/homeMedico.jsp").forward(request, response);
            }


        } catch (Exception e) {
            request.setAttribute("status", "erroatualiza");
            getServletContext().getRequestDispatcher("/homeMedico.jsp").forward(request, response);
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
