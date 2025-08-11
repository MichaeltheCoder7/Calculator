package calculator;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

    // Blue theme color constants
    private static final Color PRIMARY_BLUE = new Color(33, 150, 243);
    private static final Color DARK_BLUE = new Color(25, 118, 210);
    private static final Color LIGHT_BLUE = new Color(227, 242, 253);
    private static final Color NAVY_BLUE = new Color(13, 71, 161);
    private static final Color WHITE = Color.WHITE;
    private static final Color LIGHT_GRAY = new Color(245, 245, 245);
    private static final Color BUTTON_HOVER = new Color(100, 181, 246);

    public CalculatorForm() {
        setupLookAndFeel();
        createUI();
        setupEventListeners();
    }

    private void setupLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createUI() {
        // Main panel setup
        panel = new JPanel(new BorderLayout());
        panel.setBackground(LIGHT_BLUE);
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Create tabbed pane with custom styling
        tabbedPane1 = new JTabbedPane();
        styleTabPane(tabbedPane1);

        // Create simple calculator panel
        simple = createSimpleCalculatorPanel();
        tabbedPane1.addTab("Simple Calculator", simple);

        // Create expression calculator panel
        expr = createExpressionCalculatorPanel();
        tabbedPane1.addTab("Scientific Calculator", expr);

        panel.add(tabbedPane1, BorderLayout.CENTER);
    }

    private void styleTabPane(JTabbedPane tabbedPane) {
        tabbedPane.setBackground(PRIMARY_BLUE);
        tabbedPane.setForeground(WHITE);
        tabbedPane.setFont(new Font("Segoe UI", Font.BOLD, 14));
    }

    private JPanel createSimpleCalculatorPanel() {
        JPanel simplePanel = new JPanel();
        simplePanel.setLayout(new BorderLayout());
        simplePanel.setBackground(LIGHT_BLUE);
        simplePanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Display area
        textField1 = createStyledTextField();
        simplePanel.add(textField1, BorderLayout.NORTH);

        // Button panel
        JPanel buttonPanel = new JPanel(new GridLayout(6, 4, 10, 10));
        buttonPanel.setBackground(LIGHT_BLUE);
        buttonPanel.setBorder(new EmptyBorder(20, 0, 0, 0));

        // Create and add buttons with styling
        cButton = createStyledButton("C", DARK_BLUE, WHITE, ButtonType.CLEAR);
        button15 = createStyledButton("/", PRIMARY_BLUE, WHITE, ButtonType.OPERATOR);
        JButton emptyButton1 = createStyledButton("", LIGHT_GRAY, NAVY_BLUE, ButtonType.EMPTY);
        JButton emptyButton2 = createStyledButton("", LIGHT_GRAY, NAVY_BLUE, ButtonType.EMPTY);

        a7Button = createStyledButton("7", LIGHT_GRAY, NAVY_BLUE, ButtonType.NUMBER);
        a8Button = createStyledButton("8", LIGHT_GRAY, NAVY_BLUE, ButtonType.NUMBER);
        a9Button = createStyledButton("9", LIGHT_GRAY, NAVY_BLUE, ButtonType.NUMBER);
        button16 = createStyledButton("*", PRIMARY_BLUE, WHITE, ButtonType.OPERATOR);

        a4Button = createStyledButton("4", LIGHT_GRAY, NAVY_BLUE, ButtonType.NUMBER);
        a5Button = createStyledButton("5", LIGHT_GRAY, NAVY_BLUE, ButtonType.NUMBER);
        a6Button = createStyledButton("6", LIGHT_GRAY, NAVY_BLUE, ButtonType.NUMBER);
        button17 = createStyledButton("-", PRIMARY_BLUE, WHITE, ButtonType.OPERATOR);

        a1Button = createStyledButton("1", LIGHT_GRAY, NAVY_BLUE, ButtonType.NUMBER);
        a2Button = createStyledButton("2", LIGHT_GRAY, NAVY_BLUE, ButtonType.NUMBER);
        a3Button = createStyledButton("3", LIGHT_GRAY, NAVY_BLUE, ButtonType.NUMBER);
        button18 = createStyledButton("+", PRIMARY_BLUE, WHITE, ButtonType.OPERATOR);

        a0Button = createStyledButton("0", LIGHT_GRAY, NAVY_BLUE, ButtonType.NUMBER);
        button14 = createStyledButton(".", LIGHT_GRAY, NAVY_BLUE, ButtonType.NUMBER);
        button19 = createStyledButton("=", DARK_BLUE, WHITE, ButtonType.EQUALS);
        JButton emptyButton3 = createStyledButton("", LIGHT_BLUE, LIGHT_BLUE, ButtonType.EMPTY);

        // Add buttons to panel
        buttonPanel.add(cButton);
        buttonPanel.add(button15);
        buttonPanel.add(emptyButton1);
        buttonPanel.add(emptyButton2);

        buttonPanel.add(a7Button);
        buttonPanel.add(a8Button);
        buttonPanel.add(a9Button);
        buttonPanel.add(button16);

        buttonPanel.add(a4Button);
        buttonPanel.add(a5Button);
        buttonPanel.add(a6Button);
        buttonPanel.add(button17);

        buttonPanel.add(a1Button);
        buttonPanel.add(a2Button);
        buttonPanel.add(a3Button);
        buttonPanel.add(button18);

        buttonPanel.add(a0Button);
        buttonPanel.add(button14);
        buttonPanel.add(button19);
        buttonPanel.add(emptyButton3);

        // Radio buttons for mode switching
        JPanel radioPanel = new JPanel(new FlowLayout());
        radioPanel.setBackground(LIGHT_BLUE);
        simpleRadioButton = createStyledRadioButton("Simple", true);
        exprRadioButton = createStyledRadioButton("Scientific", false);
        
        ButtonGroup group = new ButtonGroup();
        group.add(simpleRadioButton);
        group.add(exprRadioButton);
        
        radioPanel.add(simpleRadioButton);
        radioPanel.add(exprRadioButton);

        simplePanel.add(buttonPanel, BorderLayout.CENTER);
        simplePanel.add(radioPanel, BorderLayout.SOUTH);

        return simplePanel;
    }

    private JPanel createExpressionCalculatorPanel() {
        JPanel exprPanel = new JPanel();
        exprPanel.setLayout(new BorderLayout());
        exprPanel.setBackground(LIGHT_BLUE);
        exprPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Display area
        textField2 = createStyledTextField();
        exprPanel.add(textField2, BorderLayout.NORTH);

        // Button panel with more rows for scientific functions
        JPanel buttonPanel = new JPanel(new GridLayout(8, 5, 8, 8));
        buttonPanel.setBackground(LIGHT_BLUE);
        buttonPanel.setBorder(new EmptyBorder(20, 0, 0, 0));

        // Scientific function buttons
        sinButton = createStyledButton("sin", PRIMARY_BLUE, WHITE, ButtonType.FUNCTION);
        cosButton = createStyledButton("cos", PRIMARY_BLUE, WHITE, ButtonType.FUNCTION);
        tanButton = createStyledButton("tan", PRIMARY_BLUE, WHITE, ButtonType.FUNCTION);
        cButton1 = createStyledButton("C", DARK_BLUE, WHITE, ButtonType.CLEAR);
        backspaceButton = createStyledButton("⌫", DARK_BLUE, WHITE, ButtonType.CLEAR);

        logButton = createStyledButton("log", PRIMARY_BLUE, WHITE, ButtonType.FUNCTION);
        lnButton = createStyledButton("ln", PRIMARY_BLUE, WHITE, ButtonType.FUNCTION);
        sqrtButton = createStyledButton("√", PRIMARY_BLUE, WHITE, ButtonType.FUNCTION);
        button2 = createStyledButton("(", NAVY_BLUE, WHITE, ButtonType.OPERATOR);
        button9 = createStyledButton(")", NAVY_BLUE, WHITE, ButtonType.OPERATOR);

        facButton = createStyledButton("n!", PRIMARY_BLUE, WHITE, ButtonType.FUNCTION);
        modButton = createStyledButton("mod", PRIMARY_BLUE, WHITE, ButtonType.FUNCTION);
        JButton powerButton = createStyledButton("^", PRIMARY_BLUE, WHITE, ButtonType.OPERATOR);
        button29 = createStyledButton("/", PRIMARY_BLUE, WHITE, ButtonType.OPERATOR);
        button30 = createStyledButton("*", PRIMARY_BLUE, WHITE, ButtonType.OPERATOR);

        a7Button1 = createStyledButton("7", LIGHT_GRAY, NAVY_BLUE, ButtonType.NUMBER);
        a8Button1 = createStyledButton("8", LIGHT_GRAY, NAVY_BLUE, ButtonType.NUMBER);
        a9Button1 = createStyledButton("9", LIGHT_GRAY, NAVY_BLUE, ButtonType.NUMBER);
        button31 = createStyledButton("-", PRIMARY_BLUE, WHITE, ButtonType.OPERATOR);
        JButton emptyBtn1 = createStyledButton("", LIGHT_BLUE, LIGHT_BLUE, ButtonType.EMPTY);

        a4Button1 = createStyledButton("4", LIGHT_GRAY, NAVY_BLUE, ButtonType.NUMBER);
        a5Button1 = createStyledButton("5", LIGHT_GRAY, NAVY_BLUE, ButtonType.NUMBER);
        a6Button1 = createStyledButton("6", LIGHT_GRAY, NAVY_BLUE, ButtonType.NUMBER);
        button32 = createStyledButton("+", PRIMARY_BLUE, WHITE, ButtonType.OPERATOR);
        JButton emptyBtn2 = createStyledButton("", LIGHT_BLUE, LIGHT_BLUE, ButtonType.EMPTY);

        a1Button1 = createStyledButton("1", LIGHT_GRAY, NAVY_BLUE, ButtonType.NUMBER);
        a2Button1 = createStyledButton("2", LIGHT_GRAY, NAVY_BLUE, ButtonType.NUMBER);
        a3Button1 = createStyledButton("3", LIGHT_GRAY, NAVY_BLUE, ButtonType.NUMBER);
        JButton emptyBtn3 = createStyledButton("", LIGHT_BLUE, LIGHT_BLUE, ButtonType.EMPTY);
        button33 = createStyledButton("=", DARK_BLUE, WHITE, ButtonType.EQUALS);

        a0Button1 = createStyledButton("0", LIGHT_GRAY, NAVY_BLUE, ButtonType.NUMBER);
        button13 = createStyledButton(".", LIGHT_GRAY, NAVY_BLUE, ButtonType.NUMBER);
        JButton emptyBtn4 = createStyledButton("", LIGHT_BLUE, LIGHT_BLUE, ButtonType.EMPTY);
        JButton emptyBtn5 = createStyledButton("", LIGHT_BLUE, LIGHT_BLUE, ButtonType.EMPTY);
        JButton emptyBtn6 = createStyledButton("", LIGHT_BLUE, LIGHT_BLUE, ButtonType.EMPTY);

        // Add all buttons to panel
        buttonPanel.add(sinButton);
        buttonPanel.add(cosButton);
        buttonPanel.add(tanButton);
        buttonPanel.add(cButton1);
        buttonPanel.add(backspaceButton);

        buttonPanel.add(logButton);
        buttonPanel.add(lnButton);
        buttonPanel.add(sqrtButton);
        buttonPanel.add(button2);
        buttonPanel.add(button9);

        buttonPanel.add(facButton);
        buttonPanel.add(modButton);
        buttonPanel.add(powerButton);
        buttonPanel.add(button29);
        buttonPanel.add(button30);

        buttonPanel.add(a7Button1);
        buttonPanel.add(a8Button1);
        buttonPanel.add(a9Button1);
        buttonPanel.add(button31);
        buttonPanel.add(emptyBtn1);

        buttonPanel.add(a4Button1);
        buttonPanel.add(a5Button1);
        buttonPanel.add(a6Button1);
        buttonPanel.add(button32);
        buttonPanel.add(emptyBtn2);

        buttonPanel.add(a1Button1);
        buttonPanel.add(a2Button1);
        buttonPanel.add(a3Button1);
        buttonPanel.add(emptyBtn3);
        buttonPanel.add(button33);

        buttonPanel.add(a0Button1);
        buttonPanel.add(button13);
        buttonPanel.add(emptyBtn4);
        buttonPanel.add(emptyBtn5);
        buttonPanel.add(emptyBtn6);

        // Radio buttons for mode switching
        JPanel radioPanel = new JPanel(new FlowLayout());
        radioPanel.setBackground(LIGHT_BLUE);
        exprRadioButton1 = createStyledRadioButton("Scientific", true);
        simpleRadioButton1 = createStyledRadioButton("Simple", false);
        
        ButtonGroup group = new ButtonGroup();
        group.add(exprRadioButton1);
        group.add(simpleRadioButton1);
        
        radioPanel.add(exprRadioButton1);
        radioPanel.add(simpleRadioButton1);

        exprPanel.add(buttonPanel, BorderLayout.CENTER);
        exprPanel.add(radioPanel, BorderLayout.SOUTH);

        return exprPanel;
    }

    private enum ButtonType {
        NUMBER, OPERATOR, FUNCTION, CLEAR, EQUALS, EMPTY
    }

    private JButton createStyledButton(String text, Color bgColor, Color fgColor, ButtonType type) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Add hover effect
        if (type != ButtonType.EMPTY) {
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    button.setBackground(BUTTON_HOVER);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    button.setBackground(bgColor);
                }
            });
        }

        // Make empty buttons invisible
        if (type == ButtonType.EMPTY) {
            button.setEnabled(false);
            button.setVisible(false);
        }

        return button;
    }

    private JTextField createStyledTextField() {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Consolas", Font.BOLD, 24));
        textField.setBackground(WHITE);
        textField.setForeground(NAVY_BLUE);
        textField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(PRIMARY_BLUE, 3),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setEditable(false);
        textField.setText("0");
        return textField;
    }

    private JRadioButton createStyledRadioButton(String text, boolean selected) {
        JRadioButton radioButton = new JRadioButton(text, selected);
        radioButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        radioButton.setBackground(LIGHT_BLUE);
        radioButton.setForeground(NAVY_BLUE);
        radioButton.setFocusPainted(false);
        return radioButton;
    }

    private void setupEventListeners() {
        // Simple calculator buttons
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simpleCalc.acceptInput(((JButton)e.getSource()).getText());
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

        // Radio button for simple calculator to switch to expr calculator
        exprRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabbedPane1.setSelectedIndex(1);
            }
        });

        // Expression calculator buttons
        ActionListener listener1 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonText = ((JButton)e.getSource()).getText();
                if (buttonText.equals("⌫")) {
                    buttonText = "Backspace";
                } else if (buttonText.equals("√")) {
                    buttonText = "sqrt";
                } else if (buttonText.equals("n!")) {
                    buttonText = "fac";
                }
                exprCalc.acceptInput(buttonText);
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

        // Radio button for expr calculator to switch to simple calculator
        simpleRadioButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabbedPane1.setSelectedIndex(0);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeel());
            } catch (Exception e) {
                e.printStackTrace();
            }

            frame = new JFrame("Blue Theme Calculator");
            CalculatorForm calcForm = new CalculatorForm();
            frame.setContentPane(calcForm.panel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}