/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.MedicoDAO;
import Model.Medico;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author yuricampos
 */
public class CadastroMedico extends HttpServlet {

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
            String crm = request.getParameter("crm");
            String nome = request.getParameter("nome");
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");
            if (crm.isEmpty() || nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                request.setAttribute("status", "naoautorizado");
                getServletContext().getRequestDispatcher("/indexMedico.jsp").forward(request, response);

            } else {
                String chaveCadastro = request.getParameter("chaveCadastro");
                MedicoDAO mdao1 = new MedicoDAO();
                if (mdao1.verificaAutorizacao(crm, chaveCadastro) == null) {
                    request.setAttribute("status", "naoautorizado");
                    getServletContext().getRequestDispatcher("/indexMedico.jsp").forward(request, response);
                } else {
                    Medico m = new Medico();
                    m.setCrm(crm);
                    m.setEmail(email);
                    m.setNome(nome);
                    m.setSenha(senha);
                    MedicoDAO mdao = new MedicoDAO();
                    mdao.inserir(m);
                    request.setAttribute("status", "cadastrado");
                    getServletContext().getRequestDispatcher("/indexMedico.jsp").forward(request, response);

                }


            }


        } catch (Exception e) {
            request.setAttribute("status", "jaexiste");
            getServletContext().getRequestDispatcher("/indexMedico.jsp").forward(request, response);

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
