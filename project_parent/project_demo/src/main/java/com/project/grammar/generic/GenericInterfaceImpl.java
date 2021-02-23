package com.demo.grammar.generic;

public class GenericInterfaceImpl implements GenericInterface<String> {
    @Override
    public String next() {
        return "string";
    }
}
