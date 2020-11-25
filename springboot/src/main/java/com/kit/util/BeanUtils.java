//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.kit.util;

import org.springframework.util.StringUtils;

import java.lang.reflect.Field;

public class BeanUtils {

    public BeanUtils() {
    }

    public static Field getDeclaredField(Class<?> clz, String fieldName) throws Exception {
        Field ret = null;
        if (clz != null && StringUtils.hasText(fieldName)) {
            String[] propArr = StringUtils.tokenizeToStringArray(fieldName, "\\.");
            Class<?> curClz = clz;
            Field curField = null;
            String[] var6 = propArr;
            int var7 = propArr.length;

            for(int var8 = 0; var8 < var7; ++var8) {
                for(String targetPropName = var6[var8]; curClz != Object.class; curClz = curClz.getSuperclass()) {
                    try {
                        curField = curClz.getDeclaredField(targetPropName);
                    } catch (Exception var11) {
                    }

                    if (curField != null) {
                        break;
                    }
                }

                if (curField == null) {
                    break;
                }

                curClz = curField.getType();
                ret = curField;
            }
        }

        return ret;
    }

}
