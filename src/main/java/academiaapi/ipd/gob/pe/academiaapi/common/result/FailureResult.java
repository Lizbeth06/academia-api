package academiaapi.ipd.gob.pe.academiaapi.common.result;

import lombok.Getter;

@Getter
public class FailureResult<T> implements IResultGeneric<T>{
    private boolean hasSucceeded = false;
    private final int statusCode;
    private final T value;

    public FailureResult(int statusCode, T value) {
        this.hasSucceeded = false;
        this.statusCode = statusCode;
        this.value = value;
    }

    @Override
    public boolean hasSucceeded() {
        return false;
    }
}
