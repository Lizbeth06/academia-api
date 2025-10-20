package academiaapi.ipd.gob.pe.academiaapi.service.Impl;

import academiaapi.ipd.gob.pe.academiaapi.exception.ModelNotFoundException;
import academiaapi.ipd.gob.pe.academiaapi.repository.IGenericRepo;
import academiaapi.ipd.gob.pe.academiaapi.service.ICRUD;

import java.util.List;

public abstract class CRUDImpl<T,ID> implements ICRUD<T,ID> {

    protected abstract IGenericRepo<T,ID> getRepo();

    @Override
    public T save(T t){
        return getRepo().save(t);
    }

    @Override
    public T update(ID id, T t){
        getRepo().findById(id).orElseThrow(()->new ModelNotFoundException("El ID no debe ser NULL" + id));
        return getRepo().save(t);
    }

    @Override
    public List<T> findAll() {
        return getRepo().findAll();
    }

    @Override
    public T findById(ID id) {
        return getRepo().findById(id).orElseThrow(()->new ModelNotFoundException("el ID no de debe ser NULL"+id));
    }

    @Override
    public void delete(ID id) {
        getRepo().findById(id).orElseThrow(()->new ModelNotFoundException("el ID no de debe ser NULL"+id));
        getRepo().deleteById(id);
    }


}
