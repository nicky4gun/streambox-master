package dk.NicklasogJannik.streambox;

import java.util.*;

public class StreamBoxService {
    private final List<Content> contentList = new ArrayList<>();
    private int nextId = 1;

    public Content addContent(String title, Genre genre, int lengthMinutes, int ageRating) {
        if (title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be blank");
        }

        if (genre == null) {
            throw new IllegalArgumentException("Genre cannot be null");
        }

        if (lengthMinutes <= 0) {
            throw new IllegalArgumentException("Length must be positive");
        }

        if (lengthMinutes > 600) {
            throw new IllegalArgumentException("Length is too long");
        }

        if (ageRating < 0) {
            throw new IllegalArgumentException("Rating most be positive");
        }

        if (ageRating != 0 && ageRating != 7 && ageRating != 11 && ageRating != 15 && ageRating != 18) {
            throw new IllegalArgumentException("Has to be a valid age rating");
        }

        Content content = new Content(nextId++, title, genre, lengthMinutes, ageRating);
        contentList.add(content);

        return content;
    }

    public List<Content> getCatalog() {
        List<Content> catalog = new ArrayList<>();
        catalog.addAll(contentList);

        return catalog;
    }

    public Optional<Content> findById(int id) {
        for (Content content : contentList) {
            if (content.getId() == id) {
                return Optional.of(content);
            }

        }

        return Optional.empty();
    }

    public boolean play(int contentId, int userAge) {
        Optional<Content> contents = findById(contentId);

        if (contents.isEmpty()) {
            return false;
        }

        Content content = contents.get();

        if (userAge < 0) {
            throw new IllegalArgumentException("UserAge cannot be negative");
        }

        if (userAge < content.getAgeRating()) {
            return false;
        }

        content.incrementViews();
        return true;
    }

    public List<Content> findByGenre(Genre genre) {
        List<Content> genreList = new ArrayList<>();

        for (Content content : contentList) {
            if (content.getGenre() == genre) {
                genreList.add(content);
            }
        }

        genreList.sort(Comparator.comparing(Content::getTitle));

        return genreList;
    }

    public int totalRuntimeByGenre(Genre genre) {
        int totalRuntime = 0;

        List<Content> genreList = findByGenre(genre);

        for (Content content : genreList) {
            if (content.getGenre() == genre) {
                totalRuntime += content.getLengthMinutes();
            }
        }

        return totalRuntime;
    }

    public List<Content> topTrending(int n) {
        List<Content> topTrending = new ArrayList<>(contentList);

        if (n <= 0) {
            throw new IllegalArgumentException("N must be positive");
        }

        topTrending.sort(Comparator.comparing(Content::getViews).reversed().thenComparing(Content::getTitle));

        return topTrending.subList(0, Math.min(n, topTrending.size()));
    }

    public Optional<Content> mostViewedInGenre(Genre genre) {
        Content best = null;

        for (Content content : contentList) {
            if (content.getGenre() != genre) continue;

            if (best == null || content.getViews() > best.getViews() || (content.getViews() == best.getViews() && content.getTitle().compareTo(best.getTitle()) < 0))  {
                best = content;
            }
        }

        return Optional.ofNullable(best);
    }

    public boolean removeById(int id) {
       Content content = findById(id).orElse(null);

       if (content == null) {
           return false;
       }

       return contentList.remove(content);
    }
}
