package api.copy;

import exception.AssemblerException;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.*;

/**
 * 对象通过get,set名称相互装载.比如a对象装载到b对象.如果b.setName(a.getName)
 *
 * Created by wangdan16 on 2016/10/8.
 */
public class Assembler {
    /**
     * 动态组装对象.被组装的对象(toObject)中名称为assembleTrigger()方法可以被 触发.assembleTrigger不能有参数
     * <p/>
     * 注意:如果源对象中有需要延迟加载的get方法,当get时,如果session已经关闭 了,会有session关闭错误.这在关联对象之间比较常见
     *
     * @param fromObj       源对象
     * @param toObj         目标对象
     * @param excludeFields toObj中不组装的属性.不跟属性直接打交道.只和get,set方法的名称直接关联
     * @return 组装好的对象
     */
    public static <T> T assemble(Object fromObj, T toObj, String... excludeFields) {
        if (fromObj == null) {
            return toObj;
        }
        return assemble(fromObj, toObj,false, excludeFields);
    }

    /**
     * 动态组装对象.被组装的对象(toObject)中名称为assembleTrigger()方法可以被 触发.assembleTrigger不能有参数
     * <p/>
     * 注意:如果源对象中有需要延迟加载的get方法,当get时,如果session已经关闭 了,会有session关闭错误.这在关联对象之间比较常见
     *
     * add by zhanglei31
     * cause 拷贝list
     *
     * @param fromObj       源对象
     * @param toObj         目标对象
     * @param excludeFields toObj中不组装的属性.不跟属性直接打交道.只和get,set方法的名称直接关联
     * @return 组装好的对象
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
                                // 添加List判断 能够复制list
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
                throw new AssemblerException("组装对象错误:" + e.getMessage(), e);
            }
        }
        return toObj;
    }

    /**
     * 动态组装对象.被组装的对象(toObject)中名称为assembleTrigger()方法可以被 触发.assembleTrigger不能有参数
     * <p/>
     * 注意:如果源对象中有需要延迟加载的get方法,当get时,如果session已经关闭 了,会有session关闭错误.这在关联对象之间比较常见
     *
     * @param fromObj       源对象
     * @param toObj         目标对象
     * @param includeFields toObj需要组装的对象.不跟属性直接打交道.只和get,set方法的名称直接关联
     * @return 组装好的对象
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
                throw new AssemblerException("组装对象错误:" + e.getMessage(), e);
            }
        }
        return toObj;
    }

    /**
     * 动态组装对象。指定组装属性
     *
     * @param fromList 源对象
     * @param toClass 目标类型
     * @param includeFields 需要组装的属性，get，set匹配，不跟属性直接打交道
     * @param <T> 类型
     * @return 结果list
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
            throw new AssemblerException("组装对象错误:" + e.getMessage(), e);
        }
        return toList;
    }

    /**
     * 动态组装对象,源对象为空(null)的值忽略,可以触发toObject的名称为 assembleTrigger的方法
     *
     * @param fromObj       源对象
     * @param toObj         目标对象
     * @param excludeFields toObj中不组装的属性.不跟属性直接打交道.只和get,set方法的名称直接关联
     * @return 组装后的对象
     */
    public static <T> T assembleNotNull(Object fromObj, T toObj, String... excludeFields) {
        if (fromObj == null) {
            return toObj;
        }
        return assembleNotNull(fromObj, toObj, false, excludeFields);
    }

    /**
     * 动态组装对象,源对象为空(null)的值忽略,可以触发toObject的名称为 assembleTrigger的方法
     *
     * @param fromObj       源对象
     * @param toObj         目标对象
     * @param excludeFields toObj中不组装的属性.不跟属性直接打交道.只和get,set方法的名称直接关联
     * @return 组装后的对象
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
                throw new AssemblerException("组装对象错误:" + e.getMessage(), e);
            }
        }
        return toObj;
    }

    /**
     * 从源List装配一个符合目标class类型的List.可以触发toObject的名称为 assembleTrigger的方法
     *
     * @param fromList      源list
     * @param toClass       目标class
     * @param excludeFields 不装配的属性名称
     * @return 组装后的list
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
            throw new AssemblerException("组装对象错误:" + e.getMessage(), e);
        }
        return toList;
    }


    /**
     * 从源List装配一个符合目标class类型的List.可以触发toObject的名称为 assembleTrigger的方法
     *
     * @param fromList      源list
     * @param toClass       目标class
     * @param excludeFields 不装配的属性名称
     * @return 组装后的list
     */
    public static <T> List<T> assembleListNotNull2NewList(Collection<?> fromList, Class<T> toClass, String...
            excludeFields) {
        return assembleListNotNull2NewList(fromList, toClass, false, excludeFields);
    }

    /**
     * 从源List装配一个符合目标class类型的List.可以触发toObject的名称为 assembleTrigger的方法
     *
     * @param fromList      源list
     * @param toClass       目标class
     * @param excludeFields 不装配的属性名称
     * @return 组装后的list
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
            throw new AssemblerException("组装对象错误:" + e.getMessage(), e);
        }
        return toList;
    }

    /**
     * 从源List装配一个符合目标class类型的List,可以触发toObject的名称为 assembleTrigger的方法
     *
     * @param fromList      源List
     * @param toList        目标List
     * @param excludeFields 不需要装配的属性
     * @return 组装后的list
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
            throw new AssemblerException("组装对象错误:" + e.getMessage(), e);
        }
        return toList;
    }


    /**
     * 从源List装配一个符合目标class类型的List,可以触发toObject的名称为 assembleTrigger的方法
     *
     * @param fromList      源List
     * @param toList        目标List
     * @param excludeFields 不需要装配的属性
     * @return 组装后的list
     *
     * add by zhanglei 拷贝list at 2015-02-05
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
            throw new AssemblerException("组装对象错误:" + e.getMessage(), e);
        }
        return toList;
    }
}
