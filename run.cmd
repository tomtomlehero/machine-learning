@echo off

set ML_JAR=L:\workspace\_maven_repository_\fr\mla\machine-learning\1.0-SNAPSHOT\machine-learning-1.0-SNAPSHOT.jar;L:\workspace\_maven_repository_\org\jfree\jfreechart\1.0.17\jfreechart-1.0.17.jar;L:\workspace\_maven_repository_\org\jfree\jcommon\1.0.21\jcommon-1.0.21.jar;L:\workspace\_maven_repository_\xml-apis\xml-apis\1.3.04\xml-apis-1.3.04.jar
::set ML_MAIN_CLASS=fr.mla.machinelearning.App
set ML_MAIN_CLASS=fr.mla.machinelearning.chart.ScatterPlot

set INPUT_DATA_SET=L:\workspace\machine-learning\poker.txt

%JAVA_HOME%\bin\java -cp %ML_JAR% %ML_MAIN_CLASS% %INPUT_DATA_SET%
