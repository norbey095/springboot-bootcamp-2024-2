package com.bootcamp_2024_1.api_personas.controller;

import com.bootcamp_2024_1.api_personas.dto.PersonaDto;
import com.bootcamp_2024_1.api_personas.mapper.PersonaMapper;
import com.bootcamp_2024_1.api_personas.persistencia.entity.PersonaEntity;
import com.bootcamp_2024_1.api_personas.persistencia.repository.PersonaRepository;
import com.bootcamp_2024_1.api_personas.validation.Persona;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/personas")
@RequiredArgsConstructor
public class PersonaController {

    private final PersonaRepository personaRepository;
    private final PersonaMapper personaMapper;

    @GetMapping("/constante")
    private PersonaDto obteberPersona(){
        return new PersonaDto("Alejandro", "Salazar");
    }

    @GetMapping("/parametros/{nombre}")
    private PersonaDto obteberPersonaParametro(@PathVariable String nombre,
                                      @RequestParam(required = false) String apellido){
        return new PersonaDto(nombre, Persona.validarYProcesarPersona(apellido));
    }

    @PostMapping("/crearPersona")
    private PersonaDto crearPersona(@RequestBody PersonaDto persona){
        return persona;
    }

    @GetMapping("/obtenerPersonaDB")
    private List<PersonaDto> obtenerPersonaDB() {
        return personaRepository
                .findAll()
                .stream()
                .map(personaMapper::personaDtoToPersonaEntity)
                .collect(Collectors.toList());
    }

    @PostMapping("/crearPersonaDB")
    private PersonaDto crearPersonaDB(@RequestBody PersonaDto persona) {
        PersonaEntity personaEntity = this.personaRepository.save(
                personaMapper.personaEntityToPersonaDto(persona)
        );
        return personaMapper.personaDtoToPersonaEntity(personaEntity);
    }
}
