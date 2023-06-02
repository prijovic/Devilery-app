package com.ftn.sbnz.service.services.kie;

import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.event.rule.*;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;

import java.util.List;

public class KieSessionCreatorHelper {
    public static KieSession createKieSessionFromDRL(String drl) {
        KieHelper kieHelper = new KieHelper();
        kieHelper.addContent(drl, ResourceType.DRL);

        Results results = kieHelper.verify();

        if (results.hasMessages(Message.Level.WARNING, Message.Level.ERROR)) {
            List<Message> messages = results.getMessages(Message.Level.WARNING, Message.Level.ERROR);
            for (Message message : messages) {
                System.out.println("Error: " + message.getText());
            }

            throw new IllegalStateException("Compilation errors were found. Check the logs.");
        }

        return kieHelper.build().newKieSession();
    }

    static class CustomAgendaEventListener implements AgendaEventListener {

        @Override
        public void matchCreated(MatchCreatedEvent matchCreatedEvent) {

        }

        @Override
        public void matchCancelled(MatchCancelledEvent matchCancelledEvent) {

        }

        @Override
        public void beforeMatchFired(BeforeMatchFiredEvent beforeMatchFiredEvent) {

        }

        @Override
        public void afterMatchFired(AfterMatchFiredEvent event) {
            String ruleName = event.getMatch().getRule().getName();
            System.out.println(ruleName);
        }

        @Override
        public void agendaGroupPopped(AgendaGroupPoppedEvent agendaGroupPoppedEvent) {

        }

        @Override
        public void agendaGroupPushed(AgendaGroupPushedEvent agendaGroupPushedEvent) {

        }

        @Override
        public void beforeRuleFlowGroupActivated(RuleFlowGroupActivatedEvent ruleFlowGroupActivatedEvent) {

        }

        @Override
        public void afterRuleFlowGroupActivated(RuleFlowGroupActivatedEvent ruleFlowGroupActivatedEvent) {

        }

        @Override
        public void beforeRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent ruleFlowGroupDeactivatedEvent) {

        }

        @Override
        public void afterRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent ruleFlowGroupDeactivatedEvent) {

        }
    }
}
