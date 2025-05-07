package tech.ixirsii.clash.data;

import java.util.List;

/**
 * A page of items.
 *
 * @author Ryan Porterfield
 * @param items  List of items.
 * @param paging Pagination cursors.
 * @param <T>    Type of items.
 * @since 1.0.0
 */
public record Page<T>(List<T> items, Paging paging) {
}
