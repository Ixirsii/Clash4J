package tech.ixirsii.clash.internal;

import lombok.NonNull;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Provided to OkHttpClient to store and use auth cookies.
 *
 * @author Ryan Porterfield
 * @since 1.0.0
 */
public class APICookieJar implements CookieJar {
    /**
     * Per-host cookie store.
     */
    private final Map<String, List<Cookie>> cookies = new HashMap<>();

    @NonNull
    @Override
    public List<Cookie> loadForRequest(@NonNull final HttpUrl httpUrl) {
        return cookies.getOrDefault(httpUrl.host(), Collections.emptyList());
    }

    @Override
    public void saveFromResponse(@NonNull final HttpUrl httpUrl, @NonNull final List<Cookie> list) {
        cookies.put(httpUrl.host(), list);
    }
}
