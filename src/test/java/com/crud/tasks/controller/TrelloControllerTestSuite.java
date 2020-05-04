package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.trello.facade.TrelloFacade;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloControllerTestSuite {

    @InjectMocks
    private TrelloController trelloController;

    @Mock
    private TrelloFacade trelloFacade;


    @Test
    public void testGetTrelloBoards(){
        //Given
        List<TrelloListDto> trelloList = new ArrayList<>();
        trelloList.add(new TrelloListDto("1", "test_list", false));
        List<TrelloBoardDto> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoardDto("666", "test", trelloList));
        when(trelloFacade.fetchTrelloBoards()).thenReturn(trelloBoards);
        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloController.getTrelloBoards();
        //Then
        Assert.assertEquals("666", trelloBoardDtos.get(0).getId());
        Assert.assertEquals("test", trelloBoardDtos.get(0).getName());
    }

    @Test
    public void testCreateTrelloBoards() {

        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("5", "XYZ test", "pos 1", "10");
        TrelloCardDto trelloCardEmpty = new TrelloCardDto();
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("6",
                "testCreate", "test.com");
        when(trelloFacade.createCard(trelloCardDto)).thenReturn(createdTrelloCardDto);

        CreatedTrelloCardDto createdTrelloCardDtoEmpty = new CreatedTrelloCardDto();
        //When
        CreatedTrelloCardDto testCase = trelloController.createTrelloCard(trelloCardDto);
        //Then
        Assert.assertEquals("testCreate", testCase.getName());
        Assert.assertEquals("6", testCase.getId());
        Assert.assertEquals("test.com", testCase.getShortUrl());
        Assert.assertNotNull(createdTrelloCardDtoEmpty);
        Assert.assertNotNull(trelloCardEmpty);
    }
}