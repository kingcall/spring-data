package com.kingcall.jpa.service;

import com.kingcall.jpa.entity.User;
import com.kingcall.jpa.dao.UserReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("Jpa_Userservice")
public class UserServiceImpl implements UserService {
	@Autowired
    UserReposity userDao;
	@Override
	public Integer addUser(User user) {
		userDao.save(user);
		return user.getId();
	}

    @Override
    public List<User> addUser(List<User> users) {
        return userDao.saveAll(users);
    }

    @Override
	public List<User> findAll() {
		return userDao.findAll();
	}

    @Override
    public List<User> findAllAndSort(Sort sort) {
        return userDao.findAll(sort);
    }

    @Override
    public Page<User> findAllAndPage(Pageable pageable) {
        return userDao.findAll(pageable);
    }

    @Override
	public User getOne(Integer id) {
		return userDao.getOne(id);
	}

	@Override
	public Integer count() {
		return (int) userDao.count();
	}

	@Override
	public void deleteById(Integer id) {
		userDao.deleteById(id);
	}

    @Override
    public void delete(User user) {
        userDao.delete(user);
    }

	@Override
	public List<User> findUserByNameOrderByIdDesc(String name) {
		return userDao.findUserByNameOrderByIdDesc(name);

	}

	@Override
	public List<User> findUserByNameOrderByIdAsc(String name) {
		return userDao.findUserByNameOrderByIdAsc(name);
	}

    @Override
    public List<User> findByName(String name) {
        return userDao.findByName(name);
    }

    @Override
    public List<User> findFirst3ByName(String name) {
        return userDao.findFirst3ByName(name);
    }

    @Override
    public List<User> findByNameAndId(String name, Integer id) {
        return userDao.findByNameAndId(name,id);
    }

    @Override
    public List<User> findByNameOrId(String name, Integer id) {
        return userDao.findByNameOrId(name, id);
    }

    @Override
    public List<User> findDistinctByName(String name) {
        return userDao.findDistinctByName(name);
    }

    @Override
    public List<User> findDistinctUserByName(String name) {
        return userDao.findDistinctUserByName(name);
    }

    @Override
    public List<User> findUsers(String name) {
        return userDao.findUsers(name);
    }

    @Override
    public List<User> findUsers2(String name) {
        return userDao.findUsers2(name);
    }

    @Override
    public List<User> findUsers3(String name, Integer id) {
        return userDao.findUsers3(name,id);
    }

    @Override
    public List<User> findUsers4(String name, Integer id) {
        return userDao.findUsers4(name, id);
    }

    @Override
    public List<Object[]> departmentCount() {
        return userDao.departmentCount();
    }


    @Override
    public List<User> findAll(Example example) {
        return userDao.findAll(example);
    }

    @Override
    public String deleteAndInsert(Integer id) {
//	    return userDao.deleteAndInsert(id);
        return null;
    }
}
