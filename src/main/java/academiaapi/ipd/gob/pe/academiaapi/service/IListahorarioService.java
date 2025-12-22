package academiaapi.ipd.gob.pe.academiaapi.service;


import academiaapi.ipd.gob.pe.academiaapi.model.Listahorario;
import net.sf.jasperreports.engine.JREmptyDataSource;

import java.util.List;

public interface IListahorarioService extends ICRUD<Listahorario,Integer> {

    public List<Listahorario> findDisponibles(Integer edad, Integer idModalidad, Integer idSede);
}
