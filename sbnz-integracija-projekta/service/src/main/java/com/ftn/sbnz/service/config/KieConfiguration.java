package com.ftn.sbnz.service.config;

import org.drools.core.BeliefSystemType;
import org.drools.core.SessionConfiguration;
import org.drools.core.impl.KnowledgeBaseFactory;
import org.drools.decisiontable.ExternalSpreadsheetCompiler;
import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.event.rule.*;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.utils.KieHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.util.List;

@Configuration
public class KieConfiguration {
    private static void getResourceFolderFiles(KieHelper kieHelper) throws IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader().getClass().getClassLoader();
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(classLoader);
        Resource[] resources = resolver.getResources("/rules/**/*.drl");
        for (Resource resource : resources) {
            kieHelper.addResource(ResourceFactory.newFileResource(resource.getFile()), ResourceType.DRL);
        }
    }

    private static String loadTemplate(String templatePath, String dataPath) throws IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader().getClass().getClassLoader();
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(classLoader);

        Resource template = resolver.getResource(templatePath);
        Resource data = resolver.getResource(dataPath);

        ExternalSpreadsheetCompiler converter = new ExternalSpreadsheetCompiler();
        return converter.compile(data.getInputStream(), template.getInputStream(), 3, 2);
    }

    @Bean
    public KieSession createKieSession() throws IOException {
        KieBaseConfiguration config = KnowledgeBaseFactory.newKnowledgeBaseConfiguration();
        config.setOption(EventProcessingOption.STREAM);
        KieHelper kieHelper = new KieHelper();
        getResourceFolderFiles(kieHelper);

        kieHelper.addContent(loadTemplate("/rules/delivery/busy_deliverer.drt", "/rules/delivery/busy_deliverer_rules.xls"), ResourceType.DRL);
        kieHelper.addContent(loadTemplate("/rules/discount/discount.drt", "/rules/discount/discount.xls"), ResourceType.DRL);
        kieHelper.addContent(loadTemplate("/rules/recommendation/recommendation.drt", "/rules/recommendation/recommendation.xls"), ResourceType.DRL);
        kieHelper.addContent(loadTemplate("/rules/recommendation/specialized-recommendation.drt", "/rules/recommendation/recommendation.xls"), ResourceType.DRL);

        Results results = kieHelper.verify();

        if (results.hasMessages(Message.Level.WARNING, Message.Level.ERROR)) {
            List<Message> messages = results.getMessages(Message.Level.WARNING, Message.Level.ERROR);
            for (Message message : messages) {
                System.out.println("Error: " + message.getText());
            }
            throw new IllegalStateException("Compilation errors were found. Check the logs.");
        }
        KieBase kieBase = kieHelper.build(config);
        KieSessionConfiguration ksConf = KnowledgeBaseFactory.newKnowledgeSessionConfiguration();
        ((SessionConfiguration) ksConf).setBeliefSystemType(BeliefSystemType.DEFEASIBLE);

        KieSession kieSession = kieBase.newKieSession(ksConf, null);
        kieSession.addEventListener(new CustomAgendaEventListener());
        return kieSession;
    }

    public static class CustomAgendaEventListener implements AgendaEventListener {

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
