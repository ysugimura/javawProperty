package com.cm55.javawProperty;

import java.awt.*;

import javax.swing.*;



public class Main {

  public Main() {

    JFrame frame = new JFrame("javawProperty " + Version.version);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setBounds(50, 50, 600, 100);
    frame.setLocationRelativeTo(null);
    frame.setLayout(new BorderLayout());
    JLabel label = new JLabel();
    frame.add(label, BorderLayout.CENTER);    
    frame.setVisible(true);

    
    String execCommand = Model.getExecCommand();
    if (execCommand != null) {    
      label.setText("現在のJar実行コマンド：" + execCommand);
      Model.openPropertyWindow(execCommand);
    } else {
      label.setText("Jar実行コマンドが特定できませんでした");
    }
  }
  
  public static void main(String[]args) {
    new Main();
  }
}
