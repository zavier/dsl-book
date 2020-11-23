package com.github.zavier.dsl.statemachine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Controller {
    private static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);

    private State currentState;
    private StateMachine machine;

    private CommandChannel commandsChannel;

    public CommandChannel getCommandsChannel() {
        return commandsChannel;
    }

    public void handle(String eventCode) {
        if (currentState.hasTransaction(eventCode)) {
            transactionTo(currentState.targetState(eventCode));
        } else if (machine.isResetEvent(eventCode)) {
            transactionTo(machine.getStart());
        }
        LOGGER.info("unknown eventCode:{}", eventCode);
    }

    public void transactionTo(State target) {
        currentState = target;
        currentState.executeActions(commandsChannel);
    }
}
