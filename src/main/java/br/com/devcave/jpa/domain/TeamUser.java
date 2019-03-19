package br.com.devcave.jpa.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
public class TeamUser {

    @EmbeddedId
    private TeamUserId id;

    private Boolean active;

    public TeamUser(Team team, User user) {
        this.id = new TeamUserId();
        this.id.setTeam(team);
        this.id.setUser(user);
        this.active = true;
    }
}
