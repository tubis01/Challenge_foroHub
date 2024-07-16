package com.alura.Challenge_foroHub.domain.respuesta;

import com.alura.Challenge_foroHub.domain.topico.Topico;
import com.alura.Challenge_foroHub.domain.topico.TopicoRespository;
import com.alura.Challenge_foroHub.domain.usuario.Usuario;
import com.alura.Challenge_foroHub.domain.usuario.UsuarioRepository;
import com.alura.Challenge_foroHub.infra.errores.ValidacionDeIntegridad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RespuestaService {

    private final RespuestaRepository respuestaRepository;
    private final TopicoRespository topicoRespository;
    private final UsuarioRepository usuarioRepository;

    public RespuestaService(RespuestaRepository respuestaRepo, TopicoRespository topicoRepo, UsuarioRepository usuarioRepo) {
        this.respuestaRepository = respuestaRepo;
        this.topicoRespository = topicoRepo;
        this.usuarioRepository = usuarioRepo;
    }

    //listar las respuestas
    public Page<DatosDetalleRespuesta> listarRespuestas (Pageable pageable){
        Page<Respuesta> paginacion = respuestaRepository.findAll(pageable);
        return paginacion.map(DatosDetalleRespuesta::new);

    }
    //obtener una respuesta por id
    public DatosDetalleRespuesta obtenerRespuestaId(Long id){
        validarExistencia(id);
        Respuesta respuesta = respuestaRepository.getReferenceById(id);
        return new DatosDetalleRespuesta(respuesta);
    }

    //registrar una respuesta
    public Respuesta registrarRespuesta(DatosRegistroRespuesta datos){
        validarRespuesta(datos);
        Topico topico = topicoRespository.getReferenceById(datos.topico());
        Usuario usuario = usuarioRepository.getReferenceById(datos.autor());
        Respuesta respuesta = new Respuesta(datos, topico, usuario);
        topico.agregarRespuesta(respuesta);
        respuestaRepository.save(respuesta);
        return respuesta;
    }

    // actualizar respuesta
    public DatosDetalleRespuesta editarDatos(DatosActualizarRespuesta datos){
        validarExistencia(datos.id());
        Respuesta respuesta = respuestaRepository.getReferenceById(datos.id());
        Respuesta nuevaRespuesta = respuesta.actualizarRespuesta(datos);
        return new DatosDetalleRespuesta(nuevaRespuesta);
    }

    //validar que el topico y el usuario existan
    private void validarRespuesta(DatosRegistroRespuesta datos) {
        if(!topicoRespository.existsById(datos.topico())){
            throw new ValidacionDeIntegridad("El topico no existe en la base de datos");
        }
        if(!usuarioRepository.existsById(datos.autor())){
            throw new ValidacionDeIntegridad("El id del usuario no ha sido registrado");
        }
    }
    //validar que la respuesta exista
    private void validarExistencia(Long id) {
        if (!respuestaRepository.existsById(id)){
            throw new ValidacionDeIntegridad("El id de la respuesta no ha sido registrado");
        }
    }



}
