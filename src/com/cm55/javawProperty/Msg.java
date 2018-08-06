package com.cm55.javawProperty;

import java.util.*;

/** メッセージ */
public class Msg {

  private static int lang = 0;
  
  /** 
   * ロケールを取得して、メッセージ言語を変更。
   * 現在は日本語と英語だけ。
   */
  public static void ensureLocale() {
    Locale locale = Locale.getDefault();
    if (locale.getLanguage().equals("ja")) 
      lang = 0;
    else 
      lang = 1;
  }

  /** 現在の言語設定でのメッセージを引数無しで取得する */
  public static String get(Key key) {
    return key.msg[lang];
  }

  /** メッセージキー */
  public static class Key {
    
    /** {@link Lang}のordinal()順での現在のサポートする言語別メッセージ */
    public final String[]msg;
    
    /** キー、日本語、英語を指定する */
    protected Key(String ja, String en) {
      this.msg = new String[] { ja, en };     
    }
  }


  public static final Key 現在のJar実行コマンド = new Key(
      "現在のJar実行コマンド : ",
      "Current Jar exection command : "
  );
  
  public static final Key Jar実行コマンドが特定できませんでした = new Key(
    "Jar実行コマンドが特定できませんでした",
    "Could not specify Jar execution command"
  );
  
  public static final Key 本アプリはWindows上でのみ動作します = new Key(
    "本アプリはWindows上でのみ動作します",
    "This app runs only on Windows OS"
  );
}

