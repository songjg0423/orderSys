package com.flyhawk.tcpip;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.dao.MyTestDao;

public class ServerThread extends Thread {
	Socket socket = null;
	BufferedReader in = null;
	PrintWriter out = null;
	MyTestDao myTestDao = null;

	public void setMyTestDao(MyTestDao dao) {
		this.myTestDao = dao;
	}

	public ServerThread(Socket s, MyTestDao dao) throws IOException {
		this.myTestDao = dao;
		socket = s;
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket
				.getOutputStream())), true);
	}

	public void run() {
		try {
			// while (true) {
			String str = in.readLine();
//			if (str != null && str.equals("end")) {
//				break;
//			}
//			out.println("accept!");
//			System.out.println("client= " + str);
			
			Map map = new HashMap();
			map.put("id", str);
			
			myTestDao.updateTestId(map);
			out.println("1");
			out.flush();
			// }
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
