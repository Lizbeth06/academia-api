package academiaapi.ipd.gob.pe.academiaapi.exception;

public class InscriptionLimitReachedException extends RuntimeException{
    public InscriptionLimitReachedException(String message){
        super(message);
    }
}
