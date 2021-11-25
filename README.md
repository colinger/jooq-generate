# jooq-generate
使用方法：
```
<plugin>
                <groupId>org.jooq</groupId>
                <artifactId>jooq-codegen-maven</artifactId>
                <version>${jooq.version}</version>
                <executions>
                    <execution>
                        <id>jooq-generate</id>
                        <phase>generate-resources</phase>
                        <goals>
                            <goal>generate</goal>
                        </goals>
                        <configuration>
                            <jdbc>
                                <driver>com.mysql.cj.jdbc.Driver</driver>
                                <url>jdbc:mysql://127.0.0.1:3306/learn-jooq?serverTimezone=GMT%2B8</url>
                                <user>root</user>
                                <password>admin</password>
                            </jdbc>
                            <generator>
                                <name>io.github.colinger.CustomJavaGenerator</name>
                                <strategy>
                                    <name>io.github.colinger.CustomGeneratorStrategy</name>
                                </strategy>
                                <database>
                                    <includes>s1_.*</includes>
                                    <inputSchema>learn-jooq</inputSchema>
                                </database>
                                <generate>
                                    <pojos>true</pojos>
                                    <daos>true</daos>
                                    <interfaces>true</interfaces>
                                    <springAnnotations>true</springAnnotations>
                                    <pojosEqualsAndHashCode>true</pojosEqualsAndHashCode>
                                    <javaTimeTypes>true</javaTimeTypes>
                                    <fluentSetters>true</fluentSetters>
                                </generate>
                                <target>
                                    <packageName>cn.xc.monitor.appmonitor.jooq.codegen</packageName>
                                    <directory>src/main/java</directory>
                                </target>
                            </generator>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
```
