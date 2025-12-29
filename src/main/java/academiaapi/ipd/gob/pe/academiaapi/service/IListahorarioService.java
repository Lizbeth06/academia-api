package academiaapi.ipd.gob.pe.academiaapi.service;


import academiaapi.ipd.gob.pe.academiaapi.dto.ListahorariobloqueDTO;
import academiaapi.ipd.gob.pe.academiaapi.model.Listahorario;

import java.util.List;

public interface IListahorarioService extends ICRUD<Listahorario,Integer> {

    void actualizarBloqueHorarios(Integer idConvocatoria, ListahorariobloqueDTO dto);
    void guardarBloqueHorarios(ListahorariobloqueDTO dto);
    void eliminarConvocatoria(Integer idConvocatoria);

    public List<Listahorario> findDisponibles(Integer edad, Integer idModalidad, Integer idSede);

}
