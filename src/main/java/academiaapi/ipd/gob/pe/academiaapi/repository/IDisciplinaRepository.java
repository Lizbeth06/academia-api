package academiaapi.ipd.gob.pe.academiaapi.repository;
import academiaapi.ipd.gob.pe.academiaapi.model.Disciplina;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDisciplinaRepository extends IGenericRepo<Disciplina,Integer>{

    @Query(value="SELECT d.* FROM tbl_disciplina AS d\n" +
            "INNER JOIN tbl_listadisciplina AS l ON d.id_disciplina=l.id_disciplina\n" +
            "INNER JOIN tbl_sede AS s ON l.id_sede=s.id_sede\n" +
            "WHERE s.id_sede=:idsede AND l.estado=1;",nativeQuery = true)
    List<Disciplina> findDisciplinas(@Param("idsede") Integer idsede);
}
