

import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Matteo
 */
public class CalculatorViewer {
     public static void main(String[] args){
        JFrame frame = new CalculatorFrame();  // declare JFrame and initialize using CalculatorFrame constructor
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // set close operation of frame to exit on close
        frame.setTitle("Calculator"); // set title of frame to Calculator
        frame.setVisible(true); // set visibility of frame to true
    }
}