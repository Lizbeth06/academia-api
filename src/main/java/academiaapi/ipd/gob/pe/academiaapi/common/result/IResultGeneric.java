package academiaapi.ipd.gob.pe.academiaapi.common.result;

public interface IResultGeneric<T> extends IResult {
    T getValue();
}
