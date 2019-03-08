package com.challenge.roomlocalstorage.data.repo;

import android.app.Application;
import android.os.AsyncTask;

import com.challenge.roomlocalstorage.data.dao.UserDao;
import com.challenge.roomlocalstorage.data.databse.AppDatabase;
import com.challenge.roomlocalstorage.data.entities.User;

import java.util.List;

public class Repository {

    private UserDao userDao;

    public Repository(Application application) {
        AppDatabase appDatabase = AppDatabase.getAppDatabase(application.getApplicationContext());
        userDao = appDatabase.userDao();
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public void insertUser(User user) {
        new insertUserAsyncTask(userDao).execute(user);
    }

    public void onDestroy() {
        AppDatabase.destroyInstance();
    }

    private static class insertUserAsyncTask extends AsyncTask<User, Void, Void> {

        private UserDao userDao;

        public insertUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.addUser(users[0]);
            return null;
        }
    }
}
