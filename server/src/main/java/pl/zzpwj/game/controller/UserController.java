package pl.zzpwj.game.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.zzpwj.game.dto.UserDto;
import pl.zzpwj.game.mapper.UserMapper;
import pl.zzpwj.game.model.User;
import pl.zzpwj.game.service.UserService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
public class UserController {
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
    private UserService userService;
    private UserMapper userDtoMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userDtoMapper) {
        this.userService = userService;
        this.userDtoMapper = userDtoMapper;
    }

    @RequestMapping(value = "/api/users", method = RequestMethod.POST)
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) {
        try {
            UserDto saved = userDtoMapper.mapToDto(userService.register(userDtoMapper.mapToEntity(userDto)));
            return new ResponseEntity<>(saved, HttpStatus.OK);
        } catch (Exception e) {
            LOG.info("Exception: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/api/users/{login}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(@PathVariable String login) {
        try {
            userService.delete(login);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            LOG.info("Exception: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/api/users/{login}", method = RequestMethod.GET)
    public ResponseEntity<UserDto> getUser(@PathVariable String login) {
        try {
            UserDto userDto = userDtoMapper.mapToDto(userService.findOneByLogin(login));

            LOG.info("User: {}", userDto);

            return new ResponseEntity<>(userDto, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            LOG.info("Exception: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/api/users", method = RequestMethod.GET)
    public ResponseEntity<List<UserDto>> getUsers() {
        List<User> users = userService.findAll();
        List<UserDto> usersDtoList = users.stream()
                .map(user -> userDtoMapper.mapToDto(user))
                .collect(Collectors.toList());
        return new ResponseEntity<>(usersDtoList, HttpStatus.OK);
    }
}