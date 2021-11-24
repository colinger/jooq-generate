/**
 * Copyright (c) 2021-2021 All Rights Reserved.
 */

package io.github.colinger;


import io.github.colinger.extend.AbstractExtendDAOImpl;
import org.jooq.codegen.GeneratorStrategy;
import org.jooq.codegen.JavaGenerator;
import org.jooq.codegen.JavaWriter;
import org.jooq.meta.TableDefinition;
import org.jooq.tools.JooqLogger;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;

/**
 * @密级别：classify:p2#-
 * @author: xinying.ge
 * @Date: 2021/11/25 06:36
 * @Description:
 */
public class CustomJavaGenerator extends JavaGenerator {
    private static final JooqLogger log = JooqLogger.getLogger(CustomJavaGenerator.class);


    /**
     * 重写了 generateDao， 具体的生成逻辑还是调用父级的方法，只是在生成完成后，获取文件内容，
     * 然后对文件指定的内容进行替换操作
     *
     * @param table
     */
    @Override
    protected void generateDao(TableDefinition table) {
        super.generateDao(table);
        File file = getFile(table, GeneratorStrategy.Mode.DAO);
        if (file.exists()) {
            try {
                String fileContent = new String(FileCopyUtils.copyToByteArray(file));
                String oldExtends = " extends DAOImpl";
                String newExtends = " extends AbstractExtendDAOImpl";
                fileContent = fileContent.replace("import org.jooq.impl.DAOImpl;\n", "");
                fileContent = fileContent.replace(oldExtends, newExtends);
                FileCopyUtils.copy(fileContent.getBytes(), file);
            } catch (IOException e) {
                log.error("generateDao error: {}", file.getAbsolutePath(), e);
            }
        }
    }

    @Override
    protected void generateDao(TableDefinition table, JavaWriter out) {
        // 用于生成 .AbstractDAOExtendImpl 内容
        out.ref(AbstractExtendDAOImpl.class);
        super.generateDao(table, out);
    }
}
