package academiaapi.ipd.gob.pe.academiaapi.service.Impl;

import academiaapi.ipd.gob.pe.academiaapi.service.IColaGenericoService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class ColaGenericoServiceImpl implements IColaGenericoService {
    @Async("appTaskExecutor")
    public <T> void ejecutarTarea(Consumer<T> task, T data, long delayMillis) {
        try {
            task.accept(data);               // ejecuta la tarea
            if (delayMillis > 0) {
                Thread.sleep(delayMillis);   // delay opcional
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Tarea interrumpida", e);
        }
    }

    // Procesa un batch de tareas genéricas
    @Async("appTaskExecutor")
    public <T> void ejecutarLote(Consumer<T> task, List<T> dataList, long delayMillis) {
        for (T data : dataList) {
            ejecutarTarea(task, data, delayMillis);
        }
    }
}
