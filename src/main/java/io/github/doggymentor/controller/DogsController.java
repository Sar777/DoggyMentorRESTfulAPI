package io.github.doggymentor.controller;

import io.github.doggymentor.converter.ConverterFacade;
import io.github.doggymentor.domain.command.Command;
import io.github.doggymentor.domain.command.CommandType;
import io.github.doggymentor.domain.dog.CommandStatus;
import io.github.doggymentor.domain.dog.Dog;
import io.github.doggymentor.domain.dog.DogCommand;
import io.github.doggymentor.domain.user.User;
import io.github.doggymentor.dto.DogCommandDTO;
import io.github.doggymentor.dto.DogDTO;
import io.github.doggymentor.exception.Error;
import io.github.doggymentor.exception.model.CommandException;
import io.github.doggymentor.exception.model.DogException;
import io.github.doggymentor.security.facade.SecurityContextAuthenticationFacade;
import io.github.doggymentor.service.CommandService;
import io.github.doggymentor.service.DogCommandService;
import io.github.doggymentor.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/dogs")
public class DogsController {

    private final SecurityContextAuthenticationFacade facade;

    private final ConverterFacade converterFacade;

    private final DogService dogService;

    private final DogCommandService dogCommandService;

    private final CommandService commandService;

    @Autowired
    public DogsController(final SecurityContextAuthenticationFacade facade,
                          final ConverterFacade converterFacade,
                          final DogService dogService,
                          final DogCommandService dogCommandService,
                          final CommandService commandService) {
        this.facade = facade;
        this.converterFacade = converterFacade;
        this.dogService = dogService;
        this.dogCommandService = dogCommandService;
        this.commandService = commandService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getDogs() {
        User user = facade.getUser();
        return new ResponseEntity<>(dogService.findAllDogsByOwnerId(user.getId()), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getDog(@PathVariable String id) {
        Dog dog = dogService.findById(id);
        if (dog == null) {
            throw new DogException(Error.DOG_NOD_FOUND);
        }

        User user = facade.getUser();
        if (!dog.getOwnerId().equals(user.getId())) {
            throw new DogException(Error.DOG_NOD_FOUND);
        }

        return new ResponseEntity<>(dog, HttpStatus.OK);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> addDog(@RequestBody DogDTO dogDTO) {
        User user = facade.getUser();

        if (dogService.findDogByNameAndOwnerId(dogDTO.getName(), user.getId()) != null) {
            throw new DogException(Error.DOG_EXIST);
        }

        Dog dog = converterFacade.convert(dogDTO);
        dog.setOwnerId(user.getId());

        dog = dogService.create(dog);
        return new ResponseEntity<>(dog, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}/commands", method = RequestMethod.GET)
    public ResponseEntity<?> getCommands(@PathVariable("id") String id) {
        User user = facade.getUser();
        Dog dog = dogService.findDogByIdAndOwnerId(id, user.getId());
        if (dog == null) {
            throw new DogException(Error.DOG_NOD_FOUND);
        }

        List<Command> commands = commandService.findAllCommands();
        List<DogCommand> dogCommands = dogCommandService.findDogCommands(dog.getId());

        List<DogCommandDTO> dogCommandDTOList = new ArrayList<>();
        for (Command command : commands) {
            Optional<DogCommand> dogCommand = dogCommands.stream().filter(x -> x.getId().equals(command.getId())).findFirst();

            DogCommandDTO dogCommandDTO = new DogCommandDTO();
            dogCommandDTO.setId(command.getId());
            dogCommandDTO.setName(command.getName());
            dogCommandDTO.setShortDescription(command.getShortDescription());
            dogCommandDTO.setFullDescription(command.getFullDescription());
            dogCommandDTO.setLearn(command.getLearn());
            dogCommandDTO.setAdvice(command.getAdvice());
            dogCommandDTO.setImageBase64(command.getImageBase64());
            dogCommandDTO.setPremium(command.isPremium());
            dogCommandDTO.setType(CommandType.valueOf(command.getType()));
            dogCommandDTO.setStatus(CommandStatus.NONE);

            // Status
            dogCommand.ifPresent(dogCommandPresent -> dogCommandDTO.setStatus(dogCommandPresent.getStatus()));
            dogCommandDTOList.add(dogCommandDTO);
        }

        return new ResponseEntity<>(dogCommandDTOList, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/commands/add/{commandId}", method = RequestMethod.POST)
    public ResponseEntity<?> addCommand(@PathVariable("id") String id, @PathVariable("commandId") String commandId) {
        User user = facade.getUser();
        Dog dog = dogService.findDogByIdAndOwnerId(id, user.getId());
        if (dog == null) {
            throw new DogException(Error.DOG_NOD_FOUND);
        }

        Command command = commandService.findCommand(commandId);
        if (command == null) {
            throw new CommandException(Error.COMMAND_NOT_FOUND);
        }

        DogCommand dogCommand = dogCommandService.findDogCommand(dog.getId(), command.getId());
        if (dogCommand != null) {
            throw new DogException(Error.DOG_HAS_COMMAND);
        }

        return new ResponseEntity<>(dogCommandService.addCommand(dog.getId(), command.getId()), HttpStatus.OK);
    }
}
