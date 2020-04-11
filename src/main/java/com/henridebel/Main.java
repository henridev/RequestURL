package com.henridebel;

import javax.swing.*;
import java.awt.*;

public class Main {
    public RequestMaker requestmaker;

    public static void main(String... args){
        JFrame application = createGui();
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.setVisible(true);
    }

    private static JFrame createGui() {
        JTextField baseUrlInput = new JTextField();
        JTextField parameterNameInput = new JTextField();
        JTextField parameterValueInput = new JTextField();
        JButton addBaseUrlButton = new JButton("Add baseUrl");
        JButton addParamNameButton = new JButton("Add parameter Name");
        JButton addParamValueButton = new JButton("Add parameter Value");
        JButton  CreateRequestUrl = new JButton("Add parameter Value");
        JButton  SendRequestUrl = new JButton("Add parameter Value");
        JLabel baseUrlOutput = new JLabel();
        JLabel ParamNameOutput = new JLabel();
        JLabel ParamValueOutput = new JLabel();


        baseUrlInput.setPreferredSize(new Dimension(300,40));
        parameterNameInput.setPreferredSize(new Dimension(300,40));
        parameterValueInput.setPreferredSize(new Dimension(300,40));
        baseUrlOutput.setPreferredSize(new Dimension(300,40));
        ParamNameOutput.setPreferredSize(new Dimension(300,40));
        ParamValueOutput.setPreferredSize(new Dimension(300,40));


        addParamNameButton.addActionListener(event -> {
            ParamNameOutput.setText("BaseURL is: " +parameterNameInput.getText());
        });


        addParamValueButton.addActionListener(event -> {
            ParamValueOutput.setText("BaseURL is: " +parameterValueInput.getText());
        });

        addBaseUrlButton.addActionListener(event -> {
            baseUrlOutput.setText("BaseURL is: " +baseUrlInput.getText());
        });

        CreateRequestUrl.addActionListener(event -> {
            // requestmaker = new RequestMaker();
        });

        JFrame gui = new JFrame("URL request maker");
        gui.setSize(1000,1000);
        gui.setLayout(new GridLayout(3,3));

        gui.add(baseUrlInput);
        gui.add(parameterNameInput);
        gui.add(parameterValueInput);

        gui.add(addBaseUrlButton);
        gui.add(addParamNameButton);
        gui.add(addParamValueButton);

        gui.add(baseUrlOutput);
        gui.add(baseUrlOutput);
        gui.add(baseUrlOutput);

        gui.pack();
        gui.setLocationRelativeTo(null);
        return gui;
    }
}
