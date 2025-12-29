package academiaapi.ipd.gob.pe.academiaapi.service;

import academiaapi.ipd.gob.pe.academiaapi.model.Horario;

import java.util.List;

public interface IHorarioService extends ICRUD<Horario,Integer>  {

    public List<Horario> getHorarioxsede(Integer idSede);

}
