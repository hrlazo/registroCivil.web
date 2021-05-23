package cl.inacap.registroCivilApp.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.inacap.registroCivilModel.dao.SolicitudDAO;
import cl.inacap.registroCivilModel.dto.Solicitud;

/**
 * Servlet implementation class IngresarSolicitudController
 */
@WebServlet("/IngresarSolicitudController.do")
public class IngresarSolicitudController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public AtomicInteger numSolicitud = new AtomicInteger();
	
	@Inject
	private SolicitudDAO solicitudDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IngresarSolicitudController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	public boolean validarRut(String rut) { // la utilizamos para verificar que el rut ingresado es el correcto

	    boolean validacion = false;
	    try {
	        rut = rut.toUpperCase();
	        rut = rut.replace(".", "");
	        rut = rut.replace("-", "");
	        int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));

	        char dv = rut.charAt(rut.length() - 1);

	        int m = 0, s = 1;
	        for (; rutAux != 0; rutAux /= 10) {
	            s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;		
	        }
	        if (dv == (char) (s != 0 ? s + 47 : 75)) {
	            validacion = true;
	        }

	    } catch (java.lang.NumberFormatException e) {
	    } catch (Exception e) {
	    }
	    return validacion;
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/vistas/ingresarSolicitud.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> errores =new ArrayList<>();
		
		
		String nombreC= request.getParameter("nombreCompleto-txt").trim();
		if(nombreC.isEmpty()) {
			errores.add("Ingrese su nombre y apellido");
		}
		else if(!nombreC.matches("([a-zA-Z]{2,}\s[a-zA-Z]{2,})")){//nos aseguramos que se ingresan letras 
			errores.add("Ingrese un nombre y apellido valido");
		}
		
		String rut=request.getParameter("rut-txt").trim();
		if(!validarRut(rut)) {
			errores.add("Ingrese un rut valido");
		}
		
		String solicitud= request.getParameter("solicitud-select").trim();
		String numSolicitudOriginal=request.getParameter("solicitudOriginal-txt");
		if(solicitud.equals("Retiro de Cedula de identidad") && numSolicitudOriginal==null) {
			errores.add("debe ingresar su numero de solicitud original");
			request.setAttribute("solicitud", solicitud);
			int idSolicitud=0;
			try {
				idSolicitud=Integer.parseInt(numSolicitudOriginal);
			} catch (Exception e) {
				errores.add("el valor de la solicitud original debe ser numerico");
			}
		}
		
		if(errores.isEmpty()) {
			Solicitud solicitudValida = new Solicitud();
			solicitudValida.setNombreCliente(nombreC);
			solicitudValida.setRutCliente(rut);
			solicitudValida.setTipoSolicitud(solicitud);
			solicitudValida.setNumSolicitud(numSolicitud.getAndIncrement());
			solicitudDAO.save(solicitudValida);
			request.getRequestDispatcher("AtenderSolicitudController.do").forward(request, response);
		}else{
			request.setAttribute("errores", errores);
		}

		doGet(request, response);
	}
}
