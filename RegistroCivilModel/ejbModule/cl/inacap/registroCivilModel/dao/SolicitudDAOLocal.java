package cl.inacap.registroCivilModel.dao; 

import java.util.List;

import javax.ejb.Local;

import cl.inacap.registroCivilModel.dto.Solicitud;

@Local
public interface SolicitudDAOLocal {

	void save(Solicitud solicitud);
	List<Solicitud> getAll();
	void delete(Solicitud solicitud);
	List<Solicitud> filterBynumSolicitud(int idSolicitud);

	
}
