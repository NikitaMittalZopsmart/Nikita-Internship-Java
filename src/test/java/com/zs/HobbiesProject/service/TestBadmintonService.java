package com.zs.HobbiesProject.service;

import com.zs.HobbiesProject.cache.LRUCacheService;
import com.zs.HobbiesProject.dao.BadmintonImp;
import com.zs.HobbiesProject.exceptions.ApplicationRunTimeExceptions;
import com.zs.HobbiesProject.util.LogImplement;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.SQLException;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class TestBadmintonService {
    BadmintonService badmintonService;
    LRUCacheService lruServiceObject= Mockito.mock(LRUCacheService.class);
    BadmintonImp badmintonImp=Mockito.mock(BadmintonImp.class);

    @Before
    public  void setUp()
    {
        badmintonService=new BadmintonService();
    }
    @Test
    public void TestLatestStreak() throws ApplicationRunTimeExceptions{
        when(lruServiceObject.getValue(any(), any(), any())).thenReturn(3);

        try {
            when(badmintonImp.streak(any(),any())).thenReturn(2);
            badmintonService.latsestStreak("User3", LogImplement.getLog(), lruServiceObject);
        } catch (SQLException e) {
            throw new ApplicationRunTimeExceptions(500, "Bad Request");
        }

    }

}
