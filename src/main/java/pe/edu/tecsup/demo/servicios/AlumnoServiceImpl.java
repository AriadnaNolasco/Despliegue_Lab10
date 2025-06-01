package pe.edu.tecsup.demo.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.tecsup.demo.modelo.daos.AlumnoRepository;
import pe.edu.tecsup.demo.modelo.documents.Alumno;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class AlumnoServiceImpl implements AlumnoService{

    @Autowired
    private AlumnoRepository dao;

    @Override
    public void grabar(Alumno alumno) {
        dao.save(alumno);
    }

    @Override
    public void eliminar(String id) {
        dao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Alumno buscar(String id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Alumno> listar() {
        return (List<Alumno>)dao.findAll();
    }

}
