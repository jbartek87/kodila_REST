package com.crud.tasks.domain;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BadgesTestSuite {


    @Test
    public void testBadges(){
        //Given
        Trello trello = new Trello(10,10);
        Trello trelloEmpty = new Trello();
        AttachmentByType attachmentByTypeEmpty = new AttachmentByType();
        AttachmentByType attachmentByType = new AttachmentByType(trello);
        Badges badges = new Badges(10, attachmentByType);
        Badges emptyBadges = new Badges();
        //When
        AttachmentByType testAtt = badges.getAttachment();
        int testVotes = badges.getVotes();
        //Then
        Assert.assertEquals(attachmentByType,testAtt);
        Assert.assertEquals(10,attachmentByType.getTrello().getBoard());
        Assert.assertEquals(10, testVotes);
        Assert.assertNotNull(emptyBadges);
        Assert.assertNotNull(attachmentByTypeEmpty);
        Assert.assertNotNull(trelloEmpty);
    }
}