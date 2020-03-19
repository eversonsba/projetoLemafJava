<jsp:directive.page contentType="text/html; charset=UTF-8" />
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br">
	<head>
		
	</head>
	<body>
		
			
		<div id="container" class="container-fluid">
			<h3 class="page-header">${empty cargo ? "Adicionar Cargo" : "Editar Cargo"}</h3>

			<form action="${pageContext.request.contextPath}/cargo/${action}" method="POST">
				<input type="hidden" value="${cargo.getId()}" name="id">
				<div class="row">
					<div class="form-group col-md-4">
					<label for="name">Nome</label>
						<input type="text" class="form-control" id="nome" name="nome" 
							   autofocus="autofocus" placeholder="Nome do Cargo" 
							   required oninvalid="this.setCustomValidity('Por favor, informe o nome do Cargo')"
							   oninput="setCustomValidity('')"
							   value="${cargo.getNome()}">
					</div>


					</div>
					
				</div>
				<hr />
				<div id="actions" class="row pull-right">
					<div class="col-md-12">
						<a href="${pageContext.request.contextPath}/cargos" class="btn btn-default">Cancelar</a>
						<button type="submit"x>${not empty cargo ? "Alterar Cargo" : "Cadastrar Cargo"}</button>
					</div>
				</div>
			</form>
		</div>

		<script src="js/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
	</body>
</html>