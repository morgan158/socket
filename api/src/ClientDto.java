public class ClientDto {
    String name;
    String accountNumber;
    String accountType;
    Integer sum;

    public ClientDto() {
    }

    public ClientDto(String name, String accountNumber, String accountType, Integer sum) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.sum = sum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", accountType='" + accountType + '\'' +
                ", sum=" + sum +
                '}';
    }
}
