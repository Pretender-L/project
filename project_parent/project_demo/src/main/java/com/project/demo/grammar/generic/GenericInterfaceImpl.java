package com.project.demo.grammar.generic;

public class GenericInterfaceImpl implements GenericInterface<String> {
    @Override
    public String next() {
        return "string";
    }
}
