package com.epam.dao;

import com.epam.entity.Topic;
import com.epam.entity.User;
import com.epam.exceptions.DBException;

import java.util.List;

public interface TopicDAO {

    Topic findTopicByName(String name) throws DBException;

    List<Topic> findAllTopics() throws DBException;

    Topic addTopic(Topic topic) throws DBException;

    boolean updateTopic(Topic topic) throws DBException;


}
