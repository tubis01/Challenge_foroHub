package com.alura.Challenge_foroHub.domain.Curso;

import com.alura.Challenge_foroHub.infra.errores.ValidacionDeIntegridad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CursoService {
    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public Page<DatosDetalleCurs> listarCursos(Pageable pageable) {
        return cursoRepository.findCursoByActivoTrue(pageable).map(DatosDetalleCurs::new);
    }

    public Curso obtenerCurso(Long id) {
        return verificarExistencia(id);
    }

    public Curso registrarCurso(DatosRegistroCurso datos) {
        if (cursoRepository.existsByNombreIgnoreCase(datos.nombre())){
            throw new ValidacionDeIntegridad("El curso ya fue registrado");
        }
        Curso curso = new Curso(datos);
        cursoRepository.save(curso);
        return curso;
    }

    public void deshabilitarCurso(Long id){
        Curso curso = verificarExistencia(id);
        curso.desactivarCurso();
    }

    public void habilitarCurso(Long id){
        Curso curso = verificarExistencia(id);
        curso.activarCurso();
    }

    public DatosDetalleCurs actualizarCurso(DatosActualizarCurso datos) {
        Curso curso = verificarExistencia(datos.id());
        curso.actualizarCurso(datos);
        return new DatosDetalleCurs(curso);
    }

    public Curso verificarExistencia(Long id){
        if (!cursoRepository.existsById(id)){
            throw new ValidacionDeIntegridad("No existe un curso con el id proporcionado");
        }
        return cursoRepository.getReferenceById(id);
    }
}

