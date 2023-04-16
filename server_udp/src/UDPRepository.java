import java.util.ArrayList;
import java.util.List;

public class UDPRepository {
    private List<ClientDto> clientDtoList = getRepo();

    List<ClientDto> getAll() {
        return clientDtoList;
    }

    ClientDto getByAccountNumber(String accountNumber) {
        ClientDto clientDto = null;
        for (ClientDto client : clientDtoList) {
            if (client.accountNumber.equals(accountNumber)) {
                clientDto = client;
            }
        }

        return clientDto;
    }

    /**
     * Вместо базы данных для тестирования создаем список клиентов на сервере и присваиваем его
     * полю clientDtoList.
     * @return list
     */
    private List<ClientDto> getRepo() {
        List<ClientDto> clientDtoList = new ArrayList<>();
        ClientDto client1 = new ClientDto("Alexandr", "1", "deposit", 3000000);
        ClientDto client2 = new ClientDto("Petr", "2", "credit", 5000000);
        ClientDto client3 = new ClientDto("Irina", "3", "deposit", 100000);
        ClientDto client4 = new ClientDto("Ivan", "4", "credit", 22000000);
        ClientDto client5 = new ClientDto("Anna", "5", "deposit", 3000);
        ClientDto client6 = new ClientDto("Daniil", "123", "deposit", 555000);
        clientDtoList.add(client1);
        clientDtoList.add(client2);
        clientDtoList.add(client3);
        clientDtoList.add(client4);
        clientDtoList.add(client5);
        clientDtoList.add(client6);
        return clientDtoList;
    }
}
