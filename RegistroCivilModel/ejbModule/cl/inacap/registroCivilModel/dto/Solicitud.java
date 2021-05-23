package cl.inacap.registroCivilModel.dto;

public class Solicitud {
	
	private String nombreCliente;
	private String rutCliente;
	private String tipoSolicitud;
	private int numSolicitud;
	
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public String getRutCliente() {
		return rutCliente;
	}
	public void setRutCliente(String rutCliente) {
		this.rutCliente = rutCliente;
	}
	public String getTipoSolicitud() {
		return tipoSolicitud;
	}
	public void setTipoSolicitud(String tipoSolicitud) {
		this.tipoSolicitud = tipoSolicitud;
	}
	public int getNumSolicitud() {
		return numSolicitud;
	}
	public void setNumSolicitud(int numSolicitud) {
		this.numSolicitud = numSolicitud;
	}
	
	

}
