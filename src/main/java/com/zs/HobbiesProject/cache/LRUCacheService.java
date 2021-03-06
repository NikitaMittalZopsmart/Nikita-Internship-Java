package com.zs.HobbiesProject.cache;


import com.zs.HobbiesProject.model.User;

import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;


/**
 * This method is implementing LRU cache.
 */
public class LRUCacheService {

    private LRUCache cache = null;

    public LRUCacheService() {
        cache = new LRUCache<User,Integer>(3);
    }

    /**
     * This method is calling put funtion of cache to put the data.
     * @param uid User ID
     * @param logger A logger Object
     * @param tableName Name of Hobby
     * @param laStreak Value of LastStreak.
     */
    public void putInCache(String uid, Logger logger, String tableName, int laStreak) {

        User userObj = new User();
        userObj.setuID(uid);
        userObj.setHobbyName(tableName);
        cache.putCache(userObj, new Integer(laStreak));
        logger.info("Successfully Inserted in cache");
        cache.displayCache();
    }

    /**
     * This function is giving value of cache.
     * @param uId UserId
     * @param logger A logger Object
     * @param tableName Name of Hobby
     * @return Return true if value is present in cache.
     */
    public int getValue(String uId, Logger logger, String tableName) {
        logger.info("In get value function");


        Set<Map.Entry<User, Integer>> entrySet = cache.getVal();
        if (entrySet != null) {
            for (Map.Entry<User, Integer> entry : entrySet) {
                User u = entry.getKey();

                String uid = u.getuID();
                String tName = u.getHobbyName();
                if (uid.equals(uId) && tName.equals(tableName)) {
                    logger.info(String.valueOf(entry.getValue()));
                    logger.info("found in cache");
                    return entry.getValue();
                }
            }

        }
        return -1;
    }




}

