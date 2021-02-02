package com.zs.HobbiesProject.service;

import com.zs.HobbiesProject.cache.LRUCacheService;
import com.zs.HobbiesProject.dao.BadmintonImp;
import com.zs.HobbiesProject.model.Badminton;

import java.sql.SQLException;
import java.util.logging.Logger;

public class BadmintonService {
    BadmintonImp badmintonImplement =new BadmintonImp();
    public void create(Badminton badmintonObject, Logger logger,LRUCacheService lruServiceObject) throws SQLException {
        int m=badmintonImplement.create(badmintonObject,logger);
        int max=badmintonImplement.streak(badmintonObject.getUserId(),logger);
        lruServiceObject.putInCache(badmintonObject.getUserId(),logger,"badminton",max);
    }
    public void latsestStreak(String uidInput, Logger logger, LRUCacheService lruServiceObject) throws SQLException {
        int x= lruServiceObject.getValue(uidInput,logger,"badminton");
        if(x!=-1)
        {
            logger.info("Streak in cache="+x);
            return;
        }
        else {
            int max = badmintonImplement.streak(uidInput, logger);
            logger.info("latestStreak" + max);
            lruServiceObject.putInCache(uidInput, logger, "badminton", max);
        }

    }
}
