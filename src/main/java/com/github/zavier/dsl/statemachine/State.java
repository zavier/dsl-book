package com.github.zavier.dsl.statemachine;

import com.github.zavier.dsl.util.Assert;

import java.util.*;
import java.util.stream.Collectors;

public class State {
    private String name;
    private List<Command> actions = new ArrayList<>();
    // 当前状态，在不同事件下触发的转换
    private Map<String, Transaction> transactions = new HashMap<>();

    public void addTransaction(Event event, State targetState) {
        Assert.assertNotNull(targetState, "targetState不能为空");
        transactions.put(event.getCode(), new Transaction(this, event, targetState));
    }

    public boolean hasTransaction(String eventCode) {
        return transactions.containsKey(eventCode);
    }

    public State targetState(String eventCode) {
        return transactions.get(eventCode).getTarget();
    }

    public void executeActions(CommandChannel commandsChannel) {
        actions.forEach(action -> {
            commandsChannel.send(action.getCode());
        });
    }

    Collection<State> getAllTargets() {
        return transactions.values().stream()
                .map(Transaction::getTarget)
                .collect(Collectors.toList());
    }
}
