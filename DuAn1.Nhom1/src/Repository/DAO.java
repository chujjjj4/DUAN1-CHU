/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import java.util.List;

/**
 *
 * @author hoanh
 */
public abstract class DAO<E, K> {

    abstract public void insert(E entity);

    abstract public void updtae(E entity);

    abstract public void delete(K key);

    abstract public List<E> selectAll();

    abstract public E selectById(K key);

    abstract protected List<E> selectBySql(String sql, Object... args);
}
