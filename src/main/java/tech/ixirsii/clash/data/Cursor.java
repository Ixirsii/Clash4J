package tech.ixirsii.clash.data;

/**
 * Pagination cursor.
 *
 * @author Ryan Porterfield
 * @param after  Pagination after (next page) key.
 * @param before Pagination before (previous page) key.
 * @since 1.0.0
 */
public record Cursor(String after, String before) {
}
