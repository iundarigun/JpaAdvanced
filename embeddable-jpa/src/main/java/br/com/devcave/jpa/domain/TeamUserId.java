package br.com.devcave.jpa.domain;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
@Embeddable
public class TeamUserId implements Serializable {

    @ManyToOne
    @JoinColumn(name="USER_ID", referencedColumnName="id")
    private User user;

    @ManyToOne
    @JoinColumn(name="TEAM_ID", referencedColumnName="id")
    private Team team;
}
