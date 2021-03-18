import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.*;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Matteo
 */
public class CalculatorFrame extends JFrame{
    DecimalFormat df = new DecimalFormat("#.00"); // Decimal format to set amount of decimals in answers
    private JTextArea display; //textArea 
    private JScrollPane scroll; // scrollPane for scroll bar

    private String currentNum = ""; // string to hold current number being entered 
    private String previousOp = ""; // string to hold previous operator being entered
    private String textDisplay = ""; // string to be displayed in textArea
    
    private double value1; // double to hold first value of equation
    private double value2;  // double to hold second value of equation
    private double answerValue; // double to hold answer from equation
    
   
    private boolean firstOpFlag = false; // boolean to measure if first operator of equation
    private boolean equalFlag = false; // boolean to measure if equal has been called
    private boolean opTwice = false; // boolean to measure if operators have been entered twice in same line
    private boolean decTwice = false; // boolean to measure if decimal have been entered twice in same line

    private static final int FRAME_WIDTH = 300; // int to set width of frame
    private static final int FRAME_HEIGHT = 250; // int to set height of frame
    
    public CalculatorFrame(){ // CalculatorFrame constructor
        setResizable(false); // make window not resizable
        createClearPanel(); // add ClearPanel in CalculatorFrame constructor
        createTextArea(); // add TextArea in CalculatorFrame constructor
        createButtonPanel(); // add ButtonPanel constructor in CalculatorFrame constructor
    }
    
    public void createClearPanel(){
        JPanel clearPanel = new JPanel(); // declare and initialize JPanel called clearPanel
        
        clearPanel.setLayout(new GridLayout(1,2)); // set Layout of clearPanel to a grid layout 1 row 2 columns
        clearPanel.add(makeClearLastButton("Clear Last")); // add "Clear Last" button to clear panel using makeClearLastButton method
        clearPanel.add(makeClearAllButton("Clear All"));// add "Clear All" button to clear panel using makeClearAllButton method
        
        setSize(FRAME_WIDTH, FRAME_HEIGHT); // set size method to set size of window
        
        add(clearPanel, BorderLayout.NORTH); // add clear panel to North of JFrame
    }
    
    public void createTextArea(){ //createTextArea method
        display = new JTextArea(); // initialize display variable to JTextArea
        scroll = new JScrollPane(display); // initialize scroll variable 
        
        display.setEditable(false); // display textArea is not editable
        display.setVisible(true); // display textArea is visible
        display.setLineWrap(true); // display text linewrap is set to true
        
        display.setText(""); // set text in display textArea to ""
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // set scroll bar policy to always vertical 
        
        add(scroll, BorderLayout.CENTER); // add scroll pane to Center of JFrame
    }
    
    public void createButtonPanel(){ //createButtonPanel method
        JPanel buttonPanel = new JPanel(); // declare and initialize JPanel named buttonPanel
        buttonPanel.setLayout(new GridLayout(4,4)); // set layout of button panel to grid layout with 4 rows by 4 columns
        
        buttonPanel.add(makeDigitButton("7")); // use makeDigitButton method to add button to buttonPanel
        buttonPanel.add(makeDigitButton("8")); // use makeDigitButton method to add button to buttonPanel
        buttonPanel.add(makeDigitButton("9")); // use makeDigitButton method to add button to buttonPanel
        buttonPanel.add(makeOperatorButton("/"));// use makeOperatorButton method to add button to buttonPanel
        buttonPanel.add(makeDigitButton("4")); // use makeDigitButton method to add button to buttonPanel
        buttonPanel.add(makeDigitButton("5")); // use makeDigitButton method to add button to buttonPanel
        buttonPanel.add(makeDigitButton("6")); // use makeDigitButton method to add button to buttonPanel
        buttonPanel.add(makeOperatorButton("*"));// use makeOperatorButton method to add button to buttonPanel
        buttonPanel.add(makeDigitButton("1")); // use makeDigitButton method to add button to buttonPanel
        buttonPanel.add(makeDigitButton("2")); // use makeDigitButton method to add button to buttonPanel
        buttonPanel.add(makeDigitButton("3")); // use makeDigitButton method to add button to buttonPanel
        buttonPanel.add(makeOperatorButton("-"));// use makeOperatorButton method to add button to buttonPanel
        buttonPanel.add(makeDigitButton("0")); // use makeDigitButton method to add button to buttonPanel
        buttonPanel.add(makeDigitButton(".")); // use makeDigitButton method to add button to buttonPanel
        buttonPanel.add(makeOperatorButton("="));// use makeOperatorButton method to add button to buttonPanel
        buttonPanel.add(makeOperatorButton("+"));// use makeOperatorButton method to add button to buttonPanel
        
        add(buttonPanel, BorderLayout.SOUTH); // add buttonPanel to South of JFrame
        
    }
    
    class DigitButtonListener implements ActionListener{ // DigitButtonListener class that implements ActionListener 
        private String digit; // String to hold digit
        
        public DigitButtonListener(String d){ // DigitButtonListener constructor with String as a parameter
            digit = d; // set digit variable to d argument
        }
        
        public void actionPerformed(ActionEvent event){ // actionPerformed method 
            opTwice = false;
            
            if(equalFlag){ // if equalFlag boolean is true carry out this statement
                textDisplay = textDisplay + "\n"; // add new line to current textDisplay
                currentNum= ""; // set curretNum to ""
                equalFlag = false; // set equalFlag to false
            }

            textDisplay = textDisplay + digit; // set textDisplay to textDisplay + digit that was pressed
            currentNum = currentNum + digit; // set currentNum to currentNum + digit that was pressed 
            
            if(!decTwice){ // if decTwice boolean is fault
                 if(digit.equals(".")){ // if digit is a decimal "."
                    decTwice = true; // set decTwice to true
                }
            }else if(decTwice){ // else if decTwice boolean is true
                    if(digit.equals(".")){ // if digit is a decimal "."
                        textDisplay = textDisplay +"\n" +"Error: Too Many Decimals"+"\n"; // set text display to the current display + Decimal Error message 
                        currentNum = ""; // set currentNum to ""
                        decTwice = false; // set decTwice to false
                    }
            }
            display.setText(textDisplay); // set display textArea to textDisplay string
        }
        
    }
    
    public JButton makeDigitButton(String digit){ // makeDigitButton method with String parameter
        JButton button = new JButton(digit); // declare and initialize JButton with digit parameter as argument
        
        ActionListener listener = new DigitButtonListener(digit); // declare ActionListener and initialize DigitButtonListener object with digit parameter as argument
        button.addActionListener(listener); // add ActionListener listener to button 
        
        return button; // return the button JButton
    }
    
    class OperatorButtonListener implements ActionListener{ // OperatorButtonListener class that implements ActionListener 
        private String operator; // string to hold operator
        
        public OperatorButtonListener(String op){ // OperatorButtonListener constructor with String op as parameter
            operator = op; // set operator to op argument
        }
        
        @Override
        public void actionPerformed(ActionEvent e) { // actionPerformed method 
            decTwice = false; // set decTwice to false
            
            if(previousOp.equals("/")&&currentNum.equals("0")){ // if previous operator is divided and currentNum is 0 carry out this if statement
                textDisplay = textDisplay + "\n"+"Error: Undefined, Cannot Divide by 0"+"\n"; //set textDisplay to current textDisplay
                currentNum = ""; // set currentNum to ""
                previousOp = ""; // set previousOp to ""
                
                display.setText(textDisplay); // set display textArea to textDisplay
            }
            
            if(!firstOpFlag){ // if firstOpFlag is false carry out this if statement
                firstOpFlag = true; // set firstOpFlag to true
                value1 = Double.parseDouble(currentNum); // set value1 to the parseDouble of currentNum
                previousOp = operator; // set previousOp to operator
                
                textDisplay = textDisplay+operator+"\n"; // set textDisplay to textDisplay + operator than add next line
                currentNum = ""; // set currentNum to ""
                equalFlag = false; // set equalFlag to false
            }else{ // if firstOpFlag is true carry out these statements
                if (operator.equals("=")){ // if operator is "=" 
                    firstOpFlag = false; // set firstOpFlag to false
                    value2 = Double.parseDouble(currentNum); // set value2 to the parseDouble of currentNum
                    
                    textDisplay = textDisplay+operator+"\n"; // set textDisplay to textDisplay + operator than add next line
                    
                    answerValue = calculate(value1, value2, previousOp); // set answerValue using calculate method with value1, value2 and previousOp as arguments
                    textDisplay = textDisplay + df.format(answerValue); // set textDisplay to textDisplay + answerValue using 2 decimal format method on answerValue
                    currentNum = Double.toString(answerValue); // set currentNum to string version of answerValue
                    previousOp = operator; // set previousOp to operator
                    equalFlag = true; //set equalFlag boolean to true
                    opTwice = true; // set opTwice boolean to true
                }else { // else carry out these statements
                    value2 = Double.parseDouble(currentNum); // set value2 to the parseDouble of currentNum
                    
                    textDisplay = textDisplay + "=\n"; // set textDisplay to textDisplay + equal sign and add next line
                    answerValue = calculate(value1, value2, previousOp); // set answerValue using calculate method using value1, value2 and previousOp as arugments
                    textDisplay = textDisplay + df.format(answerValue) + operator + "\n"; // set textDisplay to textDisplay + answerValue using 2 decimal format method + add next line
                    
                    value1 = answerValue; // set value1 to answerValue
                    
                    opTwice = true; // set opTwice to true
                    previousOp = operator; // set previousOp to operator
                    currentNum = ""; // set currentNum to "" 
                    }  
            }

            
            display.setText(textDisplay); // set display TextArea to textDisplay string
        }
    }
    
    public JButton makeOperatorButton(String operator){ // makeOperatorButton method with String operator as parameter
        JButton button = new JButton(operator); // declare and initialize JButton with operator as argument
        
        ActionListener listener = new OperatorButtonListener(operator); // declare ActionListener andd initialize OperatorButtonListener with operator as argument
        button.addActionListener(listener); // addActionListener listener to button
        
        return button; // return button 
    }
    
    public double calculate(double value1, double value2, String op){ // calculate method with value1, value2 and op as argument
        if(op.equals("+")){ // if op is + carry out
            return value1 + value2; // return value1 + value2
        }else if(op.equals("-")){ // else if op is - carry out
            return value1 - value2; // return value1 - value2
        }else if(op.equals("*")){ // else if op is * carry out
            return value1 * value2; // return value1 * value2
        }else if(op.equals("/")){ // else if op is / carry out
            return value1 / value2; // return value1 / value2
        }else{ // else carry out
            return value2; // return value2 
        }
    }
    
    public JButton makeClearAllButton(String cl){ // makeClearAllButton method with cl as parameter
        JButton button = new JButton(cl); // declare and initialize new JButton with cl as argument
        
        ActionListener listener = new ClearAllButtonListener(cl); // declare ActionListener and initialize ClearAllButtonListener with cl as argument
        button.addActionListener(listener); // add ActionListener listener to button JButton
        
        return button; // return button JButton
    }
    
    class ClearAllButtonListener implements ActionListener{ // ClearAllButtonListener class that implements ActionListener
        private String clear; // String variable called clear
        
        public ClearAllButtonListener(String c){ // ClearAllButtonListener constructor with String c as parameter
            clear = c; // set clear variable to c argument
        }
        
        public void actionPerformed(ActionEvent event){ // actionPerformed method
            currentNum = ""; // set currentNum to ""
            textDisplay = ""; // set textDisplay to ""
            decTwice = false; // set decTwice boolean to false
            opTwice = false; // set opTwice boolean to false
            display.setText(textDisplay); // set display textArea to textDisplay String
        }
    }
    
    public JButton makeClearLastButton(String cl){ // makeClearLastButton method with String cl as parameter
        JButton button = new JButton(cl); // declare and intialize JButton with cl as argument
        
        ActionListener listener = new ClearLastButtonListener(cl); // declare ActionListener and initialize ClearLastButtonListener with cl as argument
        button.addActionListener(listener); // add ActionListener listener to button 
        
        return button; // return JButton button
    }
    
    class ClearLastButtonListener implements ActionListener{ // class ClearLastButtonListener implements ActionListener
        private String clear; // clear String
        
        public ClearLastButtonListener(String c){ // ClearLastButtonListener constructor with String c as parameter
            clear = c; // set clear variable to c
        }
        
        public void actionPerformed(ActionEvent event){ // actionPerformed method
            if(equalFlag){ // if equalFlag is true carry out this statement
                textDisplay = textDisplay; // set textDisplay to textDisplay, do not backspace equal sign
            }else{ // if equalFlag is false
                textDisplay = textDisplay.substring(0, textDisplay.length()-1); // set textDisplay to textDisplay minus 1 off its length (backspace 1 length)
                currentNum = currentNum.substring(0, currentNum.length()-1); // set currentNum to currentNum minus 1 off its length (backspace 1 length)
            }
            

            display.setText(textDisplay); // set display textArea to textDisplay String
        }
    }
}
