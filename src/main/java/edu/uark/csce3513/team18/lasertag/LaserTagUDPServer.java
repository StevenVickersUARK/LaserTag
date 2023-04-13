// Java program to illustrate Server side
// Implementation using DatagramSocket
package edu.uark.csce3513.team18.lasertag;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.lang.StringBuilder;

public class LaserTagUDPServer {
    private static final int PORT = 7501;
    private static final int BUFFER_SIZE = 65535;

    public void start() {
        Thread serverThread = new Thread(() -> {
            // Step 1 : Create a socket to listen at port 7501
            DatagramSocket socket;
            try {
                socket = new DatagramSocket(PORT);
            } catch (SocketException e) {
                System.err.println("Failed to create socket!");
                e.printStackTrace();
                return;
            }
            byte[] buffer = new byte[BUFFER_SIZE];

            DatagramPacket packet = null;
            while (true) {

                // Step 2 : create a DatagramPacket to receive the data.
                packet = new DatagramPacket(buffer, buffer.length);

                // Step 3 : receive the data in byte buffer.
                try {
                    socket.receive(packet);
                } catch (IOException e) {
                    System.err.println("Failed to call receive on socket!");
                    e.printStackTrace();
                    break;
                }

                // Process data
                String line = data(buffer).toString();
                String[] playerIds = line.split(":");
                int hitterId = Integer.parseInt(playerIds[0]);
                int hitteeId = Integer.parseInt(playerIds[1]);
                GameState.getGameState().sendHitEvent(hitterId, hitteeId);

                // Exit the server if the client sends "bye"
                if (data(buffer).toString().equals("bye")) {
                    System.out.println("Client sent bye.....EXITING");
                    break;
                }

                // Clear the buffer after every message.
                buffer = new byte[65535];
            }
            socket.close();
        });
        serverThread.start();
    }

    // A utility method to convert the byte array
    // data into a string representation.
    public static StringBuilder data(byte[] a) {
        if (a == null)
            return null;
        StringBuilder ret = new StringBuilder();
        int i = 0;
        while (a[i] != 0) {
            ret.append((char) a[i]);
            i++;
        }
        return ret;
    }
}
