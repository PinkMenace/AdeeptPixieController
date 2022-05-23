package com.example.adeeptpixiecontroller.clinet;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Logger;

public class Client {

    public static final int CONNECTION_ESTABLISHED = 1;
    public static final int CONNECTION_FAILED_TO_ESTABLISHED = 0;

    private final InetAddress defaultAddress = Inet6Address.getByName("192.168.4.1");
    private InetAddress ipAddress;

    private Socket socket;

    InputStream inputStream;
    Writer outputStream;

    private final int PORT = 0;

    public Client() throws UnknownHostException {
        ipAddress = defaultAddress;
    }

    private int connect() {
        final int connectionAttempts = 5;
        final int connectionAttemptTime = 10 * 1000; // If an connection is not established in 10 seconds abort and retry

        socket = null;

        for (int i = 0; i < connectionAttempts; i++) {
            try {
                socket = new Socket("", PORT);
                socket.setSoTimeout(connectionAttemptTime);

                inputStream = socket.getInputStream();
                outputStream = new OutputStreamWriter(socket.getOutputStream(), "ASCII");

                return CONNECTION_ESTABLISHED;
            } catch (IOException e) {

            }
        }

        return CONNECTION_FAILED_TO_ESTABLISHED;
    }

    public void sendData(String msg) throws IOException {
        outputStream.write(msg);
        outputStream.flush();
    }

    public void setIPAddress(String ip) throws Exception{
        ipAddress = InetAddress.getByName(ip);
    }
}