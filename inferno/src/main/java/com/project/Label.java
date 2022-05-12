package com.project;

import javax.swing.*;
import java.awt.*;

public class Label extends JLabel {
    Label(String text,int size, Color color){
        this.setText(text);
        this.setFont(new Font("",Font.PLAIN,size));
        this.setForeground(color);
    }
}
