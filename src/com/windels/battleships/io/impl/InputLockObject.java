/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.windels.battleships.io.impl;

/**
 *
 * @author joshw
 */
public class InputLockObject {
    private Command userCommand;
    
    public InputLockObject()    {
        userCommand = null;
    }
    
    public Command getCommand()  {
        return userCommand;
    }
    
}
