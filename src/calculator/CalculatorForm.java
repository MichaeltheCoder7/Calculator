package calculator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorForm {
    private JTabbedPane tabbedPane1;
    private JTabbedPane tabbedPane2;
    private JTextField textField1;
    private JButton a7Button;
    private JButton a4Button;
    private JButton a1Button;
    private JButton cButton;
    private JButton a0Button;
    private JButton a2Button;
    private JButton a5Button;
    private JButton a8Button;
    private JButton a9Button;
    private JButton a6Button;
    private JButton a3Button;
    private JButton button14;
    private JButton button15;
    private JButton button16;
    private JButton button17;
    private JButton button18;
    private JButton button19;
    private JTextField textField2;
    private JButton sinButton;
    private JButton button2;
    private JButton a7Button1;
    private JButton a4Button1;
    private JButton a1Button1;
    private JButton a0Button1;
    private JButton cosButton;
    private JButton lnButton;
    private JButton button9;
    private JButton a8Button1;
    private JButton a5Button1;
    private JButton a2Button1;
    private JButton button13;
    private JButton tanButton;
    private JButton sqrtButton;
    private JButton facButton;
    private JButton a9Button1;
    private JButton a6Button1;
    private JButton a3Button1;
    private JButton modButton;
    private JButton cButton1;
    private JButton backspaceButton;
    private JButton button29;
    private JButton button30;
    private JButton button31;
    private JButton button32;
    private JButton button33;
    private JRadioButton exprRadioButton;
    private JRadioButton simpleRadioButton;
    private JRadioButton exprRadioButton1;
    private JRadioButton simpleRadioButton1;
    private JPanel panel;
    private JPanel simple;
    private JPanel expr;
    private JButton logButton;
    private Calculator simpleCalc = new Calculator();
    private ExprCalculator exprCalc = new ExprCalculator();
    public static JFrame frame;

    public CalculatorForm() {
        // buttons for simple calculator
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simpleCalc.acceptInput( ((JButton)e.getSource()).getText() );
                textField1.setText(simpleCalc.getDisplayString());
            }
        };

        a7Button.addActionListener(listener);
        a4Button.addActionListener(listener);
        a1Button.addActionListener(listener);
        cButton.addActionListener(listener);
        a0Button.addActionListener(listener);
        a2Button.addActionListener(listener);
        a5Button.addActionListener(listener);
        a8Button.addActionListener(listener);
        a9Button.addActionListener(listener);
        a6Button.addActionListener(listener);
        a3Button.addActionListener(listener);
        button14.addActionListener(listener);
        button15.addActionListener(listener);
        button16.addActionListener(listener);
        button17.addActionListener(listener);
        button18.addActionListener(listener);
        button19.addActionListener(listener);

        // radio button for simple calculator to switch to expr calculator
        exprRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // close the windows and open another one
                frame = new JFrame("CalculatorForm");
                frame.setContentPane(new CalculatorForm().expr);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });

        // buttons for expr calculator
        ActionListener listener1 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exprCalc.acceptInput( ((JButton)e.getSource()).getText() );
                textField2.setText(exprCalc.getDisplayString());
            }
        };

        sinButton.addActionListener(listener1);
        logButton.addActionListener(listener1);
        button2.addActionListener(listener1);
        a7Button1.addActionListener(listener1);
        a4Button1.addActionListener(listener1);
        a1Button1.addActionListener(listener1);
        a0Button1.addActionListener(listener1);
        cosButton.addActionListener(listener1);
        lnButton.addActionListener(listener1);
        button9.addActionListener(listener1);
        a8Button1.addActionListener(listener1);
        a5Button1.addActionListener(listener1);
        a2Button1.addActionListener(listener1);
        button13.addActionListener(listener1);
        tanButton.addActionListener(listener1);
        sqrtButton.addActionListener(listener1);
        facButton.addActionListener(listener1);
        a9Button1.addActionListener(listener1);
        a6Button1.addActionListener(listener1);
        a3Button1.addActionListener(listener1);
        modButton.addActionListener(listener1);
        cButton1.addActionListener(listener1);
        backspaceButton.addActionListener(listener1);
        button29.addActionListener(listener1);
        button30.addActionListener(listener1);
        button31.addActionListener(listener1);
        button32.addActionListener(listener1);
        button33.addActionListener(listener1);

        // radio button for expr calculator to switch to simple calculator
        simpleRadioButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // close the window and open another one
                frame = new JFrame("CalculatorForm");
                frame.setContentPane(new CalculatorForm().simple);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        // open the default simple calculator first
        frame = new JFrame("CalculatorForm");
        frame.setContentPane(new CalculatorForm().simple);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
