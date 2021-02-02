package com.zs.HobbiesProject.service;

import com.zs.HobbiesProject.cache.LRUCacheService;
import com.zs.HobbiesProject.dao.TravelImp;
import com.zs.HobbiesProject.model.Travel;

import java.sql.SQLException;
import java.util.logging.Logger;

public class TravelService {
    TravelImp travelImplment =new TravelImp();
    public void create(Travel travelObject, Logger logger,LRUCacheService lruServiceObject) throws SQLException {
        int m=travelImplment.create(travelObject,logger);
        int max=travelImplment.streak(travelObject.getUserId(),logger);
        lruServiceObject.putInCache(travelObject.getUserId(),logger,"travel",max);

    }
    public void latsestStreak(String uidInput, Logger logger, LRUCacheService lruServiceObject) throws SQLException {
        int x= lruServiceObject.getValue(uidInput,logger,"travel");
        if(x!=-1)
        {
            logger.info("Streak in cache="+x);
            return;
        }
        else {
            int max = travelImplment.streak(uidInput, logger);
            logger.info("latestStreak" + max);
            lruServiceObject.putInCache(uidInput, logger, "travel", max);
        }

    }
}
