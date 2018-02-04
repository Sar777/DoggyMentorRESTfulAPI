package io.github.doggymentor.controller;

import io.github.doggymentor.converter.ConverterFacade;
import io.github.doggymentor.domain.user.User;
import io.github.doggymentor.dto.UserDTO;
import io.github.doggymentor.dto.UserInfoDTO;
import io.github.doggymentor.exception.Error;
import io.github.doggymentor.exception.model.DogException;
import io.github.doggymentor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/oauth/signup")
public class SignUpController {

    private final UserService service;

    private final ConverterFacade converterFacade;

    @Autowired
    public SignUpController(final UserService service,
                            final ConverterFacade converterFacade) {
        this.service = service;
        this.converterFacade = converterFacade;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> signUp(@RequestBody final UserDTO dto) {
        if (service.findByEmail(dto.getEmail()) != null) {
            throw new DogException(Error.USER_EXIST);
        }

        User user = service.create(converterFacade.convert(dto));
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setId(user.getId());
        userInfoDTO.setEmail(user.getEmail());
        userInfoDTO.setUsername(user.getUsername());
        userInfoDTO.setGender(user.getGender());
        return new ResponseEntity<>(userInfoDTO, HttpStatus.OK);
    }
}
