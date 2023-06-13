package com.example.todolist_java_dvfu.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity
public class ListTask implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "timestamp")
    public long timestamp;

    public ListTask(){
    }

    protected ListTask(Parcel in) {
        uid = in.readInt();
        title = in.readString();
        timestamp = in.readLong();
    }

    public static final Creator<ListTask> CREATOR = new Creator<ListTask>() {
        @Override
        public ListTask createFromParcel(Parcel in) {
            return new ListTask(in);
        }

        @Override
        public ListTask[] newArray(int size) {
            return new ListTask[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ListTask)) return false;
        ListTask listTask = (ListTask) o;
        return uid == listTask.uid && timestamp == listTask.timestamp && Objects.equals(title, listTask.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, title, timestamp);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(uid);
        parcel.writeString(title);
        parcel.writeLong(timestamp);
    }
}
