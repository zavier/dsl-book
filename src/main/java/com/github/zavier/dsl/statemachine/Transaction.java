package com.github.zavier.dsl.statemachine;

public class Transaction {
    private final State source;
    private final Event trigger;
    private final State target;

    public Transaction(State source, Event trigger, State target) {
        this.source = source;
        this.trigger = trigger;
        this.target = target;
    }

    public State getSource() {
        return source;
    }

    public Event getTrigger() {
        return trigger;
    }

    public State getTarget() {
        return target;
    }

    public String getEventCode() {
        return trigger.getCode();
    }
}
