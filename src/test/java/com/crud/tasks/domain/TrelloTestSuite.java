package com.crud.tasks.domain;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloTestSuite {
    @Test
    public void testTrello(){
        //Given
        Trello trello = new Trello(10, 10);

        //Then
        int testBoard = trello.getBoard();
        int testCard = trello.getCard();

        //Then
        Assert.assertNotNull(testBoard);
        Assert.assertEquals(10, testCard);

    }
}