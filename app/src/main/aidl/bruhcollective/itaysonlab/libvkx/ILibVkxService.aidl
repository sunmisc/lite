// IVKExternalMusic.aidl
package bruhcollective.itaysonlab.libvkx;

interface ILibVkxService {
    // Starts playback. playbackContext can be "other" if you don't sure what it is
    void play(in List<String> audio_ids, in int index, in String playbackContext) = 1;

    // A UserID. CHECK FOR THIS TO BE EQUAL BEFORE SENDING ANYTHING TO SERVICE!
    int getUserId() = 2;

    // Cache add/remove
    void addTrackToCache(in int track_id, in int owner_id, in String access_key) = 3;
    void deleteTrackFromCache(in int track_id, in int owner_id) = 4;
    boolean isTrackCached(in int track_id, in int owner_id) = 5;

    // Get a list of cached tracks. The response is close to VK's original response (an audio list)
    // Also, the items in the list are JSON ones.
    List<String> getCache() = 6;

    // [ver 1.1]
    void downloadTrack(in int track_id, in int owner_id, in String access_key) = 7;
    void downloadPlaylist(in int playlist_id, in int owner_id, in String access_key) = 8;
    void addPlaylistToCache(in int playlist_id, in int owner_id, in String access_key) = 9;
    void deletePlaylistFromCache(in int playlist_id, in int owner_id) = 10;

    // [ver 1.2]
    String getCacheCatalog() = 11;
    List<String> getTracksInPlaylist(in String playlist_id, in String owner_id) = 12;
    String getPlaylistDefJson(in String playlist_id, in String owner_id) = 13;

    // [ver 1.3]
    boolean isPlaylistCached(in int playlist_id, in int owner_id) = 14;
}