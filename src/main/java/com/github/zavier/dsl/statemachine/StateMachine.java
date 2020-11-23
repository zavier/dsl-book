package com.github.zavier.dsl.statemachine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class StateMachine {
    private State start;

    private List<Event> resetEvents = new ArrayList<>();

    public StateMachine(State start) {
        this.start = start;
    }

    public State getStart() {
        return start;
    }

    public void addResetEvents(Event... events) {
        for (Event event : events) {
            resetEvents.add(event);
        }
    }

    public boolean isResetEvent(String eventCode) {
        return resetEventCodes().contains(eventCode);
    }

    private List<String> resetEventCodes() {
        return resetEvents.stream()
                .map(Event::getCode)
                .collect(Collectors.toList());
    }

    public Collection<State> getStates() {
        List<State> result = new ArrayList<>();
        collectStates(result, start);
        return result;
    }

    public void collectStates(Collection<State> result, State s) {
        if (result.contains(s)) {
            return;
        }
        result.add(s);
        for (State next : s.getAllTargets()) {
            collectStates(result, next);
        }
    }

}
