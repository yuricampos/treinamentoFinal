<%-- 
    Document   : homePaciente
    Created on : 16/05/2013, 15:37:58
    Author     : yuricampos
--%>

<%@page import="Model.Status"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String crm = (String) session.getAttribute("crm");
    String nome = (String) session.getAttribute("nome");
    String email = (String) session.getAttribute("email");
    String senha = (String) session.getAttribute("senha");
    if (crm == null) {
        String redirectURL = "index.jsp";
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
        <title>Int-Med Médico</title>
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
                    <ul class="nav">
                        <li><a href="#tab1" data-toggle="tab">Acessar Paciente</a></li>
                        <li><a href="#tab2" data-toggle="tab">Atualizar Dados</a></li>


                    </ul>
                    <ul class="pull-right">
                        <a  href="logoutmedico.jsp" class="btn">Logout </a>
                    </ul>
                </div>
            </div>
            <div class="tab-content" style="margin-top: 3%">
                                <%
                String embranco = (String) request.getAttribute("status");
                if (embranco == "embranco") {%>
            <div class="alert alert-error">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                <strong>Erro!</strong> Você não pode deixar seus dados em branco.
            </div>
            <%}%>
                <div class="tab-pane active" id="tab1">
<div class="well">
                <%
                String status = (String) request.getAttribute("status");
                if (status == "naencontrado") {%>
            <div class="alert alert-error">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                <strong>Erro!</strong> Dados do paciente incorretos.
            </div>
            <%}%>
                    <form class="form-horizontal" name="acessarPaciente" action="AcessarPaciente" method="post">
                        <div class="control-group">
                            <label class="control-label" for="usuario">ID</label>
                            <div class="controls">
                                <input type="text" id="id" name="id" placeholder="Usuário">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label" for="senha">Chave Medico</label>
                            <div class="controls">
                                <input type="text" id="chaveMedico" name="chaveMedico" placeholder="chaveMedico">
                            </div>
                        </div>
                        <div class="control-group">
                            <div class="controls">
                                <button type="submit" class="btn btn-large btn-primary">Acessar!</button>
                            </div>
                        </div>
                    </form>
</div>
                </div>
                    
                <div class="tab-pane" id="tab2">
                    <div class="well">
                                        <%
                String s2 = (String) request.getAttribute("status");
                if (s2 == "erroatualiza") {%>
            <div class="alert alert-error">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                <strong>Erro!</strong> Não foi possível atualizar o cadastro.
            </div>
            <%}%>
                    <form class="form-horizontal" name="login" action="AtualizarCadastroMedico" method="post">
                        <div class="control-group">
                            <label class="control-label" for="mail">Trocar o email</label>
                            <div class="controls">
                                <input type="text" id="mail" name="mail" placeholder="<%=email%>" value="<%=email%>">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label" for="pass">Trocar Senha</label>
                            <div class="controls">
                                <input type="password" id="pass" name="pass" placeholder="entre com a senha" value="<%=senha%>">
                            </div>
                        </div>		
                        <div class="control-group">
                            <div class="controls">
                                <button type="submit" class="btn btn-large btn-primary">Trocar!</button>

                            </div>
                        </div>
                    </form>
                            </div>


                </div>
            </div>
            </div>
        </div>
    </body>
</html>
