package com.xinchen.spring.statemachine;

import org.springframework.boot.CommandLineRunner;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
class InitRunner implements CommandLineRunner {

  private final StateMachine<States, Events> machine;

  InitRunner (StateMachine<States, Events> machine) {
    this.machine = machine;
  }

  @Override
  public void run(String... args) throws Exception {
    machine.sendEvent(Events.E1);
    machine.sendEvent(Events.E2);
  }
}
