<jsp:directive.page contentType="text/html; charset=UTF-8" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
 <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<style>
<!--
table, th, td {
	border: 1px solid black;
}
-->
</style>

</head>
<body>
<%@ include file="menu.html" %>
	<div id="container" class="container-fluid">
		<div id="alert"
			style="${not empty message ? 'display: block;' : 'display: none;'}"
			class="alert alert-dismissable ${alertType eq 1 ? 'alert-success' : 'alert-danger'}">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			${message}
		</div>

		<div id="top" class="row">
			<div class="col-md-3">
				<h3>Usuarios</h3>
			</div>



			<div class="col-md-3">
				<a href="/gerUsuarios/usuario/form"
					class="btn btn-success pull-right h2"><span
					class="glyphicon glyphicon-plus" /></span>&nbspAdicionar Usuarios</a>
			</div>
		</div>

		<hr />

		<div id="list" class="row">
			<div "class="table-responsive col-md-12">
				<table class="table table-striped table-hover" cellspacing="0"
					cellpadding="0">
					<thead>
						<tr>
							<th>Nome</th>
							<th>CPF</th>
							<th>Data de Nascimento</th>
							<th>Sexo</th>
							<th>Cargo</th>
							<th>Data de Cadastro</th>
							<th>Editar</th>
							<th>Excluir</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="usuarios" items="${usuarios}">
							<tr>
								<td>${usuarios.getNome()}</td>
								<td>${usuarios.getCpf()}</td>
								<td>${usuarios.getDataNascimento()}</td>
								<td>${usuarios.getSexo()}</td>
								<td>${usuarios.getCargo().getNome()}</td>
								<td>${usuarios.getData_cadastro()}</td>

								<td class="actions"><a class="btn btn-warning"
									href="${pageContext.request.contextPath}/usuario/update?cpf=${usuarios.getCpf()}">
										<span class="glyphicon glyphicon-edit"></span> Editar
								</a></td>
								<td class="actions">
									<form
										action="${pageContext.request.contextPath}/usuario/delete"
										method="POST">
										<input type="hidden" value="${usuarios.getCpf()}" name="cpf">
										<input type="submit" class="btn btn-danger" value="Excluir">
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

			</div>
		</div>
	</div>

	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			// fecha o alert após 3 segundos
			setTimeout(function() {
				$("#alert").slideUp(500);
			}, 3000);

			// ao clicar no delete de algum post, pega o nome do usuário, 
			// o id do usuário e a ação (delete) e envia para o modal 
			$(".modal-remove").click(function() {
				var nome = $(this).attr('usuario-nome');
				var cpf = $(this).attr('usuario-cpf');
				$(".modal-body #hiddenValue").text("Usuario '" + nome + "'");
				$("#cpf").attr("value", cpf);
				$("#form").attr("action", "usuario/delete");
			})
		});
	</script>
</body>
</html>