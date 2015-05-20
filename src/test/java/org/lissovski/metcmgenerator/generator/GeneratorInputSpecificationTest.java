package org.lissovski.metcmgenerator.generator;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lissovski.metcmgenerator.ui.RootShellValues;

public class GeneratorInputSpecificationTest {
    @Test
    public void testWhenValuesAreNotProvided() {
        RootShellValues input = new RootShellValues();
        
        List<String> errors = GeneratorInputSpecification.validate(input);
        
        // because value for "floorsCount" is provided by default
        assertEquals(6, errors.size());
    }

}
