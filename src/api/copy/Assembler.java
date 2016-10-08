package api.copy;

import exception.AssemblerException;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.*;

/**
 * ����ͨ��get,set�����໥װ��.����a����װ�ص�b����.���b.setName(a.getName)
 *
 * Created by wangdan16 on 2016/10/8.
 */
public class Assembler {
    /**
     * ��̬��װ����.����װ�Ķ���(toObject)������ΪassembleTrigger()�������Ա� ����.assembleTrigger�����в���
     * <p/>
     * ע��:���Դ����������Ҫ�ӳټ��ص�get����,��getʱ,���session�Ѿ��ر� ��,����session�رմ���.���ڹ�������֮��Ƚϳ���
     *
     * @param fromObj       Դ����
     * @param toObj         Ŀ�����
     * @param excludeFields toObj�в���װ������.��������ֱ�Ӵ򽻵�.ֻ��get,set����������ֱ�ӹ���
     * @return ��װ�õĶ���
     */
    public static <T> T assemble(Object fromObj, T toObj, String... excludeFields) {
        if (fromObj == null) {
            return toObj;
        }
        return assemble(fromObj, toObj,false, excludeFields);
    }

    /**
     * ��̬��װ����.����װ�Ķ���(toObject)������ΪassembleTrigger()�������Ա� ����.assembleTrigger�����в���
     * <p/>
     * ע��:���Դ����������Ҫ�ӳټ��ص�get����,��getʱ,���session�Ѿ��ر� ��,����session�رմ���.���ڹ�������֮��Ƚϳ���
     *
     * add by zhanglei31
     * cause ����list
     *
     * @param fromObj       Դ����
     * @param toObj         Ŀ�����
     * @param excludeFields toObj�в���װ������.��������ֱ�Ӵ򽻵�.ֻ��get,set����������ֱ�ӹ���
     * @return ��װ�õĶ���
     */
    public static <T> T assemble(Object fromObj, T toObj, boolean isCopyList, String... excludeFields) {
        if (fromObj == null) {
            return toObj;
        }

        Method[] toMethods = toObj.getClass().getMethods();
        Method[] fromMethods = fromObj.getClass().getMethods();
        Map<String, Method> fromMethodMap = new HashMap<String, Method>();
        for (int i = 0; i < fromMethods.length; i++)
            fromMethodMap.put(fromMethods[i].getName(), fromMethods[i]);

        String regex = "";
        for (int i = 0; i < excludeFields.length; i++) {
            regex = regex + excludeFields[i];
            if (i < excludeFields.length - 1)
                regex = regex + "|";
        }
        for (int i = 0; i < toMethods.length; i++) {
            Method m = toMethods[i];
            String mName = m.getName();
            try {
                if (mName.startsWith("set") && !mName.equals("set")) {
                    Type paramType = m.getGenericParameterTypes()[0];
                    String fName = mName.replaceFirst("set", "");
                    fName = fName.replaceFirst(fName.substring(0, 1), fName.substring(0, 1).toLowerCase());
                    if (!fName.matches(regex)) {
                        Method fm = fromMethodMap.get(mName.replaceFirst("set", "get"));
                        if (fm != null) {

                            if (isCopyList) {
                                // ���List�ж� �ܹ�����list
                                if (fm.getReturnType().equals(paramType)
                                        || (fm.getReturnType().toString().contains("java.util.List"))
                                        && paramType.toString().contains("java.util.List")) {
                                    Object o = fm.invoke(fromObj);
//                                if (o != null) {
                                    m.invoke(toObj, o);
//                                }
                                }
                            } else {
                                if (fm.getReturnType().equals(paramType)) {
                                    Object o = fm.invoke(fromObj);
//                                if (o != null) {
                                    m.invoke(toObj, o);
//                                }
                                }
                            }
                        }
                    }
                } else if (mName.equals("assembleTrigger")) {
                    m.invoke(toObj);
                }
            } catch (Exception e) {
                throw new AssemblerException("��װ�������:" + e.getMessage(), e);
            }
        }
        return toObj;
    }

    /**
     * ��̬��װ����.����װ�Ķ���(toObject)������ΪassembleTrigger()�������Ա� ����.assembleTrigger�����в���
     * <p/>
     * ע��:���Դ����������Ҫ�ӳټ��ص�get����,��getʱ,���session�Ѿ��ر� ��,����session�رմ���.���ڹ�������֮��Ƚϳ���
     *
     * @param fromObj       Դ����
     * @param toObj         Ŀ�����
     * @param includeFields toObj��Ҫ��װ�Ķ���.��������ֱ�Ӵ򽻵�.ֻ��get,set����������ֱ�ӹ���
     * @return ��װ�õĶ���
     */
    public static <T> T assembleSpecify(Object fromObj, T toObj, String... includeFields) {
        if (fromObj == null) {
            return toObj;
        }

        Method[] toMethods = toObj.getClass().getMethods();
        Method[] fromMethods = fromObj.getClass().getMethods();
        Map<String, Method> fromMethodMap = new HashMap<String, Method>();
        for (int i = 0; i < fromMethods.length; i++)
            fromMethodMap.put(fromMethods[i].getName(), fromMethods[i]);

        String regex = "";
        for (int i = 0; i < includeFields.length; i++) {
            regex = regex + includeFields[i];
            if (i < includeFields.length - 1)
                regex = regex + "|";
        }
        for (int i = 0; i < toMethods.length; i++) {
            Method m = toMethods[i];
            String mName = m.getName();
            try {
                if (mName.startsWith("set") && !mName.equals("set")) {
                    Type paramType = m.getGenericParameterTypes()[0];
                    String fName = mName.replaceFirst("set", "");
                    fName = fName.replaceFirst(fName.substring(0, 1), fName.substring(0, 1).toLowerCase());
                    if (fName.matches(regex)) {
                        Method fm = fromMethodMap.get(mName.replaceFirst("set", "get"));
                        if (fm != null) {
                            if (fm.getReturnType().equals(paramType)) {
                                m.invoke(toObj, fm.invoke(fromObj));
                            }
                        }
                    }
                } else if (mName.equals("assembleTrigger")) {
                    m.invoke(toObj);
                }
            } catch (Exception e) {
                throw new AssemblerException("��װ�������:" + e.getMessage(), e);
            }
        }
        return toObj;
    }

    /**
     * ��̬��װ����ָ����װ����
     *
     * @param fromList Դ����
     * @param toClass Ŀ������
     * @param includeFields ��Ҫ��װ�����ԣ�get��setƥ�䣬��������ֱ�Ӵ򽻵�
     * @param <T> ����
     * @return ���list
     */
    public static <T> List<T> assembleSpecifyList(Collection<?> fromList, Class<T> toClass, String... includeFields) {
        List<T> toList = new ArrayList<T>(fromList.size());
        try {
            for (Iterator<?> iterator = fromList.iterator(); iterator.hasNext(); ) {
                Object fromObject = iterator.next();
                T toObject = toClass.newInstance();
                if (includeFields.length > 0)
                    toObject = assembleSpecify(fromObject, toObject, includeFields);
                else
                    toObject = assembleSpecify(fromObject, toObject);
                toList.add(toObject);
            }
        } catch (Exception e) {
            throw new AssemblerException("��װ�������:" + e.getMessage(), e);
        }
        return toList;
    }

    /**
     * ��̬��װ����,Դ����Ϊ��(null)��ֵ����,���Դ���toObject������Ϊ assembleTrigger�ķ���
     *
     * @param fromObj       Դ����
     * @param toObj         Ŀ�����
     * @param excludeFields toObj�в���װ������.��������ֱ�Ӵ򽻵�.ֻ��get,set����������ֱ�ӹ���
     * @return ��װ��Ķ���
     */
    public static <T> T assembleNotNull(Object fromObj, T toObj, String... excludeFields) {
        if (fromObj == null) {
            return toObj;
        }
        return assembleNotNull(fromObj, toObj, false, excludeFields);
    }

    /**
     * ��̬��װ����,Դ����Ϊ��(null)��ֵ����,���Դ���toObject������Ϊ assembleTrigger�ķ���
     *
     * @param fromObj       Դ����
     * @param toObj         Ŀ�����
     * @param excludeFields toObj�в���װ������.��������ֱ�Ӵ򽻵�.ֻ��get,set����������ֱ�ӹ���
     * @return ��װ��Ķ���
     *
     * add by zhanglei31 at 2015-02-05
     */
    public static <T> T assembleNotNull(Object fromObj, T toObj, boolean isCopyList,  String... excludeFields) {
        if (fromObj == null) {
            return toObj;
        }
        Method[] toMethods = toObj.getClass().getMethods();
        Method[] fromMethods = fromObj.getClass().getMethods();
        Map<String, Method> fromMethodMap = new HashMap<String, Method>();
        for (int i = 0; i < fromMethods.length; i++)
            fromMethodMap.put(fromMethods[i].getName(), fromMethods[i]);
        String regex = "";
        for (int i = 0; i < excludeFields.length; i++) {
            regex = regex + excludeFields[i];
            if (i < excludeFields.length - 1)
                regex = regex + "|";
        }
        for (int i = 0; i < toMethods.length; i++) {
            Method m = toMethods[i];
            String mName = m.getName();

            try {
                if (mName.startsWith("set") && !mName.equals("set")) {
                    Type paramType = m.getGenericParameterTypes()[0];
                    String fName = mName.replaceFirst("set", "");
                    fName = fName.replaceFirst(fName.substring(0, 1), fName.substring(0, 1).toLowerCase());
                    if (!fName.matches(regex)) {
                        Method fm = fromMethodMap.get(mName.replaceFirst("set", "get"));
                        if (fm != null) {

                            if (isCopyList) {
                                if (fm.getReturnType().equals(paramType)
                                        || (fm.getReturnType().toString().contains("java.util.List"))
                                        && paramType.toString().contains("java.util.List")) {
                                    Object value = fm.invoke(fromObj);
                                    if (value != null)
                                        m.invoke(toObj, value);
                                }
                            } else {
                                if (fm.getReturnType().equals(paramType)) {
                                    Object value = fm.invoke(fromObj);
                                    if (value != null)
                                        m.invoke(toObj, value);
                                }
                            }
                        }
                    }
                } else if (mName.equals("assembleTrigger")) {
                    m.invoke(toObj);
                }
            } catch (Exception e) {
                throw new AssemblerException("��װ�������:" + e.getMessage(), e);
            }
        }
        return toObj;
    }

    /**
     * ��ԴListװ��һ������Ŀ��class���͵�List.���Դ���toObject������Ϊ assembleTrigger�ķ���
     *
     * @param fromList      Դlist
     * @param toClass       Ŀ��class
     * @param excludeFields ��װ�����������
     * @return ��װ���list
     */
    public static <T> List<T> assembleList2NewList(Collection<?> fromList, Class<T> toClass, String... excludeFields) {
        List<T> toList = new ArrayList<T>(fromList.size());
        try {
            for (Iterator<?> iterator = fromList.iterator(); iterator.hasNext(); ) {
                Object fromObject = iterator.next();
                T toObject = toClass.newInstance();
                if (excludeFields.length > 0)
                    toObject = assemble(fromObject, toObject, excludeFields);
                else
                    toObject = assemble(fromObject, toObject);
                toList.add(toObject);
            }
        } catch (Exception e) {
            throw new AssemblerException("��װ�������:" + e.getMessage(), e);
        }
        return toList;
    }


    /**
     * ��ԴListװ��һ������Ŀ��class���͵�List.���Դ���toObject������Ϊ assembleTrigger�ķ���
     *
     * @param fromList      Դlist
     * @param toClass       Ŀ��class
     * @param excludeFields ��װ�����������
     * @return ��װ���list
     */
    public static <T> List<T> assembleListNotNull2NewList(Collection<?> fromList, Class<T> toClass, String...
            excludeFields) {
        return assembleListNotNull2NewList(fromList, toClass, false, excludeFields);
    }

    /**
     * ��ԴListװ��һ������Ŀ��class���͵�List.���Դ���toObject������Ϊ assembleTrigger�ķ���
     *
     * @param fromList      Դlist
     * @param toClass       Ŀ��class
     * @param excludeFields ��װ�����������
     * @return ��װ���list
     */
    public static <T> List<T> assembleListNotNull2NewList(Collection<?> fromList, Class<T> toClass, boolean isCopyList,
                                                          String...excludeFields) {
        List<T> toList = new ArrayList<T>(fromList.size());
        try {
            for (Iterator<?> iterator = fromList.iterator(); iterator.hasNext(); ) {
                Object fromObject = iterator.next();
                T toObject = toClass.newInstance();
                if (excludeFields.length > 0)
                    toObject = assembleNotNull(fromObject, toObject,isCopyList, excludeFields);
                else
                    toObject = assembleNotNull(fromObject, toObject, isCopyList);
                toList.add(toObject);
            }
        } catch (Exception e) {
            throw new AssemblerException("��װ�������:" + e.getMessage(), e);
        }
        return toList;
    }

    /**
     * ��ԴListװ��һ������Ŀ��class���͵�List,���Դ���toObject������Ϊ assembleTrigger�ķ���
     *
     * @param fromList      ԴList
     * @param toList        Ŀ��List
     * @param excludeFields ����Ҫװ�������
     * @return ��װ���list
     */
    public static <T> List<T> assembleList2List(List<?> fromList, List<T> toList, Class<T> toClass, String... excludeFields) {
        try {
            for (int i = 0; i < fromList.size(); i++) {
                Object fromObject = fromList.get(i);
                T toObject = i >= toList.size() ? toClass.newInstance() : toList.get(i);
                if (excludeFields.length > 0)
                    toObject = assemble(fromObject, toObject, excludeFields);
                else
                    toObject = assemble(fromObject, toObject);
                toList.add(toObject);
            }
        } catch (Exception e) {
            throw new AssemblerException("��װ�������:" + e.getMessage(), e);
        }
        return toList;
    }


    /**
     * ��ԴListװ��һ������Ŀ��class���͵�List,���Դ���toObject������Ϊ assembleTrigger�ķ���
     *
     * @param fromList      ԴList
     * @param toList        Ŀ��List
     * @param excludeFields ����Ҫװ�������
     * @return ��װ���list
     *
     * add by zhanglei ����list at 2015-02-05
     */
    public static <T> List<T> assembleList2List(List<?> fromList, List<T> toList, Class<T> toClass, boolean isCopyList,  String... excludeFields) {
        try {
            for (int i = 0; i < fromList.size(); i++) {
                Object fromObject = fromList.get(i);
                T toObject = i >= toList.size() ? toClass.newInstance() : toList.get(i);
                if (excludeFields.length > 0)
                    toObject = assemble(fromObject, toObject, isCopyList,  excludeFields);
                else
                    toObject = assemble(fromObject, toObject, isCopyList);
                toList.add(toObject);
            }
        } catch (Exception e) {
            throw new AssemblerException("��װ�������:" + e.getMessage(), e);
        }
        return toList;
    }
}
