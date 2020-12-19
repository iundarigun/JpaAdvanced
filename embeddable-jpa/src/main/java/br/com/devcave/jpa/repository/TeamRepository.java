package br.com.devcave.jpa.repository;

import br.com.devcave.jpa.domain.Team;
import br.com.devcave.jpa.domain.TeamUser;
import br.com.devcave.jpa.domain.TeamUserId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
