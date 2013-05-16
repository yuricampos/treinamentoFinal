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
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author yuricampos
 */
public class AtualizarCadastroPaciente extends HttpServlet {

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
            String pacienteIDReq = (String) request.getSession(false).getAttribute("id");
            int id = Integer.parseInt(pacienteIDReq);
            String mail = request.getParameter("mail");
            String pass = request.getParameter("pass");
            if (mail.isEmpty() || pass.isEmpty() || mail == null || pass == null) {
                request.setAttribute("status", "embranco");
                getServletContext().getRequestDispatcher("/homePaciente.jsp").forward(request, response);

            } else {
                Paciente p = new Paciente();
                p.setId(id);
                p.setEmail(mail);
                p.setSenha(pass);
                PacienteDAO pdao = new PacienteDAO();
                pdao.atualizar(p);
                HttpSession httpSession = request.getSession();
                Paciente p1 = new Paciente();
                Status s = new Status();
                p1.setId(id);
                s.setPaciente(p1);
                StatusDAO sd = new StatusDAO();
                List top = (List) sd.listar(s);
                httpSession.removeAttribute("email");
                httpSession.removeAttribute("senha");
                httpSession.setAttribute("email", mail);
                httpSession.setAttribute("senha", pass);
                request.setAttribute("top", top);
                getServletContext().getRequestDispatcher("/homePaciente.jsp").forward(request, response);


            }


        } catch (Exception e) {
            request.setAttribute("status", "erroatualiza");
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
