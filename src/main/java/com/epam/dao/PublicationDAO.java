package com.epam.dao;

import com.epam.entity.Publication;
import com.epam.exceptions.DBException;

import java.util.List;

public interface PublicationDAO {

    List<Publication> findAllPeriodicals() throws DBException;

    List<Publication> findPeriodicalsByName(String name) throws DBException;

    Publication findPublicationByEmail(String email) throws DBException;

    boolean addPublication (Publication periodical) throws DBException;

    boolean updatePublication(Publication periodical) throws DBException;

    boolean deletePublicationByEmail(String email) throws DBException;
}
