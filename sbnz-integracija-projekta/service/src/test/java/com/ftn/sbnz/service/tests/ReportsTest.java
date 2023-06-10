//package com.ftn.sbnz.service.tests;
//
//import com.ftn.sbnz.model.models.Deliverer;
//import com.ftn.sbnz.model.models.Report;
//import com.ftn.sbnz.model.models.ReportStatus;
//import org.junit.Test;
//import org.kie.api.KieServices;
//import org.kie.api.runtime.KieContainer;
//import org.kie.api.runtime.KieSession;
//
//import java.time.LocalDateTime;
//import java.time.ZoneId;
//import java.util.Date;
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//public class ReportsTest {
//
//    @Test
//    public void testDelivererBlock() {
//        Deliverer deliverer = new Deliverer();
//        deliverer.setId(UUID.randomUUID());
//        addReports(deliverer, 3, 0);
//
//        KieSession kieSession = initKieSession();
//        kieSession.insert(deliverer);
//        for (Report report : deliverer.getReports()) {
//            kieSession.insert(report);
//        }
//        kieSession.fireAllRules();
//        kieSession.dispose();
//
//        assertTrue(deliverer.isBlocked());
//    }
//
//    @Test
//    public void testDelivererUnblock() {
//        Deliverer deliverer = new Deliverer();
//        deliverer.setBlocked(true);
//        deliverer.setId(UUID.randomUUID());
//        addReports(deliverer, 2, 10);
//
//        KieSession kieSession = initKieSession();
//        kieSession.insert(deliverer);
//        for (Report report : deliverer.getReports()) {
//            kieSession.insert(report);
//        }
//        kieSession.fireAllRules();
//        kieSession.dispose();
//
//        assertFalse(deliverer.isBlocked());
//    }
//
//    private void addReports(Deliverer deliverer, int validReportsNumber, int invalidReportsNumber) {
//        for (int i = 0; i < validReportsNumber; i++) {
//            Report report = new Report();
//            report.setDeliverer(deliverer);
//            report.setStatus(ReportStatus.ACCEPTED);
//            report.setCreatedOn(Date.from(LocalDateTime.now().minusDays(29).atZone(ZoneId.systemDefault()).toInstant()));
//            deliverer.getReports().add(report);
//        }
//        for (int i = 0; i < invalidReportsNumber; i++) {
//            Report report = new Report();
//            report.setDeliverer(deliverer);
//            report.setStatus(ReportStatus.ACCEPTED);
//            report.setCreatedOn(Date.from(LocalDateTime.now().minusMonths(2).atZone(ZoneId.systemDefault()).toInstant()));
//            deliverer.getReports().add(report);
//        }
//    }
//
//    private KieSession initKieSession() {
//        KieServices ks = KieServices.Factory.get();
//        KieContainer kContainer = ks.getKieClasspathContainer();
//        KieSession kieSession = kContainer.newKieSession("reportsKieSession");
//        return kieSession;
//    }
//}
