<%-- 
    Document   : homePaciente
    Created on : 16/05/2013, 15:37:58
    Author     : yuricampos
--%>

<%@page import="Model.Autorizacao"%>
<%@page import="Model.Status"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String usuario = (String) session.getAttribute("usuario");
    if (usuario == null) {
        String redirectURL = "loginAdministrador.jsp";
        response.sendRedirect(redirectURL);
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="js/jquery.js"></script>
        <script type="text/javascript" src="js/bootstrap.js"></script>
        <link rel="stylesheet" type="text/css" href="css/bootstrap-responsive.css" />
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
        <title>Int-Med Administrador</title>
        <style type="text/css">
            /* Override some defaults */
            html, body {
                background-color: #eee;
            }
            body {
                padding-top: 40px; 
            }
            .container {
                width: 300px;
            }

            /* The white background content wrapper */
            .container > .content {
                background-color: #fff;
                padding: 20px;
                margin: 0 -20px; 
                -webkit-border-radius: 10px 10px 10px 10px;
                -moz-border-radius: 10px 10px 10px 10px;
                border-radius: 10px 10px 10px 10px;
                -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.15);
                -moz-box-shadow: 0 1px 2px rgba(0,0,0,.15);
                box-shadow: 0 1px 2px rgba(0,0,0,.15);
            }

            .login-form {
                margin-left: 65px;
            }

            legend {
                margin-right: -50px;
                font-weight: bold;
                color: #404040;
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
                <a class="brand">Int-Med</a>
                <div class="nav-collapse collapse">

                    <ul class="pull-right">
                        <a  href="logoutadmin.jsp" class="btn">Logout </a>
                    </ul>
                </div>
            </div>
            <div class="well" style="margin-top: 3%">
                <%
                          String status = (String) request.getAttribute("status");
                          if (status == "jaexiste") {%>
                <div class="alert alert-error">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <strong>Erro!</strong> Já existe um cadastro para esse crm.
                </div>
                <%}%>


                <form class="form-horizontal" name="InserirAutorizacao" action="InserirAutorizacao" method="post">
                    <div class="control-group">
                        <label class="control-label" for="crm">CRM</label>
                        <div class="controls">
                            <input type="text" id="crm" name="crm" placeholder="Informe CRM para cadastro">
  
                        </div>
                    </div>

                    <div class="control-group">
                        <div class="controls">
                            <button type="submit" class="btn btn-primary">Inserir!</button>
                       
                        </div>
                    </div>
                </form>
                <table class="table">  
                    <tr>  
                        <td>CRM</td> 
                        <td>Chave</td>
                    </tr>  
                    <%
                        List lista = (List) request.getAttribute("top");
                        for (int i = 0; i < lista.size(); i++) {
                    %>  
                    <tr>  

                        <td> <%=((Autorizacao) lista.get(i)).getCrm()%> </td>
                        <td> <%=((Autorizacao) lista.get(i)).getChaveAutorizacao()%> </td>

                    </tr>    
                    <%
                        }
                    %>  
                </table>  


            </div>
    </body>
</html>
