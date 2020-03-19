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


	<div id="container" class="container-fluid">
		<h3 class="page-header">${empty perfil ? "Adicionar Perfil" : "Editar Perfil"}</h3>

		<form action="${pageContext.request.contextPath}/perfil/${action}"
			method="POST">
			<div class="row">
				<div class="form-group col-md-4">
					<label for="name">Nome</label> <input type="text"
					
						class="form-control" id="nome" name="nome" autofocus="autofocus"
						placeholder="Nome do Perfil" required
						oninvalid="this.setCustomValidity('Por favor, informe o nome do Perfil')"
						oninput="setCustomValidity('')" value="${perfil.getNome()}">
					<input type="hidden" value="${perfil.getId()}" name="id">
					<label for="perfil">Usuario</label> <select id="nome"
						class="form-control selectpicker" name="usuario" required
						oninvalid="this.setCustomValidity('Por favor, informe o perfil')"
						oninput="setCustomValidity('')">
						<option value="" disabled ${not emptycargo ? "" : "selected"}>Selecione
							um Usuario</option>
						<c:forEach var="usuarios" items="${usuarios}">
							<option value="${usuarios.getCpf()}"
								${perfil.getUsuario().getCpf() == usuarios.getCpf() ? "selected" : ""}>
								${usuarios.getNome()}</option>
						</c:forEach>
					</select>
				</div>


			</div>
	</div>
	<hr />
	<div id="actions" class="row pull-right">
		<div class="col-md-12">
			<a href="${pageContext.request.contextPath}/perfis"
				class="btn btn-default">Cancelar</a>
			<button type="submit" x>${not empty usuario ? "Alterar Perfis" : "Cadastrar Perfis"}</button>
		</div>
	</div>
	</form>
	</div>

	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>