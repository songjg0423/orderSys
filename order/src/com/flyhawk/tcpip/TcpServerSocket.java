package com.flyhawk.tcpip;

import java.net.ServerSocket;

import com.dao.MyTestDao;

public class TcpServerSocket extends Thread {
	private MyTestDao myTestDao;

	public void setMyTestDao(MyTestDao myTestDao) {
		this.myTestDao = myTestDao;
	}

	public final static int PORT = 9000;

	public TcpServerSocket() {
		this.start();
	}

	public void run() {
		this.myStart();
	}

	public void myStart() {
		
		while (myTestDao == null) {
			
		}
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(PORT);
			int i = 0;
			while (true) {
				new ServerThread(serverSocket.accept(), myTestDao).start();
				System.out.println("i= " + ++i);
			}
		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {

		}

	}

}
