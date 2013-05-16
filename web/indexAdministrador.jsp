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
        <title>Int-Med Administrador</title>
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
                                                            if (status == "qualquer") {%>
                        <div class="alert alert-error">
                            <button type="button" class="close" data-dismiss="alert">&times;</button>
                            <strong>Erro!</strong> Login ou senha incorretos.
                        </div>
                        <%}%>

                        
                        <form name="LoginAdministrador" action="LoginAdministrador" method="post">
                            <fieldset>
                                <div class="clearfix">
                                    <input type="text" id="usuario" name="usuario" placeholder="Digite o usuário">
                                </div>
                                <div class="clearfix">
                                    <input type="password" id="senha" name="senha" placeholder="Senha">
                                </div>
                                <button type="submit" class="btn btn-large btn-primary">Login</button>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        
</body>
</html>
