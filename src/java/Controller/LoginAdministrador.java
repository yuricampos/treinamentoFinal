/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.MedicoDAO;
import DAO.PacienteDAO;
import DAO.StatusDAO;
import Model.Administrador;
import Model.Autorizacao;
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
public class LoginAdministrador extends HttpServlet {

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
            String usuario = request.getParameter("usuario");
            System.out.println(usuario);
            String senha = request.getParameter("senha");
            System.out.println(senha);
            HttpSession httpSession = request.getSession();
            MedicoDAO mdao = new MedicoDAO();
            Administrador a = new Administrador();
            a.setSenha(senha);
            a.setUsuario(usuario);
            Administrador resultado = (Administrador) mdao.verificaLoginAdmin(usuario, senha);
            Autorizacao s = new Autorizacao();
            MedicoDAO sdao = new MedicoDAO();
            List top = (List) sdao.listarAutorizacoes();
            request.setAttribute("top", top);
            if (resultado == null) {
                System.out.println("null!");
                request.setAttribute("status", "qualquer");
                getServletContext().getRequestDispatcher("/indexAdministrador.jsp").forward(request, response);
            } else {
                System.out.println("correto!");
                httpSession.setAttribute("usuario", resultado.getUsuario());
                httpSession.setAttribute("senha", resultado.getSenha());
                getServletContext().getRequestDispatcher("/homeAdministrador.jsp").forward(request, response);
            }

        } catch (Exception e) {

            request.setAttribute("status", "qualquer");
            getServletContext().getRequestDispatcher("/indexAdministrador.jsp").forward(request, response);
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
