<jsp:directive.page contentType="text/html; charset=UTF-8" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>

</head>
<body>


	<div id="container" class="container-fluid">
		<h3 class="page-header">${empty usuario ? "Adicionar Usuario" : "Editar Usuario"}</h3>

		<form action="${pageContext.request.contextPath}/usuario/${action}"
			method="POST">
			<div class="row">
				<div class="form-group col-md-4">
					<label for="name">Nome</label> <input type="text"
						class="form-control" id="nome" name="nome" autofocus="autofocus"
						placeholder="Nome do Usuario" required
						oninvalid="this.setCustomValidity('Por favor, informe o nome do Usuario')"
						oninput="setCustomValidity('')" value="${usuario.getNome()}">
					<label for="name">CPF</label> <input type="text"
						class="form-control" id="cpf" name="cpf" autofocus="autofocus"
						placeholder="CPF do Usuario" required
						oninvalid="this.setCustomValidity('Por favor, informe o CPF do Usuario')"
						oninput="setCustomValidity('')" value="${usuario.getCpf()}">
					<label for="name">Data de nascimento</label> <input type="text"
						class="form-control" id="data_nascimento" name="data_nascimento"
						autofocus="autofocus" placeholder="Data de nascimento" required
						oninvalid="this.setCustomValidity('Por favor, informe a data de Nascimento do Usuario')"
						oninput="setCustomValidity('')"
						value="${usuario.getDataNascimento()}"> <label for="name">Sexo</label>
					<input type="text" class="form-control" id="sexo" name="sexo"
						autofocus="autofocus" placeholder="Sexo: M ou F" required
						oninvalid="this.setCustomValidity('Por favor, informe o sexo do Usuario')"
						oninput="setCustomValidity('')" value="${usuario.getSexo()}">

					<label for="cargo">Cargo</label> <select id="nome"
						class="form-control selectpicker" name="cargo" required
						oninvalid="this.setCustomValidity('Por favor, informe o cargo')"
						oninput="setCustomValidity('')">
						<option value="" disabled ${not emptycargo ? "" : "selected"}>Selecione
							um Cargo</option>
						<c:forEach var="cargos" items="${cargos}">
							<option value="${cargos.getId()}"
								${usuario.getCargo().getId() == cargos.getId() ? "selected" : ""}>
								${cargos.getNome()}</option>
						</c:forEach>
					</select>
				</div>


			</div>
	</div>
	<hr />
	<div id="actions" class="row pull-right">
		<div class="col-md-12">
			<a href="${pageContext.request.contextPath}/usuarios"
				class="btn btn-default">Cancelar</a>
			<button type="submit" x>${not empty usuario ? "Alterar Usuario" : "Cadastrar Usuario"}</button>
		</div>
	</div>
	</form>
	</div>

	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>