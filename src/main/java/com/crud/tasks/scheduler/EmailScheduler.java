package com.crud.tasks.scheduler;

//@Component
//public class EmailScheduler {
//    private static final String SUBJECT = "Tasks: Once a day email";
//
//    @Autowired
//    private SimpleEmailService simpleEmailService;
//
//    @Autowired
//    private TaskRepository taskRepository;
//
//    @Autowired
//    private AdminConfig adminConfig;
//
//    @Scheduled(fixedDelay = 10000)
//    public void sendInformationEmail(){
//        long size = taskRepository.count();
//        String taskVariant = size<2 ? " task" : " tasks";
//        simpleEmailService.send(new Mail(adminConfig.getAdminMail(),
//                SUBJECT,
//                "Currenty in database you got : " + size + taskVariant,
//                "test@com.pl"));
//    }
//}
