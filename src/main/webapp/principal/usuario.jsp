<%@page import="model.ModelLogin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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


														<form class="form-material"
															action="<%=request.getContextPath()%>/ServletUsuarioController"
															method="post" id="formuser">

															<input type="hidden" name="acao" id="acao" value="">

															<div class="form-group form-default form-static-label">
																<input type="text" name="id" id="id" readonly="readonly"
																	value="${modelLogin.id}" class="form-control" /> <span
																	class="form-bar"></span> <label class="float-label">ID:</label>
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
															<button type="button"
																class="btn btn-primary btn-round waves-effect waves-light"
																onclick="limparForm();">Novo</button>
																
															<button class="btn btn-success btn-round waves-effect waves-light">Salvar</button>
																
															<button type="button"
																class="btn btn-danger btn-round waves-effect waves-light" onclick="criarDelete();">Excluir</button>

														</form>

													</div>
												</div>


											</div>


										</div>

										<span>${msg}</span>

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


			<script type="text/javascript">
			
			function criarDelete() {
				document.getElementById("formuser").method = 'get';
				document.getElementById("acao").value = 'deletar';
				document.getElementById("formuser").submit();
				
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
