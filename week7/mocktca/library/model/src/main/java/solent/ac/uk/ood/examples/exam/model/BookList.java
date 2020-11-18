/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.exam.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cgallen
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class BookList {

    // only used by persistance layer
    private Integer lastBookId = null;

    @XmlElementWrapper(name = "books")
    @XmlElement(name = "book")
    private List<Book> books = new ArrayList<Book>();

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
    
    
    public Integer getLastBookId() {
        return lastBookId;
    }

    public void setLastBookId(Integer lastBookId) {
        this.lastBookId = lastBookId;
    }

    @Override
    public String toString() {
        return "BookList{" + "lastBookId=" + lastBookId + ", books=" + books + '}';
    }



}
