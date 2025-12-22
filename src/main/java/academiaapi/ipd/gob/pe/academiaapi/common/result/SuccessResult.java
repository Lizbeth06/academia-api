package academiaapi.ipd.gob.pe.academiaapi.common.result;
import lombok.Getter;

@Getter
public class SuccessResult<T> implements IResultGeneric<T> {
    private boolean hasSucceeded = true;
    private int statusCode = 200;
    private T value;

    public SuccessResult() {
        this.hasSucceeded = true;
        this.statusCode = 200;
        this.value = null;
    }

    public SuccessResult(T value) {
        this.hasSucceeded = true;
        this.statusCode = 200;
        this.value = value;
    }

    @Override
    public boolean hasSucceeded() {
        return true;
    }
}
