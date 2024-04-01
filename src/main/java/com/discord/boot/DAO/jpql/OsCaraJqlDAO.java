package com.discord.boot.DAO.jpql;

import com.discord.boot.DAO.interfaces.RepositorioJpql;
import com.discord.boot.entity.OsCara;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OsCaraJqlDAO extends BaseRepositorioJPQl implements RepositorioJpql<OsCara> {




    @Override
    public List<OsCara> getAll() {
        return null;
    }
    @Override
    public OsCara getAllbyPK(Integer codigo) {
        return null;
    }
    @Override
    @Transactional
    public OsCara insert(OsCara osCara) throws Exception{
        if(manager != null){
            manager.merge(osCara);
        }
        return null;
    }

    @Override
    @Transactional
    public OsCara update(OsCara obj) {
        return null;
    }

    @Override
    @Transactional
    public void delete(OsCara obj) {

    }
}
