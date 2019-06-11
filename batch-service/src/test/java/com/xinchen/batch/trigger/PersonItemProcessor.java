package com.xinchen.batch.trigger;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidationException;

/**
 * 任务执行器(附带校验)
 * @author xinchen
 * @version 1.0
 * @date 11/06/2019 15:40
 */
@Slf4j
public class PersonItemProcessor extends ValidatingItemProcessor<Person> {
    @Override
    public Person process(final Person person) throws ValidationException {
        // 需要执行super.process(item)才会调用自定义校验器
        super.process(person);

        final String firstName = person.getFirstName().toUpperCase();
        final String lastName = person.getLastName().toUpperCase();
        final Person transformedPerson = new Person(firstName, lastName);

        log.info("Converting ({}) into ({}) ",person,transformedPerson);
        return transformedPerson;

    }
}
