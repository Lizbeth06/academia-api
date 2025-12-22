package academiaapi.ipd.gob.pe.academiaapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(
        name = "tbl_apoderadoparticipante",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_apodarado_participante",
                        columnNames = {
                                "id_apoderado",
                                "id_participante"
                        }
                )
        }
)
public class Apoderadoparticipante {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @EqualsAndHashCode.Include
    private Integer idApoderadoparticipante;

    @ManyToOne
    @JoinColumn(name = "id_apoderado", foreignKey = @ForeignKey(name = "FK_APODERADOPARTICIPANTE_APODERADO"))
    private Apoderado apoderado;

    @ManyToOne
    @JoinColumn(name = "id_participante", foreignKey = @ForeignKey(name = "FK_APODERADOPARTICIPANTE_PARTICIPANTE"))
    private Participante participante;

    @ManyToOne
    @JoinColumn(name = "id_tiporelacion", foreignKey = @ForeignKey(name = "FK_PERSONA_PARENTESCO"))
    private Tiporelacion tiporelacion;
}
