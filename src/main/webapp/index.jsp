<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<title>Curso JSP</title>

<style type="text/css">
/* Se você deseja adicionar estilos personalizados, adicione-os aqui */
</style>


</head>
<body class="d-flex justify-content-center align-items-center vh-100">

	<div class="text-center">
		<h1>BEM VINDO!</h1>

		<form action="ServletLogin1" method="POST"
			class="row g-3 needs-validation" novalidate>

			<input type="hidden" value="<%=request.getParameter("url")%>"
				name="url">

			<div class="col-md-6">
				<label class="form-label">Login</label> <input name="login"
					type="text" class="form-control" required>
				<div class="valid-feedback">Looks good!</div>
				<div class="invalid-feedback">Please provide a valid login.</div>
			</div>

			<div class="col-md-6">
				<label class="form-label">Senha</label> <input name="senha"
					type="password" class="form-control" required>
				<div class="valid-feedback">Looks good!</div>
				<div class="invalid-feedback">Please provide a valid password.</div>
			</div>

			<input type="submit" value="Acessar" class="btn btn-primary">

		</form>

		<br>
		<h5>${msg}</h5>
	
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>

	<script type="text/javascript">
		
		// Example starter JavaScript for disabling form submissions if there are invalid fields
		(() => {
		  'use strict'

		  // Fetch all the forms we want to apply custom Bootstrap validation styles to
		  const forms = document.querySelectorAll('.needs-validation')

		  // Loop over them and prevent submission
		  Array.from(forms).forEach(form => {
		    form.addEventListener('submit', event => {
		      if (!form.checkValidity()) {
		        event.preventDefault()
		        event.stopPropagation()
		      }

		      form.classList.add('was-validated')
		    }, false)
		  })
		})()
		
		</script>
</body>
</html>