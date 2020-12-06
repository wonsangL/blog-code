package com.example.unittesting;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class SampleRule implements TestRule {
    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                System.err.println("rule before test");
                base.evaluate();
                System.err.println("rule after test");
            }
        };
    }
}
