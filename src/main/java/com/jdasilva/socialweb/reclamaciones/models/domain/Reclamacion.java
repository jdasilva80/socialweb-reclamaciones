package com.jdasilva.socialweb.reclamaciones.models.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
//import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

//import org.springframework.format.annotation.DateTimeFormat;

import com.jdasilva.socialweb.reclamaciones.validations.PeticionRegex;
import com.jdasilva.socialweb.reclamaciones.validations.Requerido;

@Entity
@Table(name = "reclamaciones")
public class Reclamacion implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// @Pattern(regexp = "[0-9]{3}[.,][\\d]{3}[.,][aA-zZ]{1}")
	@PeticionRegex(message = "la petición no tiene el patrón correcto.")
	private String peticion;

	// @NotEmpty(message = "username no puede estar vacío")
	@Requerido
	@Size(min = 5, max = 15, message = "el tamaño debe ser entre 3 y 15 carácteres")
	private String username;

	@NotBlank // incluye notEmpty no poner las 2.
	@Size(min = 6, max = 15, message = "el tamaño debe ser entre 6 y 15 carácteres")
	private String password;

	@Email(message = "formato de mail incorrecto")
	@Requerido
	private String email;

	@Temporal(TemporalType.DATE)
	@Column(updatable = false)
	private Date fecha;

	// @Valid
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, updatable = true, foreignKey = @ForeignKey(name = "Fk_reclamaciones_motivo_id"))
	private Motivo motivo;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, updatable = true, foreignKey = @ForeignKey(name = "Fk_reclamaciones_prioridad_id"))
	private Prioridad prioridad;

	@NotNull
	@Past
	// @Future
	// @DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_reclamacion")
	private Date fechaReclamacion;

	@NotEmpty
	private String descripcion;

	@NotNull
	@Min(1)
	@Max(5000000)
	@Column(name = "num_localizador")
	private Long numLocalizador;

//	@NotEmpty not empty también se utiliza para listas no solo para strings
//	private List<String> opciones;

	@NotEmpty
	@JoinTable(name = "reclamaciones_opciones", joinColumns = @JoinColumn(name = "reclamacion_id"), inverseJoinColumns = @JoinColumn(name = "opcion_id"), uniqueConstraints = {
			@UniqueConstraint(columnNames = { "reclamacion_id",
					"opcion_id" }) }, foreignKey = @ForeignKey(name = "Fk_reclamaciones_opcion_id"), inverseForeignKey = @ForeignKey(name = "Fk_opciones_reclamacion_id"))
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Opcion> opciones;

	@Column(name = "enviar_mail")
	private Boolean enviarMail;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, updatable = true, foreignKey = @ForeignKey(name = "Fk_reclamaciones_tipo_id"))
	private Tipo tipo;

	private String archivo;

	@PrePersist
	private void prePersist() {

		fecha = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPeticion() {
		return peticion;
	}

	public void setPeticion(String peticion) {
		this.peticion = peticion;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getNumLocalizador() {
		return numLocalizador;
	}

	public void setNumLocalizador(Long numLocalizador) {
		this.numLocalizador = numLocalizador;
	}

	public Date getFechaReclamacion() {
		return fechaReclamacion;
	}

	public void setFechaReclamacion(Date fechaReclamacion) {
		this.fechaReclamacion = fechaReclamacion;
	}

	public Motivo getMotivo() {
		return motivo;
	}

	public void setMotivo(Motivo motivo) {
		this.motivo = motivo;
	}

	public Prioridad getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(Prioridad prioridad) {
		this.prioridad = prioridad;
	}

	public List<Opcion> getOpciones() {
		return opciones;
	}

	public void setOpciones(List<Opcion> opciones) {
		this.opciones = opciones;
	}

	public Boolean getEnviarMail() {
		return enviarMail;
	}

	public void setEnviarMail(Boolean enviarMail) {
		this.enviarMail = enviarMail;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public String getArchivo() {
		return archivo;
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}

	@Override
	public Reclamacion clone() {

		Reclamacion reclamacion = new Reclamacion();
		reclamacion.setId(id);
		reclamacion.setUsername(username);
		reclamacion.setPassword(password);
		reclamacion.setEmail(email);
		reclamacion.setFecha(fechaReclamacion);
		reclamacion.setMotivo(motivo);
		reclamacion.setPrioridad(prioridad);
		reclamacion.setFechaReclamacion(fechaReclamacion);
		reclamacion.setDescripcion(descripcion);
		reclamacion.setNumLocalizador(numLocalizador);
		reclamacion.setOpciones(opciones);
		reclamacion.setEnviarMail(enviarMail);
		reclamacion.setTipo(tipo);

		return reclamacion;
	}

}
