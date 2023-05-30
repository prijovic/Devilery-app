package com.ftn.sbnz.service.services.kie;

import lombok.RequiredArgsConstructor;
import org.drools.decisiontable.ExternalSpreadsheetCompiler;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class GetDiscountKieSession {

    public KieSession execute() {
        InputStream template = KieSessionCreatorHelper.class.getResourceAsStream("/rules/discount/discount.drt");
        InputStream data = KieSessionCreatorHelper.class.getResourceAsStream("/rules/discount/discount.xls");

        ExternalSpreadsheetCompiler converter = new ExternalSpreadsheetCompiler();
        String drl = converter.compile(data, template, 3, 2);

        return KieSessionCreatorHelper.createKieSessionFromDRL(drl);
    }
}
