@echo off

set ML_JAR=L:\workspace\_maven_repository_\fr\mla\machine-learning\1.0-SNAPSHOT\machine-learning-1.0-SNAPSHOT.jar
set ML_MAIN_CLASS=fr.mla.machinelearning.App

set INPUT_DATA_SET=L:\workspace\machine-learning\poker.txt

%JAVA_HOME%\bin\java -cp %ML_JAR% %ML_MAIN_CLASS% %INPUT_DATA_SET%
