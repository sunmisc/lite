package ru.vtosters.lite.hooks;

import static ru.vtosters.lite.f0x1d.VTVerifications.isDeveloper;
import static ru.vtosters.lite.f0x1d.VTVerifications.isPrometheus;
import static ru.vtosters.lite.f0x1d.VTVerifications.isVerified;
import static ru.vtosters.lite.foaf.FoafBase.getLastSeen;
import static ru.vtosters.lite.hooks.DateHook.getLocale;
import static ru.vtosters.lite.utils.Base64Utils.decode;
import static ru.vtosters.lite.utils.Globals.getContext;
import static ru.vtosters.lite.utils.Globals.getPrefsValue;
import static ru.vtosters.lite.utils.Globals.getUserToken;
import static ru.vtosters.lite.utils.Newsfeed.checkCaption;
import static ru.vtosters.lite.utils.Newsfeed.checkCopyright;
import static ru.vtosters.lite.utils.Newsfeed.isBadNews;
import static ru.vtosters.lite.utils.Newsfeed.mFiltersLinks;
import static ru.vtosters.lite.utils.Preferences.ads;
import static ru.vtosters.lite.utils.Preferences.adsgroup;
import static ru.vtosters.lite.utils.Preferences.adsstories;
import static ru.vtosters.lite.utils.Preferences.authorsrecomm;
import static ru.vtosters.lite.utils.Preferences.dev;
import static ru.vtosters.lite.utils.Preferences.friendsblock;
import static ru.vtosters.lite.utils.Preferences.friendsrecomm;
import static ru.vtosters.lite.utils.Preferences.getBoolValue;
import static ru.vtosters.lite.utils.Preferences.hasVerification;
import static ru.vtosters.lite.utils.Preferences.postsrecomm;

import android.util.Log;

import com.vk.core.network.Network;
import com.vk.core.util.DeviceIdProvider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.util.Objects;
import java.util.Random;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import ru.vtosters.lite.utils.Globals;

public class JsonInjectors{
    private static final OkHttpClient mClient = new OkHttpClient();

    public static JSONObject profileButton(JSONObject orig) throws JSONException{
        if (haveDonateButton()) return orig;

        var pic = "https://sun1-18.userapi.com/NLd_rNpGuSaBnPV6O-j5mqCGZk8BK8drAMd2LQ/5R-DEF37PFs.png";
        var title = "Помоги проекту донатом и получи бонус!";
        var link = "https://vk.com/vtosters_official";
        var text_color = "2D81E0";

        // JSONObject jsonObj = new JSONObject("{\"action\":{\"target\":\"internal\",\"type\":\"open_url\",\"url\":\"" + link + "\"},\"title\":\"" + title + "\",\"icons\":[{\"url\":\"" + pic + "\",\"width\":20,\"height\":20},{\"url\":\"" + pic + "\",\"width\":40,\"height\":40},{\"url\":\"" + pic + "\",\"width\":60,\"height\":60},{\"url\":\"" + pic + "\",\"width\":80,\"height\":80}],\"text_color\":\"" + text_color + "\"}");
        return new JSONObject(decode("eyJhY3Rpb24iOnsidGFyZ2V0IjoiaW50ZXJuYWwiLCJ0eXBlIjoib3Blbl91cmwiLCJ1cmwiOiI=")
                + link + decode("In0sInRpdGxlIjoi")
                + title + decode("IiwiaWNvbnMiOlt7InVybCI6Ig==")
                + pic + decode("Iiwid2lkdGgiOjIwLCJoZWlnaHQiOjIwfSx7InVybCI6Ig==")
                + pic + decode("Iiwid2lkdGgiOjQwLCJoZWlnaHQiOjQwfSx7InVybCI6Ig==")
                + pic + decode("Iiwid2lkdGgiOjYwLCJoZWlnaHQiOjYwfSx7InVybCI6Ig==")
                + pic + decode("Iiwid2lkdGgiOjgwLCJoZWlnaHQiOjgwfV0sInRleHRfY29sb3IiOiI=")
                + text_color + decode("In0="));
    }

    public static JSONObject convBar(JSONObject orig) throws JSONException{
        var peerid = Objects.requireNonNull(orig.optJSONObject("peer")).optInt("id");

        var pic = "https://image.pngaaa.com/641/326641-middle.png"; // can be null
        var text = "Я не загрузил данные (9(9((";
        var link = "https://vtosters.app"; // can be null
        var linktitle = "Test button"; // can be null

        // "{\"layout\":\"tertiary\",\"text\":\"" + linktitle + "\",\"type\":\"link\",\"link\":\"" + link + "\"}";
        var buttons = decode("eyJsYXlvdXQiOiJ0ZXJ0aWFyeSIsInRleHQiOiI=") + linktitle + decode("IiwidHlwZSI6ImxpbmsiLCJsaW5rIjoi") + link + decode("In0=");
        // ,"icon":" + pic + "
        var icon = decode("LCJpY29uIjoi") + pic + decode("Ig==");

        var hasIcon = !pic.isEmpty();
        var hasButton = !buttons.isEmpty();
        var isPicture = pic.endsWith(".png") || pic.endsWith(".jpg") || pic.endsWith(".jpeg") || pic.endsWith(".webp");

        if (!isPicture) hasIcon = false;
        if (!hasIcon) icon = "";
        if (!hasButton) buttons = "";

        if (isVerified(peerid)) text = "Я купил VTosters Premium";
        if (isPrometheus(peerid)) text = "Я купил VTosters Premium Gold Prime Pro Plus";
        if (isDeveloper(peerid)) text = "Я создал говно";
        if (!isVerified(peerid) || text.equals("")) {
            if (getBoolValue("convBarRecomm", false)) {
                return null;
            } else {
                return orig.optJSONObject("conversation_bar");
            }
        }

        if (!dev()) return orig.optJSONObject("conversation_bar");

        // JSONObject("{\"name\":\"group_admin_welcome\",\"text\":\"" + textverif + "\",\"buttons\":[],\"icon\":\"" + pic + "\"}");
        return new JSONObject(decode("eyJuYW1lIjoiZ3JvdXBfYWRtaW5fd2VsY29tZSIsInRleHQiOiI=")
                + text + decode("IiwiYnV0dG9ucyI6Ww==")
                + buttons + decode("XQ==")
                + icon
                + decode("fQ=="));
    }

    public static JSONObject menu(JSONObject orig) throws JSONException{
        var Special = orig.optJSONArray("special");
        var Main = orig.getJSONArray("main");
        var Other = orig.optJSONArray("other");

        if (Special != null) {
            orig.remove("special");
        }

        return orig;
    }

    public static JSONObject superapp(JSONObject json) throws JSONException{
        var superApps = Globals.getPreferences().getString("superapp_items",
                "menu,promo,miniapps,vkpay_slim,greeting,holiday,weather,sport,games,informer,food,event,music,vk_run").split(",");
        if (superApps.length == 0) return json;

        var oldItems = json.optJSONArray("items");
        var newItems = new JSONArray();
        if (oldItems != null) {
            for (int i = 0; i < oldItems.length(); i++) {
                var item = oldItems.optJSONObject(i);
                var type = item.optString("type");
                for (String superApp : superApps) {
                    if (type.equals(superApp))
                        newItems.put(item);
                }
            }
            for (int i = 0; i < superApps.length; i++) {
                for (int j = i; j < newItems.length(); j++) {
                    var item = newItems.optJSONObject(j);
                    if (superApps[i].equals(item.optString("type")))
                        newItems.put(j, newItems.getJSONObject(i))
                                .put(i, item);
                }
            }
        }

        return json.putOpt("items", newItems);
    }

    public static JSONObject musiclink(JSONObject json){
        var oldItems = json.optJSONArray("links");

        if (oldItems != null) {
            if (oldItems.optJSONObject(0).optString("url").contains("?section=recent")) {
                json.remove("links");
                if (dev()) Log.d("VKMusic", "Removed links buttons");
            }
        }

        return json;
    }

    public static JSONObject onlineinfo(JSONObject json) throws ParseException, IOException, JSONException{
        var id = json.optInt("id");
        var onlineinfo = json.optJSONObject("online_info");
        var time = getLastSeen(0L, id);

        if (time == 0L) {
            return json;
        }

        onlineinfo.put("last_seen", time);
        onlineinfo.put("visible", true);
        onlineinfo.put("app_id", 0);
        onlineinfo.put("is_online", false);
        onlineinfo.put("is_mobile", false);
        onlineinfo.remove("status");

        var last_seen = new JSONObject();
        last_seen.put("time", time);
        last_seen.put("platform", 0);
        json.put("last_seen", last_seen);

        return json;
    }

    public static JSONObject storiesads(JSONObject json, boolean isDeleteFix) throws JSONException{
        if (!adsstories()) {
            return json;
        }

        if (json.has("ads")) {
            if (isDeleteFix) {
                json.optJSONObject("ads").optJSONObject("settings")
                        .put("stories_interval", 0)
                        .put("authors_interval", 0)
                        .put("time_interval", 0)
                        .put("stories_init", 0)
                        .put("authors_init", 0)
                        .put("time_init", 0);
                if (dev()) Log.d("StoriesAds", "Set ads settings at zero values");
            } else {
                json.remove("ads");
                if (dev()) Log.d("StoriesAds", "Removed ads block");
            }
        }

        if (!json.has("items")) return json;

        var items = json.optJSONArray("items");
        for (int i = 0; i < items.length(); i++) {
            var item = items.optJSONObject(i);
            if (item != null) {
                parseStoriesItem(item);
            }
        }

        return json;
    }

    private static void parseStoriesItem(JSONObject item) throws JSONException{
        var stories = item.optJSONArray("stories");
        var newStories = new JSONArray();

        if (stories == null) return;

        for (int j = 0; j < stories.length(); j++) {
            var story = stories.optJSONObject(j);

            if (!story.optBoolean("is_ads") && !story.optBoolean("is_promo")) {
                newStories.put(story);
            } else {
                if (dev())
                    Log.d("StoriesAds", "Fetched ad, owner id " + story.optString("owner_id") + ", caption " + story.optString("caption"));
            }
        }

        item.put("stories", newStories);
    }

    public static boolean parseRepostItem(JSONObject list) throws JSONException{
        var item = list.optJSONArray("copy_history");

        if (item == null) return false;

        for (int j = 0; j < item.length(); j++) {
            var items = item.optJSONObject(j);
            var text = items.optString("text");
            var type = items.optString("post_type");

            if (isBadNews(text)) {
                if (dev())
                    Log.d("RepostFilter", "Fetched repost ad (isBadNews), owner id " + items.optString("owner_id") + ", text: " + text);
                return true;
            } else if (isAds(items, type)) {
                if (dev())
                    Log.d("RepostFilter", "Fetched repost ad (ads), owner id " + items.optString("owner_id") + ", text: " + text);
                return true;
            }

            if (!getBoolValue("cringerepost", false)) return false;

            for (String linkfilters : mFiltersLinks) {
                if (text.contains(linkfilters)) {
                    if (dev())
                        Log.d("RepostFilter", "Fetched repost ad, owner id " + items.optString("owner_id") + ", text: " + text);
                    return true;
                }
            }
        }

        return false;
    }

    public static Boolean isAds(JSONObject list, String type) throws JSONException{
        if (list == null || type == null || !ads()) return false;

        if (list.has("ads")
                || type.equals("ads")
                || type.equals("carousel")
                || type.equals("html5_ad")
                || type.equals("ads_easy_promote")) {
            if (dev())
                Log.d("NewsfeedAdBlockV2", "Removed post " + list.optInt("post_id") + " from feed, Reason: ads");
            return true;
        }

        if (type.equals("promo_button")
                || type.equals("app_widget")
                || type.equals("app_video")
                || type.equals("app_slider")
                || type.equals("tags_suggestions")) {
            if (dev())
                Log.d("NewsfeedAdBlockV2", "Removed post " + list.optInt("post_id") + " from feed, Reason: promo");
            return true;
        }

        return false;
    }

    public static JSONArray newsfeedlist(JSONArray items) throws JSONException{
        for (int j = 0; j < items.length(); j++) {
            var list = items.optJSONObject(j);
            var name = list.optString("id");

            if (!name.equals("kpop") && !name.equals("foryou") && !name.equals("qazaqstan")) {
                list.put("is_hidden", false).put("is_unavailable", false);
                if (dev()) Log.d("NewsfeedListInj", "Unlocked " + name + " in newsfeed list");
            }
        }

        return items;
    }

    public static JSONArray newsfeedadtest(JSONArray items) throws JSONException{
        if (!getBoolValue("newadblock", true)) return items;
        var newItems = new JSONArray();

        for (int j = 0; j < items.length(); j++) {
            var list = items.optJSONObject(j);
            var type = list.optString("type");

            if (isAds(list, type)) {
                continue;
            }

            if (authorsrecomm() && (type.equals("authors_rec") || type.startsWith("recommended_") && (type.endsWith("audios") || type.endsWith("artists") || type.endsWith("playlists") || type.endsWith("groups")))) {
                if (dev())
                    Log.d("NewsfeedAdBlockV2", "Removed post " + list.optInt("post_id") + " from feed, Reason: authorsrecomm");
                continue;
            }

            if (postsrecomm() && (type.equals("inline_user_rec") || type.equals("live_recommended"))) {
                if (dev())
                    Log.d("NewsfeedAdBlockV2", "Removed post " + list.optInt("post_id") + " from feed, Reason: postsrecomm");
                continue;
            }

            if (friendsrecomm() && (type.equals("user_rec") || type.equals("friends_recomm"))) {
                if (dev())
                    Log.d("NewsfeedAdBlockV2", "Removed post " + list.optInt("post_id") + " from feed, Reason: friendsrecomm");
                continue;
            }

            if (adsgroup() && list.optInt("marked_as_ads") == 1) {
                if (dev())
                    Log.d("NewsfeedAdBlockV2", "Removed post " + list.optInt("post_id") + " from feed, Reason: marked_as_ads is true");
                continue;
            }

            if (isBadNews(list.optString("text"))) {
                if (dev())
                    Log.d("NewsfeedAdBlockV2", "Removed post " + list.optInt("post_id") + " from feed, Reason: text filters");
                continue;
            }

            if (checkCopyright(list)) {
                if (dev())
                    Log.d("NewsfeedAdBlockV2", "Removed post " + list.optInt("post_id") + " from feed, Reason: copyright filters");
                continue;
            }

            if (checkCaption(list)) {
                if (dev())
                    Log.d("NewsfeedAdBlockV2", "Removed post " + list.optInt("post_id") + " from feed, Reason: caption filters");
                continue;
            }

            if (parseRepostItem(list)) {
                if (dev())
                    Log.d("NewsfeedAdBlockV2", "Removed post " + list.optInt("post_id") + " from feed, Reason: repost ad");
                continue;
            }

            newItems.put(list);
        }

        return newItems;
    }

    public static JSONObject music(JSONObject json) throws JSONException{
        var catalog = json.optJSONObject("catalog");

        JSONArray oldItems = null;

        if (catalog != null) {
            oldItems = catalog.optJSONArray("sections");
        }

        if (oldItems != null) {
            var playlists = fetchCatalogId("https://vk.com/audio?section=my_playlists");
            if (playlists != null) {
                var catalogarr = playlists.optJSONObject("catalog").optJSONArray("sections").optJSONObject(0);

                var title = catalogarr.optString("title");
                var id = catalogarr.optString("id");
                var url = catalogarr.optString("url");

                if (dev()) Log.d("VKMusic", "Added " + title + " in music sections");

                oldItems.put(new JSONObject().put("id", id).put("title", title).put("url", url));
            }

            var albums = fetchCatalogId("https://vk.com/audio?section=albums");
            if (albums != null) {
                var catalogarr = albums.optJSONObject("catalog").optJSONArray("sections").optJSONObject(0);

                var title = catalogarr.optString("title");
                var id = catalogarr.optString("id");
                var url = catalogarr.optString("url");

                if (dev()) Log.d("VKMusic", "Added " + title + " in music sections");

                oldItems.put(new JSONObject().put("id", id).put("title", title).put("url", url));
            }

            var recent = fetchCatalogId("https://vk.com/audio?section=recent");
            if (recent != null) {
                var catalogarr = recent.optJSONObject("catalog").optJSONArray("sections").optJSONObject(0);

                var title = catalogarr.optString("title");
                var id = catalogarr.optString("id");
                var url = catalogarr.optString("url");

                if (dev()) Log.d("VKMusic", "Added " + title + " in music sections");

                oldItems.put(new JSONObject().put("id", id).put("title", title).put("url", url));
            }

            for (int i = 0; i < oldItems.length(); i++) {
                var item = oldItems.optJSONObject(i);
                var title = item.optString("title");
                var id = item.optString("id");
                var url = item.optString("url");
                var value = getPrefsValue("musicdefcatalog");

                Log.d("VKMusic", "title: " + title + " id: " + id + " url: " + url + " value: " + value);

                if (url.contains(value)) {
                    catalog.put("default_section", id);
                    if (dev()) Log.d("VKMusic", "Added " + title + " as default music section");
                }
            }
        }

        return json;
    }

    public static JSONObject fetchCatalogId(String section){
        if (section == null) return null;

        var request = new Request.a()
                .b("https://api.vk.com/method/catalog.getAudio"
                        + "?v=5.119"
                        + "&https=1"
                        + "&need_blocks=1"
                        + "&lang="
                        + getLocale()
                        + "&device_id="
                        + DeviceIdProvider.d(getContext())
                        + "&url="
                        + section
                        + "&access_token="
                        + getUserToken())
                .a(Headers.a("User-Agent", Network.l.c().a(), "Content-Type", "application/x-www-form-urlencoded; charset=utf-8")).a();
        try {
            return new JSONObject(mClient.a(request).execute().a().g()).getJSONObject("response");
        } catch (JSONException | IOException e) {
            Log.d("VTLMusic", "Error: " + e.getMessage());
        }

        return null;
    }

    public static JSONObject friends(JSONObject json) throws JSONException{
        JSONObject catalog = json;
        boolean sectionexecute = true;
        boolean hasBirthday = false;

        if (json.optJSONObject("catalog") != null) {
            catalog = json.optJSONObject("catalog");
            sectionexecute = false;
        }

        JSONArray section = null;
        JSONObject sections = null;

        if (sectionexecute) {
            sections = catalog.optJSONObject("section");
        } else {
            section = catalog.optJSONArray("sections");
        }

        JSONArray oldItems;

        if (sectionexecute) {
            oldItems = sections.optJSONArray("blocks");
        } else {
            oldItems = section.optJSONObject(0).optJSONArray("blocks");
        }

        var newItems = new JSONArray();
        if (oldItems != null) {
            for (int i = 0; i < oldItems.length(); i++) {
                var type = oldItems.optJSONObject(i);
                var name = type.optJSONObject("layout").optString("name");
                var buttons = "";
                var skip = false;
                if (type.optJSONArray("buttons") != null) {
                    buttons = type.optJSONArray("buttons").optJSONObject(0).optString("ref_layout_name");
                }
                if (name.contains("list_friend_suggests") || buttons.contains("list_friend_suggests") || name.contains("separator")) {
                    skip = friendsblock();
                }

                if (buttons.contains("friends_birthdays_list")) {
                    hasBirthday = true;
                }

                if (name.contains("separator") && hasBirthday) {
                    skip = false;
                    hasBirthday = false;
                }

                if (!skip) {
                    newItems.put(type);
                }

            }
        }

        if (sectionexecute) {
            sections.putOpt("blocks", newItems);
        } else {
            section.optJSONObject(0).putOpt("blocks", newItems);
        }

        return json;
    }


    public static boolean haveDonateButton(){
        int randomshower = new Random().nextInt(6);

        return hasVerification() || randomshower != 1;
    }
}
