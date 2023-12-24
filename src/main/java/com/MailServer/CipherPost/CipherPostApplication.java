package com.MailServer.CipherPost;


import com.MailServer.CipherPost.Services.FolderService;
import com.MailServer.CipherPost.entities.Folder;
import com.MailServer.CipherPost.entities.FolderMessage;
import com.MailServer.CipherPost.entities.Message;
import com.MailServer.CipherPost.entities.User;
import com.MailServer.CipherPost.repositories.FolderMessagesRepository;
import com.MailServer.CipherPost.repositories.FolderRepository;
import com.MailServer.CipherPost.repositories.MessageRepository;
import com.MailServer.CipherPost.repositories.UserRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@SpringBootApplication
public class CipherPostApplication {
	@Autowired
	UserRepository userRepository;
	@Autowired
	FolderRepository folderRepository;
	@Autowired
	MessageRepository messageRepository;
	@Autowired
	FolderMessagesRepository folderMessagesRepository;
	@Autowired
	FolderService folderService;
	public static void main(String[] args) {
		SpringApplication.run(CipherPostApplication.class, args);
	}
	void generateFakeUsers(int numberUsers){
		Faker faker = new Faker();
		for (int i = 0; i < numberUsers; i++) {
			String username = faker.name().username();
			User dummyUser = new User(username);
			userRepository.save(dummyUser);
			generateDefaultFoldersForUser(dummyUser);
			if (i > 10){
				generateFakeMessagesForUser(dummyUser, 5);
			}
		}

	}
	void generateDefaultFoldersForUser(User user){
		List<Folder> folders = new ArrayList<>();
		folders.add(new Folder(user, "inbox"));
		folders.add(new Folder(user, "sent"));
		folders.add(new Folder(user, "draft"));
		folders.add(new Folder(user, "trash"));
        folderRepository.saveAll(folders);
	}
	void generateFakeMessagesForUser(User user, int numberOfMessages) {
		Random random = new Random();
		Faker faker = new Faker();
		List<Message> messages = new ArrayList<>();
		List<User> recipients = userRepository.findAll(); // Include all users as potential recipients
		Folder folder;
		List<FolderMessage> folderMessages = new ArrayList<>();

		for (int i = 0; i < numberOfMessages; i++) {

            // Generate a random number between 1 and 5 (inclusive) to determine the number of recipients
			int numRecipients = random.nextInt(5) + 1;

			List<User> messageRecipients = new ArrayList<>();
			for (int j = 0; j < numRecipients; j++) {
				// Get a random user from the list
				User randomRecipient = recipients.get(random.nextInt(recipients.size()));
				messageRecipients.add(randomRecipient);
			}

			String content = faker.lorem().paragraph();

			// Increment the timestamp for each message
			Timestamp timestamp = new Timestamp(System.currentTimeMillis() - 500000 * i * 1000L); // Adjust the increment as needed

			Message fakeMessage = new Message(user, messageRecipients, content, timestamp);
			messages.add(fakeMessage);
			for (int j = 0; j < numRecipients; j++) {
				folder = folderService.findFolderByNameAndUser("inbox", messageRecipients.get(j));
				folderMessages.add(new FolderMessage(fakeMessage, folder, new Timestamp(System.currentTimeMillis() - 5000000 * i * 1000L) ));
			}

		}
		messageRepository.saveAll(messages);
		folderMessagesRepository.saveAll(folderMessages);;
	}


	@Bean
	CommandLineRunner commandLineRunner() {
		generateFakeUsers(50);
		return args -> {
			System.out.println("Complete Generation");
		};
	}
}
