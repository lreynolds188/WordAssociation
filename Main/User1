/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Server.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author student
 */
public class User1 extends JFrame implements WindowListener, ActionListener, Interface1 {

    /**
     * @param args the command line arguments
     */
    JLabel lblTitle, lblMode, lblFirstWord, lblAssocWord, lblArrayOfWords, lblWord1, lblWord2, lblLinkedList, lblBinaryTree, lblPreOrder, lblInOrder, lblPostOrder;
    JTextField txtFirstWord, txtAssocWord;
    JTextArea txtLinkedList, txtBinaryTree;
    JButton btnSend, btnCompare, btnSort1, btnSort2, btnSort3, btnExit, btnPreDisplay, btnPreSave, btnInDisplay, btnInSave, btnPostDisplay, btnPostSave;
    JPanel pnlMode, pnlTable, pnlLinkedList, pnlBinaryTree, pnlPreorder, pnlInorder, pnlPostorder;
    JTable myTable;
    JScrollPane myScrollPane;
    static JFrame myJFrame;
    int intBinaryType = 1;
    BinaryNode root;
    String strInOrder;
    String strPreOrder;
    String strPostOrder;
    boolean blnSorted = false;
    Client1 client;
    String compareString = "";
    String currentUser = "User1";
    String user2AssocWord = "";
    String lastFirstWord = "";
    String lastAssocWord = "";

    static ArrayList<Object[]> myArray = new ArrayList<Object[]>();

    static String[] tableColumns = new String[]{
        "Word 1", "Word 2"
    };

    static DefaultTableModel myTableModel = new DefaultTableModel(new Object[][]{}, tableColumns);

    static String[] comboBoxData = new String[]{
        "Play", "Review"
    };

    public static void main(String[] args) throws IOException {
        myJFrame = new User1();
        myJFrame.setVisible(true);
    }

    public User1() {
        setLocation(400, 200);
        setSize(500, 700);
        setTitle("Word Association");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        SpringLayout myLayout = new SpringLayout();
        setLayout(myLayout);

        CreateLabels(myLayout);
        CreateTextFields(myLayout);
        CreateButtons(myLayout);
        CreateTable(myLayout);
        CreateTextAreas(myLayout);
        CreatePanels(myLayout);
        Utility.CreateComboBox(myLayout, this);

        this.addWindowListener(this);

        client = new Client1(this);
    }

    public void CreateLabels(SpringLayout myLayout) {
        Font fntTitle = new Font("Verdana", Font.BOLD, 40);
        Font fntHeader = new Font("Verdana", Font.BOLD, 14);

        lblTitle = Utility.CreateLabel(myLayout, lblTitle, "         Person 1         ", 15, 25, fntTitle, Color.white, Color.blue, true, this);
        lblTitle.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));

        lblMode = Utility.CreateLabel(myLayout, lblMode, "Mode:                    ", 25, 100, fntHeader, Color.white, Color.blue, true, this);
        lblFirstWord = Utility.CreateLabel(myLayout, lblFirstWord, "First Word:", 25, 125, fntHeader, Color.blue, Color.blue, false, this);
        lblAssocWord = Utility.CreateLabel(myLayout, lblAssocWord, "Associated Word:", 25, 155, fntHeader, Color.blue, Color.blue, false, this);
        lblArrayOfWords = Utility.CreateLabel(myLayout, lblArrayOfWords, " Array of all Words ", 309, 100, fntHeader, Color.white, Color.blue, true, this);
        lblWord1 = Utility.CreateLabel(myLayout, lblWord1, "Word1", 315, 120, fntHeader, Color.blue, Color.LIGHT_GRAY, false, this);
        lblWord2 = Utility.CreateLabel(myLayout, lblWord2, "Word2", 395, 120, fntHeader, Color.blue, Color.LIGHT_GRAY, false, this);
        lblLinkedList = Utility.CreateLabel(myLayout, lblLinkedList, "Linked List (of all unmatched pairs)", 95, 300, fntHeader, Color.blue, Color.blue, false, this);
        lblBinaryTree = Utility.CreateLabel(myLayout, lblBinaryTree, "Binary Tree (of all words)", 132, 420, fntHeader, Color.blue, Color.blue, false, this);
        lblPreOrder = Utility.CreateLabel(myLayout, lblPreOrder, "  Pre-Order  ", 25, 540, fntHeader, Color.white, Color.blue, true, this);
        lblInOrder = Utility.CreateLabel(myLayout, lblInOrder, "  In-Order  ", 195, 540, fntHeader, Color.white, Color.blue, true, this);
        lblPostOrder = Utility.CreateLabel(myLayout, lblPostOrder, "  Post-Order  ", 350, 540, fntHeader, Color.white, Color.blue, true, this);
    }

    public void CreateTextAreas(SpringLayout myLayout) {
        txtLinkedList = Utility.CreateTextArea(myLayout, txtLinkedList, 25, 320, 435, 80, this);
        txtBinaryTree = Utility.CreateTextArea(myLayout, txtBinaryTree, 25, 440, 435, 80, this);
        LoadTextAreaData();
    }

    @Override
    public void Append(String msg) {
        String[] myWords = msg.split(",");
        txtFirstWord.setText(myWords[0]);
        compareString = myWords[1];
        currentUser = myWords[2];
        if (currentUser.equals("User2")) {
            user2AssocWord = myWords[3];
            if (!user2AssocWord.equalsIgnoreCase(lastAssocWord)) {
                LinkedList.AddNewNode(lastFirstWord + "-" + user2AssocWord);
                LoadTextAreaData();
            }
            btnCompare.setEnabled(true);
            txtAssocWord.setText("");
        } else {
            AddNewNodes();
        }
    }

    @Override
    public String GetWords() {
        return txtFirstWord.getText() + "," + txtAssocWord.getText() + ",User1";
    }

    public void CreateTextFields(SpringLayout myLayout) {
        txtFirstWord = Utility.CreateTextField(myLayout, txtFirstWord, 172, 126, 120, 20, true, this);
        txtAssocWord = Utility.CreateTextField(myLayout, txtAssocWord, 172, 155, 120, 20, true, this);
    }

    public void CreateButtons(SpringLayout myLayout) {
        btnSend = Utility.CreateButton(myLayout, btnSend, "Send", 177, 186, 110, 20, true, this, this);
        btnCompare = Utility.CreateButton(myLayout, btnCompare, "Compare", 177, 215, 110, 20, false, this, this);
        btnSort1 = Utility.CreateButton(myLayout, btnSort1, "1", 310, 250, 50, 20, true, this, this);
        btnSort2 = Utility.CreateButton(myLayout, btnSort2, "2", 360, 250, 50, 20, true, this, this);
        btnSort3 = Utility.CreateButton(myLayout, btnSort3, "3", 410, 250, 50, 20, true, this, this);
        btnPreDisplay = Utility.CreateButton(myLayout, btnPreDisplay, "Display", 25, 560, 98, 20, true, this, this);
        btnPreSave = Utility.CreateButton(myLayout, btnPreSave, "Save", 25, 580, 98, 20, true, this, this);
        btnInDisplay = Utility.CreateButton(myLayout, btnInDisplay, "Display", 191, 560, 98, 20, true, this, this);
        btnInSave = Utility.CreateButton(myLayout, btnInSave, "Save", 191, 580, 98, 20, true, this, this);
        btnPostDisplay = Utility.CreateButton(myLayout, btnPostDisplay, "Display", 355, 560, 98, 20, true, this, this);
        btnPostSave = Utility.CreateButton(myLayout, btnPostSave, "Save", 355, 580, 98, 20, true, this, this);
        btnExit = Utility.CreateButton(myLayout, btnExit, "Exit", 355, 625, 98, 20, true, this, this);
    }

    public void CreatePanels(SpringLayout myLayout) {
        pnlMode = Utility.CreatePanel(myLayout, pnlMode, 23, 98, 272, 152, this);
        pnlTable = Utility.CreatePanel(myLayout, pnlTable, 307, 98, 156, 176, this);
        pnlLinkedList = Utility.CreatePanel(myLayout, pnlLinkedList, 20, 298, 445, 110, this);
        pnlBinaryTree = Utility.CreatePanel(myLayout, pnlBinaryTree, 20, 417, 445, 110, this);
        pnlPreorder = Utility.CreatePanel(myLayout, pnlPreorder, 20, 535, 110, 70, this);
        pnlInorder = Utility.CreatePanel(myLayout, pnlInorder, 185, 535, 110, 70, this);
        pnlPostorder = Utility.CreatePanel(myLayout, pnlPostorder, 348, 535, 110, 70, this);
    }

    public void CreateTable(SpringLayout myLayout) {
        myTable = new JTable(myTableModel);
        myTable.setLayout(new BorderLayout());
        myTable.setPreferredSize(new Dimension(150, 90));
        myScrollPane = new JScrollPane();
        myTable.add(myScrollPane, BorderLayout.CENTER);

        add(myTable);
        add(myScrollPane);
        myLayout.putConstraint(SpringLayout.WEST, myTable, 310, SpringLayout.WEST, this);
        myLayout.putConstraint(SpringLayout.NORTH, myTable, 150, SpringLayout.NORTH, this);
    }

    public void LoadTextAreaData() {
        String myLinkedData = "HEAD <-> TAIL";
        try {
            myLinkedData = myLinkedData.substring(0, myLinkedData.length() - 4) + LinkedList.ReturnStrListdata() + myLinkedData.substring(myLinkedData.length() - 4, myLinkedData.length());
        } catch (Exception e) {

        }

        txtLinkedList.setText(myLinkedData);

        txtBinaryTree.setText(BinaryTree.CreateBinaryString(intBinaryType));

    }

    public static void LoadTableModel() {
        //Copy all values from myArray to the TableModel
        for (int i = 0; i < myArray.size(); i++) {
            myTableModel.setValueAt(myArray.get(i)[1], i, 0);
            myTableModel.setValueAt(myArray.get(i)[2], i, 1);
        }
    }

    public void AddNewNodes() {
        if (currentUser.equals("User1")) {
            BinaryTree.AddNewNode(txtFirstWord.getText());
            BinaryTree.AddNewNode(txtAssocWord.getText());
        } else {
            BinaryTree.AddNewNode(txtFirstWord.getText());
            BinaryTree.AddNewNode(compareString);
        }
        LoadTextAreaData();
    }

    public void SavePreOrder() {
        try {
            PrintWriter pWriter = new PrintWriter(new FileWriter("PreOrderSave.csv"));
            pWriter.print(txtBinaryTree.getText().substring(11));
            pWriter.close();
        } catch (IOException errorTxt) {

        }
    }

    public void SaveInOrder() {
        try {
            PrintWriter pWriter = new PrintWriter(new FileWriter("InOrderSave.csv"));
            pWriter.print(txtBinaryTree.getText().substring(10));
            pWriter.close();
        } catch (IOException errorTxt) {

        }
    }

    public void SavePostOrder() {
        try {
            PrintWriter pWriter = new PrintWriter(new FileWriter("PostOrderSave.csv"));
            pWriter.print(txtBinaryTree.getText().substring(12));
            pWriter.close();
        } catch (IOException errorTxt) {

        }
    }

    public void SaveBinaryTree(int saveType) {
        switch (saveType) {
            case 1:
                SavePreOrder();
                break;
            case 2:
                SaveInOrder();
                break;
            case 3:
                SavePostOrder();
                break;
            default:
                System.out.println("Error Saving Binary Tree");
                break;
        }

    }

    @Override
    public void windowOpened(WindowEvent we) {
    }

    @Override
    public void windowClosing(WindowEvent we) {
    }

    @Override
    public void windowClosed(WindowEvent we) {
    }

    @Override
    public void windowIconified(WindowEvent we) {
    }

    @Override
    public void windowDeiconified(WindowEvent we) {
    }

    @Override
    public void windowActivated(WindowEvent we) {
    }

    @Override
    public void windowDeactivated(WindowEvent we) {
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnExit) {
            System.exit(0);
        }

        if (ae.getSource() == btnPreSave) {
            intBinaryType = 1;
            LoadTextAreaData();
            SaveBinaryTree(1);
        }

        if (ae.getSource() == btnInSave) {
            intBinaryType = 2;
            LoadTextAreaData();
            SaveBinaryTree(2);
        }

        if (ae.getSource() == btnPostSave) {
            intBinaryType = 3;
            LoadTextAreaData();
            SaveBinaryTree(3);
        }

        if (ae.getSource() == btnSend) {
            if (txtFirstWord.getText().equals("") || txtAssocWord.getText().equals("")) {
                JOptionPane.showMessageDialog(myJFrame, "Please fill out all fields before clicking send!");
            } else if (txtFirstWord.getText().equals(txtAssocWord.getText())) {
                JOptionPane.showMessageDialog(myJFrame, "The associated word cannot be the same as the first word");
            } else {
                myArray.add(new Object[]{myArray.size(), txtFirstWord.getText(), txtAssocWord.getText()});
                myTableModel.addRow(new Object[]{txtFirstWord.getText(), txtAssocWord.getText()});
                myTableModel.fireTableDataChanged();
                btnSend.setEnabled(false);
                lastFirstWord = txtFirstWord.getText();
                lastAssocWord = txtAssocWord.getText();
                client.Send();
            }
        }

        if (ae.getSource() == btnCompare) {
            if (txtFirstWord.getText().equals("") || txtAssocWord.getText().equals("")) {
                JOptionPane.showMessageDialog(myJFrame, "Please fill out all fields before clicking compare!");
            } else {
                myArray.add(new Object[]{myArray.size(), txtFirstWord.getText(), compareString});
                myTableModel.addRow(new Object[]{txtFirstWord.getText(), compareString});
                myTableModel.fireTableDataChanged();
                if (compareString.equals(txtAssocWord.getText())) {
                    JOptionPane.showMessageDialog(myJFrame, "Match!");
                } else {
                    JOptionPane.showMessageDialog(myJFrame, "No Match");
                    LinkedList.AddNewNode(txtFirstWord.getText() + "-" + txtAssocWord.getText());
                }
                AddNewNodes();
                btnCompare.setEnabled(false);
                btnSend.setEnabled(true);
                txtFirstWord.setText("");
                txtAssocWord.setText("");
            }
        }

        if (ae.getSource() == btnPreDisplay) {
            intBinaryType = 1;
            LoadTextAreaData();
        }

        if (ae.getSource() == btnInDisplay) {
            intBinaryType = 2;
            LoadTextAreaData();
        }

        if (ae.getSource() == btnPostDisplay) {
            intBinaryType = 3;
            LoadTextAreaData();
        }

        if (ae.getSource() == btnSort1) {
            Utility.BubbleSort(myArray, blnSorted);
            myTableModel.fireTableDataChanged();
            blnSorted = !blnSorted;
        }

        if (ae.getSource() == btnSort2) {
            Utility.InsertSort(myArray, blnSorted);
            myTableModel.fireTableDataChanged();
            blnSorted = !blnSorted;
        }

        if (ae.getSource() == btnSort3) {
            Utility.SelectionSort(myArray, blnSorted);
            myTableModel.fireTableDataChanged();
            blnSorted = !blnSorted;
        }
    }
}
