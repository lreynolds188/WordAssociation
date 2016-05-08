/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Server.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;

/**
 *
 * @author Luke
 */
public class User2 extends JFrame implements WindowListener, ActionListener, Interface2 {

    //Declare global variables
    JLabel lblHeading, lblMode, lblFirstWord, lblAssocWord;

    JTextField txtFirstWord, txtAssocWord;

    JButton btnSend, btnCompare, btnExit;

    JComboBox cbMode;

    JPanel myPanel;

    Font fntTitle = new Font("Verdana", Font.BOLD, 30);
    Font fntHeader = new Font("Verdana", Font.BOLD, 14);
    Font fntParagraph = new Font("Verdana", Font.BOLD, 12);

    String[] strComboBoxData = new String[]{
        "Play", "Review"
    };

    Client2 client;

    static JFrame myJFrame;

    String compareString = "";
    String currentUser = "";
    String assocWord = "null";

    public static void main(String[] args) throws IOException {
        myJFrame = new User2();
        myJFrame.setVisible(true);
    }

    public User2() {
        setLocation(1000, 400);
        setSize(300, 300);
        setTitle("Word Association");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        SpringLayout myLayout = new SpringLayout();
        setLayout(myLayout);

        CreateButtons(myLayout);
        CreateLabels(myLayout);
        CreateTextFields(myLayout);
        CreateComboBox(myLayout);
        CreatePanel(myLayout);

        btnSend.setEnabled(false);
        btnCompare.setEnabled(false);

        this.addWindowListener(this);

        client = new Client2(this);
    }

    @Override
    public void Append(String msg) {
        String[] myWords = msg.split(",");
        txtFirstWord.setText(myWords[0]);
        compareString = myWords[1];
        currentUser = myWords[2];
        if(currentUser.equals("User1")){
            txtAssocWord.setText("");
            btnCompare.setEnabled(true);
        }
        
    }

    @Override
    public String GetWords() {
        return txtFirstWord.getText() + "," + txtAssocWord.getText() + ",User2," + assocWord;
    }

    public void CreateLabels(SpringLayout myLayout) {
        lblHeading = CreateLabel(lblHeading, "    Person 2    ", fntTitle, myLayout, 28, 5, Color.BLUE, Color.WHITE, true);
        lblHeading.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
        lblMode = CreateLabel(lblMode, "Mode:               ", fntHeader, myLayout, 20, 55, Color.BLUE, Color.WHITE, true);
        lblFirstWord = CreateLabel(lblFirstWord, "First Word:", fntParagraph, myLayout, 30, 80, Color.BLUE, Color.BLUE, false);
        lblAssocWord = CreateLabel(lblAssocWord, "Associated Word:", fntParagraph, myLayout, 30, 105, Color.BLUE, Color.BLUE, false);
    }

    public JLabel CreateLabel(JLabel myLabel, String strLabel, Font myFont, SpringLayout myLayout, int x, int y, Color bgColor, Color fgColor, boolean blnOpaque) {
        myLabel = new JLabel(strLabel);
        add(myLabel);
        myLabel.setOpaque(blnOpaque);
        myLabel.setFont(myFont);
        myLabel.setBackground(bgColor);
        myLabel.setForeground(fgColor);
        myLayout.putConstraint(SpringLayout.WEST, myLabel, x, SpringLayout.WEST, this);
        myLayout.putConstraint(SpringLayout.NORTH, myLabel, y, SpringLayout.NORTH, this);
        return myLabel;
    }

    public void CreateTextFields(SpringLayout myLayout) {
        txtFirstWord = CreateTextField(myLayout, txtFirstWord, 150, 80);
        txtAssocWord = CreateTextField(myLayout, txtAssocWord, 150, 103);
    }

    public JTextField CreateTextField(SpringLayout myLayout, JTextField myTextField, int x, int y) {
        myTextField = new JTextField();
        add(myTextField);
        myTextField.setEnabled(true);
        myTextField.setPreferredSize(new Dimension(100, 20));
        myLayout.putConstraint(SpringLayout.WEST, myTextField, x, SpringLayout.WEST, this);
        myLayout.putConstraint(SpringLayout.NORTH, myTextField, y, SpringLayout.NORTH, this);
        return myTextField;
    }

    public void CreateButtons(SpringLayout myLayout) {
        btnSend = CreateButton(myLayout, btnSend, "Send", 160, 140);
        btnCompare = CreateButton(myLayout, btnCompare, "Compare", 160, 170);
        btnExit = CreateButton(myLayout, btnExit, "Exit", 173, 220);
    }

    public JButton CreateButton(SpringLayout myLayout, JButton myButton, String strButton, int x, int y) {
        myButton = new JButton(strButton);
        add(myButton);
        myButton.setPreferredSize(new Dimension(90, 22));
        myButton.addActionListener(this);
        myLayout.putConstraint(SpringLayout.WEST, myButton, x, SpringLayout.WEST, this);
        myLayout.putConstraint(SpringLayout.NORTH, myButton, y, SpringLayout.NORTH, this);
        return myButton;
    }

    public void CreatePanel(SpringLayout myLayout) {
        myPanel = new JPanel();
        add(myPanel);
        myPanel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
        myPanel.setVisible(true);
        myPanel.setOpaque(true);
        myPanel.setBackground(Color.lightGray);
        myPanel.setPreferredSize(new Dimension(247, 148));
        myLayout.putConstraint(SpringLayout.WEST, myPanel, 18, SpringLayout.WEST, this);
        myLayout.putConstraint(SpringLayout.NORTH, myPanel, 53, SpringLayout.NORTH, this);
    }

    public void CreateComboBox(SpringLayout myLayout) {
        cbMode = new JComboBox(strComboBoxData);
        add(cbMode);
        cbMode.setPreferredSize(new Dimension(120, 18));
        cbMode.setVisible(true);
        myLayout.putConstraint(SpringLayout.WEST, cbMode, 143, SpringLayout.WEST, this);
        myLayout.putConstraint(SpringLayout.NORTH, cbMode, 55, SpringLayout.NORTH, this);
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnExit) {
            System.exit(0);
        }

        if (e.getSource() == btnCompare) {
            if (txtFirstWord.getText().equals("") || txtAssocWord.getText().equals("")) {
                JOptionPane.showMessageDialog(myJFrame, "Please fill out all fields before clicking compare!");
            } else {
                if (compareString.equals(txtAssocWord.getText())) {
                    JOptionPane.showMessageDialog(myJFrame, "Match!");
                } else {
                    JOptionPane.showMessageDialog(myJFrame, "No Match!");
                }
                assocWord = txtAssocWord.getText();
                btnCompare.setEnabled(false);
                btnSend.setEnabled(true);
                txtFirstWord.setText("");
                txtAssocWord.setText("");
            }
        }

        if (e.getSource() == btnSend) {
            if (txtFirstWord.getText().equals("") || txtAssocWord.getText().equals("")) {
                JOptionPane.showMessageDialog(myJFrame, "Please fill out all fields before clicking send!");
            } else if (txtFirstWord.getText().equals(txtAssocWord.getText())){
                JOptionPane.showMessageDialog(myJFrame, "The associated word cannot be the same as the first word");
            } else {
                btnSend.setEnabled(false);
                client.Send();
            }
        }
    }

}
