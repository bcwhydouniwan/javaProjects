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
 * Hibernate�Ļ���Dao
 * Created by wangdan16 on 2016/10/8.
 */
public abstract class BaseDao<T> {
//    private Logger logger = LoggerFactory.getLogger(BaseDao.class);
//
//    /**
//     * spring ��װ��hibernateTemplate,protected��Ϊ���ò�ͬ������Դ
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
//     * ����hibnerateTemplate������@Resourceʹ��Spring�Զ�ע��
//     *
//     * @param hibernateTemplate
//     */
//    @Resource(name="hibernateTemplate")
//    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
//        this.hibernateTemplate = hibernateTemplate;
//    }
//
//    /**
//     * ͬ�����浽���ݿ�,�ȴ�commit
//     *
//     * @author zhaolei
//     * @created 2014-11-21
//     */
//    public void flush() {
//        hibernateTemplate.flush();
//    }
//
//    /**
//     * merge����.�ڶ����������ڲ�ʱʹ��.���⸲�Ǳ����Ѿ��޸ĵ��ֶ�.���Լ��������޸ĵ�����.����ط�����.
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
//     * merge����.�ڶ����������ڲ�ʱʹ��.���⸲�Ǳ����Ѿ��޸ĵ��ֶ�.���Լ��������޸ĵ�����.����ط�����.
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
//     * ��������
//     *
//     * @param pojo �־û�����
//     *
//     * @author zhaolei
//     * @created 2014-11-21
//     */
//    public void insert(T pojo) {
//        hibernateTemplate.save(pojo);
//    }
//
//    /**
//     * ����һ������
//     *
//     * @param pojos һ��־û�����
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
//     * ����һ������
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
//     * ���³־û�����
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
//     * ����һ������
//     *
//     * @param pojos һ��־û�����
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
//     * ����һ������
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
//     * ��������.(�����unsaved-value�ж��Ǳ��滹�Ǹ���,����,��������ȷ�������.��Ҫʹ���������)
//     *
//     * @param pojo �־û�����
//     *
//     * @author zhaolei
//     * @created 2014-11-21
//     */
//    public void saveOrUpdate(T pojo) {
//        hibernateTemplate.saveOrUpdate(pojo);
//    }
//
//    /**
//     * ��������һ������(�����unsaved-value�ж��Ǳ��滹�Ǹ���,����,��������ȷ�������.��Ҫʹ���������)
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
//     * ��������һ������(�����unsaved-value�ж��Ǳ��滹�Ǹ���,����,��������ȷ�������.��Ҫʹ���������)
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
//     * ɾ������
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
//     * ɾ��һ�����
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
//     * ɾ��һ�����
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
//     * ��������ɾ������
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
//     * ����ĳ������ɾ������,�����ǵ���
//     *
//     * @param propertyName ��������
//     * @param value        ֵ
//     *
//     * @return ���µ�������
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
//     * ͨ��hql�������ɾ�����������
//     *
//     * @param hql    hql���
//     * @param values ����ֵ
//     *
//     * @return ���µ�������
//     *
//     * @author zhaolei
//     * @created 2014-11-21
//     */
//    public int updateOrDeleteByHql(String hql, Object... values) {
//        return hibernateTemplate.bulkUpdate(hql, values);
//    }
//
//    /**
//     * ͨ��hql�������ɾ���������� ֧��in
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
//     * ��ѯ����ȫ��ʵ����ɨȫ��
//     *
//     * @return ȫ���¼list
//     */
//    public List<T> findAll() {
//        DetachedCriteria dc = DetachedCriteria.forClass(clazz);
//        return find(dc);
//    }
//
//    /**
//     * �����������Ҷ���
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
//     * ����һ��������ѯ
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
//     * ָ������ʵ����������.����������ѯ
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
//     * ָ������ʵ����������.����������ѯ
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
//     * �������Բ�ѯ,�����ǵ���
//     *
//     * @author zhaolei
//     * @created 2014-11-21
//     *
//     * @param propertyName
//     *            ��������
//     * @param value
//     *            ֵ
//     * @return
//     */
//    //@SuppressWarnings("unchecked")
//    /*protected List<T> findByProperty(String propertyName, Object value) {
//        String hql = "from " + clazz.getSimpleName() + " where " + propertyName + "=?";
//        return hibernateTemplate.find(hql, value);
//    }*/
//
//    /**
//     * ���ݶ���ʵ�����ز�ѯ���
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
//     * ���ݶ���ʵ��,��ѯ�ֶν��
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
//     * ����Example���ؽ��
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
//     * ����sql��ѯ
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
//     * ����Example���ؽ��
//     *
//     * @author zhaolei
//     * @created 2012-4-21
//     * @param example
//     *            ��ѯ�ֶν��
//     * @param page
//     *            ��ҳ����
//     * @return
//     */
//    /*@SuppressWarnings("unchecked")
//    protected List<T> findByExample(Example example, Page page) {
//        return hibernateTemplate.findByExample(example, page.getStart(), page.getPageSize());
//    }*/
//
//    /**
//     * ����sql��ѯ
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
//     * ͨ��sql��ѯ
//     *
//     * @author chenchun
//     * @created 2012-10-24
//     *
//     * @param sql     sql���
//     * @param timeout ��ʱʱ�䣬��λ����
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
//     * ����sql��ѯ
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
//     * ����hql��ѯ
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
//     * ����hql�Ͳ�����ѯ
//     *
//     * @param hql
//     * @param values ռλ����Ӧ��ֵ
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
//     * ��������������ѯ
//     *
//     * @param hql      ��������������hql
//     * @param valueMap ����������map.key����������������(����:name) valueMap.put("name","wing")
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
//     * ���ݶ�̬��ѯ������ѯ
//     *
//     * @param criteria ��̬��ѯ����
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
//     * ����ĳ�����Է����map.key������ֵ,value�Ƕ���list
//     *
//     * @param <C>          �������Եķ���
//     * @param criteria     ��̬��ѯ��������
//     * @param propertyName ��������
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
//     * ����ĳ�����Է����map.key������ֵ,value�Ƕ���
//     *
//     * @param <C>          �������Եķ���
//     * @param criteria     ��̬��ѯ��������
//     * @param propertyName ��������
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
//     * ���ݶ�̬��ѯ����,�ֶβ�ѯ,���ڷ�ҳ������д���ҳ����
//     *
//     * @author zhaolei
//     * @created 2011-9-13
//     *
//     * @param criteria
//     *            ��̬��ѯ����
//     * @param page
//     *            ��ҳ����
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
//     * ���ݶ�̬��ѯ����,�ֶβ�ѯ
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
//     * ͨ����̬��ѯ������ҳ��ѯ���Զ���ѯtotalCount������ֵ��page����Ҫ�û��Լ�ѡ���Ƿ�ʹ����������������Զ�count��ѯ��Ч������
//     * <p/>
//     * zhaolei 2014/12/05
//     *
//     * @param criteria ��̬��ѯ����
//     * @param page     ��ҳ����
//     *
//     * @return ��ѯ���list
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
//     * ͨ����̬��ѯ������ҳ��ѯ���û���Ҫ�Լ���ѯcount
//     * <p/>
//     * zhaolei 2014/12/05
//     *
//     * @param criteria ��̬��ѯ����
//     * @param page     ��ҳ����
//     *
//     * @return ��ѯ���list
//     */
//    @SuppressWarnings("unchecked")
//    protected List<T> find(DetachedCriteria criteria, Page page) {
//        return (List<T>) hibernateTemplate.findByCriteria(criteria, page.getStart(), page.getPageSize());
//    }
//
//    /**
//     * ��ѯ�ض��Ĳ���
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
//     * ��ѯ�ض��Ĳ�����֧�ַ�ҳ
//     * (��project������ʱ������֮ǰ��distinct���õ���count������׼ȷ)
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
//        // ��project������ʱ������֮ǰ��distinct���õ���count������׼ȷ������
//        int count = findCount(dc);
//        page.setTotalCount(count);
//        return list;
//    }*/
//
//    /**
//     * ֱ��ͨ��sql����ѯ
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
//     * ����sql��ѯ
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
//     * ����criteria��ѯ����
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
//         * ����proxy session
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
//    // * ����Hql��ѯ��¼��
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
//    // * ����Hql��ѯ����
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
//     * hqlת����sql
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
//     * ��װ�������ĳ�����Է���,װ�س�map.������ظ���.��׷�ӵ�value��list��,����ֵΪnull,������map
//     *
//     * @param <C>          �������Ե�����
//     * @param list         ����������ֵ
//     * @param propertyName �������ݵ���������
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
//     * ��װ�������ĳ�����Է���,װ�س�map.������ظ���.�򸲸�ǰ���ֵ,����ֵΪnull,������map
//     *
//     * @param <C>          �������Ե�����
//     * @param list         ����������ֵ
//     * @param propertyName �������ݵ���������
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
//     * ΪQuery������������ֵ
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
//        // ���ò�ѯ����
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
//     * �õ�Hibernate SessionFactory
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
//     * �õ�dao��Ӧ�Ķ�����������
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
//     * ���ݶ���class�õ���������
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
//     * ��װ��ѯ����
//     *
//     * @author zhanglei31
//     * @created 2014-11-27 modify 2014-12-25
//     *
//     * @param o �������
//     * @return ���ز�ѯ DetachedCriteria
//     */
//    public DetachedCriteria createDetachedCriteria(T o) {
//        return createDetachedCriteria(o, false);
//    }
//
//    /**
//     * ��װ��ѯ����  like
//     *
//     * @param o �������
//     *
//     * @return ���ز�ѯ DetachedCriteria
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
