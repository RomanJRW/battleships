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
public abstract class UserInterface {
    protected Input input;
    protected Output output;
    
    public abstract Input getInput();
    public abstract Output getOutput();
    
}
