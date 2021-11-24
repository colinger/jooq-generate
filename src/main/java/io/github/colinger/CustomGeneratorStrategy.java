/**
 * Copyright (c) 2021-2021 All Rights Reserved.
 */

package io.github.colinger;

import org.jooq.codegen.DefaultGeneratorStrategy;
import org.jooq.meta.Definition;
import org.jooq.meta.TableDefinition;

/**
 * @密级别：classify:p2#-
 * @author: xinying.ge
 * @Date: 2021/11/25 06:43
 * @Description:
 */
public class CustomGeneratorStrategy extends DefaultGeneratorStrategy {
    @Override
    public String getJavaClassName(Definition definition, Mode mode) {
        String result = super.getJavaClassName(definition, mode);
        switch (mode) {
            case POJO:
                result += "Pojo";
                break;
            case DEFAULT:
                if (definition instanceof TableDefinition) {
                    result = "T" + result;
                }
                break;
            default:
                break;
        }
        return result;
    }
}
