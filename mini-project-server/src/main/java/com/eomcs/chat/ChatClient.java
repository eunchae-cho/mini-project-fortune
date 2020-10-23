package com.eomcs.chat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient {
  public static void main(String[] args) {
      if(args.length != 2) {
          System.out.println("사용법 : ChatClient NickName ipAddr");
          System.exit(1);
      }
      Socket sock = null;
      BufferedReader in = null;
      PrintWriter out = null;
      boolean endflag = false;
      try {
          sock = new Socket(args[1], 10001);//아아디,포트
          out = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()));
          in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
          BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

          out.println(args[0]);
          out.flush();

          InputThread it = new InputThread(sock,in);
          it.start();

          String line = null;
          while((line = keyboard.readLine()) != null) {
              out.println(line);
              out.flush();
              if(line.equals("/quit")) {
                  endflag = true;
                  break;
              }
          }
          System.out.println("클라이언트 접속 종료");
      }catch (Exception e) {
          e.printStackTrace();
      }finally {
          try {
              if(out != null) {
              //  out.close();
              }
              if(in != null) {
                  in.close();
              }
              if(sock != null) {
                  sock.close();
              }
          }catch (Exception e) {
              e.printStackTrace();
          }
      }
  }
}

class InputThread extends Thread{
  private Socket sock = null;
  private BufferedReader in = null;
  public InputThread(Socket sock,BufferedReader in) {
      this.sock = sock;
      this.in = in;
  }


  @Override
  public void run() {
      try {
          String line = null;
          while((line = in.readLine()) != null) {
              System.out.println(line);
          }
      } catch (Exception e) {
          e.printStackTrace();
      }finally {
          try {
              if(in != null) {
                  in.close();
              }
              if(sock != null) {
                  sock.close();
              }
          }catch (Exception e) {
              e.printStackTrace();
          }
      }
  }
}