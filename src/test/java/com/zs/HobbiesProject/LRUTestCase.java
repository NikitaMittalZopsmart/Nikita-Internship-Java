package com.zs.HobbiesProject;
import com.zs.HobbiesProject.model.User;
import com.zs.HobbiesProject.cache.LRUCache;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * All test  cases
 */
public class LRUTestCase {
    private final LRUCache cache = new LRUCache<User,Integer>(3);


    @Before
    public void start()
    {
        int streak1;
        User u=new User();
        u.setHobbyName("Travel");
        u.setuID("User1");
        streak1=3;
        cache.putCache(u,streak1);
         u=new User();
        u.setuID("User2");
        u.setHobbyName("Video");
        streak1=4;
        cache.putCache(u,streak1);
        /*u=new User();
        u.setuID("User3");
        u.setHobbyName("Chess");
        streak1=5;
        cache.putCache(u,streak1);*/
        u=new User();
        u.setuID("User1");
        u.setHobbyName("Travel");
        streak1=4;
        cache.putCache(u,streak1);

    }

    /**
     * Testcase to check streak value.
     */
    @Test
    public void testStreakValue() {
        Set<Map.Entry<User, Integer>> entrySet = cache.getVal();
        int actual = 0;
        for (Map.Entry<User, Integer> entry : entrySet) {
            {
                if (entry.getKey().getuID().equals("User3"))
                    actual = entry.getValue();

            }
        }
        assertEquals(4,actual);
    }
    @Test
    public void testDisplay()
    {
        cache.displayCache();
    }
    /**
     * Test that cache is full or not
     */
    @Test
    public void testCacheSize()
    {

        int size= cache.sizeOfCache();

        assertEquals(4,size);
    }

    /**
     * Testing cache is empty or not.
     */
    @Test
    public void emptyCache()
    {
        Set<Map.Entry<User, Integer>> entrySet = cache.getVal();
        assertNotNull(entrySet);
    }
    @Test
    public void firstElement() {
        Set<Map.Entry<User, Integer>> entrySet = cache.getVal();
        int actual=0;
        for (Map.Entry<User, Integer> entry : entrySet) {
            actual = entry.getValue();
            break;

        }
        assertEquals(3,actual);
    }


}
