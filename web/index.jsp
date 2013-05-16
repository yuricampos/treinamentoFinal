<%-- 
    Document   : index
    Created on : 16/05/2013, 14:17:41
    Author     : yuricampos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Int-Med</title>
        <link rel="stylesheet" type="text/css" href="css/bootstrap-responsive.css" />
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
        <script type="text/javascript" src="js/jquery.js"></script>
        <script type="text/javascript" src="js/bootstrap.js"></script>
        <script type="text/javascript" src="js/jquery.validate.js"></script>
        <script type="text/javascript" src="js/validarcadastro.js"></script>
        <style type="text/css">
            /* Override some defaults */
            html, body {
                background-color: #eee;
            }
            body {
                padding-top: 40px; 
            }


        </style>
    </head>
    <body>
        <div class="well" style="max-width: 400px; margin: 0 auto 10px; margin-top: 10%">
            <center><img src="img/logo.png" alt="Logo"></center>
            <%
                String status = (String) request.getAttribute("status");
                if (status == "erro") {%>
            <div class="alert alert-error">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                <strong>Erro!</strong> Login ou senha incorretos.
            </div>
            <%}%>
                        <%
                String jaexiste = (String) request.getAttribute("status");
                if (jaexiste == "jaexiste") {%>
            <div class="alert alert-error">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                <strong>Erro!</strong> Já existe cadastro com esses dados.
            </div>
            <%}%>
                                    <%
                String embranco = (String) request.getAttribute("status");
                if (embranco == "embranco") {%>
            <div class="alert alert-error">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                <strong>Erro!</strong> Não pode deixar campos em branco.
            </div>
            <%}%>
            <%
                String cadastro = (String) request.getAttribute("status");
                if (status == "cadastrado") {%>
            <div class="alert alert-success">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                <strong>Sucesso!</strong> Cadastro feito com sucesso.
            </div>
            <%}%>
            <button type="button" href="#paciente" data-toggle="modal" class="btn btn-large btn-block btn-primary">Eu sou um paciente</button>
            <button type="button" href="#familia" data-toggle="modal" class="btn btn-large btn-block btn-primary">Eu sou um familiar</button>
        </div>

        <div id="paciente" class="modal hide fade">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h3>Acessar área do paciente</h3>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" name="loginPaciente" action="LoginPaciente" method="post">
                    <div class="control-group">
                        <label class="control-label" for="usuario">Usuário</label>
                        <div class="controls">
                            <input type="text" id="usuario" name="usuario" placeholder="Usuário">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="senha">Senha</label>
                        <div class="controls">
                            <input type="password" id="senha" name="senha" placeholder="Senha">
                        </div>
                    </div>
                    <div class="control-group">
                        <div class="controls">
                            <button type="submit" class="btn btn-large btn-primary">Fazer meu login</button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button class="btn" data-dismiss="modal" aria-hidden="true">Fechar</button>
                <a data-dismiss="modal" aria-hidden="true" href="#paciente-cadastrar" data-toggle="modal" class="btn btn-success">Novo aqui? Fazer meu cadastro!</a>
            </div>
        </div>

        <div id="paciente-cadastrar" class="modal hide fade">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h3>Fazer o cadastro</h3>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" name="CadastroPaciente" action="CadastroPaciente" method="post">
                    <div class="control-group">
                        <label class="control-label" for="nome">Nome</label>
                        <div class="controls">
                            <input type="text" id="nome" name="nome" placeholder="Entre com o nome completo">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="login">Login</label>
                        <div class="controls">
                            <input type="text" id="login" name="login" placeholder="Entre com um login único">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="email">e-Mail</label>
                        <div class="controls">
                            <input type="text" id="nome" name="email" placeholder="Entre com um e-mail válido">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="senha">Senha</label>
                        <div class="controls">
                            <input type="password" id="nome" name="senha" placeholder="Entre com uma senha segura">
                        </div>
                    </div>
                    <div class="control-group">
                        <div class="controls">
                            <button type="submit" class="btn btn-success">Salvar Cadastro!</button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button class="btn" data-dismiss="modal" aria-hidden="true">Fechar</button>

            </div>
        </div>

        <div id="familia" class="modal hide fade">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h3>Acessar área da família</h3>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" name="loginFamilia" action="LoginFamilia" method="post">
                    <div class="control-group">
                        <label class="control-label" for="usuario"><abbr title="O id é fornecido pelo paciente">ID</abbr></label>
                        <div class="controls">
                            <input type="text" id="id" name="id" placeholder="Informe um id">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="senha"><abbr title="O chave é fornecida pelo paciente">Chave</abbr></label>
                        <div class="controls">
                            <input type="text" id="chaveFamilia" name="chaveFamilia" placeholder="Informe uma chave">
                        </div>
                    </div>
                    <div class="control-group">
                        <div class="controls">
                            <button type="submit" class="btn btn-large btn-primary">Acessar!</button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button class="btn" data-dismiss="modal" aria-hidden="true">Fechar</button>
            </div>
        </div>
    </body>
</html>
