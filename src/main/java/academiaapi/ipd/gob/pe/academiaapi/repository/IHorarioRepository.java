package academiaapi.ipd.gob.pe.academiaapi.repository;

import academiaapi.ipd.gob.pe.academiaapi.model.Horario;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;
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

    @Query("""
                SELECT h
                FROM Horario h
                JOIN h.listadisciplina l
                WHERE l.disciplina.idDisciplina = :idDisciplina
                  AND l.sede.idSede = :idSede
                  AND h.estado = '1'
                  AND l.estado = '1'
            """)
    List<Horario> findAllHorariosxidsedeiddisciplina(
            @Param("idDisciplina") Integer idDisciplina,
            @Param("idSede") Integer idSede
    );

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT h from Horario h WHERE h.idHorario = :idHorario")
    Optional<Horario> findByIdForUpdate(@Param("idHorario")Integer idHorario);
}
