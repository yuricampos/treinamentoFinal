<%-- 
    Document   : homePaciente
    Created on : 16/05/2013, 15:37:58
    Author     : yuricampos
--%>

<%@page import="Model.Status"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String nome = (String) session.getAttribute("nome");
    String chaveFamilia = (String) session.getAttribute("chaveFamilia");
    String chaveMedico = (String) session.getAttribute("chaveMedico");
    String email = (String) session.getAttribute("email");
    String login = (String) session.getAttribute("login");
    String id = (String) session.getAttribute("id");
    String senha = (String) session.getAttribute("senha");

    if (nome == null) {
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
        <title>Int-Med Paciente</title>
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
                <a class="brand">Int-Med</a>
                <div class="nav-collapse collapse">
                    <ul class="nav">
                        <li><a href="#tab1" data-toggle="tab">Meu Histórico</a></li>
                        <li><a href="#tab2" data-toggle="tab">Atualizar Dados</a></li>


                    </ul>
                    <ul class="pull-right">
                        <a  href="logout.jsp" class="btn">Logout </a>
                    </ul>
                </div>
            </div>
            <div class="tab-content" style="margin-top: 3"%>
                                                <%
                String embranco = (String) request.getAttribute("status");
                if (embranco == "embranco") {%>
            <div class="alert alert-error">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                <strong>Erro!</strong> Você não pode deixar seus dados em branco.
            </div>
            <%}%>
                                    <div class="well" style="background-color:#eee; margin-top:3px">
                    <p>Seu id: <%=id%> </p>
                    <p>Chave Família: <%=chaveFamilia%> </p>
                    <p>Chave Médico <%=chaveMedico%></p>
</div>
                
                <div class="tab-pane active" id="tab1">


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
                <div class="tab-pane" id="tab2">
                    <div class="row">
                        <div class="span9">
                            <div class="row">
                                <div class="span6">
                                    <form class="form-horizontal" name="login" action="AtualizarCadastroPaciente" method="post">
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
                                <div class="span3">
                                    <p><a href="atualizarChaveFamilia" class="btn btn-large btn-primary pull-center" type="button" >Gerar novo código para a familia!</a></p>
                                    <p><a href="atualizarChaveMedico" class="btn btn-large btn-primary " type="button" >Gerar novo código para o médico!</a></p>
                                </div>
                            </div>
                        </div>
                    </div>


                </div>

            </div>
        </div>
    </body>
</html>
