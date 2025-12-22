package academiaapi.ipd.gob.pe.academiaapi.repository;
import academiaapi.ipd.gob.pe.academiaapi.model.Listahorario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IListahorarioRepository extends IGenericRepo<Listahorario,Integer>{
    @Query("""
            SELECT lh FROM Listahorario lh
            WHERE lh.horario.temporada.fAperturainscripcion <= CURRENT_DATE AND
            lh.horario.temporada.fCierreinscripcion >= CURRENT_DATE AND
            lh.horario.estado = '1' AND
            lh.horario.categoriaEdad.edadminima <= ?1 AND
            lh.horario.categoriaEdad.edadmaxima >= ?1 AND
            lh.horario.modalidad.idModalidad = ?2 AND
            lh.horario.listadisciplina.sede.idSede = ?3
            """)
    List<Listahorario> findDisponibles(Integer edad, Integer idModalidad, Integer idSede);
}
