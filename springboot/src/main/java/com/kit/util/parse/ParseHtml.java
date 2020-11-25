package com.kit.util.parse;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 取得request对象中 所有的参数值并生成 一个相应的对象返回
 *
 * @author john
 */
@SuppressWarnings("all")
public class ParseHtml {
    /**
     * Class<T> beanClass可以接受任何类型的javaBean,使用泛型调用者不用进行强转
     *
     * @param <T>
     * @param request
     * @param beanClass
     * @return
     */
    public static <T> T request2Bean(HttpServletRequest request, Class<T> beanClass) {
        try {
/**创建封装数据的bean**/
            T bean = beanClass.newInstance();
            Map map = request.getParameterMap();
            BeanUtils.populate(bean, map);
            return bean;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}