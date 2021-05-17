package com.example.project.service.impl;

import com.example.project.dao.UserDao;
import com.example.project.dao.impl.UserDaoImpl;
import com.example.project.entity.Match;
import com.example.project.entity.User;
import com.example.project.exception.DaoException;
import com.example.project.exception.ServiceException;
import com.example.project.service.UserService;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    @Override
    public boolean createMatch(Match match) throws ServiceException {
        boolean isAdd;
        try {
            isAdd=userDao.createMatch(match);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
        return isAdd;
    }

    @Override
    public List<User> findAllUser() throws ServiceException {
        List<User> users;
        try {
            users = userDao.findAllUser();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return users;
    }

    @Override
    public Optional<User> findUserByLoginAngPassword(String login, String password) throws ServiceException {
        Optional<User> optionalUser;
        try {
            optionalUser = userDao.findUserByLoginAndPassword(login, password);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return optionalUser;
    }

    @Override
    public boolean createUser(User user) throws ServiceException {
        boolean isAdd;
        try {
            isAdd = userDao.createUser(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return isAdd;
    }

    @Override
    public boolean isExistMailAndLogin(String mail, String login) throws ServiceException {
        boolean isExist;
        try {
            isExist = userDao.isExistMailAndLogin(mail, login);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return isExist;
    }


    @Override
    public List<Match> showAllMatches() throws ServiceException {
        List <Match> matches;
        try {
            matches = userDao.showAllMatches();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return matches;
    }

    @Override
    public boolean deleteMatch(Long id) throws ServiceException {
        boolean isDeleted;
        try {
            isDeleted=userDao.deleteMatch(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return isDeleted;
    }

    @Override
    public Optional<Match> searchMatchById(Long id) throws ServiceException {
        Optional<Match> optionalMatch;
        try {
            optionalMatch = userDao.searchMatchById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return optionalMatch;
    }

    @Override
    public List<Match> searchMatchByName(String name) throws ServiceException {
        List<Match> matches;
        try {
            matches = userDao.searchMatchByName(name);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return matches;
    }

    @Override
    public List<Match> sortMatchByName() throws ServiceException {
        List <Match> matches;
        try {
            matches = userDao.sortMatchByName();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return matches;
    }

    @Override
    public List<Match> sortMatchByDate() throws ServiceException {
        List <Match> matches;
        try {
            matches = userDao.sortMatchByDate();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return matches;
    }
}
