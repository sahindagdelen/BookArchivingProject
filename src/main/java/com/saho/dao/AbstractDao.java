package com.saho.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

/**
 * Created by sahin.dagdelen on 1/24/2016.
 */
public class AbstractDao<PK extends Serializable,T> {

    private final Class<T> persistentClass;



  @SuppressWarnings("unchecked")
    public AbstractDao() {
        this.persistentClass =(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    @Autowired
    private SessionFactory sessionFactory;


    protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    public T getByKey(Long key){
        return (T) getSession().get(persistentClass,key);
    }


    public void persist(T entity){
        getSession().persist(entity);
    }


    public  void delete(T entity){
        getSession().delete(entity);
    }


    public Criteria createEntityCriteria(){
        return getSession().createCriteria(persistentClass);
    }

}
