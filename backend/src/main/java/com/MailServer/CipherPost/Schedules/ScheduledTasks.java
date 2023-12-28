package com.MailServer.CipherPost.Schedules;

import com.MailServer.CipherPost.Services.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class ScheduledTasks {

    @Autowired
    private FolderService folderService;

    @Scheduled(cron = "0 0 0 * * ?") // Run every day at midnight
    public void cleanUpTrashFolder() {
        folderService.cleanUpTrashFolder();
    }
}
