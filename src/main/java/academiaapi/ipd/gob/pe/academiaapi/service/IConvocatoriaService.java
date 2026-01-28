package academiaapi.ipd.gob.pe.academiaapi.service;


import academiaapi.ipd.gob.pe.academiaapi.dto.ListaConvocatoriaDisciplinaSedeDTO;
import academiaapi.ipd.gob.pe.academiaapi.dto.ListahorariobloqueDTO;
import academiaapi.ipd.gob.pe.academiaapi.model.Convocatoria;

import java.util.List;

public interface IConvocatoriaService extends ICRUD<Convocatoria,Integer> {
    public ListahorariobloqueDTO obtenerBloque(Integer idConvocatoria);

    public List<ListahorariobloqueDTO> listaTotal();

    public List<ListaConvocatoriaDisciplinaSedeDTO> listaPorDisciplina();
}
