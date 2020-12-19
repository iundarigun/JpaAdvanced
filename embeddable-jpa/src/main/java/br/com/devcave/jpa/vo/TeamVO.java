package br.com.devcave.jpa.vo;

import lombok.Data;

import java.util.List;

@Data
public class TeamVO {

    private String name;

    private List<Long> userList;
}
