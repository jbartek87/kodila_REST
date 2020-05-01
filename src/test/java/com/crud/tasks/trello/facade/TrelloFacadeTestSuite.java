package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.service.TrelloService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloFacadeTestSuite {

    @Autowired
    TrelloService trelloService;

    @Autowired
    TrelloMapper trelloMapper;

    @Test
    public void testTrelloFascade(){
        //Given
        TrelloList trelloList = new TrelloList("1L", "Test list", true);
        List<TrelloList> testedList = new ArrayList<>();
        testedList.add(trelloList);
        TrelloCard  trelloCard = new TrelloCard("Test Card", "test description", "1", "3");
        TrelloBoard trelloBoard = new TrelloBoard("1", "Test Board", testedList);
        List<TrelloBoard> listOfTrello = new ArrayList<>();
        listOfTrello.add(trelloBoard);
        //When
        List<TrelloListDto> trelloListDtos = trelloMapper.mapToListDto(testedList);
        List<TrelloBoardDto> trelloBoardDtos = trelloMapper.mapToBoardsDto(listOfTrello);
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        //Then
        Assert.assertEquals("Test list", trelloListDtos.get(0).getName());
        Assert.assertEquals("Test Board", trelloBoardDtos.get(0).getName());
        Assert.assertEquals("Test Card", trelloCardDto.getName());

    }

}