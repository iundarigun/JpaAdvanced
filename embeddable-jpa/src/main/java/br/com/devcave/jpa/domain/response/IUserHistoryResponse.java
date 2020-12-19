package br.com.devcave.jpa.domain.response;

public interface IUserHistoryResponse {
    void setName(String name);

    String getName();

    void setTotal(Double total);

    Double getTotal();
}
