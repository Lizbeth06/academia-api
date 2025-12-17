package academiaapi.ipd.gob.pe.academiaapi.repository;

import academiaapi.ipd.gob.pe.academiaapi.model.Tipoturno;
import academiaapi.ipd.gob.pe.academiaapi.model.Turno;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ITurnoRepository extends IGenericRepo<Turno, Integer> {

    @Query(nativeQuery = true, value = """
        SELECT t.id_turno
        FROM tbl_turno t
        JOIN tbl_listadia ld ON t.id_turno = ld.id_turno
        WHERE CAST(t.horainicio AS TIME) = :horaInicio
          AND CAST(t.horafin AS TIME) = :horaFin
          AND (:idTurno IS NULL OR t.id_turno <> :idTurno)
        GROUP BY t.id_turno
        HAVING COUNT(ld.id_listadia) = :cantidadDias
           AND SUM(CASE WHEN ld.id_dias IN (:diasIds) THEN 1 ELSE 0 END) = :cantidadDias
    """)
    List<Integer> existeTurnoDuplicado(
            @Param("horaInicio") String horaInicio,
            @Param("horaFin") String horaFin,
            @Param("diasIds") List<Integer> diasIds,
            @Param("cantidadDias") long cantidadDias,
            @Param("idTurno") Integer idTurno // null para crear, id existente para actualizar
    );
}
