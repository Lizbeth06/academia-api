package academiaapi.ipd.gob.pe.academiaapi.repository;

import academiaapi.ipd.gob.pe.academiaapi.model.Trabajador;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ITrabajadorRepository extends IGenericRepo<Trabajador,Integer>{
    /**
     * Finds a Trabajador by its ID, including records that have been logically deleted
     * (where estado = false). This is necessary to bypass the @Where clause on the entity.
     * @param id The ID of the worker to find.
     * @return An Optional containing the Trabajador if found, otherwise empty.
     */
    @Query(value = "SELECT * FROM tbl_trabajador WHERE id_trabajador = :id", nativeQuery = true)
    Optional<Trabajador> findByIdIncludeInactive(@Param("id") Integer id);

    @Query(value = """
            SELECT t.*
            FROM tbl_trabajador t
            INNER JOIN tbl_persona p ON t.id_persona = p.id_persona
            WHERE p.id_tipo_documento = :idTipoDocumento AND p.num_documento = :numeroDocumento
            """, nativeQuery = true)
    Optional<Trabajador> findByIdTipoDocumentoAndNumeroDocumentoIncludeInactive(@Param("idTipoDocumento") Integer idTipoDocumento, @Param("numeroDocumento") String numeroDocumento);

    @Modifying
    @Query("UPDATE Trabajador t SET t.isActive = 1 WHERE t.idTrabajador = :id")
    void reactivateById(@Param("id") Integer id);

}
