package com.cc.customer.loan.service;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTag;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.Architectures;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@ArchTag("rules_for_my_app")
@AnalyzeClasses(packages = "com.cc.customer.loan", importOptions = {ImportOption.DoNotIncludeTests.class})
public class ArchitectureRulesTest {

    static final Architectures.LayeredArchitecture portsAndAdaptersArchitecture =
            Architectures
                    .layeredArchitecture()
                    .layer("entity layer")
                    .definedBy("..entities..")
                    .layer("usecases layer")
                    .definedBy("..usecases..")
                    .layer("interfaceadapters layer")
                    .definedBy("..interfaceadapters..")
                    .layer("drivers layer")
                    .definedBy("..drivers..");

    @ArchTest
    static final ArchRule applicationLayerRule =
            portsAndAdaptersArchitecture
                    .whereLayer("entity layer")
                    .mayOnlyBeAccessedByLayers("usecases layer", "interfaceadapters layer", "drivers layer")
                    .whereLayer("usecases layer")
                    .mayOnlyBeAccessedByLayers("interfaceadapters layer", "drivers layer")
                    .whereLayer("interfaceadapters layer")
                    .mayOnlyBeAccessedByLayers("drivers layer")
                    .whereLayer("drivers layer")
                    .mayNotBeAccessedByAnyLayer();

    @ArchTest
    static final ArchRule entity_dependency_rule =
            classes()
                    .that()
                    .resideInAPackage("..entities..")
                    .should()
                    .onlyAccessClassesThat()
                    .resideInAnyPackage("..entities..", "java..", "org.springframework.util..");

    @ArchTest
    static final ArchRule usecases_dependency_rule =
            classes()
                    .that()
                    .resideInAPackage("..usecases..")
                    .should()
                    .onlyAccessClassesThat()
                    .resideInAnyPackage("..entities..", "..usecases..", "java..", "org.springframework.util..");

    @ArchTest
    static final ArchRule annotations_dependency_rule =
            classes()
                    .that()
                    .resideInAnyPackage("..usecases..", "..entities..")
                    .should()
                    .notBeAnnotatedWith(Component.class)
                    .andShould()
                    .notBeAnnotatedWith(Service.class);

}
