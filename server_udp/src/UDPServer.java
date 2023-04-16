import java.net.*;
import java.io.*;
import java.util.List;

public class UDPServer{
    public static void main(String args[]) {
        UDPRepository UDPRepository = new UDPRepository();
        DatagramSocket aSocket = null;
        try{
            aSocket = new DatagramSocket(6789);
            byte[] buffer = new byte[1000];
            while(true){
                DatagramPacket request = new DatagramPacket(buffer,
                        buffer.length);
                aSocket.receive(request);
                String requestString = new String(request.getData(), "UTF-8");
                String response = "Request must contain 'all' or accounts number";

                if (requestString.substring(0, 3).equals("all")) {
                    List<ClientDto> clientDtoList = UDPRepository.getAll();
                    response = "";
                    for (ClientDto clientDto : clientDtoList) {
                        response += "\n" + clientDto.toString();
                    }
                } else if (requestString.trim().matches("^[0-9]+")) {
                    ClientDto clientDto = UDPRepository.getByAccountNumber(requestString.trim());
                    if (clientDto != null) {
                        response = clientDto.toString();
                    } else {
                        response = "User not found";
                    }
                }
                DatagramPacket reply =
                        new DatagramPacket(response.getBytes(), response.length(),
                                request.getAddress(), request.getPort());
                aSocket.send(reply);
                System.out.println("Reply send");
            }
        } catch (SocketException e) {
            System.out.println("Socket: " +
                e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            if(aSocket != null) aSocket.close();
        }
    }
}