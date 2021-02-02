package com.zs.HobbiesProject.service;

import com.zs.HobbiesProject.cache.LRUCacheService;
import com.zs.HobbiesProject.dao.BadmintonImp;
import com.zs.HobbiesProject.dao.TravelImp;
import com.zs.HobbiesProject.exceptions.ApplicationRunTimeExceptions;
import com.zs.HobbiesProject.service.BadmintonService;
import com.zs.HobbiesProject.service.TravelService;
import com.zs.HobbiesProject.util.LogImplement;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.SQLException;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class TestTravelService {
    TravelService travelService;
    LRUCacheService lruServiceObject= Mockito.mock(LRUCacheService.class);
    TravelImp travelImp=Mockito.mock(TravelImp.class);
    @Before
    public  void setUp()
    {
        travelService=new TravelService();
    }
    @Test
    public void TestLatestStreak() throws ApplicationRunTimeExceptions {
        when(lruServiceObject.getValue(any(), any(), any())).thenReturn(2);
        try {
            when(travelImp.streak(any(),any())).thenReturn(2);
            travelService.latsestStreak("User3", LogImplement.getLog(), lruServiceObject);
        } catch (SQLException e) {
            throw new ApplicationRunTimeExceptions(500, "Bad Request");
        }
    }

}
