package com.example.project.service;

import com.example.project.entity.Match;
import com.example.project.entity.Player;
import com.example.project.entity.User;
import com.example.project.exception.DaoException;
import com.example.project.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    boolean createMatch(Match match) throws ServiceException;
    List<User> findAllUser() throws ServiceException;
    Optional<User> findUserByLoginAngPassword(String login, String password) throws ServiceException;
    boolean createUser(User user) throws ServiceException;
    boolean isExistMailAndLogin(String mail, String login) throws ServiceException;
    List<Match> showAllMatches() throws ServiceException;
    boolean deleteMatch(Long id) throws ServiceException;
    Optional<Match> searchMatchById(Long id) throws ServiceException;
    List<Match> searchMatchByName(String name) throws ServiceException;
    List<Match> sortMatchByName() throws ServiceException;
    List<Match> sortMatchByDate() throws ServiceException;
    boolean updateMatch(Match match) throws ServiceException;
    double method(String player) throws ServiceException;
    boolean makeABet(User user) throws ServiceException;
}
