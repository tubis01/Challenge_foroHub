package com.alura.Challenge_foroHub.domain.topico;

import com.alura.Challenge_foroHub.domain.Curso.CursoRepository;
import com.alura.Challenge_foroHub.domain.usuario.UsuarioRepository;
import com.alura.Challenge_foroHub.infra.errores.ValidacionDeIntegridad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {

    private final UsuarioRepository usuarioRepository;
    private final CursoRepository cursoRepository;
    private final TopicoRespository topicoRespository;

    public TopicoService(UsuarioRepository usuarioRepository, CursoRepository cursoRepository, TopicoRespository topicoService) {
        this.usuarioRepository = usuarioRepository;
        this.cursoRepository = cursoRepository;
        this.topicoRespository = topicoService;
    }

    public DatosDetalleTopico actualizarTopico (Long id, DatosActualizarTopico datos){
        Topico topico = verificarExistenciaTopico(id);
        topico.actualizarDatos(datos);
        return new DatosDetalleTopico(topico);
    }

    public Topico registrarTopico(DatosRegistroTopico datos){
        validarIdUsuarioCurso(datos);
        var usuario = usuarioRepository.getReferenceById(datos.autor());
        var curso = cursoRepository.getReferenceById(datos.curso());
        var topico = new Topico(datos.titulo(), datos.mensaje(), usuario, curso);
        topicoRespository.save(topico);
        return topico;
    }
    public DatosDetalleTopico topicoResuelto(Long id){
        Topico topico = verificarExistenciaTopico(id);
        topico.statusTopicoSolucionando();
        return new DatosDetalleTopico(topico);
    }


    private void validarIdUsuarioCurso(DatosRegistroTopico datos) {
        if(!usuarioRepository.existsById(datos.autor())){
            throw new ValidacionDeIntegridad("El usuario ingresado no esta registrado");
        }
        if(!cursoRepository.existsById(datos.curso())){
            throw new ValidacionDeIntegridad("El curso ingresado no esta registrado");
        }
    }

    public Page<DatosDetalleTopico> listarTopicos(Pageable pageable){
        return topicoRespository.findByStatusSolucionadoNoSolucionado(pageable).map(DatosDetalleTopico::new);
    }

    public Topico obtenerTopico(Long id){
        return verificarExistenciaTopico(id);
    }

    private Topico verificarExistenciaTopico(Long id) {
        if(!topicoRespository.existsById(id)){
            throw new ValidacionDeIntegridad("No existe el topico con el id: " + id + " ingresado");
        }
        return topicoRespository.getReferenceById(id);
    }

    public void desactivarTopico(Long id) {
        Topico topico = verificarExistenciaTopico(id);
        topico.desactivarTopico();
    }
}
