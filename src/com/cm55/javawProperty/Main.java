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
      label.setText(Msg.get(Msg.現在のJar実行コマンド) + execCommand);
      Model.openPropertyWindow(execCommand);
    } else {
      label.setText(Msg.get(Msg.Jar実行コマンドが特定できませんでした));
    }
  }
  
  public static void main(String[]args) {
    Msg.ensureLocale();
    String osName = System.getProperty("os.name").toLowerCase();
    if (osName.indexOf("windows") < 0) {
      JOptionPane.showMessageDialog(null, 
          Msg.get(Msg.本アプリはWindows上でのみ動作します), "Error", JOptionPane.ERROR_MESSAGE);
      System.exit(1);
    }
    new Main();
  }
}
