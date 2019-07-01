package com.aries.view.service;

import com.aries.view.domain.Group;

import java.util.List;

public interface GroupService
{
    void save(Group group);

    void remove(String key);

    Group find(String key);

    List<Group> list();
}
