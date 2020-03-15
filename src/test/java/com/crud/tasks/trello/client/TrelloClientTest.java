package com.crud.tasks.trello.client;

//@RunWith(MockitoJUnitRunner.class)
//public class TrelloClientTest {
//
//    @InjectMocks
//    private TrelloClient trelloClient;
//
//    @Mock
//    private RestTemplate restTemplate;
//
//    @Mock
//    private TrelloConfig trelloConfig;
//
//    @Before
//    public void init(){
//        when(trelloConfig.getTrelloApiEndpoint()).thenReturn("http://test.com");
//        when(trelloConfig.getTrelloAppKey()).thenReturn("test");
//        when(trelloConfig.getTrelloToken()).thenReturn("test");
//    }
//
//    @Test
//    public void shouldFetchTrelloBoards() throws URISyntaxException {
//        //Given
//        TrelloBoardDto[] trelloBoards = new TrelloBoardDto[1];
//        trelloBoards[0] = new TrelloBoardDto("test_id", "test_board", new ArrayList<>());
//
//        URI uri = new URI("http://test.com/members/barteksaski/boards?key=test&token=test&fields=name,id&lists=all");
//
//        when(restTemplate.getForObject(uri, TrelloBoardDto[].class)).thenReturn(trelloBoards);
//
//        //When
//        List<TrelloBoardDto> fetchedTrelloBoards = trelloClient.getTrelloBoards();
//
//        //Then
//        assertEquals(1, fetchedTrelloBoards.size());
//        assertEquals("test_id", fetchedTrelloBoards.get(0).getId());
//        assertEquals("test_board", fetchedTrelloBoards.get(0).getName());
//        assertEquals(new ArrayList<>(), fetchedTrelloBoards.get(0).getLists());
//    }
//
//    @Test
//    public void shouldCreateCard() throws URISyntaxException {
//
//        //Given
//        TrelloCardDto trelloCardDto = new TrelloCardDto(
//                "Test task",
//                "Test Description",
//                "top",
//                "test_id"
//        );
//        URI uri = new URI("http://test.com/cards?key=test&token=test&name=Test%20task&desc=Test%20Description&pos=top&idList=test_id");
//
//        CreatedTrelloCard createdTrelloCard = new CreatedTrelloCard(
//                "1",
//                "Test task",
//                "http://test.com"
//        );
//
//        when(restTemplate.postForObject(uri, null, CreatedTrelloCard.class)).thenReturn(createdTrelloCard);
//
//        //When
//        CreatedTrelloCard newCard = trelloClient.createNewCard(trelloCardDto);
//
//        //Then
//        assertEquals("1", newCard.getId());
//        assertEquals("Test task", newCard.getName());
//        assertEquals("http://test.com", newCard.getShortUrl());
//
//
//
//    }
//
//    @Test
//    public void shouldReturnEmptyList()throws URISyntaxException{
//
//        //Given
//        URI url = new URI("http://test.com/");
//        when(restTemplate.getForObject(url, TrelloBoardDto.class)).thenReturn(null);
//        //When
//        List<TrelloBoardDto> myTestList = trelloClient.getTrelloBoards();
//        //Then
//        assertEquals(0, myTestList.size());
//
//    }
//}