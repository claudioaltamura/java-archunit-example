package de.claudioaltamura.java.archunit;

// @SpringBootTest

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

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
  public static final ArchRule PACKAGE_STRUCTURE_CONFIGGURATION =
      classes()
          .that()
          .areAnnotatedWith(Configuration.class)
          .should()
          .resideInAPackage("..configuration..")
          .because("that's the right place");
}
