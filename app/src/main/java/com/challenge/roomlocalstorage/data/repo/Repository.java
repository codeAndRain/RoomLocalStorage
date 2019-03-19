package com.challenge.roomlocalstorage.data.repo;

import android.app.Application;
import android.os.AsyncTask;

import com.challenge.roomlocalstorage.data.dao.UserDao;
import com.challenge.roomlocalstorage.data.databse.AppDatabase;
import com.challenge.roomlocalstorage.data.entities.User;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class Repository {

    private UserDao userDao;

    public Repository(Application application) {
        AppDatabase appDatabase = AppDatabase.getAppDatabase(application.getApplicationContext());
        userDao = appDatabase.userDao();
    }

    public List<User> getAllUsers() throws ExecutionException, InterruptedException {
        return new GetUsersAsyncTask(userDao).execute().get();
    }

    public void insertUser(User user) {
        new InsertUserAsyncTask(userDao).execute(user);
    }

    public void onDestroy() {
        AppDatabase.destroyInstance();
    }

    private static class InsertUserAsyncTask extends AsyncTask<User, Void, Void> {

        private UserDao userDao;

        public InsertUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.addUser(users[0]);
            return null;
        }
    }

    private static class GetUsersAsyncTask extends AsyncTask<Void, Void, List<User>> {

        private UserDao userDao;

        public GetUsersAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected List<User> doInBackground(Void... voids) {
            return userDao.getAllUsers();
        }
    }
}
