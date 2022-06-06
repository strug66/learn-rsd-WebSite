package com.rsd.day4;

import com.rsd.util.JDBCUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class ServiceImpl<E> implements IService<E> {
    @Override
    public List<E> queryList() {
//        E e1 = new E();
        Class<E> clazz = getClassForGenericParadigm();

        String sql = JDBCUtil.selectSQL(clazz);
        return (List<E>) JDBCUtil.queryAllList(clazz, sql);
    }

    @Override
    public E querryById(Integer id) {
        Class<E> clazz = getClassForGenericParadigm();

        String sql = JDBCUtil.selectSQL(clazz) + " where id=" + id;
        System.out.println("---------"+sql);
        return (E) JDBCUtil.queryById(clazz, sql);

    }

    @Override
    public void insert(E e) {
        String sql = JDBCUtil.insertSQL(e);
        JDBCUtil.execute(sql);
    }

    @Override
    public void update(E e) {
        String sql = JDBCUtil.updateSQL(e);
        System.out.println(sql);
        JDBCUtil.execute(sql);
    }

    @Override
    public void delete(Integer id) {
        Class<E> clazz = getClassForGenericParadigm();
        Object obj = null;
        try {
            obj = clazz.newInstance();

            Method method = clazz.getMethod("setId", Integer.class);
            method.invoke(obj, id);//set赋id值

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        String sql = JDBCUtil.deleteSQL(obj);
        JDBCUtil.execute(sql);
    }

    private Class<E> getClassForGenericParadigm() {
        Class<E> clazz = null;
        Type type = this.getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) { //this这个类是泛型类
            ParameterizedType ptype = (ParameterizedType) type;
            Type[] actualTypeArguments = ptype.getActualTypeArguments();
            clazz = (Class<E>) actualTypeArguments[0];
        }

        return clazz;
    }
}
