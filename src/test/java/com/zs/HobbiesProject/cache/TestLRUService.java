package com.zs.HobbiesProject.cache;

import com.zs.HobbiesProject.model.User;
import com.zs.HobbiesProject.util.LogImplement;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class TestLRUService {
    LRUCache cache= Mockito.mock(LRUCache.class);
    Map<User, Integer> map;
    LRUCacheService lruServiceObject;
    Set<Entry<User,Integer>> entrySet;
    User u;
    @Before
    public void set()
    {
        entrySet=new HashSet<>();
        map=new LinkedHashMap<>();
        lruServiceObject=new LRUCacheService();
         u=new User();
        u.setHobbyName("badminton");
        u.setuID("User3");
        map.put(u,2);

        u=new User();
        u.setuID("User2");
        u.setHobbyName("travel");
       map.put(u,3);

    }
    @Test
    public void testGetValue()
    {
        entrySet=map.entrySet();
        Mockito.when(cache.getVal()).thenReturn(entrySet);
        Assert.assertEquals(-1,lruServiceObject.getValue("User1", LogImplement.getLog(),"travel"));
    }
}
