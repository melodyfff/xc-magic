# Flyway初体验

官网： https://flywaydb.org/

命令行执行环境变量: https://flywaydb.org/documentation/envvars


## 修改pom执行
```xml
            <plugin>
                <groupId>org.flywaydb</groupId>
                <artifactId>flyway-maven-plugin</artifactId>
                <configuration>
                    <url>jdbc:h2:file:./target/foobar</url>
                    <user>sa</user>
                </configuration>
                <dependencies>
                    <dependency>
                        <groupId>com.h2database</groupId>
                        <artifactId>h2</artifactId>
                        <version>1.4.197</version>
                    </dependency>
                </dependencies>
            </plugin>
```
然后执行
```bash
mvn flyway:migrate
```

## 命令行执行
```bash
mvn flyway:migrate -Dflyway.url="jdbc:h2:file:~/flyway111" -Dflyway.user="sa" -Dflyway.password=
```