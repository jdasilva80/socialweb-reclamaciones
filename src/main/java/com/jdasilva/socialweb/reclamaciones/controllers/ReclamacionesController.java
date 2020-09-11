package com.jdasilva.socialweb.reclamaciones.controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.client.RestClientException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jdasilva.socialweb.commons.errors.UsuarioNoEncontrado;
import com.jdasilva.socialweb.commons.models.usuarios.entity.Mensaje;
import com.jdasilva.socialweb.commons.models.usuarios.entity.Usuario;
import com.jdasilva.socialweb.reclamaciones.editors.MayusculasEditor;
import com.jdasilva.socialweb.reclamaciones.editors.MotivosEditor;
import com.jdasilva.socialweb.reclamaciones.editors.OpcionEditor;
import com.jdasilva.socialweb.reclamaciones.editors.PrioridadEditor;
import com.jdasilva.socialweb.reclamaciones.editors.TipoEditor;
import com.jdasilva.socialweb.reclamaciones.models.domain.Motivo;
import com.jdasilva.socialweb.reclamaciones.models.domain.Opcion;
import com.jdasilva.socialweb.reclamaciones.models.domain.Prioridad;
import com.jdasilva.socialweb.reclamaciones.models.domain.Reclamacion;
import com.jdasilva.socialweb.reclamaciones.models.domain.Tipo;
import com.jdasilva.socialweb.reclamaciones.models.service.IUploadService;
import com.jdasilva.socialweb.reclamaciones.models.service.IUsuarioService;
import com.jdasilva.socialweb.reclamaciones.models.service.MotivoService;
import com.jdasilva.socialweb.reclamaciones.models.service.OpcionService;
import com.jdasilva.socialweb.reclamaciones.models.service.PrioridadService;
import com.jdasilva.socialweb.reclamaciones.models.service.ReclamacionService;
import com.jdasilva.socialweb.reclamaciones.models.service.TipoService;
import com.jdasilva.socialweb.reclamaciones.util.paginator.PageRender;
import com.jdasilva.socialweb.reclamaciones.validations.IReclamacionValidador;

import feign.FeignException;

@Controller
@RequestMapping("/reclamaciones")
//@SessionAttributes(names = { "mensaje" }, types = { Mensaje.class }) // necesario ::
// zuul.routes.socialweb-reclamaciones.sensitive-headers=Authorization
public class ReclamacionesController {

	private static final Logger logger = LoggerFactory.getLogger(ReclamacionesController.class);

	@Autowired
	//@Qualifier("usuarioRestService2")	
	@Qualifier("usuarioFeingService")
	private IUsuarioService usuarioService;

	@Autowired
	private IReclamacionValidador reclamacionValidador;

	@Autowired
	private MayusculasEditor mayusculasEditor;

	@Autowired
	private MotivoService motivoService;

	@Autowired
	private OpcionService opcionService;

	@Autowired
	private TipoService tipoService;

	@Autowired
	private PrioridadService prioridadService;

	@Autowired
	private ReclamacionService reclamacionService;

	@Autowired
	private MotivosEditor motivosEditor;

	@Autowired
	private OpcionEditor opcionEditor;

	@Autowired
	private TipoEditor tipoEditor;

	@Autowired
	private PrioridadEditor prioridadEditor;

	@Value("${path.zuul}")
	private String pathZuul;

	@Value("${config.horario.apertura}")
	private Integer apertura;

	@Value("${config.horario.cierre}")
	private Integer cierre;

	@Autowired
	private IUploadService uploadService;

	@InitBinder
	private void initBinder(WebDataBinder binder) {

		binder.addValidators(reclamacionValidador);

		// alternativa @DateTimeFormat(pattern = "yyyy-MM-dd"), un custom editor
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(false);
		// para indicar que sea rigurosa la conversión con el formato yyyy-MM-dd
		// binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, false));

		// para ser más específicos con un atributo concreto del formulario (pasando
		// true se validará @NotNull en caso de no introudcir ningun dato.)
		binder.registerCustomEditor(Date.class, "fechaReclamacion", new CustomDateEditor(sdf, true));

		binder.registerCustomEditor(String.class, "descripcion", mayusculasEditor);

		binder.registerCustomEditor(Motivo.class, "motivo", motivosEditor);
		binder.registerCustomEditor(Opcion.class, "opciones", opcionEditor);
		binder.registerCustomEditor(Prioridad.class, "prioridad", prioridadEditor);
		binder.registerCustomEditor(Tipo.class, "tipo", tipoEditor);
	}

	@GetMapping("/errores")
	public String erroresHttp(Model model) {

		// int x = 2 / 0;
		Integer.parseInt("bxbx");

		return "plantillaNoExistente";
	}

	@GetMapping({ "/error" })
	public String error(Model model) {

		return "error";
	}

	@GetMapping({ "/horario" })
	public String horario(Model model) {

		StringBuilder sb = new StringBuilder();
		sb.append("El horario de atención al cliente es de : ");
		sb.append(apertura);
		sb.append(" h a ");
		sb.append(cierre);
		sb.append(" h");
		sb.append(", Gracias.");

		model.addAttribute("titulo", "Horario de atención al cliente");
		model.addAttribute("horarioMensaje", sb);

		return "horario";
	}

	@RequestMapping("/listar")
	public String listar(@RequestParam(name = "page", defaultValue = "0") Integer page, Model model,
			SessionStatus status) {

		Pageable pageable = PageRequest.of(page, 2);
		Page<Reclamacion> reclamaciones = reclamacionService.findAll(pageable);
		PageRender<Reclamacion> pageRender = new PageRender<>("/reclamaciones/listar", reclamaciones);

		model.addAttribute("titulo", "Listado de reclamaciones");
		model.addAttribute("reclamaciones", reclamaciones);
		model.addAttribute("page", pageRender);

		// model.addAttribute("reclamaciones", reclamacionService.findAll());
		// status.setComplete();

		return "listado";
	}

	@GetMapping({ "", "/", "/form" })
	public String crear(@ModelAttribute(name = "reclamacion") Reclamacion reclamacion, Model model,
			SessionStatus status) {

		model.addAttribute("titulo", "Crear reclamación");
		// status.setComplete();

		return "reclamacion";
	}

	@GetMapping("/uploads/{filename:.+}")
	public ResponseEntity<Resource> verArchivo(@PathVariable String filename) {

		Resource recurso = null;

		recurso = uploadService.load(filename);

		if (recurso != null) {

			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"".concat(filename)).body(recurso);
		} else {

			return ResponseEntity.noContent().build();
		}

	}

	@PostMapping("/form")
	public String guardar(@Valid Reclamacion reclamacion, BindingResult result, Model model,
			@RequestParam(name = "file", required = false) MultipartFile archivo, RedirectAttributes flash) {

		// reclamacionValidador.validate(reclamacion, result); -> mejor utilizar
		// @InitBinder para desacoplar

		if (result.hasErrors()) {

			model.addAttribute("titulo", "Crear reclamación: rectificar errores");
			return "reclamacion";
		}

		Mensaje mensaje = new Mensaje();
		List<String> infos = new ArrayList<>();
		mensaje.setInfo(infos);

//		Long id = Long.parseLong(Double.toString((Math.random())).replace(".", ""));
//		reclamacion.setId(id);

		if (reclamacion.getArchivo() != null && !reclamacion.getArchivo().isEmpty()) {

			try {
				// if
				// (!Files.deleteIfExists(resourcesPath.resolve(reclamacion.getArchivo()).toAbsolutePath()))
				// {
				if (!uploadService.delete(reclamacion.getArchivo())) {
					List<String> dangers = new ArrayList<>();
					dangers.add("No se ha podido eliminar el archivo ".concat(reclamacion.getArchivo()));
					mensaje.setDanger(dangers);
				} else {
					infos.add("Se ha eliminado el anterior archivo ".concat(reclamacion.getArchivo()));
				}
			} catch (IOException e) {
				logger.info("No se ha podido eliminar el archivo ,".concat(reclamacion.getArchivo()));
			}
		}
		// opcion 1(habria que desplegar otra vez el proyecto para que se carguen las
		// imágenes).Path resourcesPath =
		// Paths.get("src//main//resources//static//uploads//");
		// opcion 1. String rootPath = resourcesPath.toAbsolutePath().toString();
		// opcion 2. String rootPath = "C://tmp//files//";
		try {
			reclamacion.setArchivo(uploadService.copy(archivo));
		} catch (IOException e) {
			logger.info("No se ha podido copiar el archivo ,".concat(archivo.getOriginalFilename()));
		}
		
		
		StringBuilder mensajeTxt = new StringBuilder();
		if (reclamacion.getId() == null) {
			mensajeTxt.append(" Se ha creado la reclamación con id: ");
		} else {
			mensajeTxt.append(" Se ha editado la reclamación con id: ");
		}
		reclamacionService.save(reclamacion);
		mensajeTxt.append(reclamacion.getId().toString());
		infos.add(mensajeTxt.toString());
		flash.addFlashAttribute("mensajeFlash", mensaje);
		// se debe redirigir ya que es un post, y si se refresca la pagina y hubiesen
		// operaciones contra una base de datos se repetirian las acciones
		return "redirect:".concat(pathZuul).concat("/reclamaciones/resumen/").concat(reclamacion.getId().toString());
		// return "resumen";
	}

	@RequestMapping("/form/{id}")
	public String editar(@PathVariable Long id, Map<String, Object> model, RedirectAttributes flash) {

		Reclamacion reclamacion = null;
		Mensaje mensaje = new Mensaje();

		if (id > 0) {

			reclamacion = reclamacionService.findOne(id);

		} else {
			List<String> dangers = new ArrayList<>();
			dangers.add("No existe en BD la reclamación con id " + id);
			mensaje.setDanger(dangers);
//			model.put("mensaje", mensaje);
			flash.addFlashAttribute("mensajeFlash", mensaje);

			return "redirect:".concat(pathZuul).concat("/reclamaciones/listar");
		}

		model.put("reclamacion", reclamacion);
		model.put("titulo", "Editar reclamación");

		return "reclamacion";

	}

	@RequestMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Long id, Map<String, Object> model, RedirectAttributes flash) {

		Mensaje mensaje = new Mensaje();

		if (id > 0) {

			Reclamacion reclamacion = reclamacionService.findOne(id);
			reclamacionService.delete(id);
			List<String> infos = new ArrayList<>();
			infos.add("Se ha eliminado la reclamación con id ".concat(id.toString()));
			mensaje.setInfo(infos);

			try {
				List<String> dangers = new ArrayList<>();

				if (uploadService.delete(reclamacion.getArchivo())) {
					dangers.add("No se ha podido eliminar el archivo ".concat(reclamacion.getArchivo()));
				} else {
					infos.add("Se ha podido eliminar el archivo ".concat(reclamacion.getArchivo()));
				}
			} catch (IOException e) {
				logger.error("No se ha podido eliminar el archivo ,".concat(reclamacion.getArchivo()));
			}
		}
//		model.put("mensaje", mensaje);		
		flash.addFlashAttribute("mensajeFlash", mensaje);

		return "redirect:".concat(pathZuul).concat("/reclamaciones/listar");
	}

	@GetMapping("/resumen/{id}")
	public String resumen(@PathVariable Long id, Model model, RedirectAttributes flash, SessionStatus status) {
//	public String ver(@SessionAttribute(name = "reclamacion") Reclamacion reclamacion,
//			@SessionAttribute(name = "usuario") Usuario usuario, Model model, SessionStatus status) {

		Reclamacion reclamacion = reclamacionService.getById(id);

		if (reclamacion == null) {

			Mensaje mensaje = new Mensaje();
			List<String> dangers = new ArrayList<>();
			dangers.add("No existe en la BD la reclamación con id ".concat(id.toString()));
			mensaje.setDanger(dangers);
//			model.addAttribute("mensaje", mensaje);
			flash.addFlashAttribute("mensajeFlash", mensaje);

			return "reclamacion";
		}

		Usuario usuario = null;

		try {

			usuario = usuarioService.findByUsernameOptional(reclamacion.getUsername())
					.orElseThrow(() -> new UsuarioNoEncontrado(reclamacion.getUsername()));

		} catch (FeignException | RestClientException e) {

			throw new UsuarioNoEncontrado(reclamacion.getUsername());
		}

		model.addAttribute("reclamacion", reclamacion);
		model.addAttribute("usuario", usuario);
		model.addAttribute("titulo", "Resumen reclamación");

		// status.setComplete();

		return "resumen";
	}
		
	@GetMapping(value = { "/prueba" })
	public @ResponseBody Integer cargarProductosXml() {

		return 1;
	}

	@ModelAttribute("reclamacion")
	public Reclamacion createReclamacion() {

		Reclamacion reclamacion = new Reclamacion();
		reclamacion.setEnviarMail(true);
		reclamacion.setOpciones(Arrays.asList(opcionService.getById(1L)));
		reclamacion.setPrioridad(prioridadService.getById(1L));
		reclamacion.setMotivo(motivoService.getById(1L));

		return reclamacion;
	}

	@ModelAttribute("usuario")
	public Usuario createUsuario() {

		Usuario usuario = new Usuario();
		return usuario;
	}

	@ModelAttribute("prioridades")
	public List<Prioridad> prioridades() {

		return prioridadService.findAll();
	}

	@ModelAttribute("motivos")
	public List<Motivo> motivos() {

		return motivoService.findAll();
	}

	@ModelAttribute("opciones")
	public List<Opcion> opciones() {

		return opcionService.findAll();
	}

	@ModelAttribute("tipos")
	public List<Tipo> tipos() {

		return tipoService.findAll();
	}


//	@ModelAttribute("prioridades")
//	public Map<Integer, String> prioridad() {
//
//		Map<Integer, String> prioridades = new HashMap<>();
//		prioridades.put(1, "BAJA");
//		prioridades.put(2, "MEDIA");
//		prioridades.put(3, "ALTA");
//		prioridades.put(4, "URGENTE");
//
//		return prioridades;
//	}

//	@ModelAttribute("prioridades")
//	public Map<Integer, String> prioridad() {
//
//		Map<Integer, String> prioridades = new HashMap<>();
//		prioridades.put(1, "BAJA");
//		prioridades.put(2, "MEDIA");
//		prioridades.put(3, "ALTA");
//		prioridades.put(4, "URGENTE");
//
//		return prioridades;
//	}

//	@ModelAttribute("motivosList")
//	public List<Motivo> motivos() {
//
//		return motivoService.findAll();
//	}

//	@ModelAttribute("opciones")
//	public List<String> opciones() {
//
//		List<String> opciones = Arrays.asList("Acepto términos y condiciones", "Acepto recibir información vía mail",
//				"Acepto recibir información vía teléfono");
//
//		return opciones;
//	}

//	@ModelAttribute("opcionesList")
//	public List<Opcion> opciones() {
//
//		return opcionService.findAll();
//	}

//	@ModelAttribute("opciones")
//	public Map<Integer, String> opciones() {
//
//		Map<Integer, String> opciones = new HashMap<>();
//
//		opciones.put(1, "Acepto términos y condiciones");
//		opciones.put(2, "Acepto recibir información vía mail");
//		opciones.put(3, "Acepto recibir información vía teléfono");
//
//		return opciones;
//	}

//	@ModelAttribute("tipos")
//	public List<String> tipo() {
//
//		return Arrays.asList("Seguimiento", "Nueva");
//	}
}
