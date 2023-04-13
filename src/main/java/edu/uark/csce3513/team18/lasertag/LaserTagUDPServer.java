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

    static String playerFeed = "";

    public void ServerStart() throws IOException {

        // Step 1 : Create a socket to listen at port 7501
        DatagramSocket ds = new DatagramSocket(7501);
        byte[] receive = new byte[65535];

        DatagramPacket DpReceive = null;
        while (true) {

            // Step 2 : create a DatgramPacket to receive the data.
            DpReceive = new DatagramPacket(receive, receive.length);

            // Step 3 : revieve the data in byte buffer.
            ds.receive(DpReceive);

            playerFeed = playerFeed + data(receive) + "\n";

            // Exit the server if the client sends "bye"
            if (data(receive).toString().equals("bye")) {
                System.out.println("Client sent bye.....EXITING");
                break;
            }

            // Clear the buffer after every message.
            receive = new byte[65535];
        }
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
