# Lab 4: Mail Server

- Objected Oriented Programming and Design Patterns course assignment.
- [/CipherPost: Github Respository Link](https://github.com/Elshaarawy-1/CipherPost)
- Team Members:

| Name | ID |
| --- | --- |
| Ahmed Youssef Sobhy Elgoerany | 21010217 |
| Ahmed Mustafa Elmorsy Amer | 21010189 |
| Ali Hassan Ali Mohamed | 21010837 |
| Moustafa Esam El-Sayed Amer | 21011364 |

## **1. Introduction**

In response to the programming assignment for CSE 223: Programming 2 at Alexandria University's Faculty of Engineering, we have developed a robust web-based email program inspired by the functionalities of Gmail. This project combines the power of the Spring Boot framework for the backend, Vue.js for the frontend, and PostgreSQL for data storage, providing a seamless and intuitive user experience.

The main focus of this application is to simulate essential features found in popular email services, emphasizing the manipulation of emails, attachments, and contacts. By incorporating various design patterns and following clean code principles, our aim is to deliver a reliable, efficient, and user-friendly email application.

## **2. Features**

### **2.1 Mail Manipulation**

### Inbox Management

- Default and priority modes for inbox organization.
- Automatic deletion of emails from the Trash folder after 30 days.
- Ability to compose, save drafts, and send emails.
- Dedicated folders for sent emails.

### User Folders

- Dynamic creation, renaming, and deletion of user folders.

### Filters and Sorting

- Implementation of filters to categorize emails based on subject or sender.
- Search and sorting capabilities using attributes such as date, sender, receivers, importance, subject, body, and attachments.

### **2.2 Attachments**

### Attachment Handling

- Seamless addition and deletion of attachments to/from emails.
- Convenient attachment viewing features.

### **2.3 Contact Management**

### Contact Operations

- Add, edit, and delete contacts with ease.
- Efficient searching and sorting of contacts.
- Capture contact information, including one or multiple email addresses.

## 3. Database Implementation

[[[[[[[[[[[[[[[[[[[[[[[[[[ Database ]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]

## 4. Design Patterns

### **1. DTO (Data Transfer Object) Design Pattern**

### **Usage:**

- **Classes:** **`MessageDTO`**, **`FolderDTO`**, **`ContactDTO`**
- **Purpose:** These classes act as data carriers and provide a convenient way to transfer data between different layers of the application, primarily between the frontend and the backend.

### **Benefits:**

- **Encapsulation:** DTOs encapsulate the data related to messages, folders, and contacts, providing a clear contract between different components.
- **Data Validation:** DTOs can include validation logic, ensuring that the data sent between layers is consistent and valid.

### **2. Command Design Pattern**

### **Usage:**

- **Classes:** `MessageCommand`, `FolderCommand`, `ContactCommand`
- **Purpose:** These classes represent commands that encapsulate specific operations, allowing them to be executed independently.

### **Benefits:**

- **Encapsulation:** Each command encapsulates a specific operation (e.g., composing, deleting, searching, sorting) and allows decoupling of sender and receiver.
- **Reusability:** Commands can be easily reused in different contexts, promoting modular and maintainable code.
- **Undo/Redo:** The Command pattern lays the groundwork for implementing undo/redo functionalities by storing the state of the system before executing a command.

### **3. Observer Design Pattern**

### **Usage:**

- **Classes:** **`MessageService`**
- **Purpose:** The **`MessageService`** acts as an observer, responding to changes in the state of messages and triggering appropriate actions. This is efficient to send messages for the peripherals of the message.

### **Benefits:**

- **Loose Coupling:** The Observer pattern ensures that the **`MessageService`** is loosely coupled with the classes it observes (e.g., **`Message`**).
- **Scalability:** Adding new observers for different events becomes easier without modifying existing code.

### **4. Builder Design Pattern**

### **Usage:**

- **Classes:** **`Message.MessageBuilder`**
- **Purpose:** The **`MessageBuilder`** class provides a way to construct complex **`Message`** objects with varying configurations.

### **Benefits:**

- **Encapsulation of Construction:** The Builder pattern encapsulates the construction details of a **`Message`** object, providing a clear interface for clients.
- **Flexibility:** It allows the construction of different types of messages with different configurations without modifying the client code. For example, building the attachments if included.

### **5. Facade Design Pattern**

### **Usage:**

- **Classes:** **`MessageFacade`**, **`ContactFacade`**, **`FolderFacade`**
- **Purpose:** Facades provide simplified interfaces to complex subsystems, abstracting the interaction between the controllers and the underlying services such as the repository classes’ methods.

### **Benefits:**

- **Simplification of Interface:** Facades offer a simplified and unified interface to interact with complex subsystems, making it easier for controllers to use these services.
- **Decoupling:** Controllers do not need to be aware of the intricacies of the underlying services, promoting low coupling between different parts of the system.

### **6. Repository Design Pattern**

### **Usage:**

- **Classes:** **`MessageRepository`**, **`FolderRepository`**, **`FolderMessagesRepository`**
- **Purpose:** Repositories abstract the data access layer, providing a set of methods for interacting with the database.

### **Benefits:**

- **Separation of Concerns:** Repositories separate the database interaction logic from the rest of the application, improving maintainability.

### **7. Adapter Design Pattern:**

### **Usage:**

- **Classes:** **`MessageAdapter`**, **`FolderAdapter`**, **`ContactAdapter`**
- **Purpose:** Adapters (MessageAdapter, FolderAdapter, ContactAdapter) are employed to convert internal classes (Message, Folder, Contact) to Data Transfer Objects (DTOs).
- **Explanation:** Adapters encapsulate the conversion logic, translating the internal structure of classes into a format suitable for external use. This promotes separation of concerns and maintains a clean interface between internal and external components.

### **Benefits:**

- **Decoupling:** Adapters decouple internal classes from their external representations (DTOs), allowing changes in one without affecting the other. This enhances modularity and system maintainability.

- **Flexibility:** Adapters provide flexibility, accommodating changes in internal class structures or DTO requirements. This ensures adaptability to evolving system needs.

- **Reusability:** Once defined, adapters can be reused across different parts of the system where similar conversions are needed. This reduces redundancy and promotes consistency in data transformation.

- **Readability:** Adapters enhance code readability by offering a standardized approach to data conversion. This clarity contributes to a more comprehensible and maintainable codebase.

## **5. Implementation Details**

### **5.1 Technologies Used**

- Java Spring Boot for the backend.
- Vue.js for the frontend.
- Spring Data JPA
- PostgreSQL for data storage.

### **5.2 HTTP Request Types**

- POST: Used for creating new entities (e.g., sending messages, creating folders).
- DELETE: Used for deleting entities (e.g., deleting messages, deleting folders).
- GET: Used for retrieving data.

### **5.3 JSON Schema**

An efficient JSON schema is designed to store email content and metadata required for retrieving emails and their folder organizations.

### **4.5 Other Implementation Details**

- Pagination for email retrieval.
- Queue data structure for handling multiple receivers.
- Support for any file type as an attachment.
- Support for multiple attachments per email.
- Two modes of operation for the Inbox (Default and Priority).
- Support for selecting multiple emails for bulk operations.

## **6. How to Run the Code**

### 6.1 Database (Spring JPA)

- It is preferred to use Intellij IDE to run the Spring JPA and Postgresql.

```bash
sudo snap install intellij-idea-ultimate --classic

sudo apt install postgresql
```

### **6.2 Backend (Spring Boot)**

1. Clone the repository.
2. Navigate to the backend directory.
3. Run the Spring Boot application.

```bash
cd backend
./mvnw spring-boot:run
```

### **6.3 Frontend (Vue.js)**

1. Navigate to the frontend directory.
2. Install dependencies.

```bash
cd frontend
npm install
```

1. Run the Vue.js application.

```bash
npm run serve
```

1. Open the application in a web browser.

## **7. UML Diagram**

[[[[[[[[[[[[[[[[[[[[[[[[[[ UML Diagram ]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]

## **8. Design Decisions and Assumptions**

[[[[[[[[[[[[[[[[[[[[[[[[[[ Assumptions ]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]

## **9. User Guide and UI Snapshots**

[[[[[[[[[[[[[[[[[[[[[[[[[[ User Manual and UI ]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]

## **10. Conclusion**

The implementation of the Gmail clone application demonstrates the effective use of design patterns, proper HTTP request handling, and the integration of Spring Boot and Vue.js. The application meets the specified requirements and provides a user-friendly interface for managing emails, attachments, and contacts. The clean and organized code ensures maintainability and extensibility in the future.

## **11. References**

- [https://docs.spring.io/spring-data/jpa/reference/index.html](https://docs.spring.io/spring-data/jpa/reference/index.html)
