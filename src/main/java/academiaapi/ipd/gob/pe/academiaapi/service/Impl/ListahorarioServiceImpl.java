package academiaapi.ipd.gob.pe.academiaapi.service.Impl;

import academiaapi.ipd.gob.pe.academiaapi.dto.ConvocatoriaDTO;
import academiaapi.ipd.gob.pe.academiaapi.dto.ListahorarioDTO;
import academiaapi.ipd.gob.pe.academiaapi.dto.ListahorariobloqueDTO;
import academiaapi.ipd.gob.pe.academiaapi.model.Convocatoria;
import academiaapi.ipd.gob.pe.academiaapi.model.Horario;
import academiaapi.ipd.gob.pe.academiaapi.model.Listahorario;
import academiaapi.ipd.gob.pe.academiaapi.model.Temporada;
import academiaapi.ipd.gob.pe.academiaapi.repository.IConvocatoriaRepository;
import academiaapi.ipd.gob.pe.academiaapi.repository.IListahorarioRepository;
import academiaapi.ipd.gob.pe.academiaapi.repository.IGenericRepo;
import academiaapi.ipd.gob.pe.academiaapi.repository.ITemporadaRepository;
import academiaapi.ipd.gob.pe.academiaapi.service.IListahorarioService;
import academiaapi.ipd.gob.pe.academiaapi.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ListahorarioServiceImpl extends CRUDImpl<Listahorario, Integer> implements IListahorarioService {

    private final IListahorarioRepository listahorarioRepository;
    private final IConvocatoriaRepository convocatoriaRepository;
    private final ITemporadaRepository temporadaRepository;
    private final MapperUtil mapperUtil;

    @Override
    protected IGenericRepo<Listahorario, Integer> getRepo() {
        return listahorarioRepository;
    }

    @Override
    public void guardarBloqueHorarios(ListahorariobloqueDTO dto) {
        ConvocatoriaDTO convocatoriaDTO = dto.getConvocatoria();
        Temporada temporada = new Temporada();
        temporada.setIdTemporada(dto.getConvocatoria().getTemporada().getIdTemporada());

        Convocatoria convocatoria = new Convocatoria();
        convocatoria.setTitulo(convocatoriaDTO.getTitulo());
        convocatoria.setSubtitulo(convocatoriaDTO.getSubtitulo());
        convocatoria.setDescripcion(convocatoriaDTO.getDescripcion());
        convocatoria.setTemporada(temporada);
        convocatoria.setUrlImagen(convocatoriaDTO.getUrlImagen());
        convocatoria.setEstado("1");
        convocatoria.setFechacreada(java.time.LocalDateTime.now());
        convocatoria = convocatoriaRepository.save(convocatoria);

        for (ListahorarioDTO horarioDTO : dto.getListaHorarios()) {
            Listahorario entity = mapperUtil.map(horarioDTO, Listahorario.class);
            entity.setConvocatoria(convocatoria);

            if (horarioDTO.getHorario() != null && horarioDTO.getHorario().getIdHorario() != null) {
                Horario horario = new Horario();
                horario.setIdHorario(horarioDTO.getHorario().getIdHorario());
                entity.setHorario(horario);
            }
            listahorarioRepository.save(entity);
        }

    }

    public void actualizarBloqueHorarios(Integer idConvocatoria, ListahorariobloqueDTO dto) {

        Convocatoria convocatoria = convocatoriaRepository.findById(idConvocatoria)
                .orElseThrow(() -> new RuntimeException("Convocatoria no encontrada"));
        Temporada temporada = temporadaRepository.findById(dto.getConvocatoria().getTemporada().getIdTemporada()).orElseThrow(() -> new RuntimeException("Temporada no encontrada"));

        convocatoria.setTitulo(dto.getConvocatoria().getTitulo());
        convocatoria.setSubtitulo(dto.getConvocatoria().getSubtitulo());
        convocatoria.setDescripcion(dto.getConvocatoria().getDescripcion());
        convocatoria.setTemporada(temporada);
        convocatoria.setUsuariomodifica(dto.getConvocatoria().getUsuariomodifica());
        convocatoria.setFechamodificada(dto.getConvocatoria().getFechamodificada());
        convocatoria.setUrlImagen(dto.getConvocatoria().getUrlImagen());
        convocatoria.setEstado("1");

        List<Listahorario> horariosBD = listahorarioRepository.findByConvocatoriaId(idConvocatoria);

        Set<Integer> idsHorariosDTO = dto.getListaHorarios().stream()
                .filter(h -> h.getHorario() != null && h.getHorario().getIdHorario() != null)
                .map(h -> h.getHorario().getIdHorario())
                .collect(Collectors.toSet());

        Set<Integer> idsHorariosBD = horariosBD.stream()
                .map(h -> h.getHorario().getIdHorario())
                .collect(Collectors.toSet());

        for (Listahorario horarioBD : horariosBD) {
            if (!idsHorariosDTO.contains(horarioBD.getHorario().getIdHorario())) {
                horarioBD.setEstado("0");
                listahorarioRepository.save(horarioBD);
            }
        }

        for (Listahorario horarioBD : horariosBD) {
            if (idsHorariosDTO.contains(horarioBD.getHorario().getIdHorario())) {
                horarioBD.setEstado("1");
                listahorarioRepository.save(horarioBD);
            }
        }

        for (ListahorarioDTO horarioDTO : dto.getListaHorarios()) {

            Integer idHorarioDTO = horarioDTO.getHorario().getIdHorario();

            if (!idsHorariosBD.contains(idHorarioDTO)) {

                Listahorario nuevo = mapperUtil.map(horarioDTO, Listahorario.class);
                nuevo.setConvocatoria(convocatoria);

                Horario horario = new Horario();
                horario.setIdHorario(idHorarioDTO);
                nuevo.setHorario(horario);

                nuevo.setEstado("1");
                listahorarioRepository.save(nuevo);
            }
        }
    }
    @Override
    public void eliminarConvocatoria(Integer idConvocatoria) {
        Convocatoria convocatoria = convocatoriaRepository.findById(idConvocatoria)
                .orElseThrow(() -> new RuntimeException("Convocatoria no encontrada"));

        List<Listahorario> horarios = listahorarioRepository.findByConvocatoriaId(idConvocatoria);

        for (Listahorario horario : horarios) {
            horario.setEstado("0");
            listahorarioRepository.save(horario);
        }
        
        convocatoria.setEstado("0");
        convocatoriaRepository.save(convocatoria);


    }

    @Override
    public List<Listahorario> findAllListahorario() {
        return listahorarioRepository.listaTodohorario();
    }

    public List<Listahorario> findDisponibles(Integer edad, Integer idModalidad, Integer idSede){
        return this.listahorarioRepository.findDisponibles(edad,idModalidad,idSede);
    }
}


