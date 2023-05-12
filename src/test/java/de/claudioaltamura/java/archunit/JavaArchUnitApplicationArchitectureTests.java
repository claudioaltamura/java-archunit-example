package de.claudioaltamura.java.archunit;

//@SpringBootTest

import com.tngtech.archunit.core.importer.ImportOption;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packagesOf = JavaArchunitApplication.class, importOptions = ImportOption.DoNotIncludeTests.class)
class JavaArchUnitApplicationArchitectureTests {

	@ArchTest()
	public static final ArchRule CONTROLLER_NAMING = classes()
		.that().areAnnotatedWith(RestController.class)
		.or().haveSimpleNameEndingWith("Controller")
		.should().beAnnotatedWith(RestController.class)
		.andShould().haveSimpleNameEndingWith("Controller")
		.because("easy to find");

}
