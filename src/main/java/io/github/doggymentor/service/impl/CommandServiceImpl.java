package io.github.doggymentor.service.impl;

import io.github.doggymentor.domain.command.Command;
import io.github.doggymentor.repository.CommandRepository;
import io.github.doggymentor.service.CommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommandServiceImpl implements CommandService {

    private final CommandRepository repository;

    @Autowired
    public CommandServiceImpl(CommandRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Command> findAllCommands() {
        return repository.findAll();
    }

    @Override
    public Command findCommand(String id) {
        return repository.findOne(id);
    }
}
