package academiaapi.ipd.gob.pe.academiaapi.repository;

import academiaapi.ipd.gob.pe.academiaapi.model.Horario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IHorarioRepository extends IGenericRepo<Horario, Integer> {

    @Query("""
                SELECT h
                FROM Horario h
                JOIN h.listadisciplina l
                WHERE l.sede.idSede = :idSede
                  AND h.estado = '1'
                  AND l.estado = '1'
            """)
    List<Horario> findAllHorarios(
            @Param("idSede") Integer idSede
    );
}
