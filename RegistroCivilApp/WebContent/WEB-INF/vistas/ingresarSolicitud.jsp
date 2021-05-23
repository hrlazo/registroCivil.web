<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../templates/header.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<main class="container mt-6">
	<c:if test="${errores !=null}">
		<div class="columns is-centered mb-3">
			<div class="column is-6">
				<div class="notification is-warning">
					<h6>Por favor ingrese los siguientes datos</h6>
					<div class="content">
						<ul>
							<c:forEach var="error" items="${errores}">
								<li>${error}</li>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</c:if>
	<div class="columns is-centered">
		<div class="column is-6 mt-4">
			<form method="POST" action="IngresarSolicitudController.do">
				<div class="card">
					<div class="card-header has-background-primary ">
						<span class="card-header-title is-centered">Ingresar Solicitud</span>
					</div>
					<div class="card-content is-centered">
						<div class="field ">
							<label class="label " for="rut-txt">Rut del Cliente</label>
							<div class="control">
								<input type="text" class="input" id="rut-txt" name="rut-txt" />
							</div>
						</div>
						<div class="field">
							<label class="label" for="nombreCompleto-txt">Nombre Completo</label>
							<div class="control">
								<input type="text" class="input" id="nombreCompleto-txt" name="nombreCompleto-txt" />
							</div>
						</div>
						<div class="field is-centered mx-6">
							<label class="label has-text-centered" for="solicitud-select">Solicitudes</label>
							<div class="control">
								<div class="select mx-6">
									<select name="solicitud-select" id="solicitud-select">
										<option>Solicitud de Cedula de identidad</option>
										<option>Retiro de Cedula de identidad</option>
										<option>Solicitud de certificado de nacimiento</option>
										<option>Solicitud de certificado de defuncion</option>
									</select>
								</div>
							</div>
						</div>
						<c:if test="${solicitud !=null}">
							<div class="field">
								<label class="label" for="solicitudOriginal-txt">Numero solicitud original</label>
								<div class="control">
								<input type="number" class="input" id="solicitudOriginal-txt" name="solicitudOriginal-txt"/>
								</div>
							</div>
						</c:if>	
					</div>
					<div class="card-footer">
						<div class="card-footer-item">
								<button type="submit" class="button is-primary">Ingresar Solicitud</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</main>
</body>
</html>