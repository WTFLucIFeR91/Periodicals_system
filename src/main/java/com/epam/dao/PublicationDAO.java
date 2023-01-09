package com.epam.dao;

import com.epam.entity.Publication;
import com.epam.entity.Topic;
import com.epam.exceptions.DBException;

import java.util.List;

public interface PublicationDAO {

    List<Publication> findAllPeriodicals() throws DBException;

    List<Publication> findPublicationByName(String name, int start, int end) throws DBException;
    Publication findPublicationByIndex(String index) throws DBException;

    boolean addPublication (Publication periodical) throws DBException;

    boolean updatePublication(Publication periodical) throws DBException;

    boolean deletePublicationByIndex(String index) throws DBException;

    List<Publication> getPublication (int start, int total) throws DBException;
    List<Publication> getPublicationByTopic (int start, int total) throws DBException;
    List<Publication> getPublicationByTopic (Topic topic) throws DBException;
    List<Publication> getPublicationByPrice(int start, int recordsPerPage) throws DBException;

    void updatePublicationByIndex(String oldPublicationIndex, Publication publication);
}
