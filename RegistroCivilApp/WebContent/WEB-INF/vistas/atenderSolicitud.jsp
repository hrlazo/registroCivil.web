<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../templates/header.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<main class="container mt-6">
	<div class="columns is-centered">
		<div class="column is-8 mt-3">
			<form method="POST" action="AtenderSolicitudController.do">
				<div class="card">
					<div class="card-header has-background-primary ">
						<span class="card-header-title is-centered">Atender	Solicitud</span>
					</div>
					<div class="card-content">
						<div class="field">
							<label class="label has-text-centered" for="solicitud-select">Tipo de Solicitudes</label>
							<div class="control">
								<div  align="center">
									<div class="select">
										<select name="solicitud-select" id="solicitud-select">
											<option>Sin filtro</option>
											<option>Solicitud de Cedula de identidad</option>
											<option>Retiro de Cedula de identidad</option>
											<option>Solicitud de certificado de nacimiento</option>
											<option>Solicitud de certificado de defuncion</option>
										</select>
									</div>
								</div>
							</div>
						</div>
						<div class="field">
							<label class="label has-text-centered" for="filtro-button">Filtrar</label>
							<div class="control">
								<div align="center">
									<button type="submit" class="button is-primary">Filtrar</button>
								</div>
							</div>	
						</div>
						<div class="field">
							<label class="label has-text-centered" for="solicitudes-table">Solicitudes</label>
							<div class="control">
								<div align="center">
									<table class="table is-hovered is-bordered is-fullwidth">
										<thead class="has-background-primary"> 
											<tr> 
												<th>Nro Atencion</th>
												<th>Nombre del Cliente</th>
												<th>Tipo de Solicitud</th>
												<th>Atender</th>
											</tr>
										</thead>
									<tbody>
										<c:forEach var="solicitud" items="${solicitudes}">
											<tr>
												<td>${solicitud.numSolicitud} </td>
												<td>${solicitud.nombreCliente} </td>
												<td>${solicitud.tipoSolicitud} </td>
												<td>
												<a href="AtenderSolicitudController.do?solicitudElminar=${solicitud.numSolicitud}" 
													class="button is-danger">Atender</a>
												</td>
											</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</main>
</body>
</html>