package com.appspot.mccfall2017g12.photoorganizer;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Album.class, Photo.class}, version = 1, exportSchema = false)
public abstract class GalleryDatabase extends RoomDatabase {

    private static GalleryDatabase instance;

    public static GalleryDatabase getInstance() {
        return instance;
    }

    public static void initialize(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context,
                    GalleryDatabase.class, "database-name").build();
        }
    }

    public abstract GalleryDao galleryDao();

    public abstract static class Task<Params, Result>
            extends RelayPostExecutionTask<Params, Void, Result> {

        public Task(Context context) {
            GalleryDatabase.initialize(context);
        }
    }

    public static class LoadPhotosByAuthorTask extends Task<String, LiveData<Photo.Extended[]>> {

        public LoadPhotosByAuthorTask(Context context) {
            super(context);
        }

        @Override
        protected LiveData<Photo.Extended[]> doInBackground(String... params) {
            String albumKey = params[0];
            return GalleryDatabase.getInstance().galleryDao()
                    .loadAlbumsPhotosByAuthor(albumKey);
        }

    }

    public static class LoadPhotosByPeopleTask extends Task<String, LiveData<Photo.Extended[]>> {

        public LoadPhotosByPeopleTask(Context context) {
            super(context);
        }

        @Override
        protected LiveData<Photo.Extended[]> doInBackground(String... params) {
            String albumKey = params[0];
            return GalleryDatabase.getInstance().galleryDao()
                    .loadAlbumsPhotosByPeople(albumKey);
        }
    }

    public static class LoadAlbumsTask extends Task<Void, LiveData<Album.Extended[]>> {

        public LoadAlbumsTask(Context context) {
            super(context);
        }

        @Override
        protected LiveData<Album.Extended[]> doInBackground(Void... voids) {
            return GalleryDatabase.getInstance().galleryDao().loadAllAlbums();
        }
    }

    public static class InsertPhotoTask extends Task<Photo, Void> {

        public InsertPhotoTask(Context context) {
            super(context);
        }

        @Override
        protected Void doInBackground(Photo... photos) {
            GalleryDatabase.getInstance().galleryDao().insertPhotos(photos);
            return null;
        }
    }

    public static class InsertAlbumTask extends Task<Album, Void> {

        public InsertAlbumTask(Context context) {
            super(context);
        }

        @Override
        protected Void doInBackground(Album... albums) {
            GalleryDatabase.getInstance().galleryDao().insertAlbums(albums);
            return null;
        }
    }

    public static class DeletePhotoTask extends Task<Photo, Void> {

        public DeletePhotoTask(Context context) {
            super(context);
        }

        @Override
        protected Void doInBackground(Photo... photos) {
            GalleryDatabase.getInstance().galleryDao().deletePhotos(photos);
            return null;
        }
    }
}
