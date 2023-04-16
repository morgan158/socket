import java.net.*;
import java.io.*;
import java.util.List;

public class TCPServer {
    public static void main (String args[]) {
        try {
            int serverPort = 7896;
            ServerSocket listenSocket = new ServerSocket(serverPort);

            while(true) {
                Socket clientSocket = listenSocket.accept();
                Connection c = new Connection(clientSocket);
                System.out.println("Ответ отправлен");
            }
        } catch(IOException e) { System.out.println("Listen socket:"+e.getMessage());
        }
    }
}

class Connection extends Thread {
    DataInputStream in;
    DataOutputStream out;
    Socket clientSocket;
    public Connection (Socket aClientSocket) {
        try {
            clientSocket = aClientSocket;
            in = new DataInputStream( clientSocket.getInputStream());
            out = new DataOutputStream( clientSocket.getOutputStream());
            this.start();
        } catch(IOException e){System.out.println
                ("Connection:"+e.getMessage());
        }
    }
    public void run() {
        try {
            String data = in.readUTF();
            String responseData = this.getResponse(data);
            out.writeUTF(responseData);
            clientSocket.close();
        } catch (EOFException e){System.out.println ("EOF:"+e.getMessage());
        } catch (IOException e) {System.out.println
                ("readline:"+e.getMessage());}
    }

    private String getResponse(String data) {
        TCPRepository tcpRepository = new TCPRepository();
        String response = "Request must contain 'all' or accounts number";
        if (data.length() > 2 && data.substring(0, 3).equals("all")) {
            List<ClientDto> clientDtoList = tcpRepository.getAll();
            response = "";
            for (ClientDto clientDto : clientDtoList) {
                response += "\n" + clientDto.toString();
            }
        } else if (data.trim().matches("^[0-9]+")) {
            ClientDto clientDto = tcpRepository.getByAccountNumber(data.trim());
            if (clientDto != null) {
                response = clientDto.toString();
            } else {
                response = "User not found";
            }
        }
        return response;
    }
}
