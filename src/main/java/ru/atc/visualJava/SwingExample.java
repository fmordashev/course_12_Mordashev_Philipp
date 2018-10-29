package ru.atc.visualJava;


import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingExample extends JFrame implements ActionListener {

    public static final boolean AUTOSCROLL = false;
    public static final String PREFIX = "Что-то";

    public SwingExample() {
        initUI();
    }

    JLabel labelText;
    JLabel lbl;

    //написать кнопку Exit справа внизу, и мделать чтобы приложение закрывалось

    public final void initUI() {

        setTitle("UI нашей программы!");

        JButton button1 = new JButton("Попробуй");
        JButton button2 = new JButton("Вперед");

        button1.setBounds(50, 60, 100, 30);
        button2.setBounds(180, 60, 100, 30);

        getContentPane().setLayout(null);
        getContentPane().add(button1);
        getContentPane().add(button2);

        //Задание - вывести фонт в константы
        JLabel label = new JLabel("some text");
        label.setFont(new Font("Serif", Font.PLAIN, 18));
        label.setBounds(50, 160, 500, 30);
        labelText = label;

        JTextArea area = createTextArea();


        //сделать так чтобы был один листенер
        boolean simple = true;
        if(simple) {
            button1.addActionListener(this);
            button2.addActionListener(this);
        }
        else {
            button1.addActionListener(new MyActionListener(label));
            //button2.addActionListener(new MyActionListener(label));
            button2.addActionListener(new MyActionListenerCleaner(label));
            button2.addActionListener(new MyActionListenerCCalculator(label, area));

        }


        JTextField field = new JTextField(15);
        field.setBounds(50, 200, 300, 30);
        lbl = new JLabel();
        getContentPane().add(field);

        field.getDocument().addDocumentListener(new MyDocumentListener(label));


        getContentPane().add(label);

        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }


    private JTextArea createTextArea() {

        JTextArea area = new JTextArea();
        area.setBounds(50, 260, 200, 80);
        area.setLineWrap(true);

        area.setLineWrap(true);
        area.setWrapStyleWord(true);

        //setTitle("JTextArea");
        //setSize(new Dimension(350, 300));

        //getContentPane().add(area);

        //boolean autoscroll = false;

        if(AUTOSCROLL == true){
            area.setRows(25);
            area.setColumns(25);
            area.setWrapStyleWord(true);
            JScrollPane scroll = new JScrollPane (area);
            scroll.setBounds(50, 260, 200, 80);
            getContentPane().add(scroll); //Object of Jpanel

            //getContentPane().remove(scroll);
        }
        else{
            getContentPane().add(area);
        }

        return area;

    }


    public void actionPerformed(ActionEvent e){
        System.out.println("Мы что-то нажали! Параметры:" + e.getActionCommand() );
        labelText.setText( "Мы что-то нажали! Параметры:" + e.getActionCommand() );
    }
    public static void main(String[] args) {
        SwingExample ex = new SwingExample();
        ex.setVisible(true);

    }
}

//Обработчик событий от кнопок номер 1
class MyActionListener implements ActionListener{

    JLabel label;
    public MyActionListener(JLabel label){
        this.label = label;
    }

    public void actionPerformed(ActionEvent e){

        System.out.println("Мы что-то нажали! Параметры:" + e.getActionCommand() );
        label.setText( "Нажали! Параметры:" + e.getActionCommand() );

    }
}

//Обработчик событий от кнопок номер 1
class MyActionListenerCleaner implements ActionListener{

    JLabel label;
    public MyActionListenerCleaner(JLabel label){
        this.label = label;
    }

    public void actionPerformed(ActionEvent e){

        System.out.println("Ничего не вывожу! Параметры:" );
        label.setText( "Как бы пусто!"  );

    }
}

//Обработчик событий от кнопок номер 1
class MyActionListenerCCalculator implements ActionListener{

    JLabel label;
    JTextArea area;
    public MyActionListenerCCalculator(JLabel label, JTextArea area){
        this.label = label;
        this.area = area;
    }

    public void actionPerformed(ActionEvent e){

        System.out.println("Параметры:" + e.paramString());

        if(label.getText().length() == 0)
            area.setText("Да там пусто...\nВведите что-нибудь! ");
        else
            area.setText("Вот что вы написали:" +label.getText());

    }
}



class MyDocumentListener implements DocumentListener {

    private String text;

    JLabel lbl;
    public MyDocumentListener(JLabel lbl) {
        this.lbl = lbl;
    }


    @Override
    public void insertUpdate(DocumentEvent e) {
        updateLabel(e);
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        updateLabel(e);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
    }

    private void updateLabel(DocumentEvent e) {

        Document doc = e.getDocument();
        int len = doc.getLength();

        try {
            text = doc.getText(0, len);
        } catch (BadLocationException ex) {
            //Logger.getLogger(JTextFieldEx.class.getName()).log(Level.WARNING, "Bad location", ex);
        }

        lbl.setText(text);
    }
}