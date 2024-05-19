package com.ftn.sbnz.config;

import org.drools.template.DataProvider;
import org.drools.template.DataProviderCompiler;
import org.drools.template.objects.ArrayDataProvider;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.ReleaseId;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.io.StringReader;

@Configuration
public class DroolsConfig {
    @Autowired
    private KieContainer kieContainer;

    private static final Logger logger = LoggerFactory.getLogger(DroolsConfig.class);


    @Bean
    public KieSession bwKsession() {
        return kieContainer.newKieSession("bwKsession");
    }

    @Bean
    public KieSession cepKsession() {
        return kieContainer.newKieSession("cepKsession");
    }
    @Bean
    public KieBase forwardKieBase() {
        InputStream templateStream = this.getClass().getResourceAsStream("/forward/lifestyle-habits-template.drt");
        DataProvider dataProvider = new ArrayDataProvider(new String[][]{
                {"UNHEALTHY_DIET", "HYDRATION", "12", "89"},
                {"SMOKING", "ANTI_AGE", "13", "88"},
                {"ALCOHOL_CONSUMPTION", "IRRITATION_CALMING", "14", "87"},
                {"SUN_EXPOSURE", "SUN_PROTECTION", "15", "87"},
                {"LACK_OF_SLEEP", "SKIN_TEXTURE_IMPROVEMENT", "16", "86"},
                {"STRESS", "IRRITATION_CALMING", "17", "85"},
                {"INTENSE_PHYSICAL_ACTIVITY", "MOISTURIZING", "18", "84"},
                {"HORMONAL_CHANGES", "SKIN_TONE_EVENING", "19", "83"}
        });
        DataProviderCompiler converter = new DataProviderCompiler();
        String drl = converter.compile(dataProvider, templateStream);
        // Kreiraj KieBase sa generisanim DRL-om
        KieHelper kieHelper = new KieHelper();
        kieHelper.addContent(drl, ResourceType.DRL);
        KieServices kieServices = KieServices.Factory.get();
        kieHelper.addResource(kieServices.getResources().newClassPathResource("forward/forward.drl"), ResourceType.DRL);

        KieBase kieBase = kieHelper.build();
        logger.info("KieBase for forward rules successfully created.");
        return kieBase;
    }
    @Bean
    public KieSession forwardKsession() {
        return forwardKieBase().newKieSession();
    }



}
