$(document).ready( function() {
	$("#CadastroPaciente").validate({
		/* REGRAS DE VALIDAÇÃO DO FORMULÁRIO */
		rules:{
			nome:{
				required: true, /* Campo obrigatório */
                                minlength: 5    /* No mínimo 5 caracteres */
			},
			email:{
				required: true, /* Campo obrigatório */
                                email: true     /* Deverá ser um email válido */
			},
			login:{
				required: true, /* Campo obrigatório */
					minlength: 5      /* No mínimo 5 caracteres */
			},
			
			senha:{
				required: true, /* Campo obrigatório */
					minlength: 5      /* No mínimo 5 caracteres */
			},

		},

		/* DEFINIÇÃO DAS MENSAGENS DE ERRO */
		messages:{
			nome:{
				required: "Preencha o campo <u>Nome</u>",
				minlength: "O campo <u>Nome</u> deve conter no mínimo 5 caracteres"
			},
			email:{
				required: "Preencha o campo <u>Email</u>",
				email: "O campo <u>Email</u> só aceita emails válidos"
			},
			
			senha:{
				required: "Preencha o campo <u>Senha</u>",
				minlength: "O campo <u>Senha</u> deve conter no mínimo 5 caracteres"
			},
			
			login:{
				required: "Preencha o campo <u>Login</u>",
				minlength: "O campo <u>Login</u> deve conter no mínimo 5 caracteres"
			},

		}
		});
	});





