package com.capgemini.librarymanagementsystemjdbc.testrunner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="com/capgemini/librarymanagementsystemjdbc/features",
glue= {"./src/test/java/com/capgemini/librarymanagementsystemjdbc/stepdefinitions"},
dryRun=false,
tags = {"~@ignore"},
plugin= {"pretty","html:target/cucumber"},
monochrome=true)
public class TestRunner {

}
