package com.flyhawk.tcpip;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class ClientSocket {
	public ClientSocket() {
	}

	public static void main(String[] args) {
		InetAddress address = null;
		Socket socket = null;
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			address = InetAddress.getLocalHost();
			socket = new Socket(address, 9000);
			in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
			
			BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

				String consoleIn = console.readLine();
				out.println(consoleIn);
				String read = in.readLine();
				System.out.println("From   server= " + read);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				in.close();
				out.close();
				socket.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

}
