<%@page import="model.ModelLogin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- JSP - Standard Tag Library (JSTL)  -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<jsp:include page="head.jsp"></jsp:include>

<body>
	<!-- Pre-loader start -->
	<jsp:include page="theme-loader.jsp"></jsp:include>

	<!-- Pre-loader end -->
	<div id="pcoded" class="pcoded">
		<div class="pcoded-overlay-box"></div>
		<div class="pcoded-container navbar-wrapper">

			<jsp:include page="navbar.jsp"></jsp:include>

			<div class="pcoded-main-container">
				<div class="pcoded-wrapper">

					<jsp:include page="navbarmainmenu.jsp"></jsp:include>

					<div class="pcoded-content">

						<!-- Page-header start -->
						<jsp:include page="page-header.jsp"></jsp:include>

						<!-- Page-header end -->
						<div class="pcoded-inner-content">
							<!-- Main-body start -->
							<div class="main-body">
								<div class="page-wrapper">
									<!-- Page-body start -->
									<div class="page-body">



										<div class="row">
											<div class="col-sm-12">
												<!-- Basic Form Inputs card start -->
												<div class="card">
													<div class="card-header">
														<h5>Cadastro de Usuário</h5>
													</div>
													<div class="card-block">


														<form class="form-material" enctype="multipart/form-data"
															action="<%=request.getContextPath()%>/ServletUsuarioController"
															method="post" id="formuser">

															<input type="hidden" name="acao" id="acao" value="">

															<div class="form-group form-default form-static-label">
																<input type="text" name="id" id="id" readonly="readonly"
																	value="${modelLogin.id}" class="form-control" /> <span
																	class="form-bar"></span> <label class="float-label">ID:</label>
															</div>

															<div class="form-group form-default input-group mb-4">
																<div class="input-group-prepend">
																	<img id="fotoembase64" alt="Imagem User" src="" width="70px">
																</div>
																<input id="fileFoto" type="file" accept="image/*" onchange="visualizarImg('fotoembase64', 'fileFoto');" class="form-control-file" style="margin-top: 15px; margin-left: 5px">
															</div>

															<div class="form-group form-default form-static-label">
																<input type="email" name="email" id="email"
																	value="${modelLogin.email}" class="form-control"
																	required="required" /> <span class="form-bar"></span>
																<label class="float-label">Email: </label>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="text" name="nome" id="nome"
																	value="${modelLogin.nome}" class="form-control"
																	required="required" /> <span class="form-bar"></span>
																<label class="float-label">Nome:</label>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="text" name="login" id="login"
																	value="${modelLogin.login}" class="form-control"
																	required="srequired" /> <span class="form-bar"></span>
																<label class="float-label">Login:</label>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="password" name="senha" id="senha"
																	autocomplete="off" value="${modelLogin.senha}"
																	class="form-control" required="required" /> <span
																	class="form-bar"></span> <label class="float-label">Senha:</label>
															</div>

															<div class="form-group form-default form-static-label">
																<select class="form-control"
																	aria-label="Default select example" name="perfil">
																	<option disabled="disabled">[Selecione o
																		perfil]</option>

																	<option value="ADMIN"
																		<%ModelLogin modelLogin = (ModelLogin) request.getAttribute("modelLogin");

if (modelLogin != null && modelLogin.getPerfil().equalsIgnoreCase("ADMIN")) {
	out.print(" ");
	out.print("selected=\"selected\"");
	out.print(" ");
}%>>Admin</option>

																	<option value="SECRETARIA"
																		<%modelLogin = (ModelLogin) request.getAttribute("modelLogin");

if (modelLogin != null && modelLogin.getPerfil().equalsIgnoreCase("SECRETARIA")) {
	out.print(" ");
	out.print("selected=\"selected\"");
	out.print(" ");
}%>>Secretária</option>

																	<option value="AUXILIAR"
																		<%modelLogin = (ModelLogin) request.getAttribute("modelLogin");

if (modelLogin != null && modelLogin.getPerfil().equalsIgnoreCase("AUXILIAR")) {
	out.print(" ");
	out.print("selected=\"selected\"");
	out.print(" ");
}%>>Auxiliar</option>

																</select> <span class="form-bar"></span> <label
																	class="float-label">Perfil:</label>
															</div>


															<div class="form-group form-default form-static-label">
																<input type="radio" name="sexo" checked="checked"
																	value="MASCULINO"
																	<%modelLogin = (ModelLogin) request.getAttribute("modelLogin");
if (modelLogin != null && modelLogin.getSexo().equals("MASCULINO")) {
	out.print(" ");
	out.print("checked=\"checked\"");
	out.print(" ");

}%>>Masculino</>
																<input type="radio" name="sexo" value="FEMININO"
																	<%modelLogin = (ModelLogin) request.getAttribute("modelLogin");

if (modelLogin != null && modelLogin.getSexo().equals("FEMININO")) {
	out.print(" ");
	out.print("checked=\"checked\"");
	out.print(" ");

}%>>Feminino</>
															</div>

															<button type="button"
																class="btn btn-primary btn-round waves-effect waves-light"
																onclick="limparForm();">Novo</button>

															<button
																class="btn btn-success btn-round waves-effect waves-light">Salvar</button>

															<button type="button"
																class="btn btn-danger btn-round waves-effect waves-light"
																onclick="criaDeleteComAjax();">Excluir</button>

															<!-- Button trigger modal -->
															<button type="button" class="btn btn-warning btn-round"
																data-toggle="modal" data-target="#exampleModalUsuario">
																Pesquisar</button>



														</form>

													</div>
												</div>


											</div>


										</div>


										<span id="msg">${msg}</span>


										<div style="height: 400px; overflow: scroll;">
											<table class="table" id="tabelaresultadosview">

												<thead>
													<tr>
														<th scope="col">ID</th>
														<th scope="col">Nome</th>
														<th scope="col">Email</th>
														<th scope="col">Login</th>
													</tr>
												</thead>

												<tbody>
													<!-- IMPRIMINDO NA TELA AS INFORMAÇÕES DOS USUÁRIOS COM JSTL -->
													<c:forEach items='${modelLogins}' var='ml'>
														<tr>
															<td><c:out value="${ml.id}"></c:out></td>
															<td><c:out value="${ml.nome}"></c:out></td>
															<td><c:out value="${ml.email}"></c:out></td>
															<td><c:out value="${ml.login}"></c:out></td>
															<td><a class="btn btn-success"
																href="<%= request.getContextPath() %>/ServletUsuarioController?acao=buscarEditar&id=${ml.id}">Ver</a>
															</td>
														</tr>
													</c:forEach>
												</tbody>

											</table>
										</div>

										<!-- Page-body end -->
										<div id="styleSelector"></div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<jsp:include page="javascriptfile.jsp"></jsp:include>

			<!-- Modal -->
			<div class="modal fade" id=exampleModalUsuario tabindex="-1"
				role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel">Pesquisar
								Usuário</h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">

							<div class="input-group mb-3">
								<input type="text" class="form-control"
									placeholder="Buscar usuário pelo ID" aria-label="nome"
									aria-describedby="button-addon2" id="nomeBusca">
								<button class="btn btn-primary" type="button" id="button-addon2"
									onclick="buscarUsuario();">Buscar</button>
							</div>

							<div style="height: 400px; overflow: scroll;">
								<table class="table" id="tabelaresultados">

									<thead>
										<tr>
											<th scope="col">ID</th>
											<th scope="col">Nome</th>
											<th scope="col">Email</th>
											<th scope="col">Login</th>
										</tr>
									</thead>
									<tbody>
										<!-- AQUI ESTA SENDO ADICIONADO A FUNÇÃO JQUERY  -->
										<!-- LOCALIZADA NA FUNÇÃO JAVASCRIPT buscarUsuario()  -->
									</tbody>

								</table>
							</div>
							<span id="totalresultados"></span>

						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary btn-round"
								data-dismiss="modal">Fechar</button>
						</div>
					</div>
				</div>
			</div>
			<!-- END Modal -->

			<!-- INICIO DO JAVASCRIPT -->
			<script type="text/javascript">
			/**/
			
			
				function visualizarImg(fotoembase64, fileFoto) {			
					
					var preview = document.getElementById(fotoembase64); /*campo img do html*/
					var fileUser = document.getElementById(fileFoto).files[0];
					var reader = new FileReader();
					
					reader.onloadend = function () {
						preview.src = reader.result; /*carrega a fot na tela*/
					};
					
					if (fileUser) {
						reader.readAsDataURL(fileUser); /*Preview da imagem*/
					} else {
						preview.src = '';
					}
					
				}
				

				/*Função verEditar para o botão "ver"*/
				function verEditar(id) {

					var urlAction = document.getElementById('formuser').action;

					/*redirecionar com javascript*/
					window.location.href = urlAction + '?acao=buscarEditar&id='
							+ id;

				}

				/*Função buscar usuário me javaScript com Ajax*/
				function buscarUsuario() {

					var nomeBusca = document.getElementById('nomeBusca').value;
					var urlAction = document.getElementById('formuser').action;

					if (nomeBusca != null && nomeBusca != ''
							&& nomeBusca.trim() != '') { /*Validando que tem que ter valor para buscar no banco*/

						$
								.ajax(
										{

											method : "get",
											url : urlAction,
											data : "nomeBusca=" + nomeBusca
													+ "&acao=buscarUserAjax",
											success : function(response) { /*Response vem em formato de String sendo necessário transformar para Json*/

												var json = JSON.parse(response); /*JSON.parse javascript*/

												$(
														'#tabelaresultados > tbody > tr')
														.remove(); /*Função Jquery remove: Remove conteudos das linhas*/

												for (var i = 0; i < json.length; i++) {
													$(
															'#tabelaresultados > tbody')
															.append(
																	'<tr><td> '
																			+ json[i].id
																			+ '</td> <td> '
																			+ json[i].nome
																			+ '</td> <td> '
																			+ json[i].email
																			+ '</td> <td> '
																			+ json[i].login
																			+ '</td> <td> <button onclick="verEditar('
																			+ json[i].id
																			+ ')" type="button" class="btn btn-success">Ver</button> </td> </tr>'); /*Função JQuery append (adicionar)*/

												}

												document
														.getElementById('totalresultados').textContent = 'Total de resultados: '
														+ json.length;

											}

										})
								.fail(
										function(xhr, status, errorThrown) {
											alert('Erro ao buscar usuário por nome'
													+ xhr.responseText);
										});

					}

				}

				function criaDeleteComAjax() {

					if (confirm('Deseja relamente deletar o usuário?')) {

						var urlAction = document.getElementById('formuser').action;
						var idUser = document.getElementById('id').value;

						/*Ajax com Jquery (Ajax esta inserido dentro do Jquery)*/
						$
								.ajax(
										{

											method : "get",
											url : urlAction,
											data : "id=" + idUser
													+ "&acao=deletarAjax",
											success : function(response) {

												limparForm()
												document.getElementById("msg").textContent = response;
											}

										})
								.fail(
										function(xhr, status, errorThrown) {
											alert('Erro ao deletar usuário por id:'
													+ xhr.responseText);
										});

					}

				}

				function criarDelete() {

					if (confirm('Deseja realmente deletar o usuário?')) {

						document.getElementById("formuser").method = 'get';
						document.getElementById("acao").value = 'deletar';
						document.getElementById("formuser").submit();

					}
				}

				function limparForm() {
					var elementos = document.getElementById("formuser").elements; /*Retorna dos elementos HTML dentro do form*/
					/*Outra maneira de pegar os elementos HTML: document.getElementById("formuser").reset; */

					for (var i = 0; i < elementos.length; i++) {
						elementos[i].value = "";
					}
				}
			</script>
</body>

</html>
