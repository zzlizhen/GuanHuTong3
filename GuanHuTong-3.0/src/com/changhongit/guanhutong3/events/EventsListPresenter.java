package com.changhongit.guanhutong3.events;

import com.changhongit.guanhutong3.utils.pulldata.Reminder;

public interface EventsListPresenter {
    public void onResume();
    public void onItemClicked(int position);
    public void delete(int position);
    public Reminder getItem(int position);
}
