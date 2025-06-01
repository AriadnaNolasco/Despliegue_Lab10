package pe.edu.tecsup.demo.controladores;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import pe.edu.tecsup.demo.modelo.documents.Alumno;
import pe.edu.tecsup.demo.servicios.AlumnoService;

import java.util.Map;

@Controller
@SessionAttributes("alumno")
public class AlumnoController {
    @Autowired
    private AlumnoService servicio;

    @RequestMapping(value = "/alumnos", method = RequestMethod.GET)
    public String listar(Model model) {
        model.addAttribute("titulo", "Listado de alumnos");
        model.addAttribute("alumnos", servicio.listar());
        return "alumnos/listarAlumnoView";
    }

    @RequestMapping(value = "/alumnos/buscar/{id}", method = RequestMethod.GET)
    public String buscar(@PathVariable String id, Model model) {
        model.addAttribute("titulo", "Detalle de alumno");
        model.addAttribute("alumno", servicio.buscar(id));
        return "alumnos/buscarView";
    }

    @RequestMapping(value = "/alumnos/form")
    public String crear(Map<String, Object> model) {
        Alumno alumno = new Alumno();
        model.put("alumno", alumno);
        model.put("titulo", "Formulario de Alumno");
        return "alumnos/formAlumnoView";
    }

    @RequestMapping(value = "/alumnos/form/{id}")
    public String editar(@PathVariable("id") String id, Map<String, Object> model) {
        Alumno alumno = null;
        if (id != null && id.length() > 0) {
            alumno = servicio.buscar(id);
        } else {
            return "redirect:/alumnos";
        }
        model.put("alumno", alumno);
        model.put("titulo", "Editar Alumno");
        return "alumnos/formAlumnoView";
    }

    @RequestMapping(value = "/alumnos/form", method = RequestMethod.POST)
    public String guardar(@Valid Alumno alumno, BindingResult result, Model model, SessionStatus status) {
        if (result.hasErrors()) {
            model.addAttribute("alumno", alumno); // 💡 ¡esto resuelve el error!
            model.addAttribute("titulo", "Formulario de Alumno");
            return "alumnos/formAlumnoView";
        }
        servicio.grabar(alumno);
        status.setComplete();
        return "redirect:/alumnos";
    }

    @RequestMapping(value = "/alumnos/eliminar/{id}")
    public String eliminar(@PathVariable("id") String id) {
        if (id != null && id.length() > 0) {
            servicio.eliminar(id);
        }
        return "redirect:/alumnos";
    }
}
