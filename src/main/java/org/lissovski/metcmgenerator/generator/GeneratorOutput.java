package org.lissovski.metcmgenerator.generator;

import java.util.List;
import java.util.Vector;

/**
 * @author Sergei Lissovski <sergei.lissovski@gmail.com>
 */
public class GeneratorOutput {
    private GeneratorInput input;
    private List<Floor> floors = new Vector<Floor>();
    
    public GeneratorOutput(GeneratorInput input, List<Floor> floors) {
        super();
        
        this.input = input;
        this.floors = floors;
    }

    public GeneratorInput getInput() {
        return input;
    }

    public List<Floor> getFloors() {
        return floors;
    }    
}
