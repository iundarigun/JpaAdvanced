package br.com.devcave.jpa.controller;

import br.com.devcave.jpa.domain.Team;
import br.com.devcave.jpa.domain.TeamUser;
import br.com.devcave.jpa.domain.User;
import br.com.devcave.jpa.repository.TeamRepository;
import br.com.devcave.jpa.repository.UserRepository;
import br.com.devcave.jpa.vo.TeamVO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("team")
public class TeamController {

    private UserRepository userRepository;
    private TeamRepository teamRepository;

    @PostMapping
    public HttpEntity<?> create(@RequestBody TeamVO teamVO){
        Team team = new Team();
        team.setName(teamVO.getName());
        List<User> userList = teamVO.getUserList()
                .stream()
                .map(u-> userRepository.getOne(u))
                .collect(Collectors.toList());
        List<TeamUser> teamUserList = userList.stream()
                .map(u -> new TeamUser(team, u))
                .collect(Collectors.toList());
        team.setTeamUserList(teamUserList);
        teamRepository.save(team);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .build(team.getId());
        return ResponseEntity.created(location).build();
    }
}
