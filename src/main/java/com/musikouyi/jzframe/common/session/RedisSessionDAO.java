package com.musikouyi.jzframe.common.session;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.util.SerializationUtils;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Slf4j
public class RedisSessionDAO extends AbstractSessionDAO {

    @Autowired
    private RedisTemplate redisTemplate;

    private final String SHIRO_SESSION_PREFIX = "shiro-session:";

    private byte[] getKey(String key) {
        return (SHIRO_SESSION_PREFIX + key).getBytes();
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        log.info("create sessionId:" + sessionId.toString());
        assignSessionId(session, sessionId);
        saveSession(session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        if (sessionId == null) {
            return null;
        }
        log.info("read sessionId:" + sessionId.toString());
        byte[] key = getKey(sessionId.toString());
        byte[] value = (byte[]) redisTemplate.opsForValue().get(key);
        return (Session) SerializationUtils.deserialize(value);
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        saveSession(session);
    }

    @Override
    public void delete(Session session) {
        if(session == null || session.getId() == null) {
            return;
        }
        byte[] key = getKey(session.getId().toString());
        redisTemplate.delete(key);
    }

    @Override
    public Collection<Session> getActiveSessions() {
        Set<byte[]> keys = redisTemplate.keys(SHIRO_SESSION_PREFIX);
        Set<Session> sessions = new HashSet<>();
        if(CollectionUtils.isEmpty(keys)){
            return sessions;
        }
        for(byte[] key: keys){
            Session session = (Session)SerializationUtils.deserialize((byte[]) redisTemplate.opsForValue().get(key));
            sessions.add(session);
        }
        return sessions;
    }

    private void saveSession(Session session) {
        if (session != null && session.getId() != null) {
            byte[] key = getKey(session.getId().toString());
            byte[] value = SerializationUtils.serialize(session);
            redisTemplate.opsForValue().set(key, value );
//            redisTemplate.expire(key, 600, TimeUnit.MILLISECONDS);
        }
    }
}
