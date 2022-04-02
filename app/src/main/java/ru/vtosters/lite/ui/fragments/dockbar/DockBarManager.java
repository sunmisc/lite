package ru.vtosters.lite.ui.fragments.dockbar;

import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import com.vk.apps.AppsFragment;
import com.vk.discover.DiscoverFeedFragment;
import com.vk.discover.DiscoverFragment;
import com.vk.fave.fragments.FaveTabFragment;
import com.vk.menu.MenuFragment;
import com.vk.newsfeed.Feed2049;
import com.vk.newsfeed.NewsfeedFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.vk.notifications.NotificationsFragment;
import com.vtosters.lite.R;
import com.vtosters.lite.fragments.GamesFragment;
import com.vtosters.lite.fragments.PhotosFragment;
import com.vtosters.lite.fragments.d.DocumentsViewFragment;
import com.vtosters.lite.fragments.f.FriendsFragment;
import com.vtosters.lite.fragments.h.GroupsFragment;
import com.vtosters.lite.fragments.lives.LivesPostListFragment;
import com.vtosters.lite.fragments.m.VideosFragment;
import com.vtosters.lite.fragments.messages.dialogs.DialogsFragment;
import com.vtosters.lite.fragments.money.MoneyTransfersFragment;

import ru.vtosters.lite.ui.fragments.MusicFragment;
import ru.vtosters.lite.utils.Helper;
import ru.vtosters.lite.utils.Prefs;

public class DockBarManager {
    static final int MIN_SELECTED_TABS_LIMIT = 3;
    static final int MAX_SELECTED_TABS_LIMIT = 8;

    private static DockBarManager sInstance = new DockBarManager();

    public static DockBarManager getInstance() {
        if (sInstance == null)
            sInstance = new DockBarManager();
        return sInstance;
    }

    private List<DockBarTab> mSelectedTabs = new ArrayList<>();
    private List<DockBarTab> mDisabledTabs = new ArrayList<>();
    private List<String> mGroups = Arrays.asList(
            "Выбранные элементы докбара",
            "Невыбранные элементы"
    );

    public DockBarManager() {
        load();
    }

    private void load() {
        File dockbar = new File(Helper.GetContext().getFilesDir(), "dockbar.json");

        if (!dockbar.exists()) {
            mSelectedTabs.add(new DockBarTab("tab_news", R.drawable.ic_menu_newsfeed_outline_28, R.string.newsfeed, R.id.tab_news, Prefs.isUseAlternativeFragments() ? Feed2049.b.c() : NewsfeedFragment.class));
            mSelectedTabs.add(new DockBarTab("tab_discover", R.drawable.ic_menu_search_outline_28, R.string.search, R.id.tab_discover, Prefs.isUseAlternativeFragments() ? DiscoverFragment.class : DiscoverFeedFragment.class));
            mSelectedTabs.add(new DockBarTab("tab_messages", R.drawable.ic_message_28_outline, R.string.messages, R.id.tab_messages, DialogsFragment.class));
            mSelectedTabs.add(new DockBarTab("tab_feedback", R.drawable.ic_menu_notification_outline_28, R.string.feedback, R.id.tab_feedback, NotificationsFragment.class));
            mSelectedTabs.add(new DockBarTab("tab_menu",R.drawable.ic_menu_more_outline_28, R.string.menu, R.id.tab_menu, MenuFragment.class));

            mDisabledTabs.add(new DockBarTab("tab_friends", R.drawable.ic_user_24, R.string.friends, R.id.menu_friends, FriendsFragment.class));
            mDisabledTabs.add(new DockBarTab("tab_groups", R.drawable.ic_users_24, R.string.groups, R.id.menu_groups, GroupsFragment.class));
            mDisabledTabs.add(new DockBarTab("tab_photos", R.drawable.ic_camera_24, R.string.photos, R.id.menu_photos, PhotosFragment.class));
            mDisabledTabs.add(new DockBarTab("tab_audios", R.drawable.ic_music_24, R.string.music, R.id.menu_audios, MusicFragment.class));
            mDisabledTabs.add(new DockBarTab("tab_videos", R.drawable.ic_video_24, R.string.videos, R.id.menu_videos, VideosFragment.class));
            mDisabledTabs.add(new DockBarTab("tab_lives", R.drawable.ic_live_filter_24dp, R.string.sett_live, R.id.menu_lives, LivesPostListFragment.class));
            mDisabledTabs.add(new DockBarTab("tab_games", R.drawable.ic_games_24, R.string.games, R.id.menu_games, GamesFragment.class));
            mDisabledTabs.add(new DockBarTab("tab_fave", R.drawable.ic_favorite_24, R.string.fave_title, R.id.menu_fave, FaveTabFragment.class));
            mDisabledTabs.add(new DockBarTab("tab_documents", R.drawable.ic_document_24, R.string.docs, R.id.menu_documents, DocumentsViewFragment.class));
            mDisabledTabs.add(new DockBarTab("tab_payments", R.drawable.ic_money_transfer_24, R.string.money_transfer_money_transfers, R.id.menu_payments, MoneyTransfersFragment.class));
            mDisabledTabs.add(new DockBarTab("tab_vk_apps", R.drawable.ic_services_24, R.string.menu_apps, R.id.menu_vk_apps, AppsFragment.class));
        } else {
            try {
                JSONObject json = new JSONObject(readFully(new FileInputStream(dockbar)));

                JSONArray selected = json.getJSONArray("selected");
                for (int i = 0; i < selected.length(); i++) {
                    JSONObject item = selected.getJSONObject(i);
                    DockBarTab tab = new DockBarTab(
                            item.getString("tag"),
                            item.getInt("iconID"),
                            item.getInt("titleID"),
                            item.getInt("id"),
                            Class.forName(item.getString("fragmentClass"))
                    );
                    if (Prefs.isUseAlternativeFragments()) {
                        if (tab.fragmentClass == NewsfeedFragment.class) {
                            tab.fragmentClass = Feed2049.b.c();
                        } else if (tab.fragmentClass == DiscoverFeedFragment.class) {
                            tab.fragmentClass = DiscoverFragment.class;
                        }
                    } else {
                        if (tab.fragmentClass == Feed2049.b.c()) {
                            tab.fragmentClass = NewsfeedFragment.class;
                        } else if (tab.fragmentClass == DiscoverFragment.class) {
                            tab.fragmentClass = DiscoverFeedFragment.class;
                        }
                    }
                    mSelectedTabs.add(tab);
                }

                JSONArray disabled = json.getJSONArray("disabled");
                for (int i = 0; i < disabled.length(); i++) {
                    JSONObject item = disabled.getJSONObject(i);
                    DockBarTab tab = new DockBarTab(
                            item.getString("tag"),
                            item.getInt("iconID"),
                            item.getInt("titleID"),
                            item.getInt("id"),
                            Class.forName(item.getString("fragmentClass"))
                    );
                    if (Prefs.isUseAlternativeFragments()) {
                        if (tab.fragmentClass == NewsfeedFragment.class) {
                            tab.fragmentClass = Feed2049.b.c();
                        } else if (tab.fragmentClass == DiscoverFeedFragment.class) {
                            tab.fragmentClass = DiscoverFragment.class;
                        }
                    } else {
                        if (tab.fragmentClass == Feed2049.b.c()) {
                            tab.fragmentClass = NewsfeedFragment.class;
                        } else if (tab.fragmentClass == DiscoverFragment.class) {
                            tab.fragmentClass = DiscoverFeedFragment.class;
                        }
                    }
                    mDisabledTabs.add(tab);
                }

            } catch (JSONException | IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private static String readFully(InputStream is) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer = new byte[2048];
        int len;
        while ((len = is.read(buffer)) > 0) {
            bos.write(buffer, 0, len);
        }
        return bos.toString();
    }

    public void save() {
        try {
            File cache = new File(Helper.GetContext().getFilesDir(), "dockbar.json");

            JSONArray selected = new JSONArray();
            for (DockBarTab tab : mSelectedTabs) {

                JSONObject item = new JSONObject()
                        .put("tag", tab.tag)
                        .put("iconID", tab.iconID)
                        .put("titleID", tab.titleID)
                        .put("id", tab.id)
                        .put("fragmentClass", tab.fragmentClass.getName());
                selected.put(item);
            }

            JSONArray disabled = new JSONArray();
            for (DockBarTab tab : mDisabledTabs) {
                JSONObject item = new JSONObject()
                        .put("tag", tab.tag)
                        .put("iconID", tab.iconID)
                        .put("titleID", tab.titleID)
                        .put("id", tab.id)
                        .put("fragmentClass", tab.fragmentClass.getName());
                disabled.put(item);
            }

            JSONObject json = new JSONObject();
            json.put("selected", selected).put("disabled", disabled);

            FileOutputStream fos = new FileOutputStream(cache);
            fos.write(json.toString().getBytes(StandardCharsets.UTF_8));
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }

    public void swap(DockBarAdapter adapter, List<DockBarTab> list, int itemType, int fromPosition, int toPosition) {
        int curr = adapter.getIndexByViewType(fromPosition, itemType);
        int target = adapter.getIndexByViewType(toPosition, itemType);
        if (fromPosition < toPosition) {
            for (int i = curr; i < target; i++) {
                Collections.swap(list, i, i + 1);
            }
        } else {
            for (int i = curr; i > target; i--) {
                Collections.swap(list, i, i - 1);
            }
        }
        adapter.b(fromPosition, toPosition);// notifyItemMoved
    }

    public void swapAndMigrate(DockBarAdapter adapter, int fromPosition, int toPosition) {
        int curr = adapter.getIndexByViewType(fromPosition, adapter.getItemType(fromPosition));
        int target = adapter.getIndexByViewType(toPosition, adapter.getItemType(toPosition));
        if (fromPosition < toPosition) {

            if (mSelectedTabs.size() <= MIN_SELECTED_TABS_LIMIT || mSelectedTabs.get(curr).tag.equals("tab_menu")) return;

            DockBarTab tab = mSelectedTabs.get(curr);
            mSelectedTabs.remove(tab);
            mDisabledTabs.add(tab);

            for (int i = mDisabledTabs.size() - 1; i > target; i--) {
                Collections.swap(mDisabledTabs, i, i - 1);
            }

            for (int i = 0; i < target; i++) {
                Collections.swap(mDisabledTabs, i, i + 1);
            }
        } else  {

            if (mSelectedTabs.size() >= MAX_SELECTED_TABS_LIMIT) return;

            DockBarTab tab = mDisabledTabs.get(curr);
            mDisabledTabs.remove(tab);
            mSelectedTabs.add(tab);
        }
        adapter.b(fromPosition, toPosition);// notifyItemMoved
    }

    public void move(DockBarAdapter adapter, int index) {
        if (getItemType(index) == 1) {
            DockBarTab item = mSelectedTabs.get(getIndexByViewType(index, 1));

            mSelectedTabs.remove(item);

            mDisabledTabs.add(item);

            for (int i = mDisabledTabs.size() - 1; i > 0; i--) {
                Collections.swap(mDisabledTabs, i, i - 1);
            }

            adapter.e_(index); // notifyItemRemoved
            adapter.d_(getItemCount() - mDisabledTabs.size()); // notifyItemInserted
        }
    }

    public int getItemCount() {
        return mSelectedTabs.size() + mDisabledTabs.size() + mGroups.size();
    }

    public int getIndexByViewType(int position, int viewType) {
        if (viewType == DockBarAdapter.SELECTED_TAB_TYPE) {
            return position - 1;
        } else if (viewType == DockBarAdapter.DISABLED_TAB_TYPE) {
            return position - mSelectedTabs.size() - mGroups.size();
        } else if (viewType == RecyclerView.INVALID_TYPE){
            return position != 0 ? 1 : 0;
        } else {
            return -1;
        }
    }

    public int getItemType(int position) {
        if (position == 0 || position == mSelectedTabs.size() + 1) {
            return RecyclerView.INVALID_TYPE;
        } else if (position - 1 < mSelectedTabs.size()) {
            return DockBarAdapter.SELECTED_TAB_TYPE;
        } else {
            return DockBarAdapter.DISABLED_TAB_TYPE;
        }
    }

    public DockBarTab getSelectedTab(int pos) {
        return mSelectedTabs.get(getIndexByViewType(pos, 1));
    }

    public DockBarTab getDisabledTab(int pos) {
        return mDisabledTabs.get(getIndexByViewType(pos, 2));
    }

    public String getGroupTitle(int pos) {
        return mGroups.get(getIndexByViewType(pos, -1));
    }

    public List<DockBarTab> getSelectedTabs() {
        return mSelectedTabs;
    }

    public List<DockBarTab> getDisabledTabs() {
        return mDisabledTabs;
    }
}
