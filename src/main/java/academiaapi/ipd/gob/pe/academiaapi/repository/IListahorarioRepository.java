package academiaapi.ipd.gob.pe.academiaapi.repository;
import academiaapi.ipd.gob.pe.academiaapi.model.Listahorario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IListahorarioRepository extends IGenericRepo<Listahorario,Integer>{
    @Query("""
                SELECT lh
                FROM Listahorario lh
                JOIN FETCH lh.convocatoria c
                JOIN FETCH lh.horario h
                WHERE lh.estado = '1'
                  AND c.estado = '1' AND c.idConvocatoria=:idConvocatoria
            """)
    List<Listahorario> findByConvocatoriaId(@Param("idConvocatoria") Integer idConvocatoria);

    @Query("""
                SELECT lh
                FROM Listahorario lh
                JOIN FETCH lh.convocatoria c
                JOIN FETCH lh.horario h
                WHERE lh.estado = '1'
                  AND c.estado = '1' AND h.estado = '1'
            """)
    List<Listahorario> listaTodohorario();
}
