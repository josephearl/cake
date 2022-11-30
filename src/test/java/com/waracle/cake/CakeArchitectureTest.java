package com.waracle.cake;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.GeneralCodingRules;

@AnalyzeClasses(
    packages = CakeArchitectureTest.PACKAGE,
    importOptions = ImportOption.DoNotIncludeTests.class)
class CakeArchitectureTest {
  static final String PACKAGE = "com.waracle.cake";
  static final String ADAPTER_PACKAGE = PACKAGE + ".adapter..";

  @ArchTest
  static final ArchRule noClassesShouldUseFieldInjection =
      GeneralCodingRules.NO_CLASSES_SHOULD_USE_FIELD_INJECTION;

  @ArchTest
  static final ArchRule noCoreClassesShouldDependOnAdapters =
      noClasses()
          .that()
          .resideOutsideOfPackage(ADAPTER_PACKAGE)
          .should()
          .dependOnClassesThat()
          .resideInAPackage(ADAPTER_PACKAGE);
}
