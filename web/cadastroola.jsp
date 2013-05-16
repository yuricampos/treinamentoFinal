<!DOCTYPE html>
<html>
    <head>
    <head>
        <title>Faça seu login</title>
        <link rel="stylesheet" type="text/css" href="css/bootstrap-responsive.css" />
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
        <script type="text/javascript" src="js/jquery.js"></script>
        <script type="text/javascript" src="js/validarcadastro.js"></script>
        <script type="text/javascript" src="js/jquery.validate.js"></script>
        <script type="text/javascript" src="js/bootstrap.js"></script>
    </head>
</head>

<body> 
    <div class="hero-unit">
        <h1>Faça o seu cadastro</h1>
        <p>        <%
            String status = (String) request.getAttribute("status");
            if (status == "jaexiste") {%>
            Cadastro já existente!
            <%}%>
        </p>
        <p><% if (status == "naoautorizado") {%>
            Nao estao autorizado! 
        <%}%>
        </p>


        <div class="tabbable"> 
            <ul class="nav nav-tabs">
                <li class="active"><a href="#tab1" data-toggle="tab">Médico</a></li>
                <li><a href="#tab2" data-toggle="tab">Paciente</a></li>
            </ul>

            <div class="tab-content">
                <div class="tab-pane active" id="tab1">
                    <h2>Cadastrando um médico</h2>
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
                                <button type="submit" class="btn btn-large btn-primary">Acessar!</button>
                            </div>
                        </div>

                    </form>
                </div>

                <div class="tab-pane" id="tab2">
                    <h2>Cadastrando um paciente</h2>
                    <form class="form-horizontal" name="CadastroPaciente" action="CadastroPaciente" method="post">
                        <div class="control-group">
                            <label class="control-label" for="usuario">Nome</label>
                            <div class="controls">
                                <input type="text" id="nome" name="nome" placeholder="nome">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label" for="login">Login</label>
                            <div class="controls">
                                <input type="text" id="login" name="login" placeholder="login">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label" for="email">e-Mail</label>
                            <div class="controls">
                                <input type="text" id="nome" name="email" placeholder="email">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label" for="senha">Senha</label>
                            <div class="controls">
                                <input type="password" id="nome" name="senha" placeholder="senha">
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
        </div>            
    </div>
</div>

</body>
</html>