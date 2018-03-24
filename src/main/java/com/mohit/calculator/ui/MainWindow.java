package com.mohit.calculator.ui;

import com.mohit.calculator.runtime.Context;

import java.util.List;
import java.util.ArrayList;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MainWindow extends JFrame {
    private static final long serialVersionUID = -3453841920303779643L;

	private Context context;

    private JTextField inputBox;
    private JTextArea inputLog;
    private JTextField outputArea;
    private JSplitPane splitter;
    private JSplitPane panel;

    public MainWindow() {
        super();

        context = new Context();
        inputLog = new JTextArea("History: \n\n");
        inputBox = new JTextField();
        outputArea = new JTextField();
        splitter = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

        panel = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        panel.add(inputBox);
        panel.add(inputLog);
    
        splitter.add(panel);
        splitter.add(outputArea);

        add(splitter);

        Font font = new Font("Inconsolata", 0, 14);
        inputLog.setFont(font);
        outputArea.setFont(font);
        inputBox.setFont(font);
        
        inputLog.setEditable(false);
        outputArea.setEditable(false);
        
        outputArea.setForeground(Color.getColor("green"));
        outputArea.setMaximumSize(new Dimension(-1, 100));


        inputBox.addKeyListener(new KeyListener(){
        
            @Override
            public void keyTyped(KeyEvent e) {
                
            }
        
            @Override
            public void keyReleased(KeyEvent e) {
                
            }
        
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (!inputBox.getText().equals("clear")) {
                        inputLog.append(inputBox.getText()+"\n");
                        try {
                            outputArea.setText("Result = " + 
                                context.processExpression(inputBox.getText())+"\n");
                        } catch (Exception exception) {
                            outputArea.setText("ERROR: \n" + exception.getMessage());
                        }
                    } else {
                        inputLog.setText("");
                        outputArea.setText("");
                        context.clearStack();
                    }
                    inputBox.setText("");
                }
            }
        });


        setTitle("A next generation 'Calculator'");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}