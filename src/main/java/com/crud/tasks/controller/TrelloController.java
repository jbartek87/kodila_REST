package com.crud.tasks.controller;

import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/trello")
public class TrelloController {

    @Autowired
   public TrelloClient trelloClient;

    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards")
    public void getTrelloBoards() {

        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();

        Optional<TrelloBoardDto> trb = trelloBoards.stream()
                .filter(trelloBoardDto -> trelloBoardDto.getName()!= null)
                .filter(trelloBoardDto -> trelloBoardDto.getId()!= null)
                .filter(trelloBoardDto -> trelloBoardDto.getName().contains("Hamster"))
                .findAny();

        trb.ifPresent(trelloBoardDto -> System.out.println(trelloBoardDto.getId() + " " + trelloBoardDto.getName()));


    }
}