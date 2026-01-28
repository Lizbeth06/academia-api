package academiaapi.ipd.gob.pe.academiaapi.service.Impl;

import academiaapi.ipd.gob.pe.academiaapi.dto.*;
import academiaapi.ipd.gob.pe.academiaapi.model.*;
import academiaapi.ipd.gob.pe.academiaapi.repository.IConvocatoriaRepository;
import academiaapi.ipd.gob.pe.academiaapi.repository.IGenericRepo;
import academiaapi.ipd.gob.pe.academiaapi.repository.IListahorarioRepository;
import academiaapi.ipd.gob.pe.academiaapi.service.IConvocatoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConvocatoriaServiceImpl extends CRUDImpl<Convocatoria, Integer> implements IConvocatoriaService {

    private final IConvocatoriaRepository convocatoriaRepository;
    private final IListahorarioRepository listahorarioRepository;

    @Override
    protected IGenericRepo<Convocatoria, Integer> getRepo() {
        return convocatoriaRepository;
    }

    @Override
    public ListahorariobloqueDTO obtenerBloque(Integer idConvocatoria) {

        List<Listahorario> listahorarios = listahorarioRepository.findByConvocatoriaId(idConvocatoria);

        if (listahorarios.isEmpty()) {
            throw new RuntimeException("Convocatoria no encontrada o inactiva");
        }

        Convocatoria convocatoriaEntity = listahorarios.get(0).getConvocatoria();
        ConvocatoriaDTO convocatoriaDTO = mapConvocatoriaToDTO(convocatoriaEntity);

        List<ListahorarioDTO> listaHorariosDTO = listahorarios.stream()
                .map(lh -> {
                    ListahorarioDTO dto = new ListahorarioDTO();
                    dto.setIdListahorario(lh.getIdListahorario());
                    dto.setIntervaloHora(lh.getIntervaloHora());
                    dto.setTurno(lh.getTurno());
                    dto.setEstado(lh.getEstado());
                    dto.setHorario(mapHorarioToDTO(lh.getHorario()));
                    return dto;
                }).toList();

        // Armamos la respuesta final
        ListahorariobloqueDTO response = new ListahorariobloqueDTO();
        response.setConvocatoria(convocatoriaDTO);
        response.setListaHorarios(listaHorariosDTO);

        return response;
    }

    private ConvocatoriaDTO mapConvocatoriaToDTO(Convocatoria entity) {
        ConvocatoriaDTO dto = new ConvocatoriaDTO();
        dto.setIdConvocatoria(entity.getIdConvocatoria());
        dto.setTitulo(entity.getTitulo());
        dto.setSubtitulo(entity.getSubtitulo());
        dto.setDescripcion(entity.getDescripcion());
        dto.setFechacreada(entity.getFechacreada());
        dto.setUsuariocrea(entity.getUsuariocrea());
        dto.setFechamodificada(entity.getFechamodificada());
        dto.setUsuariomodifica(entity.getUsuariomodifica());
        dto.setUrlImagen(entity.getUrlImagen());
        dto.setEstado(entity.getEstado());
        dto.setTemporada(mapTemporadaToDTO(entity.getTemporada()));
        return dto;
    }

    private TemporadaDTO mapTemporadaToDTO(Temporada entity) {
        TemporadaDTO dto = new TemporadaDTO();
        dto.setIdTemporada(entity.getIdTemporada());
        dto.setDescripcion(entity.getDescripcion());
        dto.setFaperturainscripcion(entity.getFAperturainscripcion());
        dto.setFinicioclases(entity.getFInicioclases());
        dto.setFcierreclases(entity.getFCierreclases());
        dto.setFcierreinscripcion(entity.getFCierreinscripcion());
        dto.setFregistro(entity.getFRegistro());
        dto.setEstado(entity.getEstado());

        // Anio
        AnioDTO anioDTO = new AnioDTO();
        anioDTO.setIdAnio(entity.getAnio().getIdAnio());
        anioDTO.setDescripcion(entity.getAnio().getDescripcion());
        dto.setAnio(anioDTO);

        return dto;
    }

    private HorarioDTO mapHorarioToDTO(Horario entity) {
        HorarioDTO dto = new HorarioDTO();
        dto.setIdHorario(entity.getIdHorario());
        dto.setContador(entity.getContador());
        dto.setNumVacante(entity.getNumVacante());
        dto.setLimitePreinscripcion(entity.getLimitePreinscripcion());
        dto.setUsuariocrea(entity.getUsuariocrea());
        dto.setFechacreada(entity.getFechacreada());
        dto.setUsuariomodifica(entity.getUsuariomodifica());
        dto.setFechamodificada(entity.getFechamodificada());
        dto.setEstado(entity.getEstado());

        dto.setTurno(mapTurnoToDTO(entity.getTurno()));
        dto.setListadisciplina(mapListadisciplinaToDTO(entity.getListadisciplina()));
        dto.setModalidad(mapModalidadToDTO(entity.getModalidad()));
        dto.setCategoriaedad(mapCategoriaEdadToDTO(entity.getCategoriaEdad()));
        dto.setNivel(mapNivelToDTO(entity.getNivel()));

        return dto;
    }

    private TurnoDTO mapTurnoToDTO(Turno entity) {
        TurnoDTO dto = new TurnoDTO();
        dto.setIdTurno(entity.getIdTurno());
        dto.setHorainicio(entity.getHorainicio());
        dto.setHorafin(entity.getHorafin());
        dto.setEstado(entity.getEstado());
        dto.setTipoturno(mapTipoTurnoToDTO(entity.getTipoturno()));

        // Listadia
        List<ListadiaDTO> listaDiasDTO = entity.getListadia().stream()
                .map(dia -> {
                    ListadiaDTO ld = new ListadiaDTO();
                    ld.setIdListadia(dia.getIdListadia());
                    ld.setEstado(dia.getEstado());
                    DiasDTO diasDTO = new DiasDTO();
                    diasDTO.setIdDias(dia.getDias().getIdDias());
                    diasDTO.setCodigo(dia.getDias().getCodigo());
                    diasDTO.setDescripcion(dia.getDias().getDescripcion());
                    ld.setDias(diasDTO);
                    return ld;
                }).toList();
        dto.setListadia(listaDiasDTO);

        return dto;
    }

    private TipoturnoDTO mapTipoTurnoToDTO(Tipoturno entity) {
        TipoturnoDTO dto = new TipoturnoDTO();
        dto.setIdTipoturno(entity.getIdTipoturno());
        dto.setAbreviatura(entity.getAbreviatura());
        dto.setDescripcion(entity.getDescripcion());
        return dto;
    }

    private ListadisciplinaDTO mapListadisciplinaToDTO(Listadisciplina entity) {
        ListadisciplinaDTO dto = new ListadisciplinaDTO();
        dto.setIdListadisciplina(entity.getIdListadisciplina());
        dto.setEstado(entity.getEstado());

        // Sede
        dto.setSede(mapSedeToDTO(entity.getSede()));

        // Disciplina
        dto.setDisciplina(mapDisciplinaToDTO(entity.getDisciplina()));

        return dto;
    }

    private ModalidadDTO mapModalidadToDTO(Modalidad entity) {
        ModalidadDTO dto = new ModalidadDTO();
        dto.setIdModalidad(entity.getIdModalidad());
        dto.setDescripcion(entity.getDescripcion());
        return dto;
    }

    private CategoriaedadDTO mapCategoriaEdadToDTO(Categoriaedad entity) {
        CategoriaedadDTO dto = new CategoriaedadDTO();
        dto.setIdCategoriaedad(entity.getIdCategoriaedad());
        dto.setDescripcion(entity.getDescripcion());
        dto.setEdadminima(entity.getEdadminima());
        dto.setEdadmaxima(entity.getEdadmaxima());
        dto.setEstado(entity.getEstado());
        return dto;
    }

    private NivelDTO mapNivelToDTO(Nivel entity) {
        NivelDTO dto = new NivelDTO();
        dto.setIdNivel(entity.getIdNivel());
        dto.setCodigo(entity.getCodigo());
        dto.setDescripcion(entity.getDescripcion());
        return dto;
    }

    private DisciplinaDTO mapDisciplinaToDTO(Disciplina entity) {
        DisciplinaDTO dto = new DisciplinaDTO();
        dto.setIdDisciplina(entity.getIdDisciplina());
        dto.setCodigo(entity.getCodigo());
        dto.setDescripcion(entity.getDescripcion());
        dto.setEdadDeporte(entity.getEdadDeporte());
        dto.setEstado(entity.getEstado());
        dto.setEdadParadeporte(entity.getEdadParadeporte());
        dto.setFRegistro(entity.getFRegistro());
        dto.setDefinicion(entity.getDefinicion());
        dto.setEstado(entity.getEstado());
        return dto;
    }

    private SedeDTO mapSedeToDTO(Sede entity) {
        SedeDTO dto = new SedeDTO();
        dto.setIdSede(entity.getIdSede());
        dto.setNombre(entity.getNombre());
        dto.setCodubi(entity.getCodubi());
        dto.setDireccion(entity.getDireccion());
        dto.setCapacidad(entity.getCapacidad());
        dto.setUbicacion(entity.getUbicacion());
        dto.setLatitud(entity.getLatitud());
        dto.setLongitud(entity.getLongitud());
        dto.setEstado(entity.getEstado());
        SectorDTO sectorDTO = new SectorDTO();
        sectorDTO.setIdSector(entity.getSector().getIdSector());
        sectorDTO.setDescripcion(entity.getSector().getDescripcion());
        dto.setSector(sectorDTO);
        return dto;
    }

    @Override
    public List<ListahorariobloqueDTO> listaTotal() {
        List<Listahorario> listahorarios = listahorarioRepository.listaTodohorario();

        if (listahorarios.isEmpty()) {
            return Collections.emptyList();
        }

        Map<Convocatoria, List<Listahorario>> horariosConvocatoria =
                listahorarios.stream()
                        .collect(Collectors.groupingBy(Listahorario::getConvocatoria));

        List<ListahorariobloqueDTO> response = new ArrayList<>();

        for (Map.Entry<Convocatoria, List<Listahorario>> entry : horariosConvocatoria.entrySet()) {

            Convocatoria convocatoria = entry.getKey();
            List<Listahorario> horarios = entry.getValue();

            ConvocatoriaDTO convocatoriaDTO = mapConvocatoriaToDTO(convocatoria);

            List<ListahorarioDTO> listaHorariosDTO = horarios.stream()
                    .map(lh -> {
                        ListahorarioDTO dto = new ListahorarioDTO();
                        dto.setIdListahorario(lh.getIdListahorario());
                        dto.setIntervaloHora(lh.getIntervaloHora());
                        dto.setTurno(lh.getTurno());
                        dto.setEstado(lh.getEstado());
                        dto.setHorario(mapHorarioToDTO(lh.getHorario()));
                        return dto;
                    })
                    .toList();

            ListahorariobloqueDTO bloque = new ListahorariobloqueDTO();
            bloque.setConvocatoria(convocatoriaDTO);
            bloque.setListaHorarios(listaHorariosDTO);

            response.add(bloque);
        }

        return response;
    }

    @Override
    public List<ListaConvocatoriaDisciplinaSedeDTO> listaPorDisciplina() {
        List<Listahorario> listahorarios = listahorarioRepository.listaTodohorario();
        if (listahorarios.isEmpty()) {
            return Collections.emptyList();
        }
        Map<String, List<Listahorario>> grouped = listahorarios.stream()
                .collect(Collectors.groupingBy(
                        lh -> lh.getConvocatoria().getIdConvocatoria() + "-" +
                                lh.getHorario().getListadisciplina().getDisciplina().getIdDisciplina() + "-" +
                                lh.getHorario().getListadisciplina().getSede().getIdSede()
                ));
        List<ListaConvocatoriaDisciplinaSedeDTO> response = new ArrayList<>();

        for (Map.Entry<String, List<Listahorario>> entry : grouped.entrySet()) {
            List<Listahorario> horarios = entry.getValue();
            Listahorario primeraLista = horarios.get(0);

            ConvocatoriaDTO convocatoriaDTO = mapConvocatoriaToDTO(primeraLista.getConvocatoria());
            DisciplinaDTO disciplinaDTO = mapDisciplinaToDTO(primeraLista.getHorario().getListadisciplina().getDisciplina());
            SedeDTO sedeDTO = mapSedeToDTO(primeraLista.getHorario().getListadisciplina().getSede());
            List<HorarioDTO> listaHorarioDTO = horarios.stream()
                    .map(lh -> {
                        HorarioDTO dto = mapHorarioToDTO(lh.getHorario());
                        dto.setListadisciplina(null);
                        return dto;
                    })
                    .toList();

            // DTO final
            ListaConvocatoriaDisciplinaSedeDTO bloque = new ListaConvocatoriaDisciplinaSedeDTO();
            bloque.setConvocatoria(convocatoriaDTO);
            bloque.setDisciplina(disciplinaDTO);
            bloque.setSede(sedeDTO);
            bloque.setHorario(listaHorarioDTO);

            response.add(bloque);
        }

        return response;
    }
}
