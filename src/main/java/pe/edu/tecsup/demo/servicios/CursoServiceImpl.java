package pe.edu.tecsup.demo.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.tecsup.demo.modelo.daos.CursoRepository;
import pe.edu.tecsup.demo.modelo.documents.Curso;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoServiceImpl implements CursoService{
    @Autowired
    private CursoRepository dao;

    @Override
    public void grabar(Curso curso) {
        dao.save(curso);
    }

    @Override
    public void eliminar(String id) {
        dao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Curso buscar(String id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Curso> listar() {
        return (List<Curso>)dao.findAll();
    }

}
