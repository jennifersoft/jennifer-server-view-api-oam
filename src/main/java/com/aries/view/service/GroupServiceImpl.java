package com.aries.view.service;

import com.aries.view.domain.Group;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService
{
    private static List<Group> groups = new ArrayList<>();

    public GroupServiceImpl() {
        Group guest = new Group();
        guest.setId("guest");
        guest.setName("guest");
        groups.add(guest);
    }

    @Override
    public void save(Group group)
    {
        groups.add(group);
    }

    @Override
    public void remove(String key)
    {
        groups.remove(find(key));
    }

    @Override
    public Group find(String key)
    {
        for(int i = 0; i < groups.size(); i++)
        {
            Group group = groups.get(i);
            if(group.getId().equals(key)) {
                return group;
            }
        }

        return null;
    }

    @Override
    public List<Group> list() {
        return groups;
    }
}
