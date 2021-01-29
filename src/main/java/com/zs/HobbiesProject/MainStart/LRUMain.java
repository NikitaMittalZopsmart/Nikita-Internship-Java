package com.zs.HobbiesProject.MainStart;


import com.zs.HobbiesProject.Model.User;

import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;


/**
 * This method is implementing cache.
 */
public class LRUMain {

    private LRUCache cache = null;

    public LRUMain() {
        cache = new LRUCache<User,Integer>(3);
    }

    /**
     * This method is calling put function of cache to put the data.
     * @param uid User ID
     * @param logger A logger Object
     * @param tableName Name of Hobby
     * @param latestStreak Value of LastStreak.
     */
    public void putInCache(String uid, Logger logger, String tableName, int latestStreak) {

        User userObj = new User();
        userObj.setUid(uid);
        userObj.setHobbyName(tableName);
        cache.putCache(userObj, new Integer(latestStreak));
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
    public boolean getValue(String uId, Logger logger, String tableName) {
        logger.info("In get value function");
        Set<Map.Entry<User, Integer>> entrySet = cache.getVal();
        if (entrySet != null) {
            for (Map.Entry<User, Integer> entry : entrySet) {
                User userObject = entry.getKey();

                String uid = userObject.getUid();
                String tName = userObject.getHobbyName();
                if (uid.equals(uId) && tName.equals(tableName)) {
                    logger.info(String.valueOf(entry.getValue()));
                    logger.info("found in cache");
                    return true;
                }
            }

        }
        return false;
    }




}

