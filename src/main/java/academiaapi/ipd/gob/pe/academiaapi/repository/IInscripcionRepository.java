package academiaapi.ipd.gob.pe.academiaapi.repository;
import academiaapi.ipd.gob.pe.academiaapi.model.Inscripcion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IInscripcionRepository extends IGenericRepo<Inscripcion,Integer>{
    @Query("""
                SELECT CASE WHEN COUNT(i)>0 THEN true ELSE false END
                FROM Inscripcion i
                WHERE i.apoderadoparticipante.participante.idParticipante = :idParticipante AND
                i.listahorario.convocatoria.temporada.idTemporada = :idTemporada AND
                (i.estado.idEstado = 1 OR i.estado.idEstado = 2)
            """)
    Boolean existeInscripcionActiva(
            @Param("idParticipante") Integer idParticipante,
            @Param("idTemporada") Integer idTemporada
    );
}
