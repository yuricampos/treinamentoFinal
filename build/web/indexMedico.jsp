<%-- 
    Document   : indexMedico
    Created on : 16/05/2013, 16:43:30
    Author     : yuricampos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Int-Med Médico</title>
        <link rel="stylesheet" type="text/css" href="css/bootstrap-responsive.css" />
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
        <script type="text/javascript" src="js/jquery.js"></script>
        <script type="text/javascript" src="js/bootstrap.js"></script>
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
        <div class="container">
            <div class="content">
                <div class="row">
                    <div class="login-form">
                        <center><img src="img/logo.png" alt="Logo"></center>

                        <h2>Login</h2>
                        <%
                            String status = (String) request.getAttribute("status");
                            if (status == "erro") {%>
                        <div class="alert alert-error">
                            <button type="button" class="close" data-dismiss="alert">&times;</button>
                            <strong>Erro!</strong> Login ou senha incorretos.
                        </div>
                        <%}%>
                        <%
                            String cadastro = (String) request.getAttribute("status");
                            if (cadastro == "cadastrado") {%>
                        <div class="alert alert-success">
                            <button type="button" class="close" data-dismiss="alert">&times;</button>
                            <strong>Sucesso!</strong> Cadastro feito com sucesso.
                        </div>
                        <%}%>
                                                <%
                            String autorizacao = (String) request.getAttribute("status");
                            if (autorizacao == "naoautorizado") {%>
                        <div class="alert">
                            <button type="button" class="close" data-dismiss="alert">&times;</button>
                            <strong>Atenção!</strong> Você não tem uma chave válida.
                        </div>
                        <%}%>
                        <form name="loginMedico" action="LoginMedico" method="post">
                            <fieldset>
                                <div class="clearfix">
                                    <input type="text" id="usuario" name="usuario" placeholder="Digite o CRM">
                                </div>
                                <div class="clearfix">
                                    <input type="password" id="senha" name="senha" placeholder="Senha">
                                </div>
                                <button type="submit" class="btn btn-large btn-primary">Login</button>
                                <a href="#medico-cadastrar" data-toggle="modal" class="btn btn-large btn-success">Cadastrar</a>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div id="medico-cadastrar" class="modal hide fade">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h3>Fazer o cadastro</h3>
            </div>
            <div class="modal-body">
                    <form class="form-horizontal" name="CadastroMedico" action="CadastroMedico" method="post">
                        <div class="control-group">
                            <label class="control-label" for="nome">Nome</label>
                            <div class="controls">
                                <input type="text" id="nome" name="nome" placeholder="nome">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label" for="chaveCadastro">Chave de Cadastro</label>
                            <div class="controls">
                                <input type="text" id="chaveCadastro" name="chaveCadastro" placeholder="chaveCadastro">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label" for="crm">CRM</label>
                            <div class="controls">
                                <input type="text" id="crm" name="crm" placeholder="crm">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label" for="email">e-Mail</label>
                            <div class="controls">
                                <input type="text" id="email" name="email" placeholder="email">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label" for="senha">Senha</label>
                            <div class="controls">
                                <input type="password" id="senha" name="senha" placeholder="senha">
                            </div>
                        </div>
                        <div class="control-group">
                            <div class="controls">
                                <button type="submit" class="btn btn-large btn-primary">Cadastrar</button>
                            </div>
                        </div>

                    </form>
            </div>
            <div class="modal-footer">
                <button class="btn" data-dismiss="modal" aria-hidden="true">Fechar</button>

            </div>
        </div>
    </div>
</body>
</html>
