<jsp:directive.page contentType="text/html; charset=UTF-8" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>

</head>
<body>

	<div id="container" class="container-fluid">
		<div id="alert"
			style="${not empty message ? 'display: block;' : 'display: none;'}"
			class="alert alert-dismissable ${alertType eq 1 ? 'alert-success' : 'alert-danger'}">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			${message}
		</div>

		<div id="top" class="row">
			<div class="col-md-3">
				<h3>Perfis</h3>
			</div>

			<div class="col-md-6">
				<div class="input-group h2">
					<input name="data[search]" class="form-control" id="search"
						type="text" placeholder="Pesquisar Usuarios"> <span
						class="input-group-btn">
						<button class="btn btn-danger" type="submit">
							<span class="glyphicon glyphicon-search"></span>
						</button>
					</span>
				</div>
			</div>

			<div class="col-md-3">
				<a href="/gerUsuarios/perfil/form"
					class="btn btn-danger pull-right h2"><span
					class="glyphicon glyphicon-plus" /></span>&nbspAdicionar Perfis</a>
			</div>
		</div>

		<hr />

		<div id="list" class="row">
			<div class="table-responsive col-md-12">
				<table class="table table-striped table-hover" cellspacing="0"
					cellpadding="0">
					<thead>
						<tr>
							<th>ID</th>
							<th>nome</th>
							<th>Usuario</th>
							<th>Editar</th>
							<th>Excluir</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="perfis" items="${perfis}">
							<tr>
								<td>${perfis.getId()}</td>
								<td>${perfis.getNome()}</td>
								<td>${perfis.getUsuario().getNome()}</td>
								
								<td class="actions"><a
									href="${pageContext.request.contextPath}/perfil/update?id=${perfis.getId()}">
										<span class="glyphicon glyphicon-edit"></span> Editar
								</a></td>
								<td class="actions">
									<form
										action="${pageContext.request.contextPath}/perfil/delete"
										method="POST">
										<input type="hidden" value="${perfis.getId()}" name="id">
										<input type="submit" value="excluir">
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