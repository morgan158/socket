import java.net.*;
import java.io.*;
public class UDPClient {
    public static void main(String args[]){
        try {
            DatagramSocket aSocket = new DatagramSocket();
            byte [] message = args[0].getBytes(); // get number account or string "all"
            InetAddress aHost = InetAddress.getByName(args[1]);
            int serverPort = 6789;
            DatagramPacket request =
                    new DatagramPacket(message, args[0].length(), aHost, serverPort);
            aSocket.send(request);
            byte[] buffer = new byte[1000];
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
            aSocket.receive(reply);
            System.out.println("Reply: " + new String(reply.getData()));
            aSocket.close();
        } catch (SocketException e){
            System.out.println("Socket: " +
                e.getMessage());
        } catch (IOException e){
            System.out.println("IO: " + e.getMessage());
        }
    }
}