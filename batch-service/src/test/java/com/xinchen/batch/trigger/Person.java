package com.xinchen.batch.trigger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

/**
 * @author xinchen
 * @version 1.0
 * @date 11/06/2019 11:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @Size(min = 2)
    private String firstName;
    @Size(min = 2)
    private String lastName;
}
