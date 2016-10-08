package hibernate.base.dao;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * Hibernate的基础Dao
 * Created by wangdan16 on 2016/10/8.
 */
public abstract class BaseDao<T> {
//    private Logger logger = LoggerFactory.getLogger(BaseDao.class);
//
//    /**
//     * spring 封装的hibernateTemplate,protected是为了用不同的数据源
//     */
//    protected HibernateTemplate hibernateTemplate;
//    protected Class<T> clazz;
//
//    @SuppressWarnings("unchecked")
//    public BaseDao() {
//        @SuppressWarnings("rawtypes")
//        Class clazz = getClass();
//
//        while (clazz != Object.class) {
//            Type t = clazz.getGenericSuperclass();
//            if (t instanceof ParameterizedType) {
//                Type[] args = ((ParameterizedType) t).getActualTypeArguments();
//                if (args[0] instanceof Class) {
//                    this.clazz = (Class<T>) args[0];
//                    break;
//                }
//            }
//            clazz = clazz.getSuperclass();
//        }
//    }
//
//    /**
//     * 设置hibnerateTemplate，加上@Resource使得Spring自动注入
//     *
//     * @param hibernateTemplate
//     */
//    @Resource(name="hibernateTemplate")
//    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
//        this.hibernateTemplate = hibernateTemplate;
//    }
//
//    /**
//     * 同步缓存到数据库,等待commit
//     *
//     * @author zhaolei
//     * @created 2014-11-21
//     */
//    public void flush() {
//        hibernateTemplate.flush();
//    }
//
//    /**
//     * merge方法.在对象不在事务内部时使用.避免覆盖别人已经修改的字段.而自己本不想修改的数据.特殊地方调用.
//     *
//     * @param pojo the input entity
//     *
//     * @author zhaolei
//     * @created 2014-11-21
//     */
//    public void merge(T pojo) {
//        hibernateTemplate.merge(pojo);
//    }
//
//    /**
//     * merge方法.在对象不在事务内部时使用.避免覆盖别人已经修改的字段.而自己本不想修改的数据.特殊地方调用.
//     *
//     * @param pojos
//     *
//     * @author zhaolei
//     * @created 2014-11-21
//     */
//    public void merge(Collection<T> pojos) {
//        for (T t : pojos) {
//            merge(t);
//        }
//    }
//
//    /**
//     * 插入数据
//     *
//     * @param pojo 持久化对象
//     *
//     * @author zhaolei
//     * @created 2014-11-21
//     */
//    public void insert(T pojo) {
//        hibernateTemplate.save(pojo);
//    }
//
//    /**
//     * 插入一组数据
//     *
//     * @param pojos 一组持久化对象
//     *
//     * @author zhaolei
//     * @created 2014-11-21
//     */
//    public void insert(T... pojos) {
//        for (T t : pojos) {
//            hibernateTemplate.save(t);
//        }
//    }
//
//    /**
//     * 插入一组数据
//     *
//     * @param pojos
//     *
//     * @author zhaolei
//     * @created 2014-11-21
//     */
//    public void insert(Collection<T> pojos) {
//        for (T t : pojos) {
//            hibernateTemplate.save(t);
//        }
//    }
//
//    /**
//     * 更新持久化对象
//     *
//     * @param pojo
//     *
//     * @author zhaolei
//     * @created 2014-11-21
//     */
//    public void update(T pojo) {
//        hibernateTemplate.update(pojo);
//    }
//
//    /**
//     * 更新一组数据
//     *
//     * @param pojos 一组持久化对象
//     *
//     * @author zhaolei
//     * @created 2014-11-21
//     */
//    public void update(T... pojos) {
//        for (T t : pojos) {
//            hibernateTemplate.update(t);
//        }
//    }
//
//    /**
//     * 更新一组数据
//     *
//     * @param pojos
//     *
//     * @author zhaolei
//     * @created 2014-11-21
//     */
//    public void update(Collection<T> pojos) {
//        for (T t : pojos) {
//            hibernateTemplate.update(t);
//        }
//    }
//
//    /**
//     * 保存或更新.(会根据unsaved-value判断是保存还是更新,所以,在语义明确的情况下.不要使用这个方法)
//     *
//     * @param pojo 持久化对象
//     *
//     * @author zhaolei
//     * @created 2014-11-21
//     */
//    public void saveOrUpdate(T pojo) {
//        hibernateTemplate.saveOrUpdate(pojo);
//    }
//
//    /**
//     * 保存或更新一组数据(会根据unsaved-value判断是保存还是更新,所以,在语义明确的情况下.不要使用这个方法)
//     *
//     * @param pojos
//     *
//     * @author zhaolei
//     * @created 2014-11-21
//     */
//    public void saveOrUpdate(T... pojos) {
//        for (T pojo : pojos) {
//            hibernateTemplate.saveOrUpdate(pojo);
//        }
//    }
//
//    /**
//     * 保存或更新一组数据(会根据unsaved-value判断是保存还是更新,所以,在语义明确的情况下.不要使用这个方法)
//     *
//     * @param pojos
//     *
//     * @author zhaolei
//     * @created 2014-11-21
//     */
//    public void saveOrUpdate(Collection<T> pojos) {
//        for (T pojo : pojos) {
//            hibernateTemplate.saveOrUpdate(pojo);
//        }
//    }
//
//    /**
//     * 删除对象
//     *
//     * @param pojo
//     *
//     * @author zhaolei
//     * @created 2014-11-21
//     */
//    public void delete(T pojo) {
//        hibernateTemplate.delete(pojo);
//    }
//
//    /**
//     * 删除一组对象
//     *
//     * @param pojos
//     *
//     * @author zhaolei
//     * @created 2014-11-21
//     */
//    public void delete(T... pojos) {
//        hibernateTemplate.deleteAll(Arrays.asList(pojos));
//    }
//
//    /**
//     * 删除一组对象
//     *
//     * @param pojos
//     *
//     * @author zhaolei
//     * @created 2014-11-21
//     */
//    public void delete(Collection<T> pojos) {
//        hibernateTemplate.deleteAll(pojos);
//    }
//
//    /**
//     * 根据主键删除对象
//     *
//     * @param id
//     *
//     * @author zhaolei
//     * @created 2014-11-21
//     */
//    public void deleteById(Serializable id) {
//        T pojo = findById(id);
//        if (pojo != null) {
//            delete(pojo);
//        }
//    }
//
//    /**
//     * 根据某个属性删除对象,必须是等于
//     *
//     * @param propertyName 属性名称
//     * @param value        值
//     *
//     * @return 更新的数据量
//     *
//     * @author zhaolei
//     * @created 2014-11-21
//     */
//    public int deleteByProperty(String propertyName, Object value) {
//        String hql = "delete from " + clazz.getSimpleName() + " where " + propertyName + "=?";
//        return hibernateTemplate.bulkUpdate(hql, value);
//    }
//
//    /**
//     * 通过hql语句批量删除或更新数据
//     *
//     * @param hql    hql语句
//     * @param values 参数值
//     *
//     * @return 更新的数据量
//     *
//     * @author zhaolei
//     * @created 2014-11-21
//     */
//    public int updateOrDeleteByHql(String hql, Object... values) {
//        return hibernateTemplate.bulkUpdate(hql, values);
//    }
//
//    /**
//     * 通过hql语句批量删除更新数据 支持in
//     *
//     * @param queryName
//     * @param paramNames
//     * @param values
//     *
//     * @throws org.springframework.dao.DataAccessException
//     */
//    public void updateOrDeleteByHql(final String queryName, final String[] paramNames, final Object[] values)
//            throws org.springframework.dao.DataAccessException {
//        if (values != null && (paramNames == null || paramNames.length != values.length)) {
//            throw new IllegalArgumentException("Length of paramNames array must match length of values array");
//        } else {
//            Query queryObject = getSessionFactory().getCurrentSession().createQuery(queryName);
//
//            if (values != null) {
//                for (int i = 0; i < values.length; ++i) {
//                    applyNamedParameterToQuery(queryObject, paramNames[i], values[i]);
//                }
//            }
//
//            queryObject.executeUpdate();
//        }
//    }
//
//    protected void applyNamedParameterToQuery(Query queryObject, String paramName, Object value)
//            throws HibernateException {
//        if (value instanceof Collection) {
//            queryObject.setParameterList(paramName, (Collection) value);
//        } else if (value instanceof Object[]) {
//            queryObject.setParameterList(paramName, (Object[]) ((Object[]) value));
//        } else {
//            queryObject.setParameter(paramName, value);
//        }
//
//    }
//
//    /**
//     * Execute the update or delete statement
//     *
//     * @param sql
//     * @param values
//     *
//     * @return The number of entities updated or deleted.
//     *
//     * @author lichengwu
//     * @created 2012-5-24
//     */
//    public Integer updateOrDeleteSQL(final String sql, final Object... values) {
//        return hibernateTemplate.execute(new HibernateCallback<Integer>() {
//            @Override
//            public Integer doInHibernate(Session session) throws HibernateException {
//                SQLQuery sqlQuery = session.createSQLQuery(sql);
//                for (int i = 0; i < values.length; i++) {
//                    Object value = values[i];
//                    sqlQuery.setParameter(i, value);
//                }
//                return sqlQuery.executeUpdate();
//            }
//        });
//    }
//
//    /**
//     * 查询对象全部实例，扫全表
//     *
//     * @return 全表记录list
//     */
//    public List<T> findAll() {
//        DetachedCriteria dc = DetachedCriteria.forClass(clazz);
//        return find(dc);
//    }
//
//    /**
//     * 根据主键查找对象
//     *
//     * @param id
//     *
//     * @return
//     *
//     * @author zhaolei
//     * @created 2014-11-21
//     */
//    public T findById(Serializable id) {
//        return hibernateTemplate.get(clazz, id);
//    }
//
//    /**
//     * 根据一组主键查询
//     *
//     * @param ids
//     *
//     * @return
//     *
//     * @author zhaolei
//     * @created 2012-4-24
//     */
//    public List<T> findByIds(Collection<?> ids) {
//        DetachedCriteria dc = DetachedCriteria.forClass(clazz);
//        dc.add(Restrictions.in(getIdName(), ids));
//        return find(dc);
//    }
//
//    /**
//     * 指定返回实体对象的类型.根据主键查询
//     *
//     * @param <E>
//     * @param clazz
//     * @param id
//     *
//     * @return
//     *
//     * @author zhaolei
//     * @created 2014-11-21
//     */
//    public <E> E find(Class<E> clazz, Serializable id) {
//        return hibernateTemplate.get(clazz, id);
//    }
//
//    /**
//     * 指定返回实体对象的类型.根据主键查询
//     *
//     * @author zhaolei
//     * @created 2014-11-21
//     *
//     * @param <E>
//     * @param clazz
//     * @param value
//     * @return
//     */
//    /*public <E> List<E> findByProperty(Class<E> clazz, String propertyName, Object value) {
//        String hql = "from " + clazz.getSimpleName() + " where " + propertyName + "=?";
//        return hibernateTemplate.find(hql, value);
//    }*/
//
//    /**
//     * 根据属性查询,必须是等于
//     *
//     * @author zhaolei
//     * @created 2014-11-21
//     *
//     * @param propertyName
//     *            属性名称
//     * @param value
//     *            值
//     * @return
//     */
//    //@SuppressWarnings("unchecked")
//    /*protected List<T> findByProperty(String propertyName, Object value) {
//        String hql = "from " + clazz.getSimpleName() + " where " + propertyName + "=?";
//        return hibernateTemplate.find(hql, value);
//    }*/
//
//    /**
//     * 根据对象实例返回查询结果
//     *
//     * @param example
//     *
//     * @return
//     *
//     * @author zhaolei
//     * @created 2012-4-21
//     */
//    @SuppressWarnings("unchecked")
//    protected List<T> findByExample(T example) {
//        return hibernateTemplate.findByExample(example);
//    }
//
//    /**
//     * 根据对象实例,查询分段结果
//     *
//     * @author zhaolei
//     * @created 2012-4-21
//     * @param example
//     * @param page
//     * @return
//     */
//   /* @SuppressWarnings("unchecked")
//    protected List<T> findByExample(T example, Page page) {
//        return hibernateTemplate.findByExample(example, page.getStart(), page.getPageSize());
//    }*/
//
//    /**
//     * 根据Example返回结果
//     *
//     * @param example
//     *
//     * @return
//     *
//     * @author zhaolei
//     * @created 2012-4-21
//     */
//    @SuppressWarnings("unchecked")
//    protected List<T> findByExample(Example example) {
//        //        return hibernateTemplate.findByExample(example);
//        // TODO
//        return null;
//    }
//
//    /**
//     * 根据sql查询
//     *
//     * @author zhaolei
//     * @created 2011-11-30
//     *
//     * @param sql
//     * @return
//     */
//    /*@SuppressWarnings({ "unchecked", "rawtypes" })
//    protected List<Object[]> findBySql(final String sql) {
//        return (List<Object[]>) hibernateTemplate.executeFind(new HibernateCallback() {
//            @Override
//            public Object doInHibernate(Session session) throws HibernateException {
//                SQLQuery sqlQuery = session.createSQLQuery(sql);
//                return sqlQuery.list();
//            }
//        });
//    }*/
//
//    /**
//     * 根据Example返回结果
//     *
//     * @author zhaolei
//     * @created 2012-4-21
//     * @param example
//     *            查询分段结果
//     * @param page
//     *            分页对象
//     * @return
//     */
//    /*@SuppressWarnings("unchecked")
//    protected List<T> findByExample(Example example, Page page) {
//        return hibernateTemplate.findByExample(example, page.getStart(), page.getPageSize());
//    }*/
//
//    /**
//     * 根据sql查询
//     *
//     * @author liuhujun
//     * @created 2013-08-12
//     *
//     * @param sql
//     * @return
//     */
//    /*@SuppressWarnings({ "unchecked", "rawtypes" })
//    protected List<Object[]> findBySql(final String sql, final Page page) {
//        return (List<Object[]>) hibernateTemplate.executeFind(new HibernateCallback() {
//            @Override
//            public Object doInHibernate(Session session) throws HibernateException, SQLException {
//                SQLQuery sqlQuery = session.createSQLQuery(sql);
//                sqlQuery.setFirstResult(page.getStart());
//                sqlQuery.setMaxResults(page.getPageSize());
//                return sqlQuery.list();
//            }
//        });
//    }*/
//
//    /**
//     * 通过sql查询
//     *
//     * @author chenchun
//     * @created 2012-10-24
//     *
//     * @param sql     sql语句
//     * @param timeout 超时时间，单位：秒
//     * @return
//     */
//    /*@SuppressWarnings({ "unchecked", "rawtypes" })
//    protected List<Object[]> findBySqlTimeOut(final String sql, final int timeout) {
//        return (List<Object[]>) hibernateTemplate.executeFind(new HibernateCallback() {
//            @Override
//            public Object doInHibernate(Session session) throws HibernateException, SQLException {
//                SQLQuery sqlQuery = session.createSQLQuery(sql);
//                sqlQuery.setTimeout(timeout);
//                return sqlQuery.list();
//            }
//        });
//    }*/
//
//    /**
//     * 根据sql查询
//     *
//     * @author zhaolei
//     * @created 2012-4-24
//     * @param sql
//     * @param values
//     * @return
//     */
//    /*@SuppressWarnings({ "unchecked", "rawtypes" })
//    protected List<Object[]> findBySql(final String sql, final Object... values) {
//        return (List<Object[]>) hibernateTemplate.executeFind(new HibernateCallback() {
//            @Override
//            public Object doInHibernate(Session session) throws HibernateException {
//                SQLQuery sqlQuery = session.createSQLQuery(sql);
//                for (int i = 0; i < values.length; i++) {
//                    Object value = values[i];
//                    sqlQuery.setParameter(i, value);
//                }
//                return sqlQuery.list();
//            }
//        });
//    }*/
//
//    /**
//     * 根据hql查询
//     *
//     * @param hql
//     *
//     * @return
//     *
//     * @author zhaolei
//     * @created 2014-11-21
//     */
//    @SuppressWarnings("unchecked")
//    protected List<?> find(String hql) {
//        return hibernateTemplate.find(hql);
//    }
//
//    /**
//     * 根据hql和参数查询
//     *
//     * @param hql
//     * @param values 占位符对应的值
//     *
//     * @return
//     *
//     * @author zhaolei
//     * @created 2014-11-21
//     */
//    @SuppressWarnings("unchecked")
//    protected List<T> find(String hql, Object... values) {
//        return (List<T>) hibernateTemplate.find(hql, values);
//    }
//
//    /**
//     * 根据命名参数查询
//     *
//     * @param hql      带有命名参数的hql
//     * @param valueMap 命名参数的map.key是命名参数的名称(比如:name) valueMap.put("name","wing")
//     *
//     * @return
//     *
//     * @author zhaolei
//     * @created 2014-11-21
//     */
//    @SuppressWarnings({"unchecked", "rawtypes"})
//    protected List<T> find(final String hql, final Map<String, Object> valueMap) {
//        return (List<T>) hibernateTemplate.execute(new HibernateCallback() {
//            public Object doInHibernate(Session session) throws HibernateException {
//                Query query = session.createQuery(hql);
//                query = setParams(query, valueMap);
//                return query.list();
//            }
//        });
//    }
//
//    /**
//     * 根据动态查询条件查询
//     *
//     * @param criteria 动态查询条件
//     *
//     * @return
//     *
//     * @author zhaolei
//     * @created 2011-9-13
//     */
//    @SuppressWarnings("unchecked")
//    protected List<T> find(DetachedCriteria criteria) {
//        return (List<T>) hibernateTemplate.findByCriteria(criteria);
//    }
//
//    /**
//     * 根据某个属性分组成map.key是属性值,value是对象list
//     *
//     * @param <C>          分组属性的泛型
//     * @param criteria     动态查询条件对象
//     * @param propertyName 属性名称
//     *
//     * @return
//     *
//     * @author zhaolei
//     * @created 2011-11-5
//     */
//    protected <C> Map<C, List<T>> findMap4ListByProperty(DetachedCriteria criteria, String propertyName) {
//        return groupList2MapByProperty(find(criteria), propertyName);
//    }
//
//    /**
//     * 根据某个属性分组成map.key是属性值,value是对象
//     *
//     * @param <C>          分组属性的泛型
//     * @param criteria     动态查询条件对象
//     * @param propertyName 属性名称
//     *
//     * @return
//     *
//     * @author zhaolei
//     * @created 2011-11-5
//     */
//    protected <C> Map<C, T> findMapByProperty(DetachedCriteria criteria, String propertyName) {
//        return group2MapByProperty(find(criteria), propertyName);
//    }
//
//    /**
//     * 根据动态查询条件,分段查询,并在分页对象你写入分页数据
//     *
//     * @author zhaolei
//     * @created 2011-9-13
//     *
//     * @param criteria
//     *            动态查询条件
//     * @param page
//     *            分页对象
//     * @return
//     */
//    /*@SuppressWarnings("unchecked")
//    protected List<T> find(DetachedCriteria criteria, Page page) {
//        if (page == null) {
//            return find(criteria);
//        }
//        List<T> list = hibernateTemplate.findByCriteria(criteria, page.getStart(),
//                                                               page.getPageSize());
//        int count = findCount(criteria);
//        page.setTotalCount(count);
//        return list;
//    }*/
//
//    /**
//     * 根据动态查询条件,分段查询
//     *
//     * @param criteria
//     * @param start
//     * @param size
//     *
//     * @return
//     *
//     * @author zhaolei
//     * @created 2012-5-13
//     */
//    @SuppressWarnings("unchecked")
//    protected List<T> find(DetachedCriteria criteria, int start, int size) {
//        List<T> list = (List<T>) hibernateTemplate.findByCriteria(criteria, start, size);
//        return list;
//    }
//
//    /**
//     * 通过动态查询条件分页查询，自动查询totalCount，并赋值给page。需要用户自己选择是否使用这个方法，避免自动count查询的效率问题
//     * <p/>
//     * zhaolei 2014/12/05
//     *
//     * @param criteria 动态查询条件
//     * @param page     分页对象
//     *
//     * @return 查询结果list
//     */
//    @SuppressWarnings("unchecked")
//    protected List<T> findAutoCount(DetachedCriteria criteria, Page page) {
//        DetachedCriteria dcCount = CloneUtil.deepClone(criteria);
//        int count = findCount(dcCount);
//        page.setTotalCount(count);
//        return (List<T>) hibernateTemplate.findByCriteria(criteria, page.getStart(), page.getPageSize());
//    }
//
//    /**
//     * 通过动态查询条件分页查询，用户需要自己查询count
//     * <p/>
//     * zhaolei 2014/12/05
//     *
//     * @param criteria 动态查询条件
//     * @param page     分页对象
//     *
//     * @return 查询结果list
//     */
//    @SuppressWarnings("unchecked")
//    protected List<T> find(DetachedCriteria criteria, Page page) {
//        return (List<T>) hibernateTemplate.findByCriteria(criteria, page.getStart(), page.getPageSize());
//    }
//
//    /**
//     * 查询特定的参数
//     *
//     * @param <C>
//     * @param dc
//     *
//     * @return
//     *
//     * @author zhaolei
//     * @created 2011-12-28
//     */
//    @SuppressWarnings("unchecked")
//    protected <C> List<C> findProperties(DetachedCriteria dc) {
//        return (List<C>) hibernateTemplate.findByCriteria(dc);
//    }
//
//    /**
//     * 查询特定的参数，支持分页
//     * (当project被设置时，比如之前有distinct，得到的count数量不准确)
//     *
//     * @author zhangsaiyong
//     * @created Nov 20, 2012
//     *
//     * @param dc
//     * @param page
//     * @return
//     */
//   /* @SuppressWarnings("unchecked")
//    protected <C> List<C> findProperties(final DetachedCriteria dc, Page page) {
//        List<C> list = hibernateTemplate.findByCriteria(dc, page.getStart(), page.getPageSize());
//        // 当project被设置时，比如之前有distinct，得到的count数量不准确！！！
//        int count = findCount(dc);
//        page.setTotalCount(count);
//        return list;
//    }*/
//
//    /**
//     * 直接通过sql语句查询
//     *
//     * @author zhaolei
//     * @created 2013-4-8
//     *
//     * @param sql
//     * @return
//     */
//    /*@SuppressWarnings({ "unchecked", "rawtypes" })
//    protected <C> List<C> findProperties(final String sql) {
//        return (List<C>) hibernateTemplate.executeFind(new HibernateCallback() {
//            @Override
//            public Object doInHibernate(Session session) throws HibernateException {
//                SQLQuery sqlQuery = session.createSQLQuery(sql);
//                return sqlQuery.list();
//            }
//        });
//    }*/
//
//    /**
//     * 根据sql查询
//     *
//     * @author zhaolei
//     * @created 2013-4-8
//     * @param sql
//     * @param values
//     * @return
//     */
//    /*@SuppressWarnings({ "unchecked", "rawtypes" })
//    protected <C> List<C> findProperties(final String sql, final Object... values) {
//        return (List<C>) hibernateTemplate.executeFind(new HibernateCallback() {
//            @Override
//            public Object doInHibernate(Session session) throws HibernateException {
//                SQLQuery sqlQuery = session.createSQLQuery(sql);
//                for (int i = 0; i < values.length; i++) {
//                    Object value = values[i];
//                    sqlQuery.setParameter(i, value);
//                }
//                return sqlQuery.list();
//            }
//        });
//    }
//*/
//
//    /**
//     * 根据criteria查询数量
//     *
//     * @param dc
//     *
//     * @return
//     *
//     * @author zhaolei
//     * @created 2012-5-10
//     */
//    protected int findCount(final DetachedCriteria dc) {
//        @SuppressWarnings({"unchecked", "rawtypes"})
//
//        /**
//         * execute ==> executeWithNativeSession
//         * 避免proxy session
//         *
//         * @create by zhanglei31 2014-11-27
//         */ Long count = (Long) hibernateTemplate.executeWithNativeSession(new HibernateCallback() {
//            public Object doInHibernate(Session session) throws HibernateException {
//                Criteria criteria = dc.getExecutableCriteria(session);
//                criteria.setFirstResult(0);
//                return criteria.setProjection(Projections.rowCount()).uniqueResult();
//            }
//        });
//        //        dc.setProjection(null);
//        return count.intValue();
//    }
//
//    // /**
//    // * 根据Hql查询记录数
//    // *
//    // * @author zhaolei
//    // * @created 2012-5-10
//    // * @param Hql
//    // * @param values
//    // * @return
//    // */
//    // public int findCount(final String Hql, final Object... values) {
//    // if (Hql.indexOf("count(") != -1)
//    // return ((Long) hibernateTemplate.iterate(Hql, values).next()).intValue();
//    // else {
//    // @SuppressWarnings({ "unchecked", "rawtypes" })
//    // Integer count = (Integer) hibernateTemplate.execute(new
//    // HibernateCallback() {
//    // public Object doInHibernate(Session session) throws HibernateException {
//    // String h = "" + Hql;
//    // h = Hql.indexOf("count(") != -1 ? Hql
//    // : (values.length > 0 ? createCountSqlFormHql(h, values,
//    // session.getSessionFactory()) : createCountSqlFormHql(Hql,
//    // session.getSessionFactory()));
//    // Query query = session.createSQLQuery(h);
//    // for (int i = 0; i < values.length; i++) {
//    // query.setParameter(i, values[i]);
//    // }
//    // return Integer.parseInt(query.list().get(0) + "");
//    // }
//    // });
//    // return count;
//    // }
//    // }
//    //
//    // /**
//    // * 根据Hql查询数量
//    // *
//    // * @author zhaolei
//    // * @created 2012-5-10
//    // * @param Hql
//    // * @param valueMap
//    // * @return
//    // */
//    // public int findCount(final String Hql, final Map<String, Object>
//    // valueMap) {
//    // @SuppressWarnings({ "rawtypes", "unchecked" })
//    // Integer count = (Integer) hibernateTemplate.execute(new
//    // HibernateCallback() {
//    // public Object doInHibernate(Session session) throws HibernateException {
//    // String h = "" + Hql;
//    // if (Hql.indexOf("count(") != -1) {
//    // Query query = session.createQuery(h);
//    // setParams(query, valueMap);
//    // return ((Long) query.iterate().next()).intValue();
//    // } else {
//    // h = Hql.indexOf("count(") != -1 ? Hql : createCountSqlFormHql(h,
//    // valueMap,
//    // session.getSessionFactory());
//    // Query query = session.createSQLQuery(h);
//    // return Integer.parseInt(query.list().get(0) + "");
//    // }
//    // }
//    // });
//    // return count;
//    // }
//    //
//    //
//    // /**
//    // *
//    // *
//    // * @author zhaolei
//    // * @created 2012-5-10
//    // * @param hql
//    // * @param sessionFactory
//    // * @return
//    // */
//    // protected String createCountSqlFormHql(String hql, SessionFactory
//    // sessionFactory) {
//    // return "select count(*) from (" + hql2Sql(hql) + ") tmp_count_0";
//    // }
//
//    /**
//     * hql转换成sql
//     *
//     * @author zhaolei
//     * @created 2011-11-30
//     *
//     * @param hql
//     * @return
//     */
//    /*protected String hql2Sql(String hql) {
//        String h = "" + hql;
//        QueryTranslatorImpl queryTranslator = new QueryTranslatorImpl(h, h, Collections.EMPTY_MAP,
//                                                                             (org.hibernate.engine
//                                                                             .SessionFactoryImplementor)
//                                                                             getSessionFactory());
//        queryTranslator.compile(Collections.EMPTY_MAP, false);
//        return queryTranslator.getSQLString();
//    }*/
//
//    /**
//     * 组装结果根据某个属性分组,装载成map.如果有重复的.则追加到value的list内,属性值为null,不进入map
//     *
//     * @param <C>          分组属性的类型
//     * @param list         分组后的属性值
//     * @param propertyName 分组依据的属性名称
//     *
//     * @return
//     *
//     * @throws com.baidu.bainuo.sc.common.exception.DataAccessException
//     * @author zhaolei
//     * @created 2011-11-5
//     */
//    private <C> Map<C, List<T>> groupList2MapByProperty(List<T> list, String propertyName) {
//        Map<C, List<T>> map = new HashMap<C, List<T>>();
//        for (T t : list) {
//            try {
//                Method m = clazz.getDeclaredMethod("get" + propertyName.substring(0, 1).toUpperCase() + propertyName
//                        .substring(1));
//                @SuppressWarnings("unchecked")
//                C c = (C) m.invoke(t);
//                if (c != null) {
//                    if (!map.containsKey(c)) {
//                        map.put(c, new ArrayList<T>());
//                    }
//                    map.get(c).add(t);
//                }
//            } catch (Exception e) {
//                throw new DataAccessException(e.getMessage(), e);
//            }
//        }
//        return map;
//    }
//
//    /**
//     * 组装结果根据某个属性分组,装载成map.如果有重复的.则覆盖前面的值,属性值为null,不进入map
//     *
//     * @param <C>          分组属性的类型
//     * @param list         分组后的属性值
//     * @param propertyName 分组依据的属性名称
//     *
//     * @return
//     *
//     * @throws com.baidu.bainuo.sc.common.exception.DataAccessException
//     * @author zhaolei
//     * @created 2011-11-5
//     */
//    private <C> Map<C, T> group2MapByProperty(List<T> list, String propertyName) throws DataAccessException {
//        Map<C, T> map = new HashMap<C, T>();
//        for (T t : list) {
//            try {
//                Method m = clazz.getDeclaredMethod("get" + propertyName.substring(0, 1).toUpperCase() + propertyName
//                        .substring(1));
//                @SuppressWarnings("unchecked")
//                C c = (C) m.invoke(t);
//                if (c != null) {
//                    map.put(c, t);
//                }
//            } catch (Exception e) {
//                throw new DataAccessException(e.getMessage(), e);
//            }
//        }
//        return map;
//    }
//
//    /**
//     * 为Query设置命名参数值
//     *
//     * @param query
//     * @param valueMap
//     *
//     * @return
//     *
//     * @author zhaolei
//     * @created 2014-11-21
//     */
//    @SuppressWarnings("rawtypes")
//    private Query setParams(Query query, Map<String, Object> valueMap) {
//        if (valueMap == null) {
//            return query;
//        }
//        // 设置查询参数
//        for (Map.Entry<String, Object> entry : (Set<Map.Entry<String, Object>>) valueMap.entrySet()) {
//            Object value = entry.getValue();
//            if (value instanceof Collection) {
//                query.setParameterList(entry.getKey(), (Collection) value);
//            } else if (value instanceof Object[]) {
//                query.setParameterList(entry.getKey(), (Object[]) value);
//            } else {
//                query.setParameter(entry.getKey(), value);
//            }
//        }
//        return query;
//    }
//
//    /**
//     * 得到Hibernate SessionFactory
//     *
//     * @return
//     *
//     * @author zhaolei
//     * @created 2014-11-21
//     */
//    private SessionFactory getSessionFactory() {
//        return hibernateTemplate.getSessionFactory();
//    }
//
//    /**
//     * 得到dao对应的对象主键名称
//     *
//     * @author zhaolei
//     * @created 2012-4-24
//     */
//    private String getIdName() {
//        String idName = getIdNameByClass(clazz);
//        if (idName.equals("")) {
//            idName = getIdNameByClass(clazz.getSuperclass());
//        }
//        if (idName.equals("")) {
//            idName = "id";
//        }
//        return idName;
//    }
//
//    /**
//     * 根据对象class得到主键名称
//     *
//     * @param clazz
//     *
//     * @author zhaolei
//     * @created 2012-4-24
//     */
//    private String getIdNameByClass(Class<?> clazz) {
//        String idName = "";
//        for (Field f : clazz.getDeclaredFields()) {
//            Annotation an = f.getAnnotation(javax.persistence.Id.class);
//            if (an != null) {
//                idName = f.getName();
//                break;
//            }
//        }
//        if (idName.equals("")) {
//            for (Method m : clazz.getDeclaredMethods()) {
//                Annotation an = m.getAnnotation(javax.persistence.Id.class);
//                if (an != null) {
//                    idName = m.getName();
//                    if (idName.length() > 3) {
//                        idName = idName.substring(3);
//                        if (idName.length() > 1) {
//                            idName = idName.substring(0, 1).toLowerCase() + idName.substring(1);
//                        } else {
//                            idName = idName.toLowerCase();
//                        }
//                    }
//                    break;
//                }
//            }
//        }
//        return idName;
//    }
//
//    /**
//     * 组装查询参数
//     *
//     * @author zhanglei31
//     * @created 2014-11-27 modify 2014-12-25
//     *
//     * @param o 传入对象
//     * @return 返回查询 DetachedCriteria
//     */
//    public DetachedCriteria createDetachedCriteria(T o) {
//        return createDetachedCriteria(o, false);
//    }
//
//    /**
//     * 组装查询参数  like
//     *
//     * @param o 传入对象
//     *
//     * @return 返回查询 DetachedCriteria
//     *
//     * @author zhanglei31
//     * @created 2014-11-27 modify 2014-12-25
//     */
//    public DetachedCriteria createDetachedCriteria(T o, boolean isLike) {
//
//        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(o.getClass());
//        if (isLike) {
//            detachedCriteria.add(Example.create(o).enableLike(MatchMode.START));
//        } else {
//            detachedCriteria.add(Example.create(o));
//        }
//        return detachedCriteria;
//    }
}
