package solent.ac.uk.ood.examples.exam.model;

public interface RestInterface {

    public ReplyMessage retrieveMatchingBooks(Book bookTempate);
    
    public ReplyMessage retrieveBook(Integer id);
    
}
