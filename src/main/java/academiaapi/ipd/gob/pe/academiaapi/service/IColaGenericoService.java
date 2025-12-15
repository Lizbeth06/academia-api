package academiaapi.ipd.gob.pe.academiaapi.service;

import java.util.List;
import java.util.function.Consumer;

public interface IColaGenericoService {
    // Para ejecutar una tarea genérica con un delay opcional
    <T> void ejecutarTarea(Consumer<T> task, T data, long delayMillis);

    // Para ejecuta un Lote de tareas genéricas con un delay opcional
    <T> void ejecutarLote(Consumer<T> task, List<T> dataList, long delayMillis);
}
