/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.windels.battleships.io.impl;

import com.windels.battleships.io.Input;
import com.windels.battleships.io.Output;

/**
 *
 * @author joshw
 */
public abstract class Interface {
    private Input input;
    private Output output;
    
    public Interface(Input anInput, Output anOutput)  {
        input = anInput;
        output = anOutput;
    }
    
    public abstract void getInput();
    public abstract void getOutput();
    
}
