package com.xinchen.batch.auto;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

/**
 *
 * 中间处理器,转换大小写
 *
 * @author xinchen
 * @version 1.0
 * @date 11/06/2019 11:46
 */
@Slf4j
public class PersonItemProcessor implements ItemProcessor<Person,Person> {

    @Override
    public Person process(final Person person) throws Exception {
        final String firstName = person.getFirstName().toUpperCase();
        final String lastName = person.getLastName().toUpperCase();
        final Person transformedPerson = new Person(firstName, lastName);

        log.info("Converting ({}) into ({}) ",person,transformedPerson);

        return transformedPerson;
    }
}
