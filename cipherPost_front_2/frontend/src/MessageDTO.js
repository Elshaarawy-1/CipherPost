import User from './User'; // Import the User class
import Attachment from './Attachment'; // Import the Attachment class (assuming it is defined in a separate file)

class MessageDTO {
  constructor(id,sender, recipients, time, msg_priority, subject, content, attachments,cc) {
    this.id=id
    this.sender = sender instanceof User ? sender : new User(); // Use the provided sender or create a new User instance
    this.recipients = recipients;
    this.time = time instanceof Date ? time : new Date(time); // Convert the time to a Date object if not already
    this.msg_priority = typeof msg_priority === 'number' ? msg_priority : 0; // Ensure msg_priority is a number
    this.subject = typeof subject === 'string' ? subject : ''; // Ensure subject is a string
    this.content = typeof content === 'string' ? content : ''; // Ensure content is a string
    this.attachments = Array.isArray(attachments) ? attachments.map(attachment => attachment instanceof Attachment ? attachment : new Attachment()) : []; // Ensure attachments is an array of Attachment objects
    this.cc=cc;
    }
}

export default MessageDTO;