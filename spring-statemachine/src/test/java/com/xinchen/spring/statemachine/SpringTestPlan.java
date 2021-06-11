package com.xinchen.spring.statemachine;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.statemachine.StateMachine;

/**
 *
 */
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
class SpringTestPlan {

  @Autowired
  StateMachine<States, Events> machine;

  @Test
  void stateChangeTest(){
    assertDoesNotThrow(()-> {
      machine.sendEvent(Events.E1);
      machine.sendEvent(Events.E2);
    });
  }

}
