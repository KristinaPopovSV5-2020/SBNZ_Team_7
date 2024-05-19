package com.ftn.sbnz.config;

import org.drools.template.DataProvider;
import org.drools.template.DataProviderCompiler;
import org.drools.template.objects.ArrayDataProvider;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;

@Configuration
public class DroolsConfig {
    @Autowired
    private KieContainer kieContainer;

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
        InputStream templateStreamHabits = this.getClass().getResourceAsStream("/forward/lifestyle-habits-template.drt");
        DataProvider dataProviderHabits = new ArrayDataProvider(new String[][]{
                {"UNHEALTHY_DIET", "HYDRATION", "12", "89"},
                {"SMOKING", "ANTI_AGE", "13", "88"},
                {"ALCOHOL_CONSUMPTION", "IRRITATION_CALMING", "14", "87"},
                {"SUN_EXPOSURE", "SUN_PROTECTION", "15", "86"},
                {"LACK_OF_SLEEP", "SKIN_TEXTURE_IMPROVEMENT", "16", "85"},
                {"STRESS", "IRRITATION_CALMING", "17", "84"},
                {"INTENSE_PHYSICAL_ACTIVITY", "MOISTURIZING", "18", "83"},
                {"HORMONAL_CHANGES", "SKIN_TONE_EVENING", "19", "82"}
        });
        DataProviderCompiler converter = new DataProviderCompiler();
        String drlHabits = converter.compile(dataProviderHabits, templateStreamHabits);

        InputStream templateStreamProblems = this.getClass().getResourceAsStream("/forward/skin-problems-template.drt");
        DataProvider dataProviderProblems = new ArrayDataProvider(new String[][]{
                {"ACNE", "ACNE_REDUCING", "100", "1"},
                {"SPOTS", "DARK_SPOT_REMOVAL", "99", "2"},
                {"SCARS", "SKIN_TEXTURE_IMPROVEMENT", "98", "3"},
                {"ENLARGED_PORES", "DETOXIFICATION", "97", "4"},
                {"REDNESS", "IRRITATION_CALMING", "96", "5"},
                {"ITCHINESS", "IRRITATION_CALMING", "95", "6"},
                {"WRINKLES", "ANTI_AGE", "94", "7"},
                {"FRECKLES", "HYPERPIGMENTATION_REDUCTION", "93", "8"},
                {"BLACKHEADS", "CLEANSING", "92", "9"},
                {"LACK_OF_FIRMNESS", "HYDRATION", "91", "10"},
                {"ECZEMA", "MOISTURIZING", "90", "11"}
        });
        String drlProblems = converter.compile(dataProviderProblems, templateStreamProblems);

        KieHelper kieHelper = new KieHelper();
        kieHelper.addContent(drlHabits, ResourceType.DRL);
        kieHelper.addContent(drlProblems, ResourceType.DRL);

        KieServices kieServices = KieServices.Factory.get();
        kieHelper.addResource(kieServices.getResources().newClassPathResource("forward/forward.drl"), ResourceType.DRL);

        KieBase kieBase = kieHelper.build();
        return kieBase;
    }
    @Bean
    public KieSession forwardKsession() {
        return forwardKieBase().newKieSession();
    }



}
