package de.claudioaltamura.java.archunit;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.Architectures;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.core.domain.properties.CanBeAnnotated.Predicates.annotatedWith;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(
        packagesOf = JavaArchunitApplication.class,
        importOptions = ImportOption.DoNotIncludeTests.class)
class JavaArchUnitApplicationArchitectureTests {

    @ArchTest
    public static final ArchRule CONTROLLER_NAMING =
            classes()
                    .that()
                    .areAnnotatedWith(RestController.class)
                    .or()
                    .haveSimpleNameEndingWith("Controller")
                    .should()
                    .beAnnotatedWith(RestController.class)
                    .andShould()
                    .haveSimpleNameEndingWith("Controller")
                    .because("easy to find");

    @ArchTest
    public static final ArchRule PACKAGE_STRUCTURE_CONFIGURATION =
            classes()
                    .that()
                    .areAnnotatedWith(Configuration.class)
                    .should()
                    .resideInAPackage("..configuration..")
                    .because("that's the right place");


    @ArchTest
    public static final ArchRule LAYER_ACCESS = Architectures.layeredArchitecture()
            .consideringOnlyDependenciesInLayers()
            .layer("Controller").definedBy(annotatedWith(RestController.class))
            .whereLayer("Controller").mayNotBeAccessedByAnyLayer();


}
