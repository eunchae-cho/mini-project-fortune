package com.eomcs.pms.handler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class ChatFortune implements Command {

  @Override
  public void execute(PrintWriter out, BufferedReader in) {

    try {
    System.out.println("\n[운세 상담을 시작합니다!]");

      //try {
        ServerSocket server = new ServerSocket(10001);

        HashMap<String, Object> hm = new HashMap<String, Object>();

        while(true) {
            System.out.println("채팅자 접속을 기다리고 있습니다.");
            Socket sock = server.accept();
            ChatThread chatThread = new ChatThread(sock, hm);
            chatThread.start();
        }
    }catch (Exception e) {
        e.printStackTrace();

    }

 //   } catch(Exception e) {
  //    out.printf("작업 처리 중 오류 발생! - %s\n", e.getMessage());
}
}

class ChatThread extends Thread{
private Socket sock;
private String id;
private BufferedReader in;
private HashMap<String, Object> hm;
private boolean initFlag = false;


public ChatThread(Socket sock, HashMap<String,Object> hm) {
    this.sock = sock;
    this.hm = hm;
    try {
        PrintWriter out = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()));
        in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        id = in.readLine();
        broadcast("*"+ id + "* 님이 접속하셨습니다.");

        System.out.println("[ "+ id + " ]님이 접속하셨습니다."); // 서버에 뿌려줌
        synchronized (hm) {
            hm.put(this.id, out);
        }
        initFlag = true;
    } catch (Exception e) {
        e.printStackTrace();
    }
}
@Override
public void run() {
    try {
        String line = null;
        while((line = in.readLine()) != null) {
            if(line.equals("/quit")) {
              broadcast("*"+ id +"*님이 접속을 종료했습니다.");
              System.exit(0);
               // break;
            }
            if(line.indexOf("/to") == 0) {
              sendmsg(line);
          }else {
              broadcast("["+ id +"] : "+line);
           }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }finally {
        synchronized (hm) {
            hm.remove(id);
        }
        //broadcast("*"+ id +"*님이 접속을 종료했습니다.");
        //System.exit(0);
        try {
            if(sock != null) {
                sock.close();
            }
        } catch (Exception e2) {
            e2.printStackTrace();

        }
    }
}


public void sendmsg(String msg) {
  int start = msg.indexOf(" ") + 1;
  int end = msg.indexOf(" ",start);
  if(end != -1) {
      String to = msg.substring(start, end);
      String msg2 = msg.substring(end +1);
      Object obj = hm.get(to);
      if(obj != null) {
          PrintWriter out = (PrintWriter)obj;
          out.println(id + "님이 다음의 귓속말을 보내셨습니다. : " + msg2);
          out.flush();
      }
  }
}


//      try (Socket socket = new Socket("localhost", 8888);
//      PrintWriter chatout = new PrintWriter(socket.getOutputStream());
//      BufferedReader chatin = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
//
//        while (true) {
//          String input = Prompt.inputString("채팅 입력 > ", out, in);
//          out.println(input);
//          out.flush();
//
//          receiveResponse(out, in);
//
//
//          if (input.equalsIgnoreCase("quit"))
//            break;
//        }
//      }
//       } catch (Exception e) {
//          out.printf("작업 처리 중 오류 발생! - %s\n", e.getMessage());
//      }
//}
//
//
//  private static void receiveResponse(PrintWriter out, BufferedReader in) throws Exception {
//    while (true) {
//      String response = in.readLine();
//      if (response.length() == 0) {
//        break;
//      } else if (response.equals("!{}!")) {
//        // 사용자로부터 값을 입력을 받아서 서버에 보낸다.
//        out.println(Prompt.inputString(""));
//        out.flush(); // 주의! 출력하면 버퍼에 쌓인다. 서버로 보내고 싶다면 flush()를 호출하라!
//      } else {
//        System.out.println(response);
//      }
//    }
 // }

public void broadcast(String msg) {
  synchronized (hm) {
      Collection<Object> collection = hm.values();
      Iterator<?> iter = collection.iterator();
      while(iter.hasNext()) {
          PrintWriter out = (PrintWriter)iter.next();
          out.println(msg);
          out.println();
          out.flush();
      }
  }
}


}
