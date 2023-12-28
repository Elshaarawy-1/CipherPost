class Attachment {
    constructor(filename,extension,base64string){
      this.filename=filename;
      this.contentType=extension;
      this.base64String=base64string;
    }
  }
  
  export default Attachment;