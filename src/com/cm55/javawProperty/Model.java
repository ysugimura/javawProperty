package com.cm55.javawProperty;

import static com.sun.jna.platform.win32.WinReg.*;

import java.util.regex.*;

import com.sun.jna.platform.win32.*;

public class Model {
  
  /** jar実行コマンドパスを取得する */
  public static String getExecCommand() {
    String shellOpenCommand = getJarShellOpenCommand();
    if (shellOpenCommand == null) return null;
    return extractExecCommand(shellOpenCommand);
  }
  
  /** .jarのWindowsレジストリ情報「shell\open\command」を取得する */
  public static String getJarShellOpenCommand() {
    String ext = ".jar";
    try {
      String progId = Advapi32Util.registryGetStringValue(HKEY_CLASSES_ROOT, ext, "");
      //ystem.out.println("" + progId);
      String shellOpenCommand = Advapi32Util.registryGetStringValue(HKEY_CLASSES_ROOT, progId + "\\shell\\open\\command", "");
      //ystem.out.println(shellOpenCommand);
      return shellOpenCommand;
    } catch (Win32Exception ex) {
      return null;
    }
  }

  static Pattern pattern = Pattern.compile("^\\\"([^\\\"]+)\\\".*$");
  
  /** shell\open\commandの情報から実行コマンドパスを取り出す */
  public static String extractExecCommand(String command) {
    Matcher m = pattern.matcher(command);
    if (m.matches()) return m.group(1);
    return null;
  }
  
  /** 
   * 指定されたファイルのプロパティダイアログを表示する
   * 表示されたプロパティダイアログは、Java側のプログラムが終了すると消えてしまうことに注意。
   * @param file
   * @return
   */
  public static boolean openPropertyWindow(String file) {
    ShellAPI.SHELLEXECUTEINFO shellExecuteInfo = new ShellAPI.SHELLEXECUTEINFO();
    shellExecuteInfo.lpFile = file;
    shellExecuteInfo.nShow = User32.SW_SHOW;
    shellExecuteInfo.fMask = 0x0000000C;
    shellExecuteInfo.lpVerb = "properties";
    return Shell32.INSTANCE.ShellExecuteEx(shellExecuteInfo);
  }
}
