package cl.inacap.registroCivilApp.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.inacap.registroCivilModel.dao.SolicitudDAO;
import cl.inacap.registroCivilModel.dto.Solicitud;


/**
 * Servlet implementation class AtenderSolicitudController
 */
@WebServlet("/AtenderSolicitudController.do")
public class AtenderSolicitudController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Inject
	private SolicitudDAO solicitudDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AtenderSolicitudController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Solicitud> solicitudes = solicitudDAO.getAll();
		request.setAttribute("solicitudes", solicitudes);
		
		if(request.getParameter("solicitudElminar") !=null) {
			String idSolicitud = request.getParameter("solicitudElminar");
			try {
				int id = Integer.parseInt(idSolicitud);
				List<Solicitud> busqueda= solicitudDAO.filterBynumSolicitud(id);
				Solicitud solicitudAEliminar =busqueda.isEmpty()? null:busqueda.get(0);
				if (solicitudAEliminar != null) {
					solicitudDAO.delete(solicitudAEliminar);
				}
				
			} catch (Exception e) {
				//explicar por que exploto
			}
		}
		

		request.getRequestDispatcher("WEB-INF/vistas/atenderSolicitud.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Solicitud> solicitudes = solicitudDAO.getAll();
		List<Solicitud> solicitudesFiltradas= new ArrayList<>();
		String solicitudParaFiltrar = request.getParameter("solicitud-select").trim();
		
		if(solicitudParaFiltrar.equals("Sin filtro")) {
			doGet(request, response);
		}else {	
			for(Solicitud s: solicitudes) {
				if(s.getTipoSolicitud().trim().equals(solicitudParaFiltrar)) {
					solicitudesFiltradas.add(s);
				}
			}	
			request.setAttribute("solicitudes", solicitudesFiltradas);
			request.getRequestDispatcher("WEB-INF/vistas/atenderSolicitud.jsp").forward(request, response);
		}	
	}	
}

	
		


