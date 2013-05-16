<%--
    Document   : homeFamilia
    Created on : 16/05/2013, 15:04:21
    Author     : yuricampos
--%>
<%@page import="Model.Status"%>
<%@page import="java.util.List"%>
<%
    String nome = (String) session.getAttribute("nomePaciente");
    String chaveMedico = (String) session.getAttribute("chaveMedico");
    String idPaciente = (String) session.getAttribute("idPaciente");
    String chaveFamilia = (String) session.getAttribute("chaveFamilia");
    String loginPaciente = (String) session.getAttribute("loginPaciente");
    if (idPaciente == null) {
        String redirectURL = "index.jsp";
        response.sendRedirect(redirectURL);
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Int-Med - Família</title>
        <link rel="stylesheet" type="text/css" href="css/bootstrap-responsive.css" />
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
        <script type="text/javascript" src="js/jquery.js"></script>
        <script type="text/javascript" src="js/bootstrap.js"></script>
        <style type="text/css">
            /* Override some defaults */
            html, body {
                background-color: #f2f3f4;
            }
            body {
                padding-top: 40px;
            }


        </style>
    </head>
    <body>
        <div class="navbar navbar-inverse navbar-fixed-top">
            <div class="navbar-inner">
                <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="brand" href="#">Int-Med</a>
                <div class="nav-collapse collapse">
                    <ul class="nav">
                        <a class="brand" href="#"> <abbr title="O id é utilizada pelo médico">ID: <%= idPaciente %></abbr></a>
                        <a class="brand" href="#"><abbr title="A chave é utilizada pelo médico ">Chave Médico: <%= chaveMedico %></abbr></a>
                    </ul>
                    <ul class="pull-right">
                        <a  href="logout.jsp" class="btn">Logout </a>
                    </ul>
                </div>
            </div>
        </div>
        <div id="tabela" style="margin-top: 2%">
                                     <a href="ListarStatusPaciente"  class="btn btn-success pull-right ">Atualiza</a>
                        <p> Veja o histórico abaixo</p>
                                    <table class="table">  
                            <tr>  
                                <td>Descrição</td> 
                                <td>Médico</td>
                                <td>Data</td>  
                                <td>Hora</td>
                            </tr>  
                            <%
                                List lista = (List) request.getAttribute("top");
                                for (int i = 0; i < lista.size(); i++) {
                            %>  
                            <tr>  

                                <td> <%=((Status) lista.get(i)).getDescricao()%> </td>
                                <td> <%=((Status) lista.get(i)).getMedico().getNome()%> </td>
                                <td> <%=((Status) lista.get(i)).getData()%> </td>
                                <td><%=((Status) lista.get(i)).getHora()%> </td>
                            </tr>    
                            <%
                                }
                            %>  
                        </table> 
        </div>
    </body>
</html>
