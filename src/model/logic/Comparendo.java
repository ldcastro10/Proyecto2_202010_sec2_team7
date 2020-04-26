package model.logic;

import java.util.Date;

public class Comparendo implements Comparable<Comparendo> {

	private Long id;

	private Date fecha;

	private String medioDeteccion;

	private String tipoVehiculo;

	private String tipoServicio;

	private String infraccion;

	private String descripcionInfraccion;

	private String localidad;

	private String municipio;

	private Long latitud;

	private Long longitud;

	public Comparendo(Long id, Date fecha, String medioDeteccion, String tipoVehiculo, String tipoServicio,
			String infraccion, String descripcionInfraccion, String localidad, String municipio, Long latitud,
			Long longitud) {
		this.id = id;
		this.fecha = fecha;
		this.medioDeteccion = medioDeteccion;
		this.tipoVehiculo = tipoVehiculo;
		this.tipoServicio = tipoServicio;
		this.infraccion = infraccion;
		this.descripcionInfraccion = descripcionInfraccion;
		this.localidad = localidad;
		this.municipio = municipio;
		this.latitud = latitud;
		this.longitud = longitud;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getMedioDeteccion() {
		return medioDeteccion;
	}

	public void setMedioDeteccion(String medioDeteccion) {
		this.medioDeteccion = medioDeteccion;
	}

	public String getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

	public String getTipoServicio() {
		return tipoServicio;
	}

	public void setTipoServicio(String tipoServicio) {
		this.tipoServicio = tipoServicio;
	}

	public String getInfraccion() {
		return infraccion;
	}

	public void setInfraccion(String infraccion) {
		this.infraccion = infraccion;
	}

	public String getDescripcionInfraccion() {
		return descripcionInfraccion;
	}

	public void setDescripcionInfraccion(String descripcionInfraccion) {
		this.descripcionInfraccion = descripcionInfraccion;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public Long getLatitud() {
		return latitud;
	}

	public void setLatitud(Long latitud) {
		this.latitud = latitud;
	}

	public Long getLongitud() {
		return longitud;
	}

	public void setLongitud(Long longitud) {
		this.longitud = longitud;
	}

	@Override
	public String toString() {
		return "Comparendo [OBJECTID=" + id + ", FECHA_HORA=" + fecha + ", MEDIO_DETECCION=" + medioDeteccion 
				+ ", CLASE_VEHICULO=" + tipoVehiculo + ", TIPO_SERVICIO=" + tipoServicio + ", INFRACCION=" + infraccion + ", LOCALIDAD=" 
				+ localidad + ", LATITUD=" + latitud + ", LONGITUD="+ longitud + "]";
	}

	@Override
	public int compareTo(Comparendo parametro) {
		Date fechaHora = parametro.getFecha();
		double fecha1 = fechaHora.getTime();
		double fecha2 = fecha.getTime();
		if(fecha2 > fecha1) 
		{
			return 1;
		}
		else if(fecha2 < fecha1)
		{
			return -1;
		}
		else
			return 0;
	}



}
