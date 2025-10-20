package academiaapi.ipd.gob.pe.academiaapi.repository;

import academiaapi.ipd.gob.pe.academiaapi.model.Ubigeo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IUbigeoRepository extends IGenericRepo<Ubigeo,Integer> {
    //@Transactional
    //@Modifying para agregar, actuaizar y eliminar
    @Query(value = "SELECT * FROM tbl_ubigeo u WHERE u.ubi_distrito='00' AND u.ubi_provincia='00'",nativeQuery = true)
    List<Ubigeo> findAllDepartaments();

    @Query(value = "SELECT * FROM tbl_ubigeo u WHERE u.ubi_dpto=:departaments AND u.ubi_distrito='00' AND u.ubi_provincia!='00'",nativeQuery = true)
    List<Ubigeo> findAllProvinciasByDepartaments(@Param("departaments") String departaments);

    @Query(value = "SELECT * FROM tbl_ubigeo u WHERE u.ubi_dpto=:departaments  AND u.ubi_provincia=:provincias AND u.ubi_distrito!='00';",nativeQuery = true)
    List<Ubigeo> findAllDistritosByProvinciaByDistrito(@Param("departaments") String departaments,@Param("provincias") String provincias);

}
